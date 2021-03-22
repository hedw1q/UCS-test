package ru.hedw1q.dao;

import org.springframework.dao.IncorrectResultSizeDataAccessException;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @author hedw1q
 */
public interface DAO {
    Map<String, Object> getJSONEntry(int id) throws IncorrectResultSizeDataAccessException;

    void setDataSource(DataSource dataSource);
}
