package ru.fesco.test.sviridov.web.dto;

import java.math.BigDecimal;
import java.util.Objects;

public record FactDTO (Long factId, String agentName, String factOpName,
                       BigDecimal factSum, BigDecimal factSumLinked) {
    public FactDTO{
        Objects.requireNonNull(factId);
        Objects.requireNonNull(agentName);
        Objects.requireNonNull(factOpName);
        Objects.requireNonNull(factSum);
    }
}
