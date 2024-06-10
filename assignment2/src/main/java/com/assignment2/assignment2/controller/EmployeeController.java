package com.assignment2.assignment2.controller;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.assignment2.assignment2.pojo.ConfirmationForm;
import com.assignment2.assignment2.pojo.Employee;
import com.assignment2.assignment2.pojo.EmployeeLeave;
import com.assignment2.assignment2.repo.EmployeeLeaveRepo;
import com.assignment2.assignment2.repo.EmployeeRepo;

import jakarta.validation.Valid;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeRepo employeeRepo;
    
    @Autowired
    private EmployeeLeaveRepo employeeLeaveRepo;

    // display the html page 
    @GetMapping("/") 
    public String getIndex(Model model) { 
        List<Employee> employeeList = employeeRepo.findAll(); 
        model.addAttribute("employees", employeeList); 
        model.addAttribute("employee", new Employee()); 
        model.addAttribute("confirmationForm", new ConfirmationForm());
        model.addAttribute("employeeLeave", new EmployeeLeave()); // Add this line

        return "index"; 
    }

    // Insert employee data 
    @PostMapping("/create") 
    public String newEmployee(@Valid Employee employee, Model model) { 
        model.addAttribute("employee", new Employee());

        // creating dynamic Employee ID 
        int empId = 12; 
        Random random = new Random(); 
        int randomNumber = 1000 + random.nextInt(9000); 
        empId = empId + randomNumber; 
        employee.setId(empId);

        // save the employee 
        employeeRepo.save(employee);

        return "redirect:/"; 
    }

    // update the existing employee 
    @PostMapping("/update") 
    public String updateEmployee(@Valid @ModelAttribute Employee employee, Model model) { 
        model.addAttribute("employee", new Employee());
        Optional<Employee> existingEmployee = employeeRepo.findById(employee.getId());

        // checking employee exist or not 
        if (existingEmployee.isPresent()) { 
            employeeRepo.save(employee); 
        } else { 
            model.addAttribute("errorMessage", "Employee with ID " + employee.getId() + " not found."); 
        }
        return "redirect:/"; 
    }

    // delete an employee by id 
    @PostMapping("/remove") 
    public String removeEmployee(Employee employee, Model model) { 
        model.addAttribute("employee", new Employee()); 
        Optional<Employee> existingEmployee = employeeRepo.findById(employee.getId()); 
        if (existingEmployee.isPresent()) { 
            employeeRepo.deleteById(employee.getId()); 
        }
        return "redirect:/"; 
    }

    // delete all employees data by confromation 
    @PostMapping("/remove/all") 
    public String removeAll(@ModelAttribute ConfirmationForm confirmationForm, Model model) { 
        String confirmation = confirmationForm.getConfirmation(); 
        if ("Yes".equalsIgnoreCase(confirmation)) { 
            employeeRepo.deleteAll(); 
        } else { 
            return "redirect:/"; 
        }
        return "redirect:/"; 
    }
    
    @PostMapping("/applyleave")
    public String applyLeave(@ModelAttribute EmployeeLeave employeeLeave, Model model) {
        Optional<Employee> employee = employeeRepo.findById(employeeLeave.getEmpId());
        if (employee.isPresent()) {
            Employee emp = employee.get();
            emp.setTotalLeaves(emp.getTotalLeaves() + 1);
            employeeLeave.setEmployee(emp);
            employeeLeaveRepo.save(employeeLeave);
            employeeRepo.save(emp);
        } else {
            model.addAttribute("errorMessage", "Employee with ID " + employeeLeave.getEmpId() + " not found.");
        }
        return "redirect:/";
    }
}
