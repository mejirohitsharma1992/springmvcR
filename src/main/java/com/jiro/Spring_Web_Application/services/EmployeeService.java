package com.jiro.Spring_Web_Application.services;

import com.jiro.Spring_Web_Application.dto.EmployeeDTO;
import com.jiro.Spring_Web_Application.entities.EmployeeEntity;
import com.jiro.Spring_Web_Application.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public EmployeeDTO getEmployeeId(Long id) {
     EmployeeEntity employeeEntity= employeeRepository.findById(id).orElse(null);
     return modelMapper.map(employeeEntity, EmployeeDTO.class);
    }

    public List<EmployeeDTO> getEmployees() {
        List<EmployeeEntity>employeeEntities=employeeRepository.findAll();
        return employeeEntities
                .stream()
                .map(employeeEntity -> modelMapper.map(employeeEntity,EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    public EmployeeDTO createNewEmployee(EmployeeDTO inputEmployee) {
//        to check if user is admin
//        log something
        EmployeeEntity toSaveEntity=modelMapper.map(inputEmployee,EmployeeEntity.class);
        EmployeeEntity saveEmployeeEntity=employeeRepository.save(toSaveEntity);
        return modelMapper.map(saveEmployeeEntity,EmployeeDTO.class);
    }
}
