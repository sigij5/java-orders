package com.lambdaschool.orders.controllers;


import com.lambdaschool.orders.models.Agent;
import com.lambdaschool.orders.services.AgentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/agents")
public class AgentController {
    @Autowired
    private AgentServices agentServices;

    @GetMapping(value = "/agent/{agentid}", produces = "application/json")
    public ResponseEntity<?> findAgentById(@PathVariable long agentid){
        Agent myAgent = agentServices.findAgentById(agentid);
        return new ResponseEntity<>(myAgent, HttpStatus.OK);
    }
}
