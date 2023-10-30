package com.mrwho.jackson.annotation.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BeanJsonDeserialize {
    private String name;
    @JsonDeserialize(using = CustomDateDeserializer.class)
    private Date date ;
    
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    
    @Override
    public String toString() {
        return "BeanJsonDeserialize{" +
                       "name='" + name + '\'' +
                       ", date=" + date +
                       '}';
    }
    
    public static class CustomDateDeserializer extends StdDeserializer<Date> {
        
        private static final long serialVersionUID = -5451717385630622729L;
        private SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        
        public CustomDateDeserializer() {
            this(null);
        }
        
        public CustomDateDeserializer(final Class<?> vc) {
            super(vc);
        }
        
        @Override
        public Date deserialize(final JsonParser jsonparser, final DeserializationContext context) throws IOException, JsonProcessingException {
            final String date = jsonparser.getText();
            try {
                return formatter.parse(date);
            } catch (final ParseException e) {
                throw new RuntimeException(e);
            }
        }
        
    }
    
}
