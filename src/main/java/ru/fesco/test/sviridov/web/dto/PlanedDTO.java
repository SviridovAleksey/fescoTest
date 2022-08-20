package ru.fesco.test.sviridov.web.dto;

import java.math.BigDecimal;
import java.util.Objects;

public record PlanedDTO (Long planId, String planAgentName, String planOpName,
                         BigDecimal planSum, BigDecimal planSumLinked) {
    public PlanedDTO{
        Objects.requireNonNull(planId);
        Objects.requireNonNull(planAgentName);
        Objects.requireNonNull(planOpName);
        Objects.requireNonNull(planSum);
    }
}

