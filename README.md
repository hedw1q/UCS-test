# UCS-test
## Техническое задание
Написать простой web сервис для Tomcat 7 возвращающий по Get запросу с параметром найденную запись из БД в виде json.

Реализация сервиса должна позволять возвращать все поля заданного в конфигурации произвольного SQL запроса без изменения реализации.

 
Для определённости:

* в качестве БД используем derby, подключение (прописывается в context.xml) - url="jdbc:derby://localhost:1527/test" username="uname" password="pass"

* sql запрос (прописывается в web.xml) - select * from ttable where id = ?

* обрабатываемый get запрос - http://localhost:8080/db2any/bykey/getjson?key=1

* требуемый формат ответа - {"data":{"ID":1,"VALUE":"first"},"response":"ok","request":"1"}, где data - все поля запроса с их значениями, response - признак успеха или причина сбоя, request - переданный на вход параметр

Проект должен собираться Maven (pom) на Java 6.

Желательно применить (будет учитываться в плюс): jersey, jdbctemplate (spring).
## Stack
- JDK 6   
- Spring core, MVC
- Spring JDBCTemplate
- Apache Tomcat 7
- DB: Apache derby 10.10 
- Maven 3.2

## Пример работы 
Сервис возвращает строку с данными в виде JSON, data-строка из БД с заданным ID, response-ok если успех , причина сбоя если ошибка, request-запрашиваемый ID 

Для работы сервиса требуется Apache Derby Network Server, запуск сервера осуществляется путем запуска bat-файла {apache-derby}/bin/startNetworkServer.bat

- Тестовые данные в БД

![image](https://sun9-19.userapi.com/impg/Z3-rn-6GvqTt_t554rYEqnWBPnc_JQzVajj4Vw/k5hqsqxk51M.jpg?size=176x102&quality=96&sign=82b93bb17dded9a4a474e4278c2f8cca&type=album)
- GET: `http://localhost:8080/db2any/bykey/getjson?key=1`  

![image](https://sun9-58.userapi.com/impg/T9yMX0WhKWkc2t32gDcamSktiSXW3kCAhI4iLw/Pt0yp19EaEI.jpg?size=707x104&quality=96&sign=5b00aa2a2e9419e0928129a835bf83e0&type=album)  
------
- GET: `http://localhost:8080/db2any/bykey/getjson?key=5`  

![image](https://sun9-59.userapi.com/impg/uDyRMkJGujPN9Dacegz9FvT7zpgnf9zGdqr8dg/gBZnHgWwMHA.jpg?size=997x103&quality=96&sign=a863d0060d78ff5093c620f5edd7f698&type=album)  
------
