package com.mrwho.jackson.annotation.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.DatabindContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import com.fasterxml.jackson.databind.jsontype.impl.TypeIdResolverBase;

import java.util.List;

/**
 * 有注解
 * {
 *   "beans" : [ {
 *     "@type" : "bean1",
 *     "id" : 1,
 *     "firstName" : "Bean 1"
 *   }, {
 *     "@type" : "bean2",
 *     "id" : 2,
 *     "lastName" : "Bean 2"
 *   } ]
 * }
 *
 * 无注解 ：且反序列化报错
 *  {
 *   "beans" : [ {
 *     "id" : 1,
 *     "firstName" : "Bean 1"
 *   }, {
 *     "id" : 2,
 *     "lastName" : "Bean 2"
 *   } ]
 * }
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "@type"
)
@JsonTypeIdResolver(BeanJsonTypeIdResolver.BeanIdResolver.class)
public class BeanJsonTypeIdResolver {
    private int id;
    
    protected BeanJsonTypeIdResolver(int id) {
        this.id = id;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public BeanJsonTypeIdResolver() {
    }
    
    public static class FirstBean extends BeanJsonTypeIdResolver {
        private String firstName;
        
        @Override
        public String toString() {
            return "FirstBean{" +
                           "firstName='" + getFirstName() + '\'' +
                           ", id=" + getId() +
                           '}';
        }
        
        public FirstBean(int id, String name) {
            super(id);
            setFirstName(name);
        }
        
        public FirstBean() {
        }
        
        public String getFirstName() {
            return firstName;
        }
        
        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }
    }
    
    public static class LastBean extends BeanJsonTypeIdResolver {
        private String lastName;
        
        public LastBean(int id, String name) {
            super(id);
            setLastName(name);
        }
        
        @Override
        public String toString() {
            return "LastBean{" +
                           "lastName='" + getLastName() + '\'' +
                           ", id=" + getId() +
                           '}';
        }
        
        public LastBean() {
        }
        
        public String getLastName() {
            return lastName;
        }
        
        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
    }
    
    public static class BeanContainer {
        private List<BeanJsonTypeIdResolver> beans;
        
        public List<BeanJsonTypeIdResolver> getBeans() {
            return beans;
        }
        
        public void setBeans(List<BeanJsonTypeIdResolver> beans) {
            this.beans = beans;
        }
        
        @Override
        public String toString() {
            return "BeanContainer{" +
                           "beans=" + beans +
                           '}';
        }
    }
    public static class BeanIdResolver extends TypeIdResolverBase {
        
        private JavaType superType;
        
        @Override
        public void init(JavaType baseType) {
            superType = baseType;
        }
        
        @Override
        public JsonTypeInfo.Id getMechanism() {
            return JsonTypeInfo.Id.NAME;
        }
        
        @Override
        public String idFromValue(Object obj) {
            return idFromValueAndType(obj, obj.getClass());
        }
        
        @Override
        public String idFromValueAndType(Object obj, Class<?> subType) {
            String typeId = null;
            switch (subType.getSimpleName()) {
                case "FirstBean":
                    typeId = "bean1";
                    break;
                case "LastBean":
                    typeId = "bean2";
            }
            return typeId;
        }
        
        @Override
        public JavaType typeFromId(DatabindContext context, String id) {
            Class<?> subType = null;
            switch (id) {
                case "bean1":
                    subType = FirstBean.class;
                    break;
                case "bean2":
                    subType = LastBean.class;
            }
            return context.constructSpecializedType(superType, subType);
        }
    }
}
