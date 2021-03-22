package ru.hedw1q.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.hedw1q.dao.DAO;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author hedw1q
 */

@RestController("db2any/bykey/getjson")
public class DefaultController {
    @Autowired
    private DAO daoImpl;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> getJSONRow(@RequestParam int key) {

        String response = "ok";
        Map<String, Object> dataEntry = new HashMap<String, Object>();

        try {
            dataEntry = daoImpl.getJSONEntry(key);
        } catch (DataRetrievalFailureException dataRetrievalFailureException) {
            response = "FAIL: " + dataRetrievalFailureException.getLocalizedMessage();
            dataRetrievalFailureException.printStackTrace();
        }

        Map<String, Object> JSONResponse = new LinkedHashMap<String, Object>();
        JSONResponse.put("data", dataEntry);
        JSONResponse.put("response", response);
        JSONResponse.put("request", key);

        return JSONResponse;
    }
}
