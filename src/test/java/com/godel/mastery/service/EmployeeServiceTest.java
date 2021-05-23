package com.godel.mastery.service;

import com.godel.mastery.dao.impl.EmployeeDaoImpl;
import com.godel.mastery.dto.EmployeeDto;
import com.godel.mastery.exception.ResourceNotFoundException;
import com.godel.mastery.mapper.Mapper;
import com.godel.mastery.model.Employee;
import com.godel.mastery.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @InjectMocks
    private EmployeeServiceImpl employeeService;
    @Mock
    private EmployeeDaoImpl employeeDao;
    @Mock
    private Mapper employeeMapper;


    @DisplayName("Testing method insert() on positive result")
    @Test
    void insertSuccessTest() {
        Integer id = 7;
        String firstName = "Max";
        String lastName = "Donnel";
        Integer departmentId = 5;
        String jobTitle = "manager";
        String gender = "male";
        LocalDate dateOfBirth = LocalDate.parse("1992-08-12");

        EmployeeDto employeeDto = createEmployeeDto(firstName, lastName, departmentId, jobTitle, gender, dateOfBirth);
        Employee employee = createEmployee(firstName, lastName, departmentId, jobTitle, gender, dateOfBirth);

        when(employeeMapper.toEntity(employeeDto)).thenReturn(employee);
        employee.setEmployeeId(id);
        when(employeeDao.insert(employee)).thenReturn(employee);
        employeeDto.setId(id);

        when(employeeMapper.toDto(employee)).thenReturn(employeeDto);

        EmployeeDto employeeAfterInsert = employeeService.insert(employeeDto);
        assertEquals(employeeDto, employeeAfterInsert);
    }

    @DisplayName("Testing method update() on positive result")
    @Test
    void updateSuccessTest() {
        Integer idUpdated = 7;
        Integer departmentIdUpdated = 5;
        String jobTitleUpdated = "manager";

        EmployeeDto employeeDtoUpdated = createEmployeeDto(null, null, departmentIdUpdated, jobTitleUpdated, null, null);
        employeeDtoUpdated.setId(idUpdated);
        Employee employeeUpdated = createEmployee(null, null, departmentIdUpdated, jobTitleUpdated, null, null);


        Integer id = 7;
        String firstName = "Max";
        String lastName = "Donnel";
        Integer departmentId = 3;
        String jobTitle = "developer";
        String gender = "male";
        LocalDate dateOfBirth = LocalDate.parse("1992-08-12");

        Employee employeeFromDb = createEmployee(firstName, lastName, departmentId, jobTitle, gender, dateOfBirth);
        EmployeeDto employeeDto = createEmployeeDto(firstName, lastName, departmentIdUpdated, jobTitleUpdated, gender, dateOfBirth);
        employeeDto.setId(idUpdated);

        when(employeeDao.findById(id)).thenReturn(employeeFromDb);
        when(employeeMapper.toEntity(employeeDtoUpdated)).thenReturn(employeeUpdated);

        when(employeeDao.findById(id)).thenReturn(employeeFromDb);
        when(employeeMapper.toDto(employeeFromDb)).thenReturn(employeeDto);

        EmployeeDto employeeDtoAfterUpdate = employeeService.update(employeeDtoUpdated);

        verify(employeeDao, times(1)).update(employeeUpdated);
        assertEquals(employeeDto, employeeDtoAfterUpdate);

    }

    @DisplayName("Testing method update() on negative result")
    @Test
    void updateThrowsExceptionTest() {
        int id = 5;
        EmployeeDto employeeDtoUpdated = new EmployeeDto();
        employeeDtoUpdated.setId(id);

        when(employeeDao.findById(id)).thenReturn(null);

        assertThrows(ResourceNotFoundException.class,
                () -> employeeService.update(employeeDtoUpdated));
    }

    @DisplayName("Testing method delete() by id of employee on positive result")
    @Test
    void deleteSuccessTest() {
        int id = 3;
        Employee employee = new Employee();
        employee.setEmployeeId(id);
        when(employeeDao.findById(id)).thenReturn(employee);

        employeeService.delete(id);
        verify(employeeDao, times(1)).delete(id);
    }

    @DisplayName("Testing method delete() by id of employee on negative result")
    @Test
    void deleteByIdThrowsExceptionTest() {
        int id = 5;
        when(employeeDao.findById(id)).thenReturn(null);

        assertThrows(ResourceNotFoundException.class,
                () -> employeeService.delete(id));
    }

    @DisplayName("Testing method findById() on positive result")
    @Test
    void findByIdSuccessTest() {
        Integer id = 7;
        String firstName = "Max";
        String lastName = "Donnel";
        Integer departmentId = 5;
        String jobTitle = "manager";
        String gender = "male";
        LocalDate dateOfBirth = LocalDate.parse("1992-08-12");

        EmployeeDto employeeDto = createEmployeeDto(firstName, lastName, departmentId, jobTitle, gender, dateOfBirth);
        Employee employee = createEmployee(firstName, lastName, departmentId, jobTitle, gender, dateOfBirth);

        when(employeeDao.findById(id)).thenReturn(employee);
        when(employeeMapper.toDto(employee)).thenReturn(employeeDto);

        EmployeeDto expectedDto = employeeService.findById(id);

        assertEquals(expectedDto, employeeDto);
    }

    @DisplayName("Testing method findById() on exception")
    @Test
    void findByIdThrowsExceptionTest() {
        int id = 10;
        when(employeeDao.findById(id)).thenReturn(null);

        assertThrows(ResourceNotFoundException.class,
                () -> employeeService.findById(id));
    }

    @DisplayName("Testing method findAll() on positive result")
    @Test
    void findAllSuccessTest() {
        Integer id1 = 1;
        String firstName1 = "Max";
        String lastName1 = "Donnel";
        Integer departmentId1 = 3;
        String jobTitle1 = "manager";
        String gender1 = "male";
        LocalDate dateOfBirth1 = LocalDate.parse("1992-08-12");

        EmployeeDto employeeDto1 = createEmployeeDto(firstName1, lastName1, departmentId1, jobTitle1, gender1, dateOfBirth1);
        employeeDto1.setId(id1);
        Employee employee1 = createEmployee(firstName1, lastName1, departmentId1, jobTitle1, gender1, dateOfBirth1);
        employee1.setEmployeeId(id1);

        Integer id2 = 2;
        String firstName2 = "Elena";
        String lastName2 = "Rico";
        Integer departmentId2 = 7;
        String jobTitle2 = "hr";
        String gender2 = "female";
        LocalDate dateOfBirth2 = LocalDate.parse("1993-05-20");

        EmployeeDto employeeDto2 = createEmployeeDto(firstName2, lastName2, departmentId2, jobTitle2, gender2, dateOfBirth2);
        employeeDto2.setId(id2);
        Employee employee2 = createEmployee(firstName2, lastName2, departmentId2, jobTitle2, gender2, dateOfBirth2);
        employee2.setEmployeeId(id2);

        List<Employee> employeeList = Arrays.asList(employee1, employee2);
        List<EmployeeDto> employeeDtoList = Arrays.asList(employeeDto1, employeeDto2);

        when(employeeDao.findAll()).thenReturn(employeeList);
        when(employeeMapper.toDtoList(employeeList)).thenReturn(employeeDtoList);

        List<EmployeeDto> actualEmployeeDtoList = employeeService.findAll();
        Assertions.assertEquals(employeeDtoList, actualEmployeeDtoList);
    }

    private EmployeeDto createEmployeeDto(String firstName, String lastName, Integer departmentId,
                                          String jobTitle, String gender, LocalDate dateOfBirth) {
        EmployeeDto employeeDto = new EmployeeDto();

        employeeDto.setFirstName(firstName);
        employeeDto.setLastName(lastName);
        employeeDto.setDepartmentId(departmentId);
        employeeDto.setJobTitle(jobTitle);
        employeeDto.setGender(gender);
        employeeDto.setDateOfBirth(dateOfBirth);
        return employeeDto;
    }

    private Employee createEmployee(String firstName, String lastName, Integer departmentId,
                                    String jobTitle, String gender, LocalDate dateOfBirth) {
        Employee employee = new Employee();

        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setDepartmentId(departmentId);
        employee.setJobTitle(jobTitle);
        employee.setGender(gender);
        employee.setDateOfBirth(dateOfBirth);
        return employee;
    }
}
