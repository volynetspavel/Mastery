package com.godel.mastery.dao;

import com.godel.mastery.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * This class is a basic class of employee dao-layer for interacting with database.
 */
public interface EmployeeDao extends JpaRepository<Employee, Integer> {

    Optional<Employee> findFirstByFirstNameAndLastName(String firstName, String lastName);

}
