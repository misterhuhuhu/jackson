package com.mrwho.jackson.annotation.model;

import com.fasterxml.jackson.annotation.JacksonInject;

public class BeanJacksonInject {
    @JacksonInject
    public int id;
    public String name;
    
    @Override
    public String toString() {
        return "BeanJacksonInject{" +
                       "id=" + id +
                       ", name='" + name + '\'' +
                       '}';
    }
}
