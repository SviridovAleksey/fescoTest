package ru.fesco.test.sviridov.service.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fesco.test.sviridov.error_handling.InvalidControlSumException;
import ru.fesco.test.sviridov.error_handling.ResourceNotFoundException;
import ru.fesco.test.sviridov.persistence.model.Fact;
import ru.fesco.test.sviridov.persistence.model.Link;
import ru.fesco.test.sviridov.persistence.model.Planed;
import ru.fesco.test.sviridov.persistence.repository.LinkRepository;
import ru.fesco.test.sviridov.service.FactService;
import ru.fesco.test.sviridov.service.LinkedService;
import ru.fesco.test.sviridov.service.PlannedService;
import ru.fesco.test.sviridov.web.dto.LinkDTO;
import ru.fesco.test.sviridov.web.mapping.MappingDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class LinkedServiceImp implements LinkedService {

    private final MappingDTO mappingDTO;
    private final LinkRepository linkRepository;
    private final PlannedService plannedService;
    private final FactService factService;

    @Override
    public List<LinkDTO> getLinksByPlanId(Long planId) {
        log.debug("getLinksByPlanId " + planId);
        return linkRepository.findAllByLinkPlan(plannedService.getPlanByPlanId(planId))
                .stream().map(mappingDTO::linkMapToDTO).collect(Collectors.toList());
    }

    @Override
    public List<LinkDTO> getLinksByFactId(Long factId) {
        log.debug("getLinksByFactId " + factId);
        return  linkRepository.findAllByLinkFact(factService.getFactByFactId(factId))
                .stream().map(mappingDTO::linkMapToDTO).collect(Collectors.toList());
    }


    @Override
    public List<LinkDTO> getLinksByPlanIdAndFactId(Long planId, Long factId) {
        log.debug("getLinksByPlanIdAndFactId " + planId + " factId " + factId);
        return linkRepository.findAllByLinkPlanAndLinkFact(plannedService.getPlanByPlanId(planId),
                 factService.getFactByFactId(factId))
                .stream().map(mappingDTO::linkMapToDTO).collect(Collectors.toList());
    }


    @Override
    @Transactional
    public LinkDTO putNewLink(Long planId, Long factId) {
        Planed planed = plannedService.getPlanByPlanId(planId);
        Fact fact = factService.getFactByFactId(factId);
        if (!planed.getAgent().equals(fact.getAgent())) {
            throw  new ResourceNotFoundException("Planed and Fact agents does not match");
        }
        Link link = new Link();
        link.setLinkPlan(planed);
        link.setLinkFact(fact);
        link.setLinkSum(planed.getPlanSum().add(fact.getFactSum()));
        planed.setPlanSumLinked(addSum(planed.getPlanSumLinked(), planed.getPlanSum()));
        fact.setFactSumLinked(addSum(fact.getFactSumLinked(), fact.getFactSum()));
        plannedService.updatePlaned(planed);
        factService.updateFact(fact);
        chekSum(addSum(linkRepository.totalTest(), link.getLinkSum()),
                        plannedService.getTotalPlanSumLink(),
                        factService.getTotalFactSumLink());
        log.debug("put new link " + link);
        return mappingDTO.linkMapToDTO(linkRepository.save(link));
    }


    @Override
    @Transactional
    public void delLink(Long planId, Long factId) {
        Planed planed = plannedService.getPlanByPlanId(planId);
        Fact fact = factService.getFactByFactId(factId);
        planed.setPlanSumLinked(null);
        fact.setFactSumLinked(null);
        plannedService.updatePlaned(planed);
        factService.updateFact(fact);
        log.debug("delLink planId" + planId + "factId " + factId);
        linkRepository.delete(findLinkByPlanAndFact(planed,fact));

    }

    @Override
    public Link findLinkByPlanAndFact(Planed planed, Fact fact) {
        log.debug("findLinkByPlanAndFact plan" + planed + "fact " + fact);
        return linkRepository.findByLinkPlanAndLinkFact(planed, fact).orElseThrow(()
                -> new ResourceNotFoundException("Link not found by planed and fact"));
    }
}
