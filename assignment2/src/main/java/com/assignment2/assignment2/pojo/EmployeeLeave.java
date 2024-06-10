package com.assignment2.assignment2.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employee_leave")
public class EmployeeLeave {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int leaveID;

    private int empId;

//    @Temporal(TemporalType.DATE)
//    private Date date;
    private String date;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;
}
