package com.jiro.Spring_Web_Application.services;

import com.jiro.Spring_Web_Application.dto.EmployeeDTO;
import com.jiro.Spring_Web_Application.entities.EmployeeEntity;
import com.jiro.Spring_Web_Application.exceptions.ResourceNotFoundException;
import com.jiro.Spring_Web_Application.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

//    public EmployeeDTO getEmployeeId(Long id) {
//     EmployeeEntity employeeEntity= employeeRepository.findById(id).orElse(null);
//     return modelMapper.map(employeeEntity, EmployeeDTO.class);
//    }
    public Optional<EmployeeDTO> getEmployeeId(Long id) {
//        Optional<EmployeeEntity> employeeEntity= employeeRepository.findById(id);
//        return employeeEntity.map(employeeEntity1 -> modelMapper.map(employeeEntity1,EmployeeDTO.class));
          return employeeRepository.findById(id)
                  .map(employeeEntity -> modelMapper.map(employeeEntity,EmployeeDTO.class));
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

    public EmployeeDTO updateEmployeeById(Long employeeId, EmployeeDTO employeeDTO) {
    isExistsByEmployeeId(employeeId);
    EmployeeEntity employeeEntity=modelMapper.map(employeeDTO,EmployeeEntity.class);
    employeeEntity.setId(employeeId);
    EmployeeEntity savedEmployeeEntity=employeeRepository.save(employeeEntity);
    return  modelMapper.map(savedEmployeeEntity,EmployeeDTO.class);
    }

    private void isExistsByEmployeeId(Long employeeId) {
        boolean exists=employeeRepository.existsById(employeeId);
        if (!exists) throw new ResourceNotFoundException("Employee not found with id"+employeeId);
    }

    public boolean deleteEmployeeById(Long employeeId) {
       isExistsByEmployeeId(employeeId);
       employeeRepository.deleteById(employeeId);
        return true;
    }

    public EmployeeDTO updatePartialEmployeeById(Long employeeId, Map<String, Object> updates) {
        isExistsByEmployeeId(employeeId);
        EmployeeEntity employeeEntity=employeeRepository.findById(employeeId).get();
        updates.forEach((field,value)->{
            Field fieldToBeUpdated= ReflectionUtils.findRequiredField(EmployeeEntity.class,field);
            fieldToBeUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldToBeUpdated,employeeEntity,value);
        });
        return modelMapper.map(employeeRepository.save(employeeEntity),EmployeeDTO.class);
    }


}
