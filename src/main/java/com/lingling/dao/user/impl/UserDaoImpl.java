package com.lingling.dao.user.impl;

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
        return sqlSessionTemplate.delete("UserDao.delete", id);
    }

    @Override
    public int insert(User record) {
       return sqlSessionTemplate.insert("UserDao.insert", record);
    }

    @Override
    public User selectByPrimaryKey(String id) {
        return sqlSessionTemplate.selectOne("UserDao.selectByPrimaryKey", id);
    }

    @Override
    public List<User> selectAll() {
        return sqlSessionTemplate.selectList("UserDao.selectAll");
    }

    @Override
    public int updateByPrimaryKey(User record) {
        return sqlSessionTemplate.update("UserDao.updateByPrimaryKey",record);
    }
}
