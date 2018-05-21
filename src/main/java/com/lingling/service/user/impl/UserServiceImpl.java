package com.lingling.service.user.impl;

import com.lingling.common.BizException;
import com.lingling.dao.user.UserDao;
import com.lingling.domin.user.User;
import com.lingling.domin.votecount.VoteCount;
import com.lingling.service.baseservice.BaseService;
import com.lingling.service.user.UserService;
import com.lingling.utils.IdGenerator;
import com.lingling.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.io.Serializable;
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
    public Result insert(User record,String verificationCode) {
        Result result = new Result(false);
        //判断是否存在相同用户
        User userquery = new User();
        userquery.setUserCode(record.getUserCode());
        if(this.selectUsersByQuery(userquery).size()!=0){
            result.setErrorMessage("邮箱已存在！");
            return result;
        }
        this.checkVerificationCode(record.getUserCode(),verificationCode);
        record.setId(IdGenerator.getId());
        userDao.insert(record);
        result.setSuccess(true);
        result.setSuccessMessage("新增成功");
        return result;
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

    @Override
    public Result updatePswByVerificationCode(String newPsw, String verificationCode,String userCode) {
        Result result = new Result(false);
        User userQuery = new User();
        userQuery.setUserCode(userCode);
        List<User> userList = this.selectUsersByQuery(userQuery);
        this.checkVerificationCode(userList.get(0).getUserCode(),verificationCode);
        userList.get(0).setPassword(newPsw);
        this.updateByPrimaryKey(userList.get(0));
        result.setSuccess(true);
        result.setSuccessMessage("更新成功");
        return result;
    }

    /*校验验证码*/
    private void checkVerificationCode(String key,String verificationCode){
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        Result result = new Result();
        result.setSuccess(false);
        //log.info("输入验证码："+verificationCode);

        //log.info("服务器验证码："+operations.get(key));
        if(verificationCode == null || verificationCode.equals("")){
            throw new BizException("验证码不能为空");
        }else if(operations.get(key) == null){
            throw new BizException("验证码错误");
        }
        else if(!verificationCode.equals(operations.get(key).toString())){
            throw new BizException("验证码错误");
        }
    }
}
