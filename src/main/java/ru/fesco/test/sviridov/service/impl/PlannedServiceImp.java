package ru.fesco.test.sviridov.service.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.fesco.test.sviridov.error_handling.ResourceNotFoundException;
import ru.fesco.test.sviridov.persistence.model.Planed;
import ru.fesco.test.sviridov.persistence.repository.PlanedRepository;
import ru.fesco.test.sviridov.service.AgentService;
import ru.fesco.test.sviridov.service.OperationService;
import ru.fesco.test.sviridov.service.PlannedService;
import ru.fesco.test.sviridov.web.dto.PlanedDTO;
import ru.fesco.test.sviridov.web.mapping.MappingDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlannedServiceImp implements PlannedService {

    private final PlanedRepository planedRepository;
    private final MappingDTO mappingDTO;
    private final AgentService agentService;
    private final OperationService operationService;

    @Override
    public List<PlanedDTO> getAllPlans() {
        return planedRepository.findAll().stream().map(mappingDTO::planedMapToDTO).collect(Collectors.toList());
    }

    @Override
    public PlanedDTO getPlanDTOByPlanId(Long planId) {
        log.debug("getPlanDTOByPlanId " + planId);
        return mappingDTO.planedMapToDTO(planedRepository.findByplanId(planId).orElseThrow(() ->
                new ResourceNotFoundException("Plan not found by planId")
        ));
    }

    @Override
    public Planed getPlanByPlanId(Long planId) {
        log.debug("getPlanByPlanId " + planId);
        return planedRepository.findByplanId(planId).orElseThrow(() ->
                new ResourceNotFoundException("Plan not found by planId"));
    }

    @Override
    public List<PlanedDTO> getAllPlannedByAgentCode(String agentCode) {
        log.debug("getAllPlannedByAgentCode " + agentCode);
        return planedRepository.findAllByAgent(agentService.getAgentByAgentCode(agentCode))
                .stream().map(mappingDTO::planedMapToDTO).collect(Collectors.toList());
    }

    @Override
    public List<PlanedDTO> getAllPlannedByOperationCode(String opCode) {
        log.debug("getAllPlannedByOperationCode " + opCode);
        return planedRepository.findAllByOperation(operationService.getOperationByOperationCode(opCode))
                .stream().map(mappingDTO::planedMapToDTO).collect(Collectors.toList());
    }


    @Override
    public Planed updatePlaned(Planed planed) {
        getPlanByPlanId(planed.getPlanId());
        log.debug("update planed " + planed);
        return planedRepository.save(planed);
    }

    @Override
    public BigDecimal getTotalPlanSumLink() {
        return planedRepository.totalTest();
    }

}
