package com.example.testjdbc;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class OneController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("/")
    @ResponseBody
    public String index(){
        String sql = "insert into test2 (id,doc) values (1,'nio')";
        jdbcTemplate.execute(sql);
        System.out.println("执行完成");
        return "hello spring boot";
    }

    @RequestMapping("/get")
    @ResponseBody
    public String get(){

        String sql = "select * from test2 where id = 1";
        Map<String, Object> map = jdbcTemplate.queryForMap(sql);
        System.out.println("执行完成");

        // doc 是个blob类型的数据
        Object o = map.get("doc");
        byte[] bytes = (byte[]) o;
        String t = new String(bytes);
        System.out.println(t);
        return "hello spring boot";
    }
}