package com.jiro.Spring_Web_Application.controllers;

import com.jiro.Spring_Web_Application.dto.EmployeeDTO;
import com.jiro.Spring_Web_Application.entities.EmployeeEntity;
import com.jiro.Spring_Web_Application.repositories.EmployeeRepository;
import com.jiro.Spring_Web_Application.services.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

//@RestController is a shorthand of
// @Controller and @ResponseBody
@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {
//    @GetMapping(path = "/getSecretMessage")
//    public String getMySuperSecretMessage(){
//    return "Secret Message Returning";
//    }

   private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/{employeeId}")
    public EmployeeDTO getEmployeeId(@PathVariable(name = "employeeId") Long id){
        return employeeService.getEmployeeId(id);
//        ctrl+n
    }

    @GetMapping
    public List<EmployeeDTO> getEmployees(@RequestParam(required = false,name = "inputAge") Integer age, @RequestParam(required = false) String sortBy){
        return employeeService.getEmployees();
    }
    @PostMapping
    public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO inputEmployee){
        return employeeService.createNewEmployee(inputEmployee);
    }
    @PutMapping
    public String updateEmployeeById(){
        return "Hello from put";
    }


}
