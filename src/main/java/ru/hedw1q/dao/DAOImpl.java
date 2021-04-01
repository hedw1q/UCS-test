package ru.hedw1q.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.servlet.ServletContext;
import javax.sql.DataSource;
import java.util.Map;
import java.util.logging.Logger;


/**
 * @author hedw1q
 */
public class DAOImpl implements DAO {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    ServletContext servletContext;

    private static Logger log = Logger.getLogger(DAOImpl.class.getName());

    public DAOImpl() {
    }

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * Retrives one row with ID from database. If there is no data, @throws IncorrectResultSizeDataAccessException
     *
     * @param id as int:
     * @return data as Map<String,Object>, where String key is column name, Object value is column value
     * @throws IncorrectResultSizeDataAccessException when data in empty
     */
    @Override
    public Map<String, Object> getJSONEntry(int id) throws IncorrectResultSizeDataAccessException {
        String SQL = servletContext.getInitParameter("Query");

        return jdbcTemplate.queryForMap(SQL, id);
    }

}
