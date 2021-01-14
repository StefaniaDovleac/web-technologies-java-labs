package com.unibuc.demo.repository;

import com.unibuc.demo.domain.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class PermissionRepository {
    public final JdbcTemplate jdbcTemplate;

    @Autowired
    public PermissionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<Permission> getById(Integer id) {
        String sql = "SELECT * FROM permissions WHERE id = ?";
        RowMapper<Permission> mapper = ((resultSet, rowNumber) ->
                new Permission(resultSet.getInt("id"),
                        resultSet.getInt("userId"),
                        resultSet.getBoolean("createRight"),
                        resultSet.getBoolean("deleteRight"),
                        resultSet.getBoolean("renameRight"),
                        resultSet.getBoolean("moveRight"),
                        resultSet.getBoolean("uploadRight"),
                        resultSet.getBoolean("downloadRight")
                ));
        return jdbcTemplate.query(sql, mapper, id).stream()
                .filter(permission -> permission.getId().equals(id))
                .findFirst();
    }


    public Optional<Permission> getByUserId(Integer userId) {
        String sql = "SELECT * FROM permissions WHERE userId = ?";
        RowMapper<Permission> mapper = ((resultSet, rowNumber) ->
                new Permission(resultSet.getInt("id"),
                        resultSet.getInt("userId"),
                        resultSet.getBoolean("createRight"),
                        resultSet.getBoolean("deleteRight"),
                        resultSet.getBoolean("renameRight"),
                        resultSet.getBoolean("moveRight"),
                        resultSet.getBoolean("uploadRight"),
                        resultSet.getBoolean("downloadRight")
                ));
        return jdbcTemplate.query(sql, mapper, userId).stream()
                .filter(permission -> permission.getId().equals(userId))
                .findFirst();
    }

    public List<Permission> getAll() {
        String sql = "SELECT * FROM permissions";
        RowMapper<Permission> mapper = ((resultSet, rowNumber) ->
                new Permission(resultSet.getInt("id"),
                        resultSet.getInt("userId"),
                        resultSet.getBoolean("createRight"),
                        resultSet.getBoolean("deleteRight"),
                        resultSet.getBoolean("renameRight"),
                        resultSet.getBoolean("moveRight"),
                        resultSet.getBoolean("uploadRight"),
                        resultSet.getBoolean("downloadRight")

                ));
        return jdbcTemplate.query(sql, mapper).stream().collect(Collectors.toList());
    }

    public Permission save(Permission permission) {
        String sql = "INSERT INTO permissions VALUES(null, ?, ?, ?, ?, ?, ?, ?)";
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, permission.getUserId());
            preparedStatement.setObject(2, permission.getCreateRight());
            preparedStatement.setObject(3, permission.getDeleteRight());
            preparedStatement.setObject(4, permission.getRenameRight());
            preparedStatement.setObject(5, permission.getMoveRight());
            preparedStatement.setObject(6, permission.getUploadRight());
            preparedStatement.setObject(7, permission.getDownloadRight());

            return preparedStatement;
        }, holder);
        permission.setId(holder.getKey().intValue());
        return permission;
    }

    public Permission update(Permission permission) {
        String sql = "UPDATE permissions SET userId = ?, createRight = ?, deleteRight = ?, renameRight = ?, moveRight = ?, uploadRight = ?, downloadRight = ?  WHERE id = ?";
        int affectedRows = jdbcTemplate.update(
                sql, permission.getUserId(), permission.getCreateRight(), permission.getDeleteRight(), permission.getRenameRight(),
                permission.getMoveRight(), permission.getUploadRight(), permission.getDownloadRight(), permission.getId());
        if (affectedRows == 1) {
            return permission;
        }
        return null;
    }

    public Boolean delete(Integer id) {
        String sql = "DELETE FROM permissions WHERE id = ?";
        int affectedRows = jdbcTemplate.update(sql, id);
        return affectedRows == 1;
    }
}
