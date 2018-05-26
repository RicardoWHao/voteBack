package com.lingling.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * Author:wanghao
 * email:wanghao63@jd.com
 * Data:Created in 10:26 2018/5/21
 * Description:
 */
public class VoteThread implements Runnable{
    //Jedis jedis = null;
    String key = "prdNum";// 商品主键
    String clientName;
    String clientList = clientName+"List";//// 投票客户主键列表
    @Resource(name = "redisTemplate")
    private RedisTemplate redisTemplate;

    @Resource(name = "redisTemplate")
    private ValueOperations<String, Object> vOps;

    public VoteThread(int num) {
        clientName = "编号=" + num;
    }
    ListOperations listOperations = redisTemplate.opsForList();
    @Override
    public void run() {

        try {
            Thread.sleep((int)(Math.random()*5000));// 随机睡眠一下
        } catch (InterruptedException e1) {
        }
        while (true) {
            try {
                int voteCount = Integer.parseInt((String)vOps.get(key));// 当前商品个数
                redisTemplate.watch(voteCount);
                if (voteCount > 0) {
                    redisTemplate.multi();
                    vOps.set(clientName, String.valueOf(voteCount + 1));
                    List<Object> result = redisTemplate.exec();
                    if (result == null || result.isEmpty()) {
                        System.out.println("用户：" + clientName + "投票失败");// 可能是watch-key被外部修改，或者是数据操作被驳回
                    } else {
                        listOperations.rightPush(clientList, clientName);//记录投票用户
                        System.out.println(clientName + "投票成功");
                        break;
                    }
                } else {
                    System.out.println("悲剧了，库存为0，顾客:" + clientName + "用户投票失败");
                    continue;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                redisTemplate.unwatch();
            }

        }
    }
}
