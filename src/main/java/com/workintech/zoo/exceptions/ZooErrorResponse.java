package com.workintech.zoo.exceptions;

import lombok.*;
import org.springframework.http.HttpStatus;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ZooErrorResponse {
   private   int status;
    private   String message;
   private   long timestamp;


    public ZooErrorResponse(HttpStatus httpStatus, String message, long timestamp) {
    }

    public ZooErrorResponse(String message, int status, long timestamp) {
        this.message = message;
        this.status = status;
        this.timestamp = timestamp;
    }
}
