package com.godel.mastery.controller;

import com.godel.mastery.dto.EmployeeDto;
import com.godel.mastery.service.Insertable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for handling requests for jms.
 */
@RestController
@RequestMapping("/jms")
public class ActiveMQController {

    private final JmsTemplate jmsTemplate;

    @Value("${activemq.queue}")
    private String queue;

    @Autowired
    public ActiveMQController(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public EmployeeDto sendMessage(@RequestBody @Validated(Insertable.class) EmployeeDto employeeDto) {
        jmsTemplate.convertAndSend(queue, employeeDto);
        return employeeDto;
    }
}
