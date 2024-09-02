package com.jiro.Spring_Web_Application.advices;

import lombok.Data;

import java.time.LocalDateTime;
//for getter use data
@Data
public class ApiResponse<T> {
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
