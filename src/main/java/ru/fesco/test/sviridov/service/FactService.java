package ru.fesco.test.sviridov.service;

import ru.fesco.test.sviridov.persistence.model.Fact;
import ru.fesco.test.sviridov.web.dto.FactDTO;

import java.math.BigDecimal;
import java.util.List;

public interface FactService {
    List<FactDTO> getAllFacts();

    FactDTO getFactDTOByFactId(Long factId);

    Fact getFactByFactId(Long factId);

    List<FactDTO> getAllFactsByAgentCode(String agentCode);

    List<FactDTO> getAllFactsByOperationCode(String opCode);

    Fact updateFact(Fact fact);

    BigDecimal getTotalFactSumLink();
}
