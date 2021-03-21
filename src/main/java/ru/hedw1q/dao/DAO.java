package ru.hedw1q.dao;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @author hedw1q
 */
public interface DAO {
    Map<String, Object> getJSONEntry(int id);

    void setDataSource(DataSource dataSource);
}
