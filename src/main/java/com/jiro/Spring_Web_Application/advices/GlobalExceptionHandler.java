package com.jiro.Spring_Web_Application.advices;

import com.jiro.Spring_Web_Application.exceptions.ResourceNotFoundException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(NoSuchElementException.class)
//    public ResponseEntity<String> handleResourceNotFound(NoSuchElementException exception){
//        return new ResponseEntity<>("Resource not found", HttpStatus.NOT_FOUND);
//    }

//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<ApiError> handleResourceNotFound(ResourceNotFoundException exception){
//        ApiError apiError=ApiError.builder().status(HttpStatus.NOT_FOUND).message(exception.getMessage()).build();
//        return new ResponseEntity<>(apiError,HttpStatus.NOT_FOUND);
//    }
@ExceptionHandler(ResourceNotFoundException.class)
public ResponseEntity<ApiResponse<?>> handleResourceNotFound(ResourceNotFoundException exception){
    ApiError apiError=ApiError.builder().status(HttpStatus.NOT_FOUND).message(exception.getMessage()).build();
    return buildErrorResponseEntity(apiError);
}



//    @ExceptionHandler(Exception.class)
//public ResponseEntity<ApiError>handleInternalServerError(Exception exception){
//    ApiError apiError=ApiError.builder()
//            .status(HttpStatus.INTERNAL_SERVER_ERROR)
//            .message(exception.getMessage())
//            .build();
//    return new ResponseEntity<>(apiError,HttpStatus.INTERNAL_SERVER_ERROR);
//
//}
@ExceptionHandler(Exception.class)
public ResponseEntity<ApiResponse<?>>handleInternalServerError(Exception exception){
    ApiError apiError=ApiError.builder()
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .message(exception.getMessage())
            .build();
   return buildErrorResponseEntity(apiError);

}
//@ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ApiError> handleInputValidationError(MethodArgumentNotValidException exception){
//   List<String>errors= exception
//            .getBindingResult()
//            .getAllErrors()
//            .stream()
//            .map(DefaultMessageSourceResolvable::getDefaultMessage)
//            .collect(Collectors.toList());
//    ApiError apiError=ApiError.builder()
//            .status(HttpStatus.BAD_REQUEST)
//            .message("Input Validation Failed")
//            .subErrors(errors)
//            .build();
//    return new ResponseEntity<>(apiError,HttpStatus.BAD_REQUEST);
//
//}
@ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<ApiResponse<?>> handleInputValidationError(MethodArgumentNotValidException exception){
    List<String>errors= exception
            .getBindingResult()
            .getAllErrors()
            .stream()
            .map(DefaultMessageSourceResolvable::getDefaultMessage)
            .collect(Collectors.toList());
    ApiError apiError=ApiError.builder()
            .status(HttpStatus.BAD_REQUEST)
            .message("Input Validation Failed")
            .subErrors(errors)
            .build();
    return buildErrorResponseEntity(apiError);


}
    private ResponseEntity<ApiResponse<?>> buildErrorResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(new ApiResponse<>(apiError),apiError.getStatus());
    }
}
