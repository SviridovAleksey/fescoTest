package ru.fesco.test.sviridov.persistence.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@Table(name = "Operation")
@NoArgsConstructor
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Op_code")
    private String opCode;

    @Column(name = "Op_Name")
    private String opName;
}
