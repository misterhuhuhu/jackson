package com.mrwho.jackson.annotation.model;

import com.fasterxml.jackson.databind.annotation.JsonAppend;

//@JsonAppend注释用于在序列化对象时向对象添加除常规属性之外的虚拟属性
@JsonAppend(attrs = {
        @JsonAppend.Attr(value = "version")
})
public class BeanJsonAppend {
    private int id;
    private String name;
    
    public BeanJsonAppend(int id, String name) {
        this.id = id;
        this.name = name;
    }
    //字段必须加上get set
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
}
