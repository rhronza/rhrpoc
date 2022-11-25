package cz.hronza.rhpoc.easy_be.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.hronza.rhpoc.easy_be.PocEasyBePackage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan(basePackageClasses = PocEasyBePackage.class)
public class PocEasyBeConfiguration {

    public static final String EASY_BE_REST_TEMPLATE_NAME = "cz.hronza.rhpoc.easy.be.config.initEasyBeRestTemplate";

    @Bean(EASY_BE_REST_TEMPLATE_NAME)
    public RestTemplate initEasyBeRestTemplate(ObjectMapper objectMapper) {
        RestTemplate restTemplate = new RestTemplate();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(objectMapper);
        restTemplate.getMessageConverters().add(0, converter);
        return restTemplate;
    }


}
