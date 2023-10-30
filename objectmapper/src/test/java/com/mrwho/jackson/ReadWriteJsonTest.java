package com.mrwho.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;


public class ReadWriteJsonTest {
    static Logger logger = LoggerFactory.getLogger(ReadWriteJsonTest.class);
    final String EXAMPLE_JSON = "{ \"color\" : \"Black\", \"type\" : \"BMW\" }";
    final String LOCAL_JSON = "[{ \"color\" : \"Black\", \"type\" : \"BMW\" }, { \"color\" : \"Red\", \"type\" : \"BMW\" }]";

    @Test
    void JavaToJson() throws JsonProcessingException {
        final ObjectMapper objectMapper = new ObjectMapper();
        final Car car = new Car("yellow", "renault");
        final String carAsString = objectMapper.writeValueAsString(car);
        System.out.println(carAsString);
    }
    
    @Test
    void WriteToFile() throws IOException {
        File resultFile = File.createTempFile("car",".json");
        
        ObjectMapper objectMapper = new ObjectMapper();
        Car car = new Car("yellow", "renault");
        objectMapper.writeValue(resultFile, car);
        
        //需要无参构造函数
        Car fromFile = objectMapper.readValue(resultFile, Car.class);
        System.out.println(fromFile);
    }
    
    
    @Test
    void ReadJsonToJsonNode() throws JsonProcessingException {
        final ObjectMapper objectMapper = new ObjectMapper();
        final JsonNode jsonNode = objectMapper.readTree(EXAMPLE_JSON);
        for (JsonNode node : jsonNode) {
            System.out.println(node);
        }
    }
    
    @Test
    void ReadJsonToList() throws JsonProcessingException {
        final ObjectMapper objectMapper = new ObjectMapper();
        final List<Car> listCar = objectMapper.readValue(LOCAL_JSON, new TypeReference<List<Car>>() {
        
        });
        for (final Car car : listCar) {
            logger.info("cat:{}",car);
        }
    }
    
    @Test
    void ReadJsonToMap() throws JsonProcessingException {
        final ObjectMapper objectMapper = new ObjectMapper();
        final Map<String, Object> map = objectMapper.readValue(EXAMPLE_JSON, new TypeReference<Map<String, Object>>() {
        });
        logger.info("map{}",map);
        for (final String key : map.keySet()) {
            logger.info("{}",key);
        }
    }
    
    @Test
    void ReadFromFile() throws IOException {
        File resource = new File("src/test/resources/json_car.json");
        ObjectMapper objectMapper = new ObjectMapper();
        Car fromFile = objectMapper.readValue(resource, Car.class);
        logger.info("{}",fromFile);
    }
    
    @Test
    void ReadFromUrl() throws IOException {
        
        URL resource = new URL("file:src/test/resources/json_car.json");
        
        ObjectMapper objectMapper = new ObjectMapper();
        Car fromFile = objectMapper.readValue(resource, Car.class);
        
        logger.info("{}",fromFile);
        
    }
}