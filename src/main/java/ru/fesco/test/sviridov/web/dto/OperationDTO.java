package ru.fesco.test.sviridov.web.dto;

import java.util.Objects;

public record OperationDTO (String opCode, String opName) {
    public OperationDTO{
        Objects.requireNonNull(opCode);
        Objects.requireNonNull(opName);
    }
}
