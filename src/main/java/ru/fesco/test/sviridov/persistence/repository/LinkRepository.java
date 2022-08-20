package ru.fesco.test.sviridov.persistence.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.fesco.test.sviridov.persistence.model.Fact;
import ru.fesco.test.sviridov.persistence.model.Link;
import ru.fesco.test.sviridov.persistence.model.Planed;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface LinkRepository extends JpaRepository<Link, Long> {

    List<Link> findAllByLinkPlan(Planed linkPlan);
    List<Link> findAllByLinkFact(Fact linkFact);
    List<Link> findAllByLinkPlanAndLinkFact(Planed linkPlan, Fact linkFact);
    Optional<Link> findByLinkPlanAndLinkFact(Planed linkPlan, Fact linkFact);
    @Query(value = "SELECT SUM(Link.Link_Sum) FROM Link", nativeQuery = true)
    BigDecimal totalTest ();

}
