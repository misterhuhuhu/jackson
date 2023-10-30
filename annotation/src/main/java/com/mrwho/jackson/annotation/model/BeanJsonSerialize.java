package com.mrwho.jackson.annotation.model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BeanJsonSerialize {
    private final Integer id = 1;
    private final String name = "JsonSerialize";
    @JsonSerialize(using = CustomDateSerializer.class)
    private final Date date = new Date();
    
    public static class CustomDateSerializer extends StdSerializer<Date> {
        
        private static final long serialVersionUID = -2894356342227378312L;
        private SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        
        public CustomDateSerializer() {
            this(null);
        }
        
        public CustomDateSerializer(final Class<Date> t) {
            super(t);
        }
        
        @Override
        public void serialize(final Date value, final JsonGenerator gen, final SerializerProvider arg2) throws IOException, JsonProcessingException {
            gen.writeString(formatter.format(value));
        }
    }
}
