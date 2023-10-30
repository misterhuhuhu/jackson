package com.mrwho.jackson.annotation.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRawValue;

import java.util.HashMap;
import java.util.Map;

public class BeanJsonRawValue {
    public BeanJsonRawValue(String name, String json) {
        this.name = name;
        this.json = json;
    }
    
    public String name;
    @JsonRawValue
    public String json;
}
