package ru.fesco.test.sviridov.persistence.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "Agent")
@NoArgsConstructor
public class Agent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Agent_code")
    private String agentCode;

    @Column(name = "Agent_Name")
    private String agentName;


}
