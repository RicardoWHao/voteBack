package com.lingling.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2018/4/5.
 */
@RestController
public class Welcome {
    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }
}
