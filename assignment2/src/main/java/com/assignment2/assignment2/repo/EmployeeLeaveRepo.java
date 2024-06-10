package com.assignment2.assignment2.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment2.assignment2.pojo.EmployeeLeave;

@Repository
public interface EmployeeLeaveRepo extends JpaRepository<EmployeeLeave, Integer> {
}
