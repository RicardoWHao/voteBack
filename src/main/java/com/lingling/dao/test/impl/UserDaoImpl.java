package com.lingling.dao.test.impl;

import com.lingling.dao.user.UserDao;
import com.lingling.domin.user.User;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2018/4/8.
 */
@Component("userDao")
public class UserDaoImpl implements UserDao {
    @Resource(name="sqlSessionTemplate")
    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    public int deleteByPrimaryKey(String id) {
        return 0;
    }

    @Override
    public int insert(User record) {
       return sqlSessionTemplate.insert("UserDao.insert", record);
    }

    @Override
    public User selectByPrimaryKey(String id) {
        return null;
    }

    @Override
    public List<User> selectAll() {
        return null;
    }

    @Override
    public int updateByPrimaryKey(User record) {
        return 0;
    }
}
