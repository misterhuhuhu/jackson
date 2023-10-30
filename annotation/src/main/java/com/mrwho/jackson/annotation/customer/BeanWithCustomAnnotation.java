package com.mrwho.jackson.annotation.customer;

import java.util.Date;

@CustomAnnotation
public class BeanWithCustomAnnotation {
    public int id;
    public String name;
    public Date dateCreated;
    
    public BeanWithCustomAnnotation(int id, String name, Date dateCreated) {
        this.id = id;
        this.name = name;
        this.dateCreated = dateCreated;
    }
    
    @Override
    public String toString() {
        return "BeanWithCustomAnnotation{" +
                       "id=" + id +
                       ", name='" + name + '\'' +
                       ", dateCreated=" + dateCreated +
                       '}';
    }
}