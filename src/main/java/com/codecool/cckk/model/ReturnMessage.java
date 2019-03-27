package com.codecool.cckk.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReturnMessage {
    private boolean success;
    private String message;
}
