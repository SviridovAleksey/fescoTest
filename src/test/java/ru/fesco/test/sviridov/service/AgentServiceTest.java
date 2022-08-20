package ru.fesco.test.sviridov.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.fesco.test.sviridov.error_handling.ResourceNotFoundException;
import ru.fesco.test.sviridov.persistence.model.Agent;
import ru.fesco.test.sviridov.persistence.repository.AgentRepository;
import ru.fesco.test.sviridov.web.dto.AgentDTO;
import ru.fesco.test.sviridov.web.mapping.MappingDTO;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AgentServiceTest {

    @Autowired
    private MappingDTO mappingDTO;

    @MockBean
    private AgentRepository agentRepository;

    @Autowired
    private AgentService agentService;

    @Test
    public void getAllAgentsTest() {

        Mockito.when(agentRepository.findAll()).thenReturn(List.of(
                createTestAgent("001"),
                createTestAgent("007")
        ));

        assertEquals(
                List.of(createTestDtoAgent("001"), createTestDtoAgent("007")),
                agentService.getAllAgents()
        );
    }

    @Test
    public void getAgentDTOByAgentCodeTest() {

        String testAgentCode = "007";
        Mockito.when(agentRepository.findByAgentCode(testAgentCode))
                .thenReturn(Optional.of(createTestAgent(testAgentCode)));

        assertEquals(
                createTestDtoAgent("007"),
                agentService.getAgentDTOByAgentCode(testAgentCode)
        );
    }

    @Test
    public void methodGetAgentDTOByAgentCodeExceptionTest() {

        String testAgentCode = "007";
        Mockito.when(agentRepository.findByAgentCode(testAgentCode))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> agentService.getAgentDTOByAgentCode(testAgentCode)
        );
    }

    @Test
    public void getAgentByAgentCodeTest() {

        String testAgentCode = "007";
        Mockito.when(agentRepository.findByAgentCode(testAgentCode))
                .thenReturn(Optional.of(createTestAgent(testAgentCode)));

        assertEquals(
                createTestAgent("007"),
                agentService.getAgentByAgentCode(testAgentCode)
        );
    }

    @Test
    public void methodGetAgentByAgentCodeExceptionTest() {

        String testAgentCode = "007";
        Mockito.when(agentRepository.findByAgentCode(testAgentCode))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> agentService.getAgentByAgentCode(testAgentCode)
        );
    }

    private Agent createTestAgent(String agentCode) {
        Agent agent = new Agent();
        agent.setAgentCode(agentCode);
        agent.setAgentName("Agent" + agentCode);

        return agent;
    }

    private AgentDTO createTestDtoAgent(String agentCode) {
        return new AgentDTO(agentCode, "Agent" + agentCode);
    }

}