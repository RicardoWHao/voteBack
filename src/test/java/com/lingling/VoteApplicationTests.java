package com.lingling;

import com.lingling.controller.user.UserController;
import com.lingling.dao.user.UserDao;
import com.lingling.service.user.UserService;
import com.lingling.service.votecount.VoteCountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.Serializable;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VoteApplicationTests {
	@Autowired
	private UserDao userDao;
	@Autowired
	private UserService userService;
	@Autowired
	private UserController userController;
	@Autowired
	private VoteCountService voteCountService;
	@Autowired
	public RedisTemplate redisTemplate;
	@Test
	public void test(){
		voteCountService.getUserByVoteId("1");
		ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
		operations.set("a","aa");
		System.out.println(operations.get("a"));
	}
	@Test
	public  void  tsetemail(){
		String content ="哈喽你好！";
		String content1 ="哈喽你好！";
	}
	@Test
	public void contextLoads() {

	}

}
