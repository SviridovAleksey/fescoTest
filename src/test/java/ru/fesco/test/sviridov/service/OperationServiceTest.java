package ru.fesco.test.sviridov.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.fesco.test.sviridov.persistence.model.Operation;
import ru.fesco.test.sviridov.persistence.repository.OperationRepository;
import ru.fesco.test.sviridov.web.dto.OperationDTO;
import ru.fesco.test.sviridov.web.mapping.MappingDTO;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class OperationServiceTest {

    @Autowired
    private MappingDTO mappingDTO;

    @MockBean
    private OperationRepository operationRepository;

    @Autowired
    private OperationService operationService;

    @Test
    public void getAllOperationsTest() {

        Mockito.when(operationRepository.findAll()).thenReturn(List.of(
                createTestOperation("001"),
                createTestOperation("007")
        ));

        assertEquals(
                List.of(createTestDtoOperation("001"), createTestDtoOperation("007")),
                operationService.getAllOperations()
        );
    }

    @Test
    public void getOperationDTOByOpCodeTest() {

        String testOpCode = "007";
        Mockito.when(operationRepository.findByopCode(testOpCode))
                .thenReturn(Optional.of(createTestOperation(testOpCode)));

        assertEquals(
                createTestDtoOperation("007"),
                operationService.getOperationDTOByOpCode(testOpCode)
        );
    }

    @Test
    public void getOperationByOperationCodeTest() {

        String testOpCode = "007";
        Mockito.when(operationRepository.findByopCode(testOpCode))
                .thenReturn(Optional.of(createTestOperation(testOpCode)));

        assertEquals(
                createTestOperation("007"),
                operationService.getOperationByOperationCode(testOpCode)
        );
    }

    private Operation createTestOperation(String opCode) {

        Operation operation = new Operation();
        operation.setOpCode(opCode);
        operation.setOpName("Operation" + opCode);

        return operation;
    }

    private OperationDTO createTestDtoOperation(String opCode) {
        return new OperationDTO(opCode, "Operation" + opCode);
    }

}