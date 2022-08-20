package ru.fesco.test.sviridov.web.dto;

import java.math.BigDecimal;
import java.util.Objects;

public record LinkDTO (Long linkPlanId, Long linkFactId, BigDecimal linkSum) {
    public LinkDTO{
        Objects.requireNonNull(linkPlanId);
        Objects.requireNonNull(linkFactId);
        Objects.requireNonNull(linkSum);
    }
}
