package com.mrwho.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class SerializationAndDeserializationTest {
    
    final String EXAMPLE_JSON = "{ \"color\" : \"Black\", \"type\" : \"BMW\" }";
    final String JSON_CAR = "{ \"color\" : \"Black\", \"type\" : \"Fiat\", \"year\" : \"1970\" }";
    final String JSON_ARRAY = "[{ \"color\" : \"Black\", \"type\" : \"BMW\" }, { \"color\" : \"Red\", \"type\" : \"BMW\" }]";
    
    @Test
    void testFailOnUnknownPropertiesFalse() throws JsonProcessingException {
        
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        final Car car = objectMapper.readValue(JSON_CAR, Car.class);
        final JsonNode jsonNodeRoot = objectMapper.readTree(JSON_CAR);
        final JsonNode jsonNodeYear = jsonNodeRoot.get("year");
        final String year = jsonNodeYear.asText();
        
    }
    
    @Test
    void CustomSerializerDeserializer() throws JsonProcessingException {
        final ObjectMapper mapper1 = new ObjectMapper();
        final SimpleModule serializerModule = new SimpleModule("CustomSerializer", new Version(1, 0, 0, null, null, null));
        serializerModule.addSerializer(Car.class, new CustomCarSerializer());
        mapper1.registerModule(serializerModule);
        final Car car = new Car("yellow", "renault");
        final String carJson = mapper1.writeValueAsString(car);
        
        final ObjectMapper mapper = new ObjectMapper();
        final SimpleModule deserializerModule = new SimpleModule("CustomCarDeserializer", new Version(1, 0, 0, null, null, null));
        deserializerModule.addDeserializer(Car.class, new CustomCarDeserializer());
        mapper.registerModule(deserializerModule);
        final Car carResult = mapper.readValue(EXAMPLE_JSON, Car.class);
        
    }
    
    @Test
    void DateFormatSet() throws JsonProcessingException {
        
        final ObjectMapper objectMapper = new ObjectMapper();
        final Car car = new Car("yellow", "renault");
        final Request request = new Request();
        request.setCar(car);
        request.setDatePurchased(new Date());
        final DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm a z");
        objectMapper.setDateFormat(df);
        final String carAsString = objectMapper.writeValueAsString(request);
        System.out.println(carAsString);
    }
    
    @Test
    void UseJavaArrayForJsonArray() throws JsonProcessingException {
        
        
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);
        final Car[] cars = objectMapper.readValue(JSON_ARRAY, Car[].class);
        for (final Car car : cars) {
            System.out.println(car);
        }
        ObjectMapper objectMapper1 = new ObjectMapper();
        List<Car> listCar = objectMapper.readValue(JSON_ARRAY, new TypeReference<List<Car>>(){});
        for (final Car car : listCar) {
            System.out.println(car);
        }
        
    }
}
