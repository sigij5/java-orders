package com.lambdaschool.orders.services;


import com.lambdaschool.orders.models.Agent;
import com.lambdaschool.orders.models.Customer;
import com.lambdaschool.orders.repositories.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service(value = "agentServices")
public class AgentServicesImpl implements AgentServices{
    @Autowired
    AgentRepository agentrepos;

    @Override
    public Agent save(Agent agent){
        return agentrepos.save(agent);
    }

    @Override
    public Agent findAgentById(long id) {
        return agentrepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Agent " + id + " Not Found"));
    }

}
