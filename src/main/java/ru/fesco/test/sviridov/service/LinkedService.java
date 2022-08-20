package ru.fesco.test.sviridov.service;

import org.springframework.transaction.annotation.Transactional;
import ru.fesco.test.sviridov.error_handling.InvalidControlSumException;
import ru.fesco.test.sviridov.persistence.model.Fact;
import ru.fesco.test.sviridov.persistence.model.Link;
import ru.fesco.test.sviridov.persistence.model.Planed;
import ru.fesco.test.sviridov.web.dto.LinkDTO;

import java.math.BigDecimal;
import java.util.List;

public interface LinkedService {
    List<LinkDTO> getLinksByPlanId(Long planId);

    List<LinkDTO> getLinksByFactId(Long factId);

    List<LinkDTO> getLinksByPlanIdAndFactId(Long planId, Long factId);

    @Transactional
    LinkDTO putNewLink(Long planId, Long factId);

    default void chekSum(BigDecimal linkSum, BigDecimal planLinkSum, BigDecimal factLinkSum) {
        if (linkSum.compareTo(addSum(planLinkSum, factLinkSum)) != 0) {
            throw new InvalidControlSumException("the amount of connections exceeds the amount of expenses");
        }
    }

    default BigDecimal addSum(BigDecimal one, BigDecimal two) {
        if (one == null) {
            one = new BigDecimal(0);
        }
        if (two == null) {
            two = new BigDecimal(0);
        }
        return one.add(two);
    }

    @Transactional
    void delLink(Long planId, Long factId);

    Link findLinkByPlanAndFact(Planed planed, Fact fact);
}
