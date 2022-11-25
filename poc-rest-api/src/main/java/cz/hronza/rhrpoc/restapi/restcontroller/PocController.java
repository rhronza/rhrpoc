package cz.hronza.rhrpoc.restapi.restcontroller;

import cz.hronza.rhrpoc.business_logic.domain.Result;
import cz.hronza.rhrpoc.business_logic.facade.CalculationFacade;
import cz.hronza.rhrpoc.business_logic.facade.ClientEasyBeFacade;
import cz.hronza.rhrpoc.core.api.api.PocRestApi;
import cz.hronza.rhrpoc.core.api.dto.OutputDto;
import cz.hronza.rhrpoc.core.api.dto.ResultDto;
import cz.hronza.rhrpoc.core.api.dto.SellerAndSoldProductsDto;
import cz.hronza.rhrpoc.core.common.enums.MultipleOperationsEnum;
import cz.hronza.rhrpoc.core.common.enums.OperationsEnum;
import cz.hronza.rhrpoc.restapi.converter.ResultConverter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class PocController implements PocRestApi {
    private final ClientEasyBeFacade clientEasyBeFacade;
    private final CalculationFacade calculationFacade;


    private final ResultConverter resultConverter;
    private static final System.Logger logger = System.getLogger(PocController.class.getSimpleName());


    public PocController(CalculationFacade calculationFacade, ClientEasyBeFacade clientEasyBeFacade, ResultConverter resultConverter) {
        this.calculationFacade = calculationFacade;
        this.clientEasyBeFacade = clientEasyBeFacade;
        this.resultConverter = resultConverter;
    }


    @Override
    public ResponseEntity<ResultDto> makeOperation(Integer variableA,
                                                   Integer variableB,
                                                   OperationsEnum operationsEnum) {
        Result result = calculationFacade.calculate(variableA, variableB, operationsEnum);

        logger.log(
                System.Logger.Level.INFO,
                String.format(
                        "makeOperation is running, operation=%s, variableA=%d, variableB=%d, result=%d",
                        operationsEnum.toString(), variableA, variableB, result.getResult()));

        return ResponseEntity.ok(resultConverter.toDto(result));
    }

    @Override
    public ResponseEntity<ResultDto> makeMultipleOperation(@Valid MultipleOperationsEnum multipleOperationsEnum, @Valid List<Integer> numbers) {
        Result result = calculationFacade.multipleCaculation(multipleOperationsEnum, numbers);
        return ResponseEntity.ok(resultConverter.toDto(result));
    }

    @Override
    public ResponseEntity<SellerAndSoldProductsDto> addSellerAndSoldPoducts(SellerAndSoldProductsDto sellerAndSoldProductsDto) throws RuntimeException {
        // String niceJson = new ObjectMapper().registerModule(new JavaTimeModule()).writerWithDefaultPrettyPrinter().writeValueAsString(sellerAndSoldProductsDto) ;

        return ResponseEntity.ok(sellerAndSoldProductsDto);
    }

    @Override
    public ResponseEntity<OutputDto> reverseEndpointFromEasyBe(String id, String name) {
        return clientEasyBeFacade.reverseEndpointFromEasyBe(id, name);
    }
}
