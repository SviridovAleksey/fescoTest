package ru.fesco.test.sviridov.web.dto;

import java.util.Objects;

public record AgentDTO (String agentCode, String agentName) {
    public AgentDTO{
        Objects.requireNonNull(agentCode);
        Objects.requireNonNull(agentName);
    }
}
