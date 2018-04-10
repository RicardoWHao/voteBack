package com.lingling.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/4/5.
 */
@RestController
@ComponentScan(value="com.linglin")
public class VoteGener extends BaseController{

    @RequestMapping(value = "voteGener/getVoteGenerByPage", method = {RequestMethod.POST, RequestMethod.GET })
    public @ResponseBody String getVoteGenerByPage() {
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        operations.set("qq", "wanghao");
        return (String)operations.get("qq");
    }
}
