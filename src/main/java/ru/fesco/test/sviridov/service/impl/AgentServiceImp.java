package ru.fesco.test.sviridov.service.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.fesco.test.sviridov.error_handling.ResourceNotFoundException;
import ru.fesco.test.sviridov.persistence.model.Agent;
import ru.fesco.test.sviridov.persistence.repository.AgentRepository;
import ru.fesco.test.sviridov.service.AgentService;
import ru.fesco.test.sviridov.web.dto.AgentDTO;
import ru.fesco.test.sviridov.web.mapping.MappingDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AgentServiceImp implements AgentService {


    private final AgentRepository agentRepository;
    private final MappingDTO mappingDTO;


    @Override
    public List<AgentDTO> getAllAgents() {
        return agentRepository.findAll().stream().map(mappingDTO::agentMapToDTO).collect(Collectors.toList());
    }

   @Override
   public AgentDTO getAgentDTOByAgentCode(String agentCode) {
       log.debug("getAgentDTOByAgentCode " + agentCode);
        return mappingDTO.agentMapToDTO(agentRepository.findByAgentCode(agentCode).orElseThrow(() ->
                new ResourceNotFoundException("Agent not found by agentCode")
        ));
   }

   @Override
   public Agent getAgentByAgentCode(String agentCode) {

        log.debug("getAgentByAgentCode " + agentCode);
        return agentRepository.findByAgentCode(agentCode).orElseThrow(() ->
                new ResourceNotFoundException("Agent not found by agentCode"));
   }

}
