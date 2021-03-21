package ru.hedw1q.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.hedw1q.dao.DAOImpl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

//import ru.hedw1q.dao.DAO;

/**
 * @author hedw1q
 */
//* обрабатываемый get запрос - http://localhost:8080/db2any/bykey/getjson?key=1
@RestController("")
public class DefaultController {

    @Autowired
    private DAOImpl daoImpl;

    //TODO: Сделать вид как требуется, строка в XML, ответ в HHTTP
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> test(@PathVariable int id) {
        String response = "ok";
        Map<String, Object> DBEntry = new HashMap<String, Object>();
        try {
            DBEntry = daoImpl.getJSONEntry(id);
        } catch (DataRetrievalFailureException dataRetrievalFailureException) {
            response = "FAIL: " + dataRetrievalFailureException.getLocalizedMessage();
        }
        Map<String, Object> dataResponse = new LinkedHashMap<String, Object>();
        dataResponse.put("data", DBEntry);
        dataResponse.put("response", response);
        dataResponse.put("request", id);
        return dataResponse;
    }
}
