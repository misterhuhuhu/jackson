package com.mrwho.jackson.annotation.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BeanJsonCreator {
    public int id;
    public String name;

    @JsonCreator
    public BeanJsonCreator(@JsonProperty("id") final int id, @JsonProperty("theName") final String name) {
        this.id = id;
        this.name = name;
    }
    
    @Override
    public String toString() {
        return "BeanJsonCreator{" +
                       "id=" + id +
                       ", name='" + name + '\'' +
                       '}';
    }
}
