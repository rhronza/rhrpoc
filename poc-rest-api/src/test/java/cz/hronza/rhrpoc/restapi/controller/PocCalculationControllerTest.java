package cz.hronza.rhrpoc.restapi.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import cz.hronza.rhpoc.easy_be.config.PocEasyBeConfiguration;
import cz.hronza.rhpoc.easy_be.service.EasyBeClient;
import cz.hronza.rhpoc.easy_be.service.EasyBeClientImpl;
import cz.hronza.rhrpoc.business_logic.domain.DateTimeDays;
import cz.hronza.rhrpoc.business_logic.domain.DateTimeNew;
import cz.hronza.rhrpoc.business_logic.domain.Result;
import cz.hronza.rhrpoc.business_logic.facade.CalculationFacadeImpl;
import cz.hronza.rhrpoc.business_logic.facade.ClientEasyBeFacade;
import cz.hronza.rhrpoc.business_logic.facade.ClientEasyBeFacadeImpl;
import cz.hronza.rhrpoc.business_logic.facade.OffsetDateTimeFacade;
import cz.hronza.rhrpoc.business_logic.facade.OffsetDateTimeFacadeImpl;
import cz.hronza.rhrpoc.business_logic.service.ClientEasyBe;
import cz.hronza.rhrpoc.business_logic.service.ClientEasyBeImpl;
import cz.hronza.rhrpoc.business_logic.service.OffsetDateTimeService;
import cz.hronza.rhrpoc.business_logic.service.OffsetDateTimeServiceImpl;
import cz.hronza.rhrpoc.core.common.enums.MultipleOperationsEnum;
import cz.hronza.rhrpoc.core.common.enums.OperationsEnum;
import cz.hronza.rhrpoc.core.common.exception.RhrPocCannotBeDividedByZero;
import cz.hronza.rhrpoc.core.common.exception.RhrPocExceptionHandler;
import cz.hronza.rhrpoc.restapi.converter.ResultConverter;
import cz.hronza.rhrpoc.restapi.converter.StockConverter;
import cz.hronza.rhrpoc.restapi.converter.StockItemsMovementsDtoRecConverter;
import cz.hronza.rhrpoc.restapi.converter.StockTitleRecConverter;
import cz.hronza.rhrpoc.restapi.restcontroller.PocCalculationController;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.client.RestTemplate;

import static cz.hronza.rhrpoc.business_logic.service.OperationServiceImpl.CANNOT_BE_DIVIDED_BY_0_MESSAGE;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@ContextConfiguration(classes = {
        ResultConverter.class,
        PocCalculationController.class,
        ClientEasyBe.class,
        ClientEasyBeFacade.class,
        ClientEasyBeImpl.class,
        ClientEasyBeFacadeImpl.class,
        EasyBeClient.class,
        EasyBeClientImpl.class,
        RestTemplate.class,
        PocEasyBeConfiguration.class,
        DateTimeDays.class,
        DateTimeNew.class,
        OffsetDateTimeFacade.class,
        OffsetDateTimeFacadeImpl.class,
        OffsetDateTimeService.class,
        OffsetDateTimeServiceImpl.class,
        StockTitleRecConverter.class,
        StockConverter.class,
        StockItemsMovementsDtoRecConverter.class,

})
class PocCalculationControllerTest extends AbstractControllerTest {
    public static final int RESULT_SUM = 28;
    public static final int RESULT_DIFFRENCE = 14;
    public static final int RESULT_DIVIDE = 3;
    public static final int RESULT_MULTIPLICATION = 147;
    public static final String VARIABLE_B_VALUE = "7";
    public static final String VARIABLE_A_VALUE = "21";
    public static final Integer RESULT_SUM_MULTIPLICATION = 80;
    public static final int RESULT_MULTIPLE_MULTIPLICATION = 23625;
    @MockBean
    private CalculationFacadeImpl calculationFacade;

    @Test
    void makeOperationSumPositiveTest() throws Exception {
        // when(calculationFacade.calculate(20, 32, OperationsEnum.SUM)).thenReturn(new Result().setResult(RESULT_SUM).setOperationsEnum(OperationsEnum.SUM));

        universalMockCalculate();

        MvcResult mvcResult = mockMvc.perform(get("/calculation/v1/")
                        .param("variableA", VARIABLE_A_VALUE)
                        .param("variableB", VARIABLE_B_VALUE)
                        .param("operationsEnum", "SUM")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk()).andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        JsonNode response = new ObjectMapper().registerModule(new JavaTimeModule()).readTree(content);

        assertEquals(OperationsEnum.SUM.toString(), response.get("operation").asText());
        assertEquals(RESULT_SUM, response.get("result").asInt());

        // přes JSONObject:
        String niceJson = response.toPrettyString();
        JSONObject jsonObject = new JSONObject(niceJson); // šel by dosadit i content
        assertEquals(OperationsEnum.SUM.toString(), jsonObject.get("operation"));
        assertEquals(String.valueOf(RESULT_SUM), jsonObject.get("result"));
    }

