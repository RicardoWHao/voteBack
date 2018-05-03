package com.lingling.controller.base;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2018/4/5.
 */
@RestController
public class BaseController {
    @Autowired
    public RedisTemplate redisTemplate;


}
