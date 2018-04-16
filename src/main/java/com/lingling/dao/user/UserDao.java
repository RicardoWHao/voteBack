package com.lingling.dao.user;

import com.lingling.domin.user.User;
import com.lingling.domin.votecount.VoteCount;

import java.util.List;

public interface UserDao {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    User selectByPrimaryKey(String id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    List getUserList(List<VoteCount> list);
}