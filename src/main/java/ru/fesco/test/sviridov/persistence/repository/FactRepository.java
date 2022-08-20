package ru.fesco.test.sviridov.persistence.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.fesco.test.sviridov.persistence.model.Agent;
import ru.fesco.test.sviridov.persistence.model.Fact;
import ru.fesco.test.sviridov.persistence.model.Operation;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface FactRepository extends JpaRepository<Fact, Long> {

    Optional<Fact> findByFactId(Long factId);
    List<Fact> findAllByAgent(Agent agent);
    List<Fact> findAllByOperation(Operation operation);
    @Query(value = "SELECT SUM(Fact.Fact_Sum_Linked) FROM Fact", nativeQuery = true)
    BigDecimal totalTest ();

}
