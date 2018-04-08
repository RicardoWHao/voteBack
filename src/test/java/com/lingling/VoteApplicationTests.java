package com.lingling;

import com.lingling.domin.test.UserDao;
import com.lingling.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VoteApplicationTests {
	@Autowired
	private UserDao userDao;
	@Test
	public void test(){
		User user = new User();
		user.setId(1);
		user.setName("1");
		user.setPassword("1");
		user.setPhone("1");
		userDao.insertUser(user);
	}
	@Test
	public void contextLoads() {

	}

}
