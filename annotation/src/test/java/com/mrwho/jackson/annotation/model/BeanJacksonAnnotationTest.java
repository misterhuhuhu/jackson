package com.mrwho.jackson.annotation.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.factories.SchemaFactoryWrapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class BeanJacksonAnnotationTest {
    
    
    private ObjectMapper objectMapper=new ObjectMapper();
    @Test
    public void testJsonAnyGetter() throws JsonProcessingException {
        final BeanJsonAnyGetter bean = new BeanJsonAnyGetter("1");
        final String result = objectMapper.writeValueAsString(bean);
        System.out.println(result);
    }
    
    @Test
    public void testJsonGetter() throws JsonProcessingException {
        final BeanJsonGetter bean = new BeanJsonGetter("My bean");
        final String result = objectMapper.writeValueAsString(bean);
        System.out.println(result);
    }
    
    @Test
    public void testJsonPropertyOrder() throws JsonProcessingException {
        final BeanJsonPropertyOrder bean = new BeanJsonPropertyOrder("My bean");
        final String result = objectMapper.writeValueAsString(bean);
        System.out.println(result);
    }
    
    @Test
    public void testJsonRawValue() throws JsonProcessingException {
        final BeanJsonRawValue bean = new BeanJsonRawValue("My bean", "{\"attr\":false}");
        final String result = objectMapper.writeValueAsString(bean);
        System.out.println(result);
    }
    
    @Test
    public void testJsonValue() throws JsonProcessingException {
        final String result = objectMapper.writeValueAsString(BeanJsonValue.TYPE1);
        System.out.println(result);
    }
    
    @Test
    public void testJsonRootName() throws JsonProcessingException {
        final BeanJsonRootName bean = new BeanJsonRootName("My bean");
        final String result = objectMapper.enable(SerializationFeature.WRAP_ROOT_VALUE).writeValueAsString(bean);
        System.out.println(result);
    }
    
    @Test
    public void testJsonRootNameXml() throws JsonProcessingException {
        final BeanJsonRootNameXml bean = new BeanJsonRootNameXml("My bean");
        
        final String result = new XmlMapper()//需导入jackson-dataformat-xml
                                      .enable(SerializationFeature.WRAP_ROOT_VALUE)
                                      .enable(SerializationFeature.INDENT_OUTPUT)//缩进输出
                                      .writeValueAsString(bean);
        System.out.println(result);
    }
    
    @Test
    public void testJsonSerialize() throws JsonProcessingException {
        final BeanJsonSerialize bean = new BeanJsonSerialize();
        
        final String result = new JsonMapper()
                                      .enable(SerializationFeature.WRAP_ROOT_VALUE)
                                      .enable(SerializationFeature.INDENT_OUTPUT)//缩进输出
                                      .writeValueAsString(bean);
        System.out.println(result);
    }
    
    @Test
    public void testJsonDeSerialize() throws JsonProcessingException {
        final String json = "{\"name\":\"party\",\"date\":\"20-12-2014 02:30:00\"}";
        //必须有setter
        final BeanJsonDeserialize result = new JsonMapper().readerFor(BeanJsonDeserialize.class).readValue(json);
        System.out.println(result);
    }
    
    @Test
    public void testJsonCreator() throws IOException {
        final String json = "{\"id\":1,\"theName\":\"My bean\"}";
        
        final BeanJsonCreator bean = objectMapper.readerFor(BeanJsonCreator.class)
                                             .readValue(json);
        System.out.println(bean);
    }
    
    @Test
    public void testJacksonInject() throws IOException {
        final String json = "{\"name\":\"My bean\"}";
        final InjectableValues inject = new InjectableValues.Std().addValue(int.class, 1);
        
        final BeanJacksonInject bean = objectMapper.reader(inject)
                                               .forType(BeanJacksonInject.class)
                                               .readValue(json);
        System.out.println(bean);
    }
    
    @Test
    public void testJsonAnySetter() throws IOException {
        String json = "{\"name\":\"My bean\",\"attr2\":\"val2\",\"attr1\":\"val1\"}";
        System.out.println(json);
        BeanJsonAnySetter bean = objectMapper.readerFor(BeanJsonAnySetter.class).readValue(json);
        System.out.println(bean);
    }
    
    @Test
    public void testBeanJsonIgnoreType()
            throws JsonProcessingException {
        
        BeanJsonIgnoreType.Name name = new BeanJsonIgnoreType.Name("John", "Doe");
        BeanJsonIgnoreType user = new BeanJsonIgnoreType(1, name);
        
        String result = objectMapper
                                .writeValueAsString(user);
        System.out.println(result);

    }
    
    @Test
    public void testJsonAutoDetect() throws JsonProcessingException {
        
        BeanJsonAutoDetect bean = new BeanJsonAutoDetect(1, "My bean");
        
        String result = objectMapper
                                .writeValueAsString(bean);
        
        System.out.println(result);
    }
    
    @Test
    public void testJsonFormat() throws JsonProcessingException, ParseException {
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        
        String toParse = "20-12-2014 02:30:00";
        Date date = df.parse(toParse);
        BeanJsonFormat event = new BeanJsonFormat("party", date);
        
        String result = objectMapper.writeValueAsString(event);
        System.out.println(result);
        BeanJsonFormat event2= objectMapper.readerFor(BeanJsonFormat.class).readValue(result);
        System.out.println(event2);
        
    }
    
    
    @Test
    void testJsonFilter() throws JsonProcessingException {
        BeanJsonFilter bean = new BeanJsonFilter(1, "My bean");
        
        FilterProvider filters
                = new SimpleFilterProvider().addFilter(
                "myFilter",
                SimpleBeanPropertyFilter.filterOutAllExcept("name"));//序列化中排除除name之外的所有其他属性
        
        String result = new ObjectMapper()
                                .writer(filters)
                                .writeValueAsString(bean);
        System.out.println(result);
        
    }
    
    
    @Test
    void testJsonIdentityInfo() throws JsonProcessingException {
        BeanJsonIdentityInfo1 user = new BeanJsonIdentityInfo1(1, "John");
        BeanJsonIdentityInfo1.BeanJsonIdentityInfo2 item = new BeanJsonIdentityInfo1.BeanJsonIdentityInfo2(2, "book", user);
        user.addItem(item);
        
        String result = new ObjectMapper().writeValueAsString(item);
        System.out.println(result);
    }
    
    @Test
    void TestJsonAppend() throws JsonProcessingException {
        BeanJsonAppend bean = new BeanJsonAppend(2, "Bean With Append Annotation");
        ObjectWriter writer
                = new ObjectMapper().writerFor(BeanJsonAppend.class).withAttribute("version", "1.0");
        String jsonString = writer.writeValueAsString(bean);
        System.out.println(jsonString);
    }
    
    
    @Test
    void testJsonNaming() throws JsonProcessingException {
        BeanJsonNaming bean = new BeanJsonNaming(3, "Naming Bean");
        String jsonString = new ObjectMapper().writeValueAsString(bean);
        System.out.println(jsonString);
    }
    
    @Test
    void testJsonPropertyDescription() throws JsonProcessingException {
        BeanJsonPropertyDescription bean = new BeanJsonPropertyDescription(3, "Naming Bean");
        
        SchemaFactoryWrapper wrapper = new SchemaFactoryWrapper();
        ObjectMapper mapper = new ObjectMapper();
        
        mapper.acceptJsonFormatVisitor(BeanJsonPropertyDescription.class, wrapper);
        JsonSchema jsonSchema = wrapper.finalSchema();
        String jsonString = mapper.enable(SerializationFeature.INDENT_OUTPUT).writeValueAsString(jsonSchema);
        System.out.println(jsonString);
    }
    
    @Test
    void testJsonPOJOBuilder() throws JsonProcessingException {
        String jsonString = "{\"id\":5,\"name\":\"POJO Builder Bean\"}";
        ObjectMapper mapper = new ObjectMapper();
        BeanJsonPOJOBuilder bean = mapper.readValue(jsonString, BeanJsonPOJOBuilder.class);
        System.out.println(bean);
    }
    
    @Test
    void testJsonTypeIdResolver() throws JsonProcessingException {
        BeanJsonTypeIdResolver.FirstBean bean1 = new BeanJsonTypeIdResolver.FirstBean(1, "Bean 1");
        BeanJsonTypeIdResolver.LastBean bean2 = new BeanJsonTypeIdResolver.LastBean(2, "Bean 2");
        
        List<BeanJsonTypeIdResolver> beans = new ArrayList<>();
        beans.add(bean1);
        beans.add(bean2);
        
        BeanJsonTypeIdResolver.BeanContainer serializedContainer = new BeanJsonTypeIdResolver.BeanContainer();
        serializedContainer.setBeans(beans);
        String jsonString = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT).writeValueAsString(serializedContainer);
        
        System.out.println(jsonString);
        
        //反序列化
        BeanJsonTypeIdResolver.BeanContainer deserializedContainer = new ObjectMapper()
                                                                             .readValue(jsonString, BeanJsonTypeIdResolver.BeanContainer.class);
        List<BeanJsonTypeIdResolver> beanList = deserializedContainer.getBeans();
        beanList.forEach(System.out::println);
    }
}