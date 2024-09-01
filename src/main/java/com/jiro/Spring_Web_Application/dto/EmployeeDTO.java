package com.jiro.Spring_Web_Application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jiro.Spring_Web_Application.annotations.EmployeeRoleValidation;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
//POJO class used to define entities
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
//    ctrl+alt+shift+click multiple line select
    private Long id;
//    @NotNull(message = "Required field in Employee:name")
//    private String name;
//    @NotEmpty(message = "Name of the employee cannot be empty")
//    private String name;
    @NotBlank(message = "Name of the employee cannot be blank")
    @Size(min = 3,max = 10,message = "Number of characters in name should be in the range :[3,10]")
    private String name;

    @NotBlank(message = "Email of the employee cannot be blank")
    @Email(message = "Email should be valid email")
    private String email;

    @NotNull(message = "Age of the employee cannot be blank")
    @Max(value = 80,message = "age should not be greater than 80")
    @Min(value = 18,message = "age should be greater than 18")
    private Integer age;

    @NotBlank(message = "Role of the employee cannot be blank")
//    @Pattern(regexp = "^(ADMIN|USER)$",message = "Role of Employee can be USER or ADMIN")
    @EmployeeRoleValidation
    private String role;//ADMIN or USER

    @NotNull(message ="salary of the employee should not be null" )
    @Positive(message = "Salary of the employee should be positive")
    @Digits(integer = 6,fraction = 2,message = "salary can be only in xxxx.yy form")
    @DecimalMax(value = "10000.99")
    @DecimalMin(value = "100.50")
    private Double salary;

    @PastOrPresent(message = "date of joining cannot be future")
    private LocalDate dateOfJoining;

    @AssertTrue(message = "Employee should be active")
    @JsonProperty("isActive")
    private Boolean isActive;
//    Default constructor
//    public EmployeeDTO(){
//
//    }
//    //alt+insert for all args constructor
//    public EmployeeDTO(Long id, String name, String email, Integer age, LocalDate dateOfJoining, Boolean isActive) {
//        this.id = id;
//        this.name = name;
//        this.email = email;
//        this.age = age;
//        this.dateOfJoining = dateOfJoining;
//        this.isActive = isActive;
//    }
//
//    //alt+insert to select getter setter
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public Integer getAge() {
//        return age;
//    }
//
//    public void setAge(Integer age) {
//        this.age = age;
//    }
//
//    public LocalDate getDateOfJoining() {
//        return dateOfJoining;
//    }
//
//    public void setDateOfJoining(LocalDate dateOfJoining) {
//        this.dateOfJoining = dateOfJoining;
//    }
//
//    public Boolean getActive() {
//        return isActive;
//    }
//
//    public void setActive(Boolean active) {
//        isActive = active;
//    }
}
