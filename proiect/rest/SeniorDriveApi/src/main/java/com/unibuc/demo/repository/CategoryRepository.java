package com.unibuc.demo.repository;

import com.unibuc.demo.domain.Category;
import com.unibuc.demo.domain.Directory;
import com.unibuc.demo.domain.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class CategoryRepository {
    public final JdbcTemplate jdbcTemplate;

    @Autowired
    public CategoryRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<Category> getById(Integer id) {
        String sql = "SELECT * FROM categories WHERE id = ?";
        RowMapper<Category> mapper = ((resultSet, rowNumber) ->
                new Category(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description")
                ));
        return jdbcTemplate.query(sql, mapper, id).stream()
                .filter(category -> category.getId().equals(id))
                .findFirst();
    }

    public List<Category> getAll() {
        String sql = "SELECT * FROM categories";
        RowMapper<Category> mapper = ((resultSet, rowNumber) ->
                new Category(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description")
                ));
        return jdbcTemplate.query(sql, mapper).stream().collect(Collectors.toList());
    }

    public Category save(Category category) {
        String sql = "INSERT INTO categories VALUES(?, ?, ?)";
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, null);
            preparedStatement.setString(2, category.getName());
            preparedStatement.setObject(3, category.getDescription());
            return preparedStatement;
        }, holder);
        category.setId(holder.getKey().intValue());
        return category;
    }

    public Category update(Category category) {
        String sql = "UPDATE categories SET name = ?, description = ? WHERE id = ?";
        int affectedRows = jdbcTemplate.update(sql, category.getName(), category.getDescription(), category.getId());
        if (affectedRows == 1) {
            return category;
        }
        return null;
    }

    public Boolean delete(Integer id) {
        String sql = "DELETE FROM categories WHERE id = ?";
        int affectedRows =  jdbcTemplate.update(sql, id);
        return affectedRows == 1;
    }
}
