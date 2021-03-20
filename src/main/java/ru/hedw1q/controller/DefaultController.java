package ru.hedw1q.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
//import ru.hedw1q.dao.DAO;
import ru.hedw1q.dao.DAO;
import ru.hedw1q.model.Employee;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hedw1q
 */
//* обрабатываемый get запрос - http://localhost:8080/db2any/bykey/getjson?key=1
@RestController("")
public class DefaultController {

    @Autowired
    private DAO dao;


//    @GetMapping(value = "/",produces = MediaType.APPLICATION_JSON_VALUE)
//    public Employee sayHello() {
//        Employee employee=new Employee(1,"NAEM","SURNAME");
//        return employee;
//    }
//TODO: Сделать вид как требуется, строка в XML, ответ в HHTTP
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Map<String,Object>> test() {
        List<Map<String,Object>> list=dao.getJSONEntry(1);
        System.out.println(list.get(0));
        return list;
    }
}
