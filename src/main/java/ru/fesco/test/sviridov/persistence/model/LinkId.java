package ru.fesco.test.sviridov.persistence.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class LinkId implements Serializable {

    private Planed linkPlan;
    private Fact linkFact;
    private BigDecimal linkSum;

}
