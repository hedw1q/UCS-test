package ru.hedw1q.dao;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.Map;


/**
 * @author hedw1q
 */
//* в качестве БД используем derby, подключение (прописывается в context.xml) -
// url="jdbc:derby://localhost:1527/test" username="uname" password="pass"
public class DAOImpl implements DAO {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public DAOImpl() {
    }

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Map<String, Object> getJSONEntry(int id) throws IncorrectResultSizeDataAccessException {
        String SQL = "SELECT * FROM Ttable WHERE id=?";
        return jdbcTemplate.queryForMap(SQL, id);
    }


}
