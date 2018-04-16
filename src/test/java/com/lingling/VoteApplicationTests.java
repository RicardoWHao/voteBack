package com.lingling;

import com.lingling.controller.user.UserController;
import com.lingling.dao.user.UserDao;
import com.lingling.domin.user.User;
import com.lingling.domin.votecount.VoteCount;
import com.lingling.service.user.UserService;
import com.lingling.service.votecount.VoteCountService;
import com.lingling.utils.EmailHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
	@Test
	public void test(){
		List list = voteCountService.getUserByVoteId("1");
	}
	@Test
	public  void  tsetemail(){
		String content ="哈喽你好！";
		List list = voteCountService.getVoteResultByUserId("1");
		String content1 ="哈喽你好！";
	}
	@Test
	public void contextLoads() {

	}

}
