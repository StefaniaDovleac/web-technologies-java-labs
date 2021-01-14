package com.unibuc.demo.repository;

import com.unibuc.demo.domain.User;
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
public class UserRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User save(User user) {
        String sql = "INSERT INTO users (id, userName, email, hashedPassword, isAdmin) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, null);
            preparedStatement.setString(2, user.getUserName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setObject(5, user.getIsAdmin());

            return preparedStatement;
        }, holder);
        user.setId(holder.getKey().intValue());
        return user;
    }

    public Optional<User> findBy(Long id) {
        String query = "SELECT * FROM users WHERE id = ?";
        RowMapper<User> mapper = ((resultSet, i) -> new User(
                resultSet.getInt("id"),
                resultSet.getString("userName"),
                resultSet.getString("email"),
                resultSet.getBoolean("isAdmin"),
                resultSet.getString("email")
        ));
        return jdbcTemplate.query(query, mapper, id).stream().findAny();
    }

    public Optional<User> findByEmail(String email){
        String query = "SELECT * FROM users WHERE email = ?";
        RowMapper<User> mapper = ((resultSet, i) -> new User(
                resultSet.getInt("id"),
                resultSet.getString("userName"),
                resultSet.getString("email"),
                resultSet.getBoolean("isAdmin"),
                resultSet.getString("email")
        ));
        return jdbcTemplate.query(query, mapper, email).stream().findAny();
    }

    public int delete(Integer id){
        String sql = "DELETE FROM users WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
