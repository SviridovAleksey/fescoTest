package ru.fesco.test.sviridov.web.controllers;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.fesco.test.sviridov.service.PlannedService;

@RestController
@RequestMapping(value = "/api/v1/planned")
@RequiredArgsConstructor
@Slf4j
public class PlannedController {

    private final PlannedService plannedService;


    @GetMapping
    public ResponseEntity<?> findAllPlans() {
        return new ResponseEntity<>(plannedService.getAllPlans(), HttpStatus.OK);
    }

    @GetMapping("/{planId}")
    public ResponseEntity<?> findPlanById(@PathVariable Long planId) {
        if (planId == null) {
            log.error("received null in findPlanById");
            return new ResponseEntity<>("planId must be not null", HttpStatus.BAD_REQUEST);
        }
        log.debug("receive in findPlanById " + planId);
        return new ResponseEntity<>(plannedService.getPlanDTOByPlanId(planId), HttpStatus.OK);
    }

    @GetMapping("byAgentCode/{agentCode}")
    public ResponseEntity<?> findAllPlanedByAgentCode(@PathVariable String agentCode) {
        if (agentCode == null) {
            log.error("received null in findAllPlanedByAgentCode");
            return new ResponseEntity<>("agentCode must be not null", HttpStatus.BAD_REQUEST);
        }
        log.debug("receive in findAllPlanedByAgentCode " + agentCode);
        return new ResponseEntity<>(plannedService.getAllPlannedByAgentCode(agentCode), HttpStatus.OK);
    }

    @GetMapping("byOperationCode/{opCode}")
    public ResponseEntity<?> findAllPlanedByOperationCode(@PathVariable String opCode) {
        if (opCode == null) {
            log.error("received null in findAllPlanedByOperationCode");
            return new ResponseEntity<>("opCode must be not null", HttpStatus.BAD_REQUEST);
        }
        log.debug("receive in findAllPlanedByOperationCode " + opCode);
        return new ResponseEntity<>(plannedService.getAllPlannedByOperationCode(opCode), HttpStatus.OK);
    }


}
