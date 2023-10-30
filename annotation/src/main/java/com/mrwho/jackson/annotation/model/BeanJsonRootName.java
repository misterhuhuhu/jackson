package com.mrwho.jackson.annotation.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.HashMap;
import java.util.Map;

@JsonRootName("name")
public class BeanJsonRootName {
    public BeanJsonRootName(String name) {
        this.name = name;
    }
    
    public String name;
    
    private Map<String, String> properties = new HashMap<>() {{
        put("attr1", "val1");
        put("attr2", "val2");
    }};
    @JsonGetter("customerProperties")
    public Map<String, String> getProperties() {
        return properties;
    }
}
