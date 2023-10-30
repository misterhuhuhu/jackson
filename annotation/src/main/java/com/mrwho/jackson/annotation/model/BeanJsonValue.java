package com.mrwho.jackson.annotation.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum BeanJsonValue {
    TYPE1(1, "A"), TYPE2(2, "B");
    
    private final Integer id;
    private final String name;
    
    BeanJsonValue(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
    
    @JsonValue
    public String getName() {
        return name;
    }
}
