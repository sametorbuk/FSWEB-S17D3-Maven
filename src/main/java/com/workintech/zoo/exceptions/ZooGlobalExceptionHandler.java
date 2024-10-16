package com.workintech.zoo.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ZooGlobalExceptionHandler {

    @ExceptionHandler(ZooException.class)
    public ResponseEntity<ZooErrorResponse> handleAnimalNotFound(ZooException exception) {
        ZooErrorResponse response = new ZooErrorResponse(

                exception.getHttpStatus(),
                exception.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, exception.getHttpStatus());
    }



    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ZooErrorResponse> handleRunTimeException(RuntimeException exception){
        ZooErrorResponse response = new ZooErrorResponse(HttpStatus.BAD_REQUEST,exception.getMessage(),
                System.currentTimeMillis() );


        return new ResponseEntity<>(response , HttpStatus.BAD_REQUEST);
    }


}
