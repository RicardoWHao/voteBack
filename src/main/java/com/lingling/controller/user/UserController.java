package com.lingling.controller.user;

import com.lingling.controller.base.BaseController;
import com.lingling.domin.user.User;
import com.lingling.service.user.UserService;
import com.lingling.utils.EmailHelper;
import com.lingling.utils.IdGenerator;
import com.lingling.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

/**
 * Created by Administrator on 2018/4/11.
 */
@RestController
public class UserController extends BaseController{
    @Autowired
    UserService userService;
    private static Logger logger = Logger.getLogger("UserController.class");



    public int deleteByPrimaryKey(String id){
        return userService.deleteByPrimaryKey(id);
    }

    //新增用户
    @RequestMapping("user/insert")
    public Result insert(User record,String verificationCode){
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        Result result = new Result();
        result.setSuccess(false);
        if (verificationCode==(String)operations.get(record.getPhone())){
            result.setErrorMessage("验证码错误！");
        }else {
            result.setSuccess(true);
            result.addDefaultModel(record);
            result.setSuccessMessage("新增成功！");
        }
        return result;
    }

    //登录
    @RequestMapping("user/login")
    public Result login(User record, HttpSession httpSession){
        httpSession.setAttribute("userId", record.getId());
        return userService.login(record);
    }
    //查询用户登录信息
    @RequestMapping("user/selectByPrimaryKey")
    public Result selectByPrimaryKey(String id,HttpSession httpSession){
        return userService.selectByPrimaryKey(id,httpSession);
    }

    public List<User> selectAll(){
        return userService.selectAll();
    }
    //更新用户密码
    @RequestMapping("user/updatePsw")
    public Result updatePsw(String id,String newPsw,String oldPsw){
        return userService.updatePsw(id,newPsw,oldPsw);
    }

    public int updateByPrimaryKey(User record,HttpSession httpSession){
        Result result = new Result(false);
        if (record.getId()!=null){
            result.setSuccess(true);
        }
        if (record.getId()==null && httpSession.getId()==null){
            result.setErrorMessage("请先登录！");
        }
        if (record.getId()==null && httpSession.getId()!=null){
            record.setId(httpSession.getId());
        }
        return userService.updateByPrimaryKey(record);
    }
    //发送验证码
    @RequestMapping("user/sendVerificationCode")
    public void sendVerificationCode(String email){
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        int random=new Random().nextInt(9000)+1000;//为变量赋随机值1000-9999;
        try {
            EmailHelper.sendEmail("您的验证码为"+ random,email);
            operations.set(email, random);
        } catch (Exception e) {
            e.printStackTrace();
            logger.warning(e.getMessage());
        }
    }


}
