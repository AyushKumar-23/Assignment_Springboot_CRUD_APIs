package com.assignment2.assignment2.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

	@NotNull(message="Should not be blank!")
    private String employeeName;
    
	@NotNull(message="Should not be blank!")
    private String employeeEmail;
    
	@Digits(integer=10,fraction=0)
	@NotNull(message="Should not be blank!")
    private Long employeePhone;
    
    
    private String employeeGender;
    
    @Min(0)
    private String employeeSalary;
    
    
    private String employeeRole;
    
    @Min(0)
    private int totalLeaves;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<EmployeeLeave> leaves;
}
