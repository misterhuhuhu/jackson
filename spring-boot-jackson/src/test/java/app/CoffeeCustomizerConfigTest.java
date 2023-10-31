package app;

import jackson.config.CoffeeCustomizerConfig;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;

import javax.annotation.Resource;

@Import(CoffeeCustomizerConfig.class)
public class CoffeeCustomizerConfigTest extends AbstractCoffeeIntegrationTest {
    @Resource
    private ApplicationContext applicationContext;
    
    @Test
    void name() {
        System.out.println(applicationContext.getBeanDefinitionCount());
    }
}
