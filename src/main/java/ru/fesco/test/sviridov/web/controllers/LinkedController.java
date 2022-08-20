package ru.fesco.test.sviridov.web.controllers;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.fesco.test.sviridov.service.LinkedService;

@RestController
@RequestMapping(value = "/api/v1/linked")
@RequiredArgsConstructor
@Slf4j
public class LinkedController {

    private final LinkedService linkedService;


    @GetMapping("byPlaned/{planId}")
    public ResponseEntity<?> findAllLinksByPlanedId(@PathVariable Long planId) {
        if (planId == null) {
            log.error("received null in findAllLinksByPlanedId");
            return new ResponseEntity<>("planId must be not null", HttpStatus.BAD_REQUEST);
        }
        log.debug("receive in findAllLinksByPlanedId " + planId);
        return new ResponseEntity<>(linkedService.getLinksByPlanId(planId), HttpStatus.OK);
    }

    @GetMapping("byFact/{planId}")
    public ResponseEntity<?> findAllLinksByFactId(@PathVariable Long factId) {
        if (factId == null) {
            log.error("received null in findAllLinksByFactId");
            return new ResponseEntity<>("planId must be not null", HttpStatus.BAD_REQUEST);
        }
        log.debug("receive in findAllLinksByFactId " + factId);
        return new ResponseEntity<>(linkedService.getLinksByFactId(factId), HttpStatus.OK);
    }

    @GetMapping("/{planId}&{factId}")
    public ResponseEntity<?> findAllLinksByFactIdAndPlanedId(@PathVariable Long planId, @PathVariable Long factId) {
        if (factId == null || planId == null) {
            log.error("received null in findAllLinksByFactIdAndPlanedId");
            return new ResponseEntity<>("planId and factId must be not null", HttpStatus.BAD_REQUEST);
        }
        log.debug("receive in findAllLinksByFactIdAndPlanedId planId" + planId + " factId " + factId);
        return new ResponseEntity<>(linkedService.getLinksByPlanIdAndFactId(planId, factId), HttpStatus.OK);
    }

    @PutMapping("/{planId}&{factId}")
    public ResponseEntity<?> putNewLink(@PathVariable Long planId, @PathVariable Long factId) {
        if (factId == null || planId == null) {
            log.error("received null in putNewLink");
            return new ResponseEntity<>("planId and factId must be not null", HttpStatus.BAD_REQUEST);
        }
        log.debug("receive in putNewLink planId" + planId + " factId " + factId);
        return new ResponseEntity<>(linkedService.putNewLink(planId, factId), HttpStatus.OK);
    }

    @DeleteMapping("/{planId}&{factId}")
    public ResponseEntity<?> delLink(@PathVariable Long planId, @PathVariable Long factId) {
        if (factId == null || planId == null) {
            log.error("received null in delLink");
            return new ResponseEntity<>("planId must be not null", HttpStatus.BAD_REQUEST);
        }
        log.debug("receive in delLink planId" + planId + " factId " + factId);
        linkedService.delLink(planId, factId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
