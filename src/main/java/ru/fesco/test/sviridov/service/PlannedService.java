package ru.fesco.test.sviridov.service;

import ru.fesco.test.sviridov.persistence.model.Planed;
import ru.fesco.test.sviridov.web.dto.PlanedDTO;

import java.math.BigDecimal;
import java.util.List;

public interface PlannedService {
    List<PlanedDTO> getAllPlans();

    PlanedDTO getPlanDTOByPlanId(Long planId);

    Planed getPlanByPlanId(Long planId);

    List<PlanedDTO> getAllPlannedByAgentCode(String agentCode);

    List<PlanedDTO> getAllPlannedByOperationCode(String opCode);

    Planed updatePlaned(Planed planed);

    BigDecimal getTotalPlanSumLink();
}
