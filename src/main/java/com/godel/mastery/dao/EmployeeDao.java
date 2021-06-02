package com.godel.mastery.dao;

import com.godel.mastery.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This class is a basic class of employee dao-layer for interacting with database.
 */
@Repository
public interface EmployeeDao extends JpaRepository<Employee, Integer> {
}
