package com.lingling.service.user;

import com.lingling.domin.user.User;
import com.lingling.domin.votecount.VoteCount;
import com.lingling.utils.Result;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Administrator on 2018/4/10.
 */
public interface UserService {
    int deleteByPrimaryKey(String id);

    Result insert(User record,String verificationCode);

    Result selectByPrimaryKey(HttpSession httpSession);

    User selectByPrimaryKey(String id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    List getUserList(List<VoteCount> list);

    List getUserByIds(List<String> list);

    Result login(User record,HttpSession httpSession);

    List<User> selectUsersByQuery(User userQuery);

    Result updatePsw(String newPsw,String oldPsw,HttpSession httpSession);
    /*忘记密码，根据邮箱验证码更改密码*/
    Result updatePswByVerificationCode(String newPsw,String verificationCode,String userCode);

}
