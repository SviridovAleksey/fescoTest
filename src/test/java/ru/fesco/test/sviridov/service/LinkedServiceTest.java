package ru.fesco.test.sviridov.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.fesco.test.sviridov.persistence.model.*;
import ru.fesco.test.sviridov.persistence.repository.LinkRepository;
import ru.fesco.test.sviridov.web.dto.LinkDTO;
import ru.fesco.test.sviridov.web.mapping.MappingDTO;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class LinkedServiceTest {

    @Autowired
    private MappingDTO mappingDTO;

    @MockBean
    private FactService factService;

    @MockBean
    private LinkRepository linkRepository;

    @MockBean
    private PlannedService plannedService;

    @Autowired
    private LinkedService linkedService;

    @Test
    public void getLinksByPlanIdTest() {

        Long testPlanId = 42L;
        Planed planed = new Planed();
        planed.setPlanId(testPlanId);

        Mockito.when(plannedService.getPlanByPlanId(testPlanId)).thenReturn(planed);
        Mockito.when(linkRepository.findAllByLinkPlan(planed)).thenReturn(
                List.of(createTestLink(42L, 1L), createTestLink(42L, 2L))
        );

        assertEquals(
                List.of(createTestLinkDto(42L, 1L), createTestLinkDto(42L, 2L)),
                linkedService.getLinksByPlanId(testPlanId)
        );
    }

    @Test
    public void getLinksByFactIdTest() {

        Long testFactId = 42L;
        Fact fact = new Fact();
        fact.setFactId(testFactId);

        Mockito.when(factService.getFactByFactId(testFactId)).thenReturn(fact);
        Mockito.when(linkRepository.findAllByLinkFact(fact)).thenReturn(
                List.of(createTestLink(1L, 42L), createTestLink(2L, 42L))
        );

        assertEquals(
                List.of(createTestLinkDto(1L, 42L), createTestLinkDto(2L, 42L)),
                linkedService.getLinksByFactId(testFactId)
        );
    }

    private Link createTestLink(Long planId, Long factId) {

        Planed planed = new Planed();
        planed.setPlanId(planId);

        Fact fact = new Fact();
        fact.setFactId(factId);

        Link link = new Link();
        link.setLinkPlan(planed);
        link.setLinkFact(fact);
        link.setLinkSum(new BigDecimal(planId + factId));

        return link;
    }

    private LinkDTO createTestLinkDto(Long planId, Long factId) {
        return new LinkDTO(planId, factId, new BigDecimal(planId + factId));
    }

}