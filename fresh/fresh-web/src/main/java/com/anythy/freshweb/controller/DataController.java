package com.anythy.freshweb.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class DataController {
    @RequestMapping("/get")
    public Map<String, Object> get(){
        Map<String, Object> result = new HashMap<>(2);
        result.put("admin/index", "home");
        result.put("test", "name");
        return result;
    }
}
