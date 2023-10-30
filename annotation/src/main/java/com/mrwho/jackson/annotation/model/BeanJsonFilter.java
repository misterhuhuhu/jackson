package com.mrwho.jackson.annotation.model;

import com.fasterxml.jackson.annotation.JsonFilter;


@JsonFilter("myFilter")
public class BeanJsonFilter {
    public int id;
    public String name;
    
    public BeanJsonFilter(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
