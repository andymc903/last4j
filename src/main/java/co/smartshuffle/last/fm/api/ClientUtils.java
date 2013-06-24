package co.smartshuffle.last.fm.api;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.client.RestTemplate;

public class ClientUtils {
    public static RestTemplate getRestTemplate()   {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        return context.getBean(RestTemplate.class);
    }
    
    
}
