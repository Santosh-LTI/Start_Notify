package com.karle.CRUDspringMySQL.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.karle.CRUDspringMySQL.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
