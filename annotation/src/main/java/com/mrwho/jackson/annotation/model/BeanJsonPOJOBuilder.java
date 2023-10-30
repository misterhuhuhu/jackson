package com.mrwho.jackson.annotation.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;


@JsonDeserialize(builder = BeanJsonPOJOBuilder.BeanBuilder.class)
public class BeanJsonPOJOBuilder {
    private int identity;
    private String beanName;
    
    @Override
    public String toString() {
        return "BeanJsonPOJOBuilder{" +
                       "identity=" + identity +
                       ", beanName='" + beanName + '\'' +
                       '}';
    }
    
    public int getIdentity() {
        return identity;
    }
    
    public void setIdentity(int identity) {
        this.identity = identity;
    }
    
    public String getBeanName() {
        return beanName;
    }
    
    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }
    
    public BeanJsonPOJOBuilder(int identity, String beanName) {
        this.identity = identity;
        this.beanName = beanName;
    }
    /**
     * JsonPOJOBuilder注释附带两个属性：
     *
     * buildMethodName：无参数方法的名称，用于在将 JSON 字段绑定到该 bean 的属性后实例化预期的 bean。默认名称是构建。
     * withPrefix：自动检测 JSON 和 bean 属性之间匹配的名称前缀。默认前缀是.
     */
    @JsonPOJOBuilder(buildMethodName = "createBean", withPrefix = "construct")
    public static class BeanBuilder {
        private int idValue;
        private String nameValue;
        
        public BeanBuilder constructId(int id) {
            idValue = id;
            return this;
        }
        
        public BeanBuilder constructName(String name) {
            nameValue = name;
            return this;
        }
        
        public BeanJsonPOJOBuilder createBean() {
            return new BeanJsonPOJOBuilder(idValue, nameValue);
        }
    }
}
