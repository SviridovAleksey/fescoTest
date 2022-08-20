package ru.fesco.test.sviridov.web.mapping;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.fesco.test.sviridov.persistence.model.*;
import ru.fesco.test.sviridov.web.dto.*;


@Component
@Scope("prototype")
public class MappingDTO {

    public AgentDTO agentMapToDTO(Agent agent) {
        return new AgentDTO(agent.getAgentCode(), agent.getAgentName());
    }

    public FactDTO factMapToDTO(Fact fact) {
        return new FactDTO(fact.getFactId(), fact.getAgent().getAgentName(), fact.getOperation().getOpName(),
                fact.getFactSum(), fact.getFactSumLinked());
    }

    public LinkDTO linkMapToDTO(Link link) {
        return new LinkDTO(link.getLinkPlan().getPlanId(), link.getLinkFact().getFactId(), link.getLinkSum());
    }

    public OperationDTO operationMapToDTO(Operation operation) {
        return new OperationDTO(operation.getOpCode(), operation.getOpName());
    }

    public PlanedDTO planedMapToDTO(Planed planed) {
        return new PlanedDTO(planed.getPlanId(), planed.getAgent().getAgentName(),
                planed.getOperation().getOpName(), planed.getPlanSum(), planed.getPlanSumLinked());
    }

}
