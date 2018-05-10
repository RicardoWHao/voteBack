package com.lingling.service.user.impl;

import com.lingling.dao.user.UserDao;
import com.lingling.domin.user.User;
import com.lingling.domin.votecount.VoteCount;
import com.lingling.service.baseservice.BaseService;
import com.lingling.service.user.UserService;
import com.lingling.utils.IdGenerator;
import com.lingling.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Administrator on 2018/4/10.
 */
@Service
public class UserServiceImpl extends BaseService implements UserService{

    @Autowired
    private UserDao userDao;

    @Override
    public int deleteByPrimaryKey(String id) {
        return userDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(User record) {
        record.setId(IdGenerator.getId());
        return userDao.insert(record);
    }


    @Override
    public Result selectByPrimaryKey(HttpSession httpSession) {
        Result result = new Result(false);
        String id;
        if (httpSession.getAttribute("userId")==null){
            result.setErrorMessage("请先登录！");
        }
        if (httpSession.getAttribute("userId")!=null){
            id = (String) httpSession.getAttribute("userId");
            result.addDefaultModel(userDao.selectByPrimaryKey(id));
        }
        return result;
    }
    @Override
    public User selectByPrimaryKey(String id) {
        return  userDao.selectByPrimaryKey(id);
    }

    @Override
    public List<User> selectAll() {
        return userDao.selectAll();
    }

    @Override
    public int updateByPrimaryKey(User record) {
        return userDao.updateByPrimaryKey(record);
    }

    @Override
    public List getUserList(List<VoteCount> list) {
        return userDao.getUserList(list);
    }

    @Override
    public List getUserByIds(List<String> list) {
        return userDao.getUserByIds(list);
    }

    @Override
    public Result login(User record,HttpSession httpSession) {
        Result result = new Result(false);
        User userQuery = new User();
        //userQuery.setPhone(record.getPhone());
        userQuery.setUserCode(record.getUserCode());
        List<User> userList = selectUsersByQuery(userQuery);
        if (record.getPassword().equals(userList.get(0).getPassword())){
            result.setSuccess(true);
            httpSession.setAttribute("userId", userList.get(0).getId());
            result.setSuccessMessage("登录成功");
        }else {
            result.setErrorMessage("密码错误");
        }
        return result;
    }

    @Override
    public List<User> selectUsersByQuery(User userQuery){
        List<User> list = null;
        list = userDao.selectUsersByQuery(userQuery);
        return list;
    }
    @Override
    public Result updatePsw(String newPsw, String oldPsw , HttpSession httpSession) {

        String id = (String)httpSession.getAttribute("userId");
        Result result = new Result(false);
        if (id == null){
            result.setErrorMessage("请先登录！");
            return  result;
        }
        User user = this.selectByPrimaryKey(id);
        if (user.getPassword().equals(oldPsw)){
            user.setPassword(newPsw);
            this.updateByPrimaryKey(user);
            result.setSuccess(true);
        }else {
            result.setErrorMessage("密码错误！");
        }
        return result;

    }
}
