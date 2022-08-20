package ru.fesco.test.sviridov.persistence.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@Entity
@IdClass(LinkId.class)
@Table(name = "Link")
@NoArgsConstructor
public class Link {

    @Id
    @ManyToOne
    @JoinColumn(name = "Link_Plan_Id")
    private Planed linkPlan;

    @Id
    @ManyToOne
    @JoinColumn(name = "Link_Fact_Id")
    private Fact linkFact;

    @Column(name = "Link_Sum")
    private BigDecimal linkSum;

}