    @Test
    void makeOperationDifferencePositiveTest() throws Exception {
        universalMockCalculate();

        MvcResult mvcResult = mockMvc.perform(get("/calculation/v1")
                        .param("variableA", VARIABLE_A_VALUE)
                        .param("variableB", VARIABLE_B_VALUE)
                        .param("operationsEnum", "DIFFERENCE")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        JsonNode response = new ObjectMapper().registerModule(new JavaTimeModule()).readTree(content);

        assertEquals(OperationsEnum.DIFFERENCE.toString(), response.get("operation").asText());
        assertEquals(RESULT_DIFFRENCE, response.get("result").asInt());

        // přes JSONObject:
        String niceJson = response.toPrettyString();
        JSONObject jsonObject = new JSONObject(niceJson); // šel by dosadit i content
        assertEquals(OperationsEnum.DIFFERENCE.toString(), jsonObject.get("operation"));
        assertEquals(String.valueOf(RESULT_DIFFRENCE), jsonObject.get("result"));
    }

    @Test
    void makeOperationDividePositiveTest() throws Exception {
        universalMockCalculate();

        MvcResult mvcResult = mockMvc.perform(get("/calculation/v1")
                        .param("variableA", VARIABLE_A_VALUE)
                        .param("variableB", VARIABLE_B_VALUE)
                        .param("operationsEnum", "DIVIDE")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.operation", is("DIVIDE")))
                .andExpect(jsonPath("$.result", is("3")))
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        JsonNode response = new ObjectMapper().registerModule(new JavaTimeModule()).readTree(content);

        assertEquals(OperationsEnum.DIVIDE.toString(), response.get("operation").asText());
        assertEquals(RESULT_DIVIDE, response.get("result").asInt());

        // přes JSONObject:
        String niceJson = response.toPrettyString();
        JSONObject jsonObject = new JSONObject(niceJson); // šel by dosadit i content
        assertEquals(OperationsEnum.DIVIDE.toString(), jsonObject.get("operation"));
        assertEquals(String.valueOf(RESULT_DIVIDE), jsonObject.get("result"));
    }

    @Test
    void makeOperationDivideNegativeTest() throws Exception {
        universalMockCalculate();

        MvcResult mvcResult = mockMvc.perform(get("/calculation/v1")
                        .param("variableA", VARIABLE_A_VALUE)
                        .param("variableB", "0")
                        .param("operationsEnum", "DIVIDE")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.errorItemDtos[0].code", is("CANNOT_BE_DIVIDED_BY_ZERO")))
                .andExpect(jsonPath("$.errorItemDtos[0].severity", is("ERROR")))
                .andExpect(jsonPath("$.errorItemDtos[0].parameters", hasSize(1)))
                .andExpect(jsonPath("$.errorItemDtos[0].parameters.[0].key", is("message")))
                .andExpect(jsonPath("$.errorItemDtos[0].parameters.[0].value", is("variableB is equals 0")))
                .andReturn();
        JSONObject errorItemsDtos = (JSONObject) new JSONObject(mvcResult.getResponse().getContentAsString()).getJSONArray("errorItemDtos").get(0);

        //asserty přes JSONObject:
        assertEquals("ERROR", errorItemsDtos.get("severity"));
        assertEquals(RhrPocExceptionHandler.ERROR_MESSAGE_RHR_CANNOT_BE_DIVIDED_BY_ZERO, errorItemsDtos.get("code"));

        JSONObject parameters = (JSONObject) errorItemsDtos.getJSONArray("parameters").get(0);
        assertEquals("message", parameters.get("key"));
        assertEquals("variableB is equals 0", parameters.get("value"));
    }

    @Test
    void makeOperationMultiplicationPositiveTest() throws Exception {
        universalMockCalculate();

        MvcResult mvcResult = mockMvc.perform(get("/calculation/v1")
                        .param("variableA", VARIABLE_A_VALUE)
                        .param("variableB", VARIABLE_B_VALUE)
                        .param("operationsEnum", "MULTIPLICATION")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        JsonNode response = new ObjectMapper().registerModule(new JavaTimeModule()).readTree(content);

        assertEquals(OperationsEnum.MULTIPLICATION.toString(), response.get("operation").asText());
        assertEquals(RESULT_MULTIPLICATION, response.get("result").asInt());
    }


    @Test
    void makeOperationMultipleSumPositiveTest() throws Exception {
        universalMockMultiplicationCalculate();

        MvcResult mvcResult = mockMvc.perform(get(String.format("/calculation/v1/%s", "MULTIPLE_SUM"))
                        .param("numbers", "25")
                        .param("numbers", "3")
                        .param("numbers", "45")
                        .param("numbers", "7")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        JsonNode response = new ObjectMapper().registerModule(new JavaTimeModule()).readTree(content);

        assertEquals(MultipleOperationsEnum.MULTIPLE_SUM.toString(), response.get("operation").asText());
        Assertions.assertEquals(RESULT_SUM_MULTIPLICATION, response.get("result").asInt());

        // přes JSONObject:
        JSONObject jsonObject = new JSONObject(content);
        assertEquals(MultipleOperationsEnum.MULTIPLE_SUM.toString(), jsonObject.get("operation"));
        assertEquals(RESULT_SUM_MULTIPLICATION.toString(), jsonObject.get("result"));
    }

    @Test
    void makeOperationMultipleMultiplicationPositiveTest() throws Exception {
        universalMockMultiplicationCalculate();

        MvcResult mvcResult = mockMvc.perform(get(String.format("/calculation/v1/%s", "MULTIPLE_MULTIPLICATION"))
                        .param("numbers", "25")
                        .param("numbers", "3")
                        .param("numbers", "45")
                        .param("numbers", "7")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        JsonNode response = new ObjectMapper().registerModule(new JavaTimeModule()).readTree(content);

        assertEquals(MultipleOperationsEnum.MULTIPLE_MULTIPLICATION.toString(), response.get("operation").asText());
        Assertions.assertEquals(RESULT_MULTIPLE_MULTIPLICATION, response.get("result").asInt());
    }


    private void universalMockCalculate() {
        doAnswer(args -> {
            Integer arg0 = args.getArgument(0);
            Integer arg1 = args.getArgument(1);
            OperationsEnum currentOperation = args.getArgument(2);
            if (OperationsEnum.SUM.equals(currentOperation))
                return arg0 != null && arg1 != null ? new Result().setResult(arg0 + arg1).setOperationsEnum(OperationsEnum.SUM) : null;
            if (OperationsEnum.DIFFERENCE.equals(currentOperation))
                return arg0 != null && arg1 != null ? new Result().setResult(arg0 - arg1).setOperationsEnum(OperationsEnum.DIFFERENCE) : null;
            if (OperationsEnum.DIVIDE.equals(currentOperation)) {
                if (arg1 == 0)
                    throw new RhrPocCannotBeDividedByZero(CANNOT_BE_DIVIDED_BY_0_MESSAGE, "message", "variableB is equals 0");
                return arg0 != null ? new Result().setResult(arg0 / arg1).setOperationsEnum(OperationsEnum.DIVIDE) : null;
            }
            if (OperationsEnum.MULTIPLICATION.equals(currentOperation))
                return arg0 != null && arg1 != null ? new Result().setResult(arg0 * arg1).setOperationsEnum(OperationsEnum.MULTIPLICATION) : null;
            else return null;
        }).when(calculationFacade).calculate(any(), any(), any());
    }

    private void universalMockMultiplicationCalculate() {
        doAnswer(args -> {
            MultipleOperationsEnum currentMultipleOperationsEnum = args.getArgument(0);
            if (MultipleOperationsEnum.MULTIPLE_SUM.equals(currentMultipleOperationsEnum))
                return new Result().setResult(RESULT_SUM_MULTIPLICATION).setMultipleOperationsEnum(MultipleOperationsEnum.MULTIPLE_SUM);
            if (MultipleOperationsEnum.MULTIPLE_MULTIPLICATION.equals(currentMultipleOperationsEnum))
                return new Result().setResult(RESULT_MULTIPLE_MULTIPLICATION).setMultipleOperationsEnum(MultipleOperationsEnum.MULTIPLE_MULTIPLICATION);
            else return null;
        }).when(calculationFacade).multipleCaculation(any(), any());
    }
}
