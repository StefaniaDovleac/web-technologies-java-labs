package com.unibuc.lab10.repository;

import com.unibuc.lab10.domain.Address;
import com.unibuc.lab10.domain.Employee;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Optional;

@Repository
public class EmployeeRepository {

    private final JdbcTemplate jdbcTemplate;
    private final AddressRepository addressRepository;

    public EmployeeRepository(JdbcTemplate jdbcTemplate, AddressRepository addressRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.addressRepository = addressRepository;
    }

    public Employee save(Employee employee) {
        String sql = "INSERT INTO employees VALUES(?, ?, ?, ?, ?)";
        KeyHolder holder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, null);
            preparedStatement.setString(2, employee.getName());
            preparedStatement.setDouble(3, employee.getSalary());
            preparedStatement.setLong(4, addressRepository.findBy(employee.getAddress().getId())
                    .stream().findFirst()
                    .orElseThrow(() -> new RuntimeException("Not found!")).getId()
            );
            preparedStatement.setBoolean(5, employee.getPromoted());
            return preparedStatement;
        }, holder);

        employee.setId(holder.getKey().longValue());
        return employee;
    }

    public Optional<Employee> findBy(Long id) {
        //todo: TEMA: -> folositi JOIN pentru a obtine informatiile din cele doua tabele
//        String sql = " SELECT * FROM employees WHERE id = ?";
//        RowMapper<Employee> mapper = (resultSet, rowNumber) ->
//                new Employee(resultSet.getLong("id"),
//                        resultSet.getString("name"),
//                        resultSet.getDouble("salary"),
//                        addressRepository.findBy(resultSet.getLong("address_id")).get(),
//                        resultSet.getBoolean("promoted"));

        String sql = " SELECT * FROM employees e JOIN addresses a ON e.address_id = a.id";
        RowMapper<Employee> mapper = (resultSet, rowNumber) ->
                new Employee(resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("salary"),
                        new Address(resultSet.getLong("id"), resultSet.getString("city"), resultSet.getString("street_name")),
                        resultSet.getBoolean("promoted"));

        return jdbcTemplate.query(sql, mapper, id).stream().findFirst();
    }

    public void updatePromotion(Long id) {
        String sql = "UPDATE employees SET promoted = 1 WHERE id = ? AND promoted = 0";
        int affectedRows = jdbcTemplate.update(sql, id);
        if (affectedRows == 0) {
            throw new RuntimeException("No rows affected!");
        }
    }

    public void updateSalary(Long id, String name) {
        String sql = "UPDATE employees SET salary = salary + 0.1 * salary WHERE id = ? and name = ? ";
        int affectedRows = jdbcTemplate.update(sql, id, name);
        if (affectedRows == 0) {
            throw new RuntimeException("No rows affected!");
        }
    }

}
