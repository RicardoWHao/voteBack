package com.lingling.dao.user.impl;

import com.lingling.dao.user.UserDao;
import com.lingling.domin.user.User;
import com.lingling.domin.votecount.VoteCount;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2018/4/8.
 */
@Component("userDao")
public class UserDaoImpl implements UserDao {
    /**
     * 当多个web线程调用同一个dao时，
     * 它们使用的是同一个SqlSessionTemplate，
     * 也就是同一个SqlSession
     */
    @Resource(name="sqlSessionTemplate")
    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    public int deleteByPrimaryKey(String id) {
        return sqlSessionTemplate.delete("UserMapper.delete", id);
    }

    @Override
    public int insert(User record) {
       return sqlSessionTemplate.insert("UserMapper.insert", record);
    }

    @Override
    public User selectByPrimaryKey(String id) {
        return sqlSessionTemplate.selectOne("UserMapper.selectByPrimaryKey", id);
    }

    @Override
    public List<User> selectAll() {
        return sqlSessionTemplate.selectList("UserMapper.selectAll");
    }

    @Override
    public int updateByPrimaryKey(User record) {
        return sqlSessionTemplate.update("UserMapper.updateByPrimaryKey",record);
    }

    @Override
    public List getUserList(List<VoteCount> list) {
        return sqlSessionTemplate.selectList("UserMapper.getUserList",list);
    }

    @Override
    public List getUserByIds(List<String> list) {
        return sqlSessionTemplate.selectList("UserMapper.getUserByIds",list);
    }

    @Override
    public List<User> selectUsersByQuery(User userQuery) {
        return sqlSessionTemplate.selectList("UserMapper.selectUsersByQuery",userQuery);
    }
}
