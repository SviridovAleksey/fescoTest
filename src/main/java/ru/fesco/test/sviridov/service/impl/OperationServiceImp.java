package ru.fesco.test.sviridov.service.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.fesco.test.sviridov.error_handling.ResourceNotFoundException;
import ru.fesco.test.sviridov.persistence.model.Operation;
import ru.fesco.test.sviridov.persistence.repository.OperationRepository;
import ru.fesco.test.sviridov.service.OperationService;
import ru.fesco.test.sviridov.web.dto.OperationDTO;
import ru.fesco.test.sviridov.web.mapping.MappingDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OperationServiceImp implements OperationService {

    private final MappingDTO mappingDTO;
    private final OperationRepository operationRepository;


    @Override
    public List<OperationDTO> getAllOperations() {
        return operationRepository.findAll().stream().map(mappingDTO::operationMapToDTO).collect(Collectors.toList());
    }

    @Override
    public OperationDTO getOperationDTOByOpCode(String opCode) {
        log.debug("getOperationDTOByOpCode " + opCode);
        return mappingDTO.operationMapToDTO(operationRepository.findByopCode(opCode).orElseThrow(() ->
                new ResourceNotFoundException("Operation not found by opCode")
        ));
    }

    @Override
    public Operation getOperationByOperationCode(String opCode) {
        log.debug("getOperationByOperationCode " + opCode);
        return operationRepository.findByopCode(opCode).orElseThrow(() ->
                new ResourceNotFoundException("Operation not found by opCode"));
    }


}
