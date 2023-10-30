package com.mrwho.jackson.annotation.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;

import java.util.HashMap;
import java.util.Map;

public class BeanJsonAnyGetter {
    public BeanJsonAnyGetter(String name) {
        this.name = name;
    }
    
    public String name;
    @JsonAnyGetter
    private Map<String, String> properties = new HashMap<>() {{
        put("attr1", "val1");
        put("attr2", "val2");
    }};
}
