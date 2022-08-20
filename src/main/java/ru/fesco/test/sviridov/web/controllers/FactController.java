package ru.fesco.test.sviridov.web.controllers;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.fesco.test.sviridov.service.FactService;

@RestController
@RequestMapping(value = "/api/v1/fact")
@RequiredArgsConstructor
@Slf4j
public class FactController {

    private final FactService factService;

    @GetMapping
    public ResponseEntity<?> findAllFact() {
        return new ResponseEntity<>(factService.getAllFacts(), HttpStatus.OK);
    }

    @GetMapping("/{factId}")
    public ResponseEntity<?> findFactBy(@PathVariable Long factId) {
        if (factId == null) {
            log.error("received null in findFactBy");
            return new ResponseEntity<>("factId must be not null", HttpStatus.BAD_REQUEST);
        }
        log.debug("receive in findFactBy " + factId);
        return new ResponseEntity<>(factService.getFactDTOByFactId(factId), HttpStatus.OK);
    }

    @GetMapping("byAgentCode/{agentCode}")
    public ResponseEntity<?> findAllFactsByAgentCode(@PathVariable String agentCode) {
        if (agentCode == null) {
            log.error("received null in findAllFactsByAgentCode");
            return new ResponseEntity<>("agentCode must be not null", HttpStatus.BAD_REQUEST);
        }
        log.debug("receive in findAllFactsByAgentCode " + agentCode);
        return new ResponseEntity<>(factService.getAllFactsByAgentCode(agentCode), HttpStatus.OK);
    }

    @GetMapping("byOperationCode/{opCode}")
    public ResponseEntity<?> findAllFactsByOperationCode(@PathVariable String opCode) {
        if (opCode == null) {
            log.error("received null in findAllFactsByOperationCode");
            return new ResponseEntity<>("opCode must be not null", HttpStatus.BAD_REQUEST);
        }
        log.debug("receive in findAllFactsByOperationCode " + opCode);
        return new ResponseEntity<>(factService.getAllFactsByOperationCode(opCode), HttpStatus.OK);
    }


}
