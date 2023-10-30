package com.mrwho.jackson.annotation.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.ArrayList;
import java.util.List;


/**
 * 双向关系
 */
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class BeanJsonIdentityInfo1 {
    
    public int id;
    public String name;
    public List<BeanJsonIdentityInfo2> userItems;
    
    public BeanJsonIdentityInfo1() {
        super();
    }
    
    public BeanJsonIdentityInfo1(final int id, final String name) {
        this.id = id;
        this.name = name;
        userItems = new ArrayList<BeanJsonIdentityInfo2>();
    }
    
    public void addItem(final BeanJsonIdentityInfo2 item) {
        userItems.add(item);
    }
    
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    public static class BeanJsonIdentityInfo2 {
        public int id;
        public String itemName;
        public BeanJsonIdentityInfo1 owner;
        
        public BeanJsonIdentityInfo2() {
            super();
        }
        
        public BeanJsonIdentityInfo2(final int id, final String itemName, final BeanJsonIdentityInfo1 owner) {
            this.id = id;
            this.itemName = itemName;
            this.owner = owner;
        }
    }
}
