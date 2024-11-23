package com.rushi.healthprovider.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class APIError {

    private HttpStatus status;
    private List<String> errors;
    private LocalDateTime timeStamp;
    private String pathUri;

}
