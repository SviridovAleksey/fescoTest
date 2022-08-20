package ru.fesco.test.sviridov.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.fesco.test.sviridov.persistence.model.Agent;
import ru.fesco.test.sviridov.persistence.model.Operation;
import ru.fesco.test.sviridov.persistence.model.Planed;
import ru.fesco.test.sviridov.persistence.repository.PlanedRepository;
import ru.fesco.test.sviridov.web.dto.PlanedDTO;
import ru.fesco.test.sviridov.web.mapping.MappingDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PlannedServiceTest {

    @Autowired
    private MappingDTO mappingDTO;

    @MockBean
    private PlanedRepository planedRepository;

    @MockBean
    private OperationService operationService;

    @MockBean
    private AgentService agentService;

    @Autowired
    private PlannedService plannedService;

    @Test
    public void getAllPlansTest() {

        Mockito.when(planedRepository.findAll()).thenReturn(
                List.of(createTestPlanned(1L, "001"), createTestPlanned(2L, "002"))
        );

        assertEquals(
                List.of(createTestPlannedDto(1L, "001"), createTestPlannedDto(2L, "002")),
                plannedService.getAllPlans()
        );
    }

    @Test
    public void getPlanDTOByPlanId() {

        Long testPlanId = 42L;
        Mockito.when(planedRepository.findByplanId(testPlanId)).thenReturn(
                Optional.of(createTestPlanned(testPlanId, "001"))
        );

        assertEquals(
                createTestPlannedDto(testPlanId, "001"),
                plannedService.getPlanDTOByPlanId(testPlanId)
        );
    }

    @Test
    public void getPlanByPlanId() {

        Long testPlanId = 42L;
        Mockito.when(planedRepository.findByplanId(testPlanId)).thenReturn(
                Optional.of(createTestPlanned(testPlanId, "001"))
        );

        assertEquals(
                createTestPlanned(testPlanId, "001"),
                plannedService.getPlanByPlanId(testPlanId)
        );
    }

    @Test
    public void getAllPlannedByAgentCodeTest() {

        String agentCode = "007";
        Agent agent = new Agent();
        agent.setAgentCode(agentCode);
        agent.setAgentName("Agent" + agentCode);

        Mockito.when(agentService.getAgentByAgentCode(agentCode)).thenReturn(agent);
        Mockito.when(planedRepository.findAllByAgent(agent)).thenReturn(
                List.of(createTestPlanned(1L, "007"), createTestPlanned(2L, "007"))
        );

        assertEquals(
                List.of(createTestPlannedDto(1L, "007"), createTestPlannedDto(2L, "007")),
                plannedService.getAllPlannedByAgentCode(agentCode)
        );
    }

    @Test
    public void getAllPlannedByOperationCodeTest() {

        String opCode = "007";
        Operation operation = new Operation();
        operation.setOpCode(opCode);
        operation.setOpName("Operation" + opCode);

        Mockito.when(operationService.getOperationByOperationCode(opCode)).thenReturn(operation);
        Mockito.when(planedRepository.findAllByOperation(operation)).thenReturn(
                List.of(createTestPlanned(1L, "007"), createTestPlanned(2L, "007"))
        );

        assertEquals(
                List.of(createTestPlannedDto(1L, "007"), createTestPlannedDto(2L, "007")),
                plannedService.getAllPlannedByOperationCode(opCode)
        );
    }

    @Test
    public void updatePlannedTest() {

        Long testPlanId = 42L;
        Mockito.when(planedRepository.findByplanId(testPlanId)).thenReturn(
                Optional.of(createTestPlanned(testPlanId, "001"))
        );

        Planed planed = new Planed();
        planed.setPlanId(testPlanId);
        planed.setPlanSum(new BigDecimal(testPlanId * 1000));

        plannedService.updatePlaned(planed);
        Mockito.verify(planedRepository).save(planed);
    }

    private Planed createTestPlanned(Long planId, String code) {

        Agent agent = new Agent();
        agent.setAgentCode(code);
        agent.setAgentName("Agent" + code);

        Operation operation = new Operation();
        operation.setOpCode(code);
        operation.setOpName("Operation" + code);

        Planed planed = new Planed();
        planed.setPlanId(planId);
        planed.setAgent(agent);
        planed.setOperation(operation);
        planed.setPlanSum(new BigDecimal(planId * 1000));

        return planed;
    }

    private PlanedDTO createTestPlannedDto(Long planId, String code) {

        return new PlanedDTO(
                planId,
                "Agent" + code,
                "Operation" + code,
                new BigDecimal(planId * 1000),
                null
        );
    }

}