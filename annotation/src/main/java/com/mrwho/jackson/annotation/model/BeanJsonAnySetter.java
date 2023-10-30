package com.mrwho.jackson.annotation.model;

import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.HashMap;
import java.util.Map;

public class BeanJsonAnySetter {
    public String name;
    private Map<String, String> properties = new HashMap<>();
    
    @JsonAnySetter
    public void add(String key, String value) {
        properties.put(key, value);
    }
    
    @Override
    public String toString() {
        return "BeanJsonAnySetter{" +
                       "name='" + name + '\'' +
                       ", properties=" + properties +
                       '}';
    }
}
