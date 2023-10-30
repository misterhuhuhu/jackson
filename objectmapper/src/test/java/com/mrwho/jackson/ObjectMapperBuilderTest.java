package com.mrwho.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class ObjectMapperBuilderTest {
    ObjectMapper mapper = new ObjectMapperBuilder()
                                  .enableIndentation()
                                  .dateFormat()
                                  .preserveOrder(true)
                                  .build();
    
    Car givenCar = new Car("White", "Sedan");
    String givenCarJsonStr = "{ \"color\" : \"White\", \"type\" : \"Sedan\" }";
    
    @Test
    public void ReadCarJsonStr() throws JsonProcessingException {
        Car actual = mapper.readValue(givenCarJsonStr, Car.class);
        Assertions.assertEquals("White", actual.getColor());
        Assertions.assertEquals("Sedan", actual.getType());
    }
    
    @Test
    public void WriteRequestObject() throws JsonProcessingException {
        Request request = new Request();
        request.setCar(givenCar);
        Date date = new Date(1684909857000L);
        request.setDatePurchased(date);
        
        String actual = mapper.writeValueAsString(request);
        System.out.println(actual);
    }
}
