package com.mrwho.jackson.annotation.model;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

/**
 * <p>JsonPropertyDescription注释允许通过提供描述字段将人类可读的描述添加到创建的 JSON 模式中</p>
 */
public class BeanJsonPropertyDescription {
    private int id;
    @JsonPropertyDescription("This is a description of the name property")
    private String name;
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public BeanJsonPropertyDescription(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
