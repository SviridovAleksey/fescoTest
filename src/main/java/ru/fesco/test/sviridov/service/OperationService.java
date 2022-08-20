package ru.fesco.test.sviridov.service;

import ru.fesco.test.sviridov.persistence.model.Operation;
import ru.fesco.test.sviridov.web.dto.OperationDTO;

import java.util.List;

public interface OperationService {
    List<OperationDTO> getAllOperations();

    OperationDTO getOperationDTOByOpCode(String opCode);

    Operation getOperationByOperationCode(String opCode);
}
