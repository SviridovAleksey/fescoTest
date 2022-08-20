package ru.fesco.test.sviridov.web.controllers;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.fesco.test.sviridov.service.OperationService;

@RestController
@RequestMapping(value = "/api/v1/operation")
@RequiredArgsConstructor
@Slf4j
public class OperationController {

    private final OperationService operationService;

    @GetMapping
    public ResponseEntity<?> findAllAgent() {
        return new ResponseEntity<>(operationService.getAllOperations(), HttpStatus.OK);
    }

    @GetMapping("/{opCode}")
    public ResponseEntity<?> findInstructionBy(@PathVariable String opCode) {
        if (opCode == null) {
            log.error("received null in findInstructionBy");
            return new ResponseEntity<>("opCode must be not null", HttpStatus.BAD_REQUEST);
        }
        log.debug("receive in findInstructionBy " + opCode);
        return new ResponseEntity<>(operationService.getOperationDTOByOpCode(opCode), HttpStatus.OK);
    }

}
