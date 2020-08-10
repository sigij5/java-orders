package com.lambdaschool.orders.services;


import com.lambdaschool.orders.models.Agent;
import com.lambdaschool.orders.repositories.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "agentServices")
public class AgentServicesImpl implements AgentServices{
    @Autowired
    AgentRepository agentrepos;

    @Override
    public Agent save(Agent agent){
        return agentrepos.save(agent);
    }
}
