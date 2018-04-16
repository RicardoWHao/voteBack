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
    public Result selectByPrimaryKey(String id, HttpSession httpSession) {
        Result result = new Result(false);
        if (id!=null){
            result.setSuccess(true);
            result.addDefaultModel(userDao.selectByPrimaryKey(id));
        }
        if (id==null && httpSession.getId()==null){
            result.setErrorMessage("请先登录！");
        }
        if (id==null && httpSession.getId()!=null){
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
    public Result login(User record) {
        Result result = new Result(false);
        User user = selectByPrimaryKey(record.getId());
        if (record.getPassword()==user.getPassword()){
            result.setSuccess(true);
            result.setSuccessMessage("登录成功");
        }else {
            result.setErrorMessage("密码错误");
        }
        return result;
    }

    @Override
    public Result updatePsw(String id, String newPsw, String oldPsw) {
        Result result = new Result(false);
        User user = this.selectByPrimaryKey(id);
        if (user.getPassword()==oldPsw){
            user.setPassword(newPsw);
            this.updateByPrimaryKey(user);
            result.setSuccess(true);
        }else {
            result.setErrorMessage("密码错误！");
        }
        return result;

    }
}
