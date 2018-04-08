package com.lingling.domin.test.impl;

import com.lingling.domin.test.UserDao;
import com.lingling.model.User;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2018/4/8.
 */
@Component("userDao")
public class UserDaoImpl implements UserDao {
    @Resource(name="sqlSessionTemplate")
    private SqlSessionTemplate sqlSessionTemplate;


    @Override
    public void insertUser(User user) {
        sqlSessionTemplate.insert("user.insertUser", user);
    }
}
