package ru.fesco.test.sviridov.persistence.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "Fact")
@NoArgsConstructor
public class Fact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Fact_Id")
    private Long factId;

    @ManyToOne
    @JoinColumn(name = "Fact_Agent_code")
    private Agent agent;

    @ManyToOne
    @JoinColumn(name = "Fact_Op_Code")
    private Operation operation;

    @Column(name = "Fact_Sum")
    private BigDecimal factSum;

    @Column(name = "Fact_Sum_Linked")
    private BigDecimal factSumLinked;
}
