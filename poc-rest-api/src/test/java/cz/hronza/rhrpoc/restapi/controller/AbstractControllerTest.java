package cz.hronza.rhrpoc.restapi.controller;

import config.RhrPocRestApiTestConfiguration;
import cz.hronza.rhrpoc.restapi.exception.RhrPocResponseEntityExceptionHandler;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ContextConfiguration(
        classes = {
                RhrPocRestApiTestConfiguration.class,
                RhrPocResponseEntityExceptionHandler.class
        }
)
public class AbstractControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    protected MockMvc mockMvc;

    @BeforeEach
    void initMvc() {
        if (this.mockMvc == null) mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
}
