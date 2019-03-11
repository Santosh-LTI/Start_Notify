package com.karle.CRUDspringMySQL.controller;

import java.io.BufferedInputStream;
import java.util.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URLConnection;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import java.net.InetAddress;

import com.karle.CRUDspringMySQL.exception.FileException;
import com.karle.CRUDspringMySQL.model.ErrorResponse;
import com.karle.CRUDspringMySQL.response.Response;
//import com.karle.CRUDspringMySQL.CruDspringMySqlApplication;
@RestController
@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
@RequestMapping("/uploads")
public class FileUploadController {
	


	// method for uploading single file
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public Response uploadFile(@RequestParam("file") MultipartFile file) {

		File uploadedFile = new File("C:\\upload", file.getOriginalFilename());
		
		try {
			uploadedFile.createNewFile();
			FileOutputStream fileOutputStream = new FileOutputStream(uploadedFile);
			fileOutputStream.write(file.getBytes());
			fileOutputStream.close();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		String status = "File Uploaded Successfully";
		//String url = "http://localhost:8080/downloads/downloadFile";
		String url =   "/uploads/downloadFile/"+ file.getOriginalFilename();
		String filename = file.getOriginalFilename();
		String filetype = file.getContentType();
		return new Response(filename,filetype,status,url);	
		}

	// method for uploading multiple files
	@RequestMapping(value = "/uploadmultipleFiles", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public List<Response> uploadmultipleFile(@RequestParam("files") MultipartFile[] files) {
		FileOutputStream fileOutputStream = null;
		List<Response> l1 = new ArrayList<Response>();
		for (MultipartFile multipartFile : files) {

			File uploadedFile = new File("C:\\upload", multipartFile.getOriginalFilename());

			try {
				uploadedFile.createNewFile();
				fileOutputStream = new FileOutputStream(uploadedFile);
				fileOutputStream.write(multipartFile.getBytes());
				fileOutputStream.close();
				String status = "File Uploaded Successfully";
				String url ="/uploads/downloadFile/"+ multipartFile.getOriginalFilename();
				String filename = multipartFile.getOriginalFilename();
				String filetype = multipartFile.getContentType();
				Response r = new Response(filename,filetype,status,url);
				l1.add(r);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		
		

		return l1  ;
	}

	// method for downloading file
	/*
	 * @RequestMapping(value = "/downloadFile/{fileName:.+}", method =
	 * RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	 * public ResponseEntity<Object> downloadFile(@PathVariable String fileName,
	 * HttpServletResponse response) {
	 * 
	 * String filePath = "C:\\upload\\" + fileName; Path path = Paths.get(filePath);
	 * Resource resource = null;
	 * 
	 * try { resource = new UrlResource(path.toUri()); } catch
	 * (MalformedURLException e) { e.printStackTrace(); }
	 * 
	 * if (resource.exists()) { return ResponseEntity.ok()
	 * .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +
	 * resource.getFilename() + "\"") .body(resource);
	 * 
	 * 
	 * 
	 * //return new ResponseEntity<Object>(resource, HttpStatus.OK); } else { return
	 * new ResponseEntity<Object>("File Not Found ", HttpStatus.OK); } }
	 * 
	 */

	// Try 1

	/*
	 * @RequestMapping(value = "downloadFile/{fileName:.+}", method =
	 * RequestMethod.GET) public StreamingResponseBody
	 * getSteamingFile(HttpServletResponse response) throws IOException {
	 * response.setContentType("text/csv");
	 * response.setHeader("Content-Disposition",
	 * "attachment; filename=\"EmployeeDetails.csv\""); InputStream inputStream =
	 * new FileInputStream(new File("C:\\upload\\EmployeeDetails.csv")); return
	 * outputStream -> { int nRead; byte[] data = new byte[1024]; while ((nRead =
	 * inputStream.read(data, 0, data.length)) != -1) {
	 * System.out.println("Writing some bytes.."); outputStream.write(data, 0,
	 * nRead); } }; }
	 */

	private static final String EXTERNAL_FILE_PATH = "C:/upload/";

	@RequestMapping(value = "/downloadFile/{fileName:.+}")
	public void downloadPDFResource(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("fileName") String fileName) throws IOException, FileException {

		File file = new File(EXTERNAL_FILE_PATH + fileName);
		if (file.exists()) {

			// get the mimetype
			String mimeType = URLConnection.guessContentTypeFromName(file.getName());
			if (mimeType == null) {
				// unknown mimetype so set the mimetype to application/octet-stream
				mimeType = "application/octet-stream";
			}

			response.setContentType(mimeType);

			/**
			 * In a regular HTTP response, the Content-Disposition response header is a
			 * header indicating if the content is expected to be displayed inline in the
			 * browser, that is, as a Web page or as part of a Web page, or as an
			 * attachment, that is downloaded and saved locally.
			 * 
			 */

			/**
			 * Here we have mentioned it to show inline
			 */
			// response.setHeader("Content-Disposition", String.format("inline; filename=\""
			// + file.getName() + "\""));

			// Here we have mentioned it to show as attachment
			response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + file.getName() + "\""));

			response.setContentLength((int) file.length());

			InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

			FileCopyUtils.copy(inputStream, response.getOutputStream());

		}

		else {
			 throw new FileException("File not Found! Check the File Name again... ");
		}
	}
	@ExceptionHandler(FileException.class)
    public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {
        ErrorResponse error = new ErrorResponse();
        error.setErrorCode(HttpStatus.PRECONDITION_FAILED.value());
        error.setMessage(ex.getMessage());
        return new ResponseEntity<ErrorResponse>(error, HttpStatus.OK);
    }
}
