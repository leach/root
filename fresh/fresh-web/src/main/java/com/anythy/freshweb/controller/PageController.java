package com.anythy.freshweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/", "/admin"})
public class PageController {

    @RequestMapping("/")
    public String index(){
        return "admin/index/index";
    }
}
