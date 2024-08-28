package com.jiro.Spring_Web_Application.repositories;

import com.jiro.Spring_Web_Application.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Long> {

}
