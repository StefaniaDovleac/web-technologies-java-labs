package com.unibuc.demo.repository;

import com.unibuc.demo.domain.FileHistory;
import com.unibuc.demo.enums.ActionType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.stream.Collectors;

@Repository
public class FileHistoryRepository {

    private final JdbcTemplate jdbcTemplate;

    public FileHistoryRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<FileHistory> getByFileId(Integer fileId) {
        String sql = "SELECT * FROM fileshistory WHERE fileId = ? ORDER BY revision DESC";
        RowMapper<FileHistory> mapper = ((resultSet, rowNumber) ->
                new FileHistory(resultSet.getInt("id"),
                        resultSet.getInt("fileId"),
                        resultSet.getString("title"),
                        resultSet.getInt("size"),
                        resultSet.getInt("parentId"),
                        resultSet.getInt("modifiedBy"),
                        resultSet.getDate("modifiedOn"),
                        resultSet.getString("fileUri"),
                        resultSet.getInt("revision"),
                        ActionType.valueOf(resultSet.getString("action"))
                ));
        return jdbcTemplate.query(sql, mapper, fileId).stream().collect(Collectors.toList());

    }

    public FileHistory save(FileHistory fileHistory) {
        List<FileHistory> historyList = getByFileId(fileHistory.getFileId());
        if (historyList != null && !historyList.isEmpty()) {
            fileHistory.setRevision(historyList.stream().findFirst().get().getRevision() + 1); // subselect???
        } else {
            fileHistory.setRevision(1);
        }
        String sql = "INSERT INTO fileshistory VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, null);
            preparedStatement.setInt(2, fileHistory.getFileId());
            preparedStatement.setString(3, fileHistory.getTitle());
            preparedStatement.setObject(4, fileHistory.getSize());
            preparedStatement.setObject(5, fileHistory.getParentId());
            preparedStatement.setInt(6, fileHistory.getModifiedBy());
            preparedStatement.setObject(7, fileHistory.getModifiedOn());
            preparedStatement.setString(8, fileHistory.getFileUri());
            preparedStatement.setInt(9, fileHistory.getRevision());
            preparedStatement.setString(10, fileHistory.getAction().toString());
            return preparedStatement;
        }, holder);
        fileHistory.setId(holder.getKey().intValue());
        return fileHistory;
    }
}

