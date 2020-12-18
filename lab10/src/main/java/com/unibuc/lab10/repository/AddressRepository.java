package com.unibuc.lab10.repository;

import com.unibuc.lab10.domain.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Optional;

@Repository
public class AddressRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AddressRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Address save(Address address) {
        String sql = "INSERT INTO addresses VALUES(?, ?, ?)";
        KeyHolder holder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, null);
            preparedStatement.setString(2, address.getCity());
            preparedStatement.setString(3, address.getStreetName());
            return preparedStatement;
        }, holder);

        address.setId(holder.getKey().longValue());
        return address;
    }

    public Optional<Address> findBy(Long id) {
        String sql = " SELECT * FROM addresses WHERE id = ?";
        RowMapper<Address> mapper = (resultSet, rowNumber) ->
                new Address(resultSet.getLong("id"),
                        resultSet.getString("city"),
                        resultSet.getString("street_name"));

        return jdbcTemplate.query(sql, mapper, id).stream().findFirst();
    }
}
