package com.lingling.service.baseservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Created by Administrator on 2018/4/11.
 */

public class BaseService {
    @Autowired
    public RedisTemplate redisTemplate;
    //public SnowflakeIdWorker snowflakeIdWorker = new SnowflakeIdWorker(0,1);
}
