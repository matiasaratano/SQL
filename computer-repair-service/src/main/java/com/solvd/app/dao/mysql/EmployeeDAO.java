package com.solvd.app.dao.mysql;

import com.solvd.app.dao.IEmployeeDAO;
import com.solvd.app.models.Employee;
import com.solvd.app.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO extends MySQLDAO implements IEmployeeDAO {

    private static final Logger LOGGER = LogManager.getLogger(EmployeeDAO.class);
    private final static String GET_EMPLOYEE = "Select * from RepairService.Employees where EmployeeID=?";
    private final static String GET_ALL_EMPLOYEES = "SELECT * FROM RepairService.Employees";
    private final static String CREATE_EMPLOYEE = "INSERT INTO Employees (FirstName, LastName, Address, Phone, Sector, HireDate, Salary) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private final static String UPDATE_EMPLOYEE = "UPDATE RepairService.Employees SET FirstName = ?, LastName=?, Address = ?, Phone=?, Sector=?, HireDate=?, Salary=? WHERE EmployeeID= ?";
    private final static String DELETE_EMPLOYEE = "DELETE FROM RepairService.Employees WHERE EmployeeId= ?";
    private final static String EMPLOYEE_BY_REPAIR = "SELECT employees.* FROM employees INNER JOIN repairs ON employees.employeeId = repairs.employeeId WHERE repairs.RepairID = ?";
    private final Connection connection;

    public EmployeeDAO() throws SQLException {
        this.connection = ConnectionPool.getInstance().getConnection();
    }

    @Override
    public Employee getEntityById(int id) throws SQLException {
        Employee employee = new Employee();
        try (PreparedStatement ps = connection.prepareStatement(GET_EMPLOYEE)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                employee.setId(rs.getInt("employeeID"));
                employee.setFirstName(rs.getString("FirstName"));
                employee.setLastName(rs.getString("LastName"));
                employee.setAddress(rs.getString("Address"));
                employee.setPhone(rs.getString("Phone"));
                employee.setSector(rs.getString("Sector"));
                employee.setHireDate(rs.getString("HireDate"));
                employee.setSalary(rs.getInt("Salary"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employee;
    }

    @Override
    public Employee createEntity(Employee entity) {
        try {
            PreparedStatement statement = connection.prepareStatement(CREATE_EMPLOYEE);

            statement.setString(1, entity.getEmployeeFirstName());
            statement.setString(2, entity.getEmployeeLastName());
            statement.setString(3, entity.getEmployeeAddress());
            statement.setString(4, entity.getEmployeePhone());
            statement.setString(5, entity.getEmployeeSector());
            statement.setString(6, entity.getEmployeeHireDate());
            statement.setInt(7, entity.getEmployeeSalary());
            statement.executeUpdate();
            LOGGER.info("Employee created.");
            LOGGER.info(entity);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return entity;
    }

    @Override
    public void updateEntity(Employee entity) {
        LOGGER.info("Updating employee with id " + entity.getEmployeeId() + ".");
        try {
            PreparedStatement statement = connection.prepareStatement(UPDATE_EMPLOYEE);

            statement.setString(1, entity.getEmployeeFirstName());
            statement.setString(2, entity.getEmployeeLastName());
            statement.setString(3, entity.getEmployeeAddress());
            statement.setString(4, entity.getEmployeePhone());
            statement.setString(5, entity.getEmployeeSector());
            statement.setString(6, entity.getEmployeeHireDate());
            statement.setInt(7, entity.getEmployeeSalary());
            statement.setInt(8, entity.getEmployeeId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void removeById(int id) {
        LOGGER.info("Deleting employee with id " + id + ".");
        try {
            PreparedStatement statement = connection.prepareStatement(DELETE_EMPLOYEE);

            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }

    }

    @Override
    public List<Employee> findAll() {
        LOGGER.info("Finding all Employees.");
        List<Employee> employees = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(GET_ALL_EMPLOYEES);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
                employees.add(new Employee(
                        resultSet.getInt("employeeId"),
                        resultSet.getString("FirstName"),
                        resultSet.getString("LastName"),
                        resultSet.getString("Address"),
                        resultSet.getString("Phone"),
                        resultSet.getString("Sector"),
                        resultSet.getString("HireDate"),
                        resultSet.getInt("Salary")
                ));
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return employees;
    }

    @Override
    public ArrayList<Employee> getEmployeesByRepairId(int repairId) {
        ArrayList<Employee> employees = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    EMPLOYEE_BY_REPAIR);
            statement.setInt(1, repairId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setId(resultSet.getInt("EmployeeID"));
                employee.setFirstName(resultSet.getString("FirstName"));
                employee.setLastName(resultSet.getString("LastName"));
                employee.setAddress(resultSet.getString("Address"));
                employee.setPhone(resultSet.getString("Phone"));
                employee.setSector(resultSet.getString("Sector"));
                employee.setHireDate(resultSet.getString("HireDate"));
                employee.setSalary(resultSet.getInt("Salary"));
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

}
