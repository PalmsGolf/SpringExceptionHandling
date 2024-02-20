package com.javaclub.springexceptionhandlingdemo.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MessageRequest {

    @JsonProperty("isUppercase")
    private Boolean isUppercase;
}
