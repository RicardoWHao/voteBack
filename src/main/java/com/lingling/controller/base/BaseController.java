package com.lingling.controller.base;


import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import com.lingling.utils.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RestController;
/**
 * Created by Administrator on 2018/4/5.
 */
@RestController
public class BaseController {
    @Autowired
    public RedisTemplate redisTemplate;


}
