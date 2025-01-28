package com.personalfolio.voyage.utils;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter @Setter
public class ResponseSchema<T>  {
    private HttpStatusCode status;
    private String message;
    private T data;
    private LocalTime timestamp;

    public ResponseSchema() {
        this.timestamp = LocalTime.from(LocalDateTime.now());
    }

    public ResponseSchema(HttpStatusCode status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.timestamp = LocalTime.from(LocalDateTime.now());
    }

    public static <T> ResponseSchema<T> of(T data, HttpStatusCode status, String message) {
        return new ResponseSchema<>(status, message, data);
    }

    public static <T> ResponseEntity <T> ofWithBody(String header,String headerValue,Object content) {
        return (ResponseEntity<T>) ResponseEntity.ok().header(header, headerValue)
                .body(content);
    }

    public static ResponseEntity <String> ofByDefault(String body, HttpStatus statusCode) {
        return  ResponseEntity.status(statusCode).body(body);
    }
}