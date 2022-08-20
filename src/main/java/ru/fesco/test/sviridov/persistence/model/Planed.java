package ru.fesco.test.sviridov.persistence.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "Planed")
@NoArgsConstructor
public class Planed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Plan_Id")
    private Long planId;

    @ManyToOne
    @JoinColumn(name = "Plan_Agent_Code")
    private Agent agent;

    @ManyToOne
    @JoinColumn(name = "Plan_OP_Code")
    private Operation operation;

    @Column(name = "Plan_Sum")
    private BigDecimal planSum;

    @Column(name = "Plan_Sum_Linked")
    private BigDecimal planSumLinked;

}
