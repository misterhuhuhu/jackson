package com.mrwho.jackson.annotation.model;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

public class BeanJsonIgnoreType {
    public int id;
    
    public Name name;
    
    public BeanJsonIgnoreType() {
    
    }
    
    public BeanJsonIgnoreType(final int id, final Name name) {
        this.id = id;
        this.name = name;
    }
    
    @JsonIgnoreType
    public static class Name {
        public String firstName;
        public String lastName;
        
        public Name() {
        
        }
        
        public Name(final String firstName, final String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }
    }
    
}
