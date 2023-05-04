package com.last.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.last.model.Employee;
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	

}
