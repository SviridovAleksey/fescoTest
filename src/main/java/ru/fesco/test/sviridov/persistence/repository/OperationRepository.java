package ru.fesco.test.sviridov.persistence.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fesco.test.sviridov.persistence.model.Agent;
import ru.fesco.test.sviridov.persistence.model.Operation;

import java.util.Optional;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {

    Optional<Operation> findByopCode(String opCode);

}
