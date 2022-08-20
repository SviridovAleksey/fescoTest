package ru.fesco.test.sviridov.web.controllers;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.fesco.test.sviridov.service.AgentService;


@RestController
@RequestMapping(value = "/api/v1/agentAgent")
@RequiredArgsConstructor
@Slf4j
public class AgentController {

    private final AgentService agentService;


    @GetMapping
    public ResponseEntity<?> findAllAgent() {
        return new ResponseEntity<>(agentService.getAllAgents(), HttpStatus.OK);
    }

    @GetMapping("/{agentCode}")
    public ResponseEntity<?> findInstructionBy(@PathVariable String agentCode) {
        if (agentCode == null) {
            log.error("received null in findAllAgent");
           return new ResponseEntity<>("agentCode must be not null", HttpStatus.BAD_REQUEST);
        }
        log.debug("receive in findInstructionBy " + agentCode);
        return new ResponseEntity<>(agentService.getAgentDTOByAgentCode(agentCode), HttpStatus.OK);
    }
}
