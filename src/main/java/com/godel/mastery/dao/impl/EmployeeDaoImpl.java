package com.godel.mastery.dao.impl;

import com.godel.mastery.constant.EmployeeTableColumn;
import com.godel.mastery.dao.EmployeeDao;
import com.godel.mastery.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This class is an implementation of EmployeeDao.
 */
@Repository
public class EmployeeDaoImpl implements EmployeeDao {

    private static final String SQL_INSERT_EMPLOYEE = "INSERT INTO employee " +
            "(first_name, last_name, department_id, job_title, gender, date_of_birth ) " +
            "VALUES (:first_name, :last_name, :department_id, :job_title, :gender, :date_of_birth);";
    private static final String SQL_UPDATE_EMPLOYEE = "UPDATE employee SET " +
            "first_name=COALESCE(?,first_name), " +
            "last_name=COALESCE(?,last_name), " +
            "department_id=COALESCE(?,department_id), " +
            "job_title =COALESCE(?,job_title), " +
            "gender=COALESCE(?,gender), " +
            "date_of_birth=COALESCE(?,date_of_birth) WHERE employee_id = ?;";
    private static final String SQL_DELETE_EMPLOYEE_BY_ID = "DELETE FROM employee WHERE employee_id = ?;";
    private static final String SQL_FIND_EMPLOYEE_BY_ID =
            "SELECT employee_id, first_name, last_name, department_id, job_title, gender, date_of_birth " +
                    "FROM employee WHERE employee_id = ?;";
    private static final String SQL_FIND_ALL_EMPLOYEES = "SELECT employee_id, first_name, last_name, " +
            "department_id, job_title, gender, date_of_birth FROM employee";


    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public EmployeeDaoImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Employee insert(Employee newEmployee) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue(EmployeeTableColumn.FIRST_NAME, newEmployee.getFirstName())
                .addValue(EmployeeTableColumn.LAST_NAME, newEmployee.getLastName())
                .addValue(EmployeeTableColumn.DEPARTMENT_ID, newEmployee.getDepartmentId())
                .addValue(EmployeeTableColumn.JOB_TITLE, newEmployee.getJobTitle())
                .addValue(EmployeeTableColumn.GENDER, newEmployee.getGender())
                .addValue(EmployeeTableColumn.DATE_OF_BIRTH, newEmployee.getDateOfBirth());

        namedParameterJdbcTemplate.update(SQL_INSERT_EMPLOYEE, parameterSource, keyHolder);
        newEmployee.setEmployeeId(keyHolder.getKey().intValue());
        return newEmployee;
    }

    @Override
    public void update(Employee employee) {
        jdbcTemplate.update(SQL_UPDATE_EMPLOYEE,
                employee.getFirstName(), employee.getLastName(), employee.getDepartmentId(),
                employee.getJobTitle(), employee.getGender(), employee.getDateOfBirth(),
                employee.getEmployeeId());
    }

    @Override
    public void delete(Integer id) {
        jdbcTemplate.update(SQL_DELETE_EMPLOYEE_BY_ID, id);
    }

    @Override
    public Employee findById(Integer id) {
        return jdbcTemplate.query(SQL_FIND_EMPLOYEE_BY_ID, new BeanPropertyRowMapper<>(Employee.class), id)
                .stream()
                .findAny()
                .orElse(null);
    }

    @Override
    public List<Employee> findAll() {
        return jdbcTemplate.query(SQL_FIND_ALL_EMPLOYEES, new BeanPropertyRowMapper<>(Employee.class));
    }
}
