package ru.fesco.test.sviridov.service;

import ru.fesco.test.sviridov.persistence.model.Agent;
import ru.fesco.test.sviridov.web.dto.AgentDTO;

import java.util.List;

public interface AgentService {
    List<AgentDTO> getAllAgents();

    AgentDTO getAgentDTOByAgentCode(String agentCode);

    Agent getAgentByAgentCode(String agentCode);
}
