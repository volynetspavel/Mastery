package com.godel.mastery.consumer;

import com.godel.mastery.dto.EmployeeDto;
import com.godel.mastery.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Consumer of messages from message service.
 */
@Component
@Slf4j
public class ActiveMQConsumer {

    private final EmployeeService employeeService;

    @Autowired
    public ActiveMQConsumer(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @JmsListener(destination = "${activemq.queue}")
    public void messageListener(EmployeeDto employeeDto) {
        employeeService.insert(employeeDto);
        log.debug("Message received! {}", employeeDto);
    }
}
