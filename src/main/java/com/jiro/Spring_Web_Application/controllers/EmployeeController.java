package com.jiro.Spring_Web_Application.controllers;

import com.jiro.Spring_Web_Application.dto.EmployeeDTO;
import com.jiro.Spring_Web_Application.entities.EmployeeEntity;
import com.jiro.Spring_Web_Application.exceptions.ResourceNotFoundException;
import com.jiro.Spring_Web_Application.repositories.EmployeeRepository;
import com.jiro.Spring_Web_Application.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

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

//    @GetMapping(path = "/{employeeId}")
//    public EmployeeDTO getEmployeeId(@PathVariable(name = "employeeId") Long id){
//        return employeeService.getEmployeeId(id);
////        ctrl+n
//    }
    @GetMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> getEmployeeId(@PathVariable(name = "employeeId") Long id){
//        EmployeeDTO employeeDTO= employeeService.getEmployeeId(id);
//        if(employeeDTO==null) return  ResponseEntity.notFound().build();
//        return ResponseEntity.ok(employeeDTO);
        //        ctrl+n
        Optional<EmployeeDTO> employeeDTO=employeeService.getEmployeeId(id);
        return employeeDTO
                .map(employeeDTO1 -> ResponseEntity.ok(employeeDTO1))
//                .orElse(ResponseEntity.notFound().build());
                .orElseThrow(()->new ResourceNotFoundException("Employee not found with id"+id));
    }

//    @GetMapping
//    public List<EmployeeDTO> getEmployees(@RequestParam(required = false,name = "inputAge") Integer age, @RequestParam(required = false) String sortBy){
//        return employeeService.getEmployees();
//    }
    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getEmployees(@RequestParam(required = false,name = "inputAge") Integer age, @RequestParam(required = false) String sortBy){
        return ResponseEntity.ok(employeeService.getEmployees());
    }
//    @PostMapping
//    public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO inputEmployee){
//        return employeeService.createNewEmployee(inputEmployee);
//    }
    @PostMapping
    public ResponseEntity<EmployeeDTO> createNewEmployee(@RequestBody @Valid EmployeeDTO inputEmployee){
        EmployeeDTO savedEmployee=employeeService.createNewEmployee(inputEmployee);
        return  new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }
//    @PutMapping(path = "/{employeeId}")
//    public EmployeeDTO updateEmployeeById(@RequestBody EmployeeDTO employeeDTO,@PathVariable Long employeeId){
//        return employeeService.updateEmployeeById(employeeId,employeeDTO);
//    }
    @PutMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> updateEmployeeById(@RequestBody @Valid EmployeeDTO employeeDTO,@PathVariable Long employeeId){
        return ResponseEntity.ok(employeeService.updateEmployeeById(employeeId,employeeDTO));
    }
//    @DeleteMapping(path = "/{employeeId}")
//    public boolean deleteEmployeeById(@PathVariable Long employeeId){
//       return employeeService.deleteEmployeeById(employeeId);
//    }
    @DeleteMapping(path = "/{employeeId}")
    public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable Long employeeId){
       boolean gotDeleted= employeeService.deleteEmployeeById(employeeId);
       if (gotDeleted) return ResponseEntity.ok(true);
       return ResponseEntity.notFound().build();
       }
//    @PatchMapping(path = "/{employeeId}")
//    public EmployeeDTO updatePartialEmployeeById(@RequestBody Map<String,Object> updates, @PathVariable Long employeeId){
//        return employeeService.updatePartialEmployeeById(employeeId,updates);
//    }
@PatchMapping(path = "/{employeeId}")
public ResponseEntity<EmployeeDTO> updatePartialEmployeeById(@RequestBody Map<String,Object> updates, @PathVariable Long employeeId){
    EmployeeDTO employeeDTO=employeeService.updatePartialEmployeeById(employeeId,updates);
    if (employeeDTO==null) return ResponseEntity.notFound().build();
    return ResponseEntity.ok(employeeDTO);
    }


}
