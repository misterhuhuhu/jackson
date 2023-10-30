package com.mrwho.jackson.annotation.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

// InvalidDefinitionException: No serializer found for
// class com.mrwho.jackson.annotation.model.BeanJsonAutoDetect
// and no properties discovered to create BeanSerializer
// (to avoid exception, disable SerializationFeature.FAIL_ON_EMPTY_BEANS)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class BeanJsonAutoDetect {
    private int id;
    private String name;
    
    public BeanJsonAutoDetect(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    @Override
    public String toString() {
        return "BeanJsonAutoDetect{" +
                       "id=" + id +
                       ", name='" + name + '\'' +
                       '}';
    }
}
