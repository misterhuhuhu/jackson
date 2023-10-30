package com.mrwho.jackson.annotation.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class BeanJsonFormat {
    private String name;
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "dd-MM-yyyy hh:mm:ss")
    private Date eventDate;
    public BeanJsonFormat( String name, Date eventDate) {
        this.name = name;
        this.eventDate = eventDate;
    }
    
    public BeanJsonFormat() {
    }
}
