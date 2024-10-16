package com.workintech.zoo.exceptions;

import lombok.*;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Data


public class ZooException extends RuntimeException{
    HttpStatus httpStatus;



    public ZooException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }


    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
