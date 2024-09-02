package com.jiro.Spring_Web_Application.advices;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDateTime;
//for getter use data
@Data
public class ApiResponse<T> {

//    @Pattern(regexp = "hh-mm-ss dd-mm-yyyy")
    @JsonFormat(pattern = "hh:mm:ss dd-MM-yyyy" )
    private LocalDateTime timeStamp;
    private  T data;
    private  ApiError error;

    public ApiResponse(){
        this.timeStamp=LocalDateTime.now();
    }
    //    constructor with only data
    public ApiResponse(T data) {
        this();
        this.data = data;
    }
    public ApiResponse(ApiError error) {
       this();
       this.error = error;
    }
}
