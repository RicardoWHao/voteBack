package com.lingling.service.user;

import com.lingling.domin.user.User;

import java.util.List;

/**
 * Created by Administrator on 2018/4/10.
 */
public interface UserService {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    User selectByPrimaryKey(String id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);
}
