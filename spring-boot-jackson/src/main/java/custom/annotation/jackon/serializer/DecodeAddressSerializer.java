package custom.annotation.jackon.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.Collections;

public class DecodeAddressSerializer extends StdSerializer<String> {
    public DecodeAddressSerializer(StdSerializer<?> src) {
        super(src);
    }
    
    public DecodeAddressSerializer() {
        super(String.class);
    }
    
    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        int i = value.length() / 3;
        gen.writeString(String.format("%s%s%s", value.substring(0, i),String.join("",Collections.nCopies(i,"*")), value.substring(value.length()- i)));
    }
}
