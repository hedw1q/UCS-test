package ru.hedw1q.dao;

import com.sun.org.apache.xpath.internal.objects.XObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * @author hedw1q
 */
//* в качестве БД используем derby, подключение (прописывается в context.xml) -
// url="jdbc:derby://localhost:1527/test" username="uname" password="pass"
public class DAO {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public DAO() { }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate=new JdbcTemplate(dataSource);
    }

        public List<Map<String,Object>> getJSONEntry(int id) {
            String SQL = "SELECT * FROM Ttable WHERE id=?";
            return jdbcTemplate.queryForList(SQL, id);
    }


}
