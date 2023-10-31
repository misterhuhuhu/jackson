package jsondateformat;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT, classes = ContactApp.class)
@TestPropertySource(properties = {
        "spring.jackson.date-format=yyyy-MM-dd HH:mm:ss"
})
public class ContactAppIntegrationTest {

    private final ObjectMapper mapper = new ObjectMapper();
    
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void givenJsonFormatAnnotationAndJava8DateType_whenGet_thenReturnExpectedDateFormat() throws IOException, ParseException {
        ResponseEntity<String> response = restTemplate.getForEntity( "/contacts", String.class);

        assertEquals(200, response.getStatusCodeValue());

        List<Map<String, String>> respMap = mapper.readValue(response.getBody(), new TypeReference<>() {
        });

        LocalDate birthdayDate = LocalDate.parse(respMap.get(0).get("birthday"), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDateTime lastUpdateTime = LocalDateTime.parse(respMap.get(0).get("lastUpdate"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        assertNotNull(birthdayDate);
        assertNotNull(lastUpdateTime);
    }

    @Test
    public void givenJsonFormatAnnotationAndLegacyDateType_whenGet_thenReturnExpectedDateFormat() throws IOException {
        ResponseEntity<String> response = restTemplate.getForEntity("/contacts/javaUtilDate", String.class);

        assertEquals(200, response.getStatusCodeValue());

        List<Map<String, String>> respMap = mapper.readValue(response.getBody(), new TypeReference<List<Map<String, String>>>(){});

        LocalDate birthdayDate = LocalDate.parse(respMap.get(0).get("birthday"), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDateTime lastUpdateTime = LocalDateTime.parse(respMap.get(0).get("lastUpdate"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        assertNotNull(birthdayDate);
        assertNotNull(lastUpdateTime);
    }

    @Test
    public void givenDefaultDateFormatInAppPropertiesAndLegacyDateType_whenGet_thenReturnExpectedDateFormat() throws IOException {
        ResponseEntity<String> response = restTemplate.getForEntity("/contacts/plainWithJavaUtilDate", String.class);

        assertEquals(200, response.getStatusCodeValue());

        List<Map<String, String>> respMap = mapper.readValue(response.getBody(), new TypeReference<List<Map<String, String>>>(){});

        LocalDate birthdayDate = LocalDate.parse(respMap.get(0).get("birthday"), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDateTime lastUpdateTime = LocalDateTime.parse(respMap.get(0).get("lastUpdate"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        assertNotNull(birthdayDate);
        assertNotNull(lastUpdateTime);
    }

    @Test()
    public void givenDefaultDateFormatInAppPropertiesAndJava8DateType_whenGet_thenNotApplyFormat() throws IOException {
        ResponseEntity<String> response = restTemplate.getForEntity( "/contacts/plain", String.class);

        assertEquals(200, response.getStatusCodeValue());

        List<Map<String, String>> respMap = mapper.readValue(response.getBody(), new TypeReference<List<Map<String, String>>>(){});

        LocalDate birthdayDate = LocalDate.parse(respMap.get(0).get("birthday"), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        assertThrows(DateTimeParseException.class,()->LocalDateTime.parse(respMap.get(0).get("lastUpdate"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        
        
    }

}
