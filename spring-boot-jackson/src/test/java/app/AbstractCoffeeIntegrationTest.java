package app;

import jackson.app.Application;
import jackson.config.CoffeeConstants;
import org.apache.catalina.core.ApplicationContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.Resource;
import java.time.format.DateTimeFormatter;

import static jackson.config.CoffeeConstants.FIXED_DATE;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class AbstractCoffeeIntegrationTest {

    @Resource
    protected TestRestTemplate restTemplate;


    @Test
    public void whenGetCoffee_thenSerializedWithDateAndNonNull() {
        String formattedDate = DateTimeFormatter.ofPattern(CoffeeConstants.DATETIME_FORMAT)
                .format(FIXED_DATE);

      
        String response = restTemplate.getForObject("/coffee?brand="+"Lavazza", String.class);
        
        System.out.println(response);
    }
}
