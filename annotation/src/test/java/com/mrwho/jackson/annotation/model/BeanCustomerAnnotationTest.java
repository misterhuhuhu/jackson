package com.mrwho.jackson.annotation.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mrwho.jackson.annotation.customer.BeanWithCustomAnnotation;
import org.junit.jupiter.api.Test;

public class BeanCustomerAnnotationTest {
    @Test
    public void whenSerializingUsingCustomAnnotation_thenCorrect()
            throws JsonProcessingException {
        BeanWithCustomAnnotation bean
                = new BeanWithCustomAnnotation(1, "My bean", null);
        
        String result = new ObjectMapper().writeValueAsString(bean);
        
        System.out.println(result);
    }
}
