package ru.fesco.test.sviridov.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.fesco.test.sviridov.error_handling.ResourceNotFoundException;
import ru.fesco.test.sviridov.persistence.model.Fact;
import ru.fesco.test.sviridov.persistence.repository.FactRepository;
import ru.fesco.test.sviridov.service.AgentService;
import ru.fesco.test.sviridov.service.FactService;
import ru.fesco.test.sviridov.service.OperationService;
import ru.fesco.test.sviridov.web.dto.FactDTO;
import ru.fesco.test.sviridov.web.mapping.MappingDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class FactServiceImp implements FactService {

    private final MappingDTO mappingDTO;
    private final FactRepository factRepository;
    private final AgentService agentService;
    private final OperationService operationService;


    @Override
    public List<FactDTO> getAllFacts() {
        return factRepository.findAll().stream().map(mappingDTO::factMapToDTO).collect(Collectors.toList());
    }

    @Override
    public FactDTO getFactDTOByFactId(Long factId) {
        log.debug("getFactDTOByFactId " + factId);
        return mappingDTO.factMapToDTO(factRepository.findByFactId(factId).orElseThrow(() ->
                new ResourceNotFoundException("Fact not found by opCode")
        ));
    }

    @Override
    public Fact getFactByFactId(Long factId) {
        log.debug("getFactByFactId " + factId);
        return factRepository.findByFactId(factId).orElseThrow(() ->
                new ResourceNotFoundException("Fact not found by opCode"));
    }

    @Override
    public List<FactDTO> getAllFactsByAgentCode(String agentCode) {
        log.debug("getAllFactsByAgentCode " + agentCode);
        return factRepository.findAllByAgent(agentService.getAgentByAgentCode(agentCode))
                .stream().map(mappingDTO::factMapToDTO).collect(Collectors.toList());
    }

    @Override
    public List<FactDTO> getAllFactsByOperationCode(String opCode) {
        log.debug("getAllFactsByOperationCode " + opCode);
        return factRepository.findAllByOperation(operationService.getOperationByOperationCode(opCode))
                .stream().map(mappingDTO::factMapToDTO).collect(Collectors.toList());
    }

    @Override
    public Fact updateFact(Fact fact) {
        getFactByFactId(fact.getFactId());
        log.debug("update fact" + fact);
        return factRepository.save(fact);
    }

    @Override
    public BigDecimal getTotalFactSumLink() {
        return factRepository.totalTest();
    }
}
