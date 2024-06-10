package com.assignment2.assignment2.repo;

 

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository; 

  

import com.assignment2.assignment2.pojo.Employee; 

  
@Repository

public interface EmployeeRepo extends JpaRepository<Employee, Integer>{ 

  
}