package com.unibuc.demo.repository;

import com.unibuc.demo.domain.Directory;
import com.unibuc.demo.domain.File;
import com.unibuc.demo.exceptions.EntityNotFoundException;
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
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class FileRepository {
    private List<File> files = new ArrayList<>();

    private final JdbcTemplate jdbcTemplate;

    public FileRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<File> getAll() {
        String sql = "SELECT * FROM files";
        RowMapper<File> mapper = ((resultSet, rowNumber) ->
                new File(resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getInt("size"),
                        resultSet.getInt("parentId"),
                        resultSet.getInt("createdBy"),
                        resultSet.getDate("createdOn"),
                        resultSet.getInt("lastModifiedBy"),
                        resultSet.getDate("lastModifiedOn"),
                        resultSet.getString("content"),
                        resultSet.getBoolean("isPublic"),
                        resultSet.getString("fileUri")
                ));
        return jdbcTemplate.query(sql, mapper).stream().collect(Collectors.toList());

    }

    public Optional<File> getById(Integer id) {
        String sql = "SELECT * FROM files WHERE id = ?";
        RowMapper<File> mapper = ((resultSet, rowNumber) ->
                new File(resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getInt("size"),
                        resultSet.getInt("parentId"),
                        resultSet.getInt("createdBy"),
                        resultSet.getDate("createdOn"),
                        resultSet.getInt("lastModifiedBy"),
                        resultSet.getDate("lastModifiedOn"),
                        resultSet.getString("content"),
                        resultSet.getBoolean("isPublic"),
                        resultSet.getString("fileUri")
                ));
        return jdbcTemplate.query(sql, mapper, id).stream()
                .filter(file -> file.getId().equals(id))
                .findFirst();
    }

    public File save(File file) {
        String sql = "INSERT INTO files VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, null);
            preparedStatement.setString(2, file.getTitle());
            preparedStatement.setObject(3, file.getParentId());
            preparedStatement.setInt(4, file.getCreatedBy());
            preparedStatement.setObject(5, file.getCreatedOn());
            preparedStatement.setInt(6, file.getLastModifiedBy());
            preparedStatement.setObject(7, file.getLastModifiedOn());
            preparedStatement.setBoolean(8, file.getIsPublic());
            preparedStatement.setInt(9, file.getSize());
            preparedStatement.setString(10, file.getFileUri());
            preparedStatement.setObject(11, null);


            return preparedStatement;
        }, holder);
        file.setId(holder.getKey().intValue());
        return file;
    }

    public boolean delete(Integer id) {
        String sql = "DELETE FROM files WHERE id = ?";
        int affectedRows = jdbcTemplate.update(sql, id);
        return affectedRows == 1;
    }

    public File updateFile(File file) {
        String sql = "UPDATE files SET title = ?, size = ?, parentId = ?, lastModifiedBy = ?, lastModifiedOn = ?" +
                ", isPublic = ?, fileUri = ? WHERE id = ?";
        int affectedRows = jdbcTemplate.update(sql, file.getTitle(), file.getSize(), file.getParentId(), file.getLastModifiedBy(), file.getLastModifiedOn(),
                file.getIsPublic(), file.getFileUri(), file.getId());
        if (affectedRows == 1) {
            return file;
        }
        return null;
    }
}

