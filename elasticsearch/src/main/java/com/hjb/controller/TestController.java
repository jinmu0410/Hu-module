package com.hjb.controller;

import com.hjb.ck.CkUtil;
import com.hjb.es.EsService;
import com.hjb.model.RequestParmam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;


@RestController
public class TestController {

    @Autowired
    private EsService esService;

    @Autowired
    private CkUtil ckClient;

    @GetMapping(value = "/query")
    public Object test(String index,String source,String keyword){
        return esService.search(index,source,keyword);
    }

    @PostMapping(value = "batch")
    public Object batchInsert(@RequestBody RequestParmam requestParmam){
        return esService.bulkIndex(requestParmam.getIndex(),requestParmam.getList());
    }

    @GetMapping("test")
    public Object test(String sql){
        try {
            return ckClient.execSql(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
