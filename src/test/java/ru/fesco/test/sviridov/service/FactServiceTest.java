package ru.fesco.test.sviridov.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.fesco.test.sviridov.persistence.model.Agent;
import ru.fesco.test.sviridov.persistence.model.Fact;
import ru.fesco.test.sviridov.persistence.model.Operation;
import ru.fesco.test.sviridov.persistence.repository.FactRepository;
import ru.fesco.test.sviridov.web.dto.FactDTO;
import ru.fesco.test.sviridov.web.mapping.MappingDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class FactServiceTest {

    @Autowired
    private MappingDTO mappingDTO;

    @Autowired
    private FactService factService;

    @MockBean
    private FactRepository factRepository;

    @MockBean
    private AgentService agentService;

    @MockBean
    private OperationService operationService;

    @Test
    public void getAllFactsTest() {

        Mockito.when(factRepository.findAll()).thenReturn(List.of(
                createTestFact(1L, "001"),
                createTestFact(2L, "002")
        ));

        assertEquals(
                List.of(createTestFactDto(1L, "001"), createTestFactDto(2L, "002")),
                factService.getAllFacts()
        );
    }

    @Test
    public void getFactDTOByFactIdTest() {

        Long testId = 42L;
        Mockito.when(factRepository.findByFactId(testId)).thenReturn(Optional.of(createTestFact(testId, "007")));

        assertEquals(
                createTestFactDto(testId, "007"),
                factService.getFactDTOByFactId(testId)
        );
    }

    @Test
    public void getFactByFactIdTest() {

        Long testId = 42L;
        Mockito.when(factRepository.findByFactId(testId)).thenReturn(Optional.of(createTestFact(testId, "007")));

        assertEquals(
                createTestFact(testId, "007"),
                factService.getFactByFactId(testId)
        );
    }

    @Test
    public void getAllFactsByAgentCodeTest() {

        String testAgentCode = "007";
        Agent testAgent = new Agent();
        testAgent.setAgentCode(testAgentCode);
        testAgent.setAgentName("Agent" + testAgentCode);

        Mockito.when(agentService.getAgentByAgentCode(testAgentCode)).thenReturn(testAgent);
        Mockito.when(factRepository.findAllByAgent(testAgent))
                .thenReturn(
                        List.of(createTestFact(1L, "007"), createTestFact(2L, "007"))
                );

        assertEquals(
                List.of(createTestFactDto(1L, testAgentCode), createTestFactDto(2L, testAgentCode)),
                factService.getAllFactsByAgentCode(testAgentCode)
        );
    }

    @Test
    public void getAllFactsByOperationCodeTest() {

        String testOpCode = "42";
        Operation testOperation = new Operation();
        testOperation.setOpCode(testOpCode);
        testOperation.setOpName("Operation" + testOpCode);

        Mockito.when(operationService.getOperationByOperationCode(testOpCode)).thenReturn(testOperation);
        Mockito.when(factRepository.findAllByOperation(testOperation))
                .thenReturn(
                        List.of(createTestFact(1L, "42"), createTestFact(2L, "42"))
                );

        assertEquals(
                List.of(createTestFactDto(1L, "42"), createTestFactDto(2L, "42")),
                factService.getAllFactsByOperationCode(testOpCode)
        );
    }

    @Test
    public void updateFactTest() {

        Long testId = 42L;
        Mockito.when(factRepository.findByFactId(testId)).thenReturn(Optional.of(createTestFact(testId, "007")));

        Fact testFact = new Fact();
        testFact.setFactId(testId);
        testFact.setFactSum(new BigDecimal(1000));

        factService.updateFact(testFact);
        Mockito.verify(factRepository).save(testFact);
    }

    private Fact createTestFact(Long factId, String code) {

        Agent agent = new Agent();
        agent.setAgentCode(code);
        agent.setAgentName("Agent" + code);

        Operation operation = new Operation();
        operation.setOpCode(code);
        operation.setOpName("Operation" + code);

        Fact fact = new Fact();
        fact.setFactId(factId);
        fact.setFactSum(new BigDecimal(factId * 1000));
        fact.setAgent(agent);
        fact.setOperation(operation);

        return fact;
    }

    private FactDTO createTestFactDto(Long factId, String code) {
        return new FactDTO(
                factId,
                "Agent" + code,
                "Operation" + code,
                new BigDecimal(factId * 1000),
                null
        );
    }

}