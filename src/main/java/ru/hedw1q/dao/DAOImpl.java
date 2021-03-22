package ru.hedw1q.dao;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.sql.DataSource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;


/**
 * @author hedw1q
 */
public class DAOImpl implements DAO {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

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
     * If you are deploying this application in your PC, you should change web.xml Absolute path to yours(yes, it's a real flaw)
     *
     * @param id as int:
     * @return data as Map<String,Object>, where String key is column name, Object value is column value
     * @throws IncorrectResultSizeDataAccessException when data in empty
     */
    @Override
    public Map<String, Object> getJSONEntry(int id) throws IncorrectResultSizeDataAccessException {
        File webXMLFile;
        String SQL = null;
        try {
            webXMLFile = new File("C:\\Users\\hedw1q\\Desktop\\UCS-test-project\\src\\main\\webapp\\WEB-INF\\web.xml");
            SQL = getQueryByName(webXMLFile, "Ttable.findRowByID");
            System.out.println(SQL);
        } catch (IOException ioException) {
            log.severe("XML File not found");
            ioException.printStackTrace();
        }
        return jdbcTemplate.queryForMap(SQL, id);
    }

    /**
     * Returns single query body as String from queries located in xml file
     * queryMap is intermediate result as Hashmap with key-query name, value-query body
     *
     * @param xmlFile   as File: File for parsing
     * @param queryName as String: named-query property in xml file, name of query
     * @return String SQL query
     * @throws IOException if file not found
     */
    public String getQueryByName(File xmlFile, String queryName) throws IOException {
        Document doc = null;
        try {
            //Parse xmlFile
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        Map<String, String> queryMap = new HashMap<String, String>();

        //Get all named queries as nodelist
        NodeList queryNodeList = doc.getElementsByTagName("named-query");

        //Put all nodes into hashmap, key-query name, value-query body
        for (int temp = 0; temp < queryNodeList.getLength(); temp++) {
            Node queryNode = queryNodeList.item(temp);
            if (queryNode.getNodeType() == Node.ELEMENT_NODE) {
                Element queryElement = (Element) queryNode;
                queryMap.put(queryElement.getAttribute("name"), queryElement.getElementsByTagName("query").item(0).getTextContent());
            }
        }

        return queryMap.get(queryName);
    }

}
