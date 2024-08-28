package com.jiro.Spring_Web_Application.controllers;

import com.jiro.Spring_Web_Application.dto.EmployeeDTO;
import com.jiro.Spring_Web_Application.entities.EmployeeEntity;
import com.jiro.Spring_Web_Application.repositories.EmployeeRepository;
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

   private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping(path = "/{employeeId}")
    public EmployeeEntity getEmployeeId(@PathVariable(name = "employeeId") Long id){
        return employeeRepository.findById(id).orElse(null);
    }

    @GetMapping
    public List<EmployeeEntity> getEmployees(@RequestParam(required = false,name = "inputAge") Integer age, @RequestParam(required = false) String sortBy){
        return employeeRepository.findAll();
    }
    @PostMapping
    public EmployeeEntity createNewEmployee(@RequestBody EmployeeEntity inputEmployee){
        return employeeRepository.save(inputEmployee);
    }
    @PutMapping
    public String updateEmployeeById(){
        return "Hello from put";
    }


}
