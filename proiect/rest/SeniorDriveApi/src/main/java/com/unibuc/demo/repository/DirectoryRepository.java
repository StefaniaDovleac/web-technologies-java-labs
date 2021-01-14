package com.unibuc.demo.repository;

import com.unibuc.demo.domain.Directory;
import com.unibuc.demo.domain.File;
import com.unibuc.demo.dto.FileDTO;
import com.unibuc.demo.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.validation.constraints.Size;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class DirectoryRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DirectoryRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Directory> getAll() {
        String sql = "SELECT * FROM directories";
        RowMapper<Directory> mapper = ((resultSet, rowNumber) ->
                new Directory(resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getInt("parentId"),
                        resultSet.getInt("createdBy"),
                        resultSet.getDate("createdOn"),
                        resultSet.getInt("lastModifiedBy"),
                        resultSet.getDate("lastModifiedOn"),
                        resultSet.getInt("categoryId")
                ));
        return jdbcTemplate.query(sql, mapper).stream().collect(Collectors.toList());
    }

    public Optional<Directory> getById(Integer id) {
        String sql = "SELECT * FROM directories WHERE id = ?";
        RowMapper<Directory> mapper = ((resultSet, rowNumber) ->
                new Directory(resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getInt("parentId"),
                        resultSet.getInt("createdBy"),
                        resultSet.getDate("createdOn"),
                        resultSet.getInt("lastModifiedBy"),
                        resultSet.getDate("lastModifiedOn"),
                        resultSet.getInt("categoryId")
                ));
        return jdbcTemplate.query(sql, mapper, id).stream()
                .filter(directory -> directory.getId().equals(id))
                .findFirst();
    }

    public boolean delete(Integer id) {
        String sql = "DELETE FROM directories WHERE id = ?";
        int affectedRows =  jdbcTemplate.update(sql, id);
        return affectedRows == 1;
    }

    public Directory update(Directory directory) {
        String sql = "UPDATE directories SET title = ?, categoryId = ?, parentId = ?, lastModifiedBy = ?, lastModifiedOn = ?  WHERE id = ?";
        int affectedRows = jdbcTemplate.update(sql,directory.getTitle(), directory.getCategoryId(),directory.getParentId(),directory.getLastModifiedBy(),directory.getLastModifiedOn(), directory.getId());
        if(affectedRows == 1){
            return directory;
        }
        return null;
    }

    public Directory save(Directory directory) {
        String sql = "INSERT INTO directories VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, null);
            preparedStatement.setString(2, directory.getTitle());
            preparedStatement.setObject(3, directory.getParentId());
            preparedStatement.setInt(4, directory.getCreatedBy());
            preparedStatement.setObject(5, directory.getCreatedOn());
            preparedStatement.setInt(6, directory.getLastModifiedBy());
            preparedStatement.setObject(7, directory.getLastModifiedOn());
            preparedStatement.setObject(8, directory.getCategoryId());
            return preparedStatement;
        }, holder);
        directory.setId(holder.getKey().intValue());
        return directory;
    }
}
