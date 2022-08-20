package ru.fesco.test.sviridov.persistence.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.fesco.test.sviridov.persistence.model.Agent;
import ru.fesco.test.sviridov.persistence.model.Operation;
import ru.fesco.test.sviridov.persistence.model.Planed;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface PlanedRepository extends JpaRepository<Planed, Long> {

    Optional<Planed> findByplanId(Long planId);
    List<Planed> findAllByAgent(Agent agent);
    List<Planed> findAllByOperation(Operation operation);
    @Query(value = "SELECT SUM(Planed.Plan_Sum_Linked) FROM Planed", nativeQuery = true)
    BigDecimal totalTest ();


}
