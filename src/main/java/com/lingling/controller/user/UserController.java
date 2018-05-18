package com.lingling.controller.user;

import com.lingling.common.BizException;
import com.lingling.controller.base.BaseController;
import com.lingling.domin.user.User;
import com.lingling.service.user.UserService;
import com.lingling.utils.EmailHelper;
import com.lingling.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
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
        Result result = new Result(false);
        try {
            result = userService.insert(record,verificationCode);
        }catch (BizException bizException){
            result.setSuccess(false);
            result.setErrorMessage(bizException.getMessage());
            return result;
        }
        return result;

    }
    /*//更新用户
    @RequestMapping("user/insert")
    public Result insert(User record,String verificationCode){
        Result result = userService.insert(record,verificationCode);
        return result;
    }*/
    /*@RequestMapping("user/insert")
    public Result insert(User record,String verificationCode){
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        Result result = new Result();
        result.setSuccess(false);
        logger.info(record.toString());
        logger.info("输入验证码："+verificationCode);
        logger.info("服务器验证码："+operations.get(record.getUserCode().toString()));
        if(verificationCode == null && verificationCode.equals("")){
            result.setErrorMessage("验证码不能为空！");
            return result;
        }
        User userquery = new User();
        userquery.setUserCode(record.getUserCode());
        if(userService.selectUsersByQuery(userquery).size()!=0){
            result.setErrorMessage("邮箱已存在！");
            return result;
        }
        if (!verificationCode.equals(operations.get(record.getUserCode()).toString())){
            result.setErrorMessage("验证码错误！");
        }else {
            userService.insert(record);
            result.setSuccess(true);
            result.addDefaultModel(record);
            result.setSuccessMessage("新增成功！");
        }
        return result;
    }*/

    //登录
    @RequestMapping("user/login")
    public Result login(User record, HttpSession httpSession){
        return userService.login(record,httpSession);
    }
    //注销
    @RequestMapping("user/Cancellation")
    public Result Cancellation(HttpSession httpSession){
        Result result = new Result(false);
        try {
            httpSession.removeAttribute("userId");
        }catch (Exception e){

        }
        result.setSuccess(true);
        return result;
    }
    //查询用户登录信息
    @RequestMapping("user/selectByPrimaryKey")
    public Result selectByPrimaryKey(HttpSession httpSession){
        return userService.selectByPrimaryKey(httpSession);
    }

    public List<User> selectAll(){
        return userService.selectAll();
    }
    //更新用户密码
    @RequestMapping("user/updatePsw")
    public Result updatePsw(String newPsw,String oldPsw,HttpSession httpSession){
        return userService.updatePsw(newPsw,oldPsw,httpSession);
    }

    //更新用户密码
    @RequestMapping("user/updatePswByVerificationCode")
    public Result updatePswByVerificationCode(String newPsw,String verificationCode,String userCode){
        Result result = null;
        try {
            result = userService.updatePswByVerificationCode(newPsw,verificationCode,userCode);
        }catch (BizException bizException){
            result.setErrorMessage(bizException.getMessage());
            return result;
        }
        return result;
    }

    public int updateByPrimaryKey(User record,HttpSession httpSession){
        Result result = new Result(false);
        if (httpSession.getAttribute("userId")==null){
            result.setErrorMessage("请先登录！");
        }else{
            record.setId((String)httpSession.getAttribute("userId"));
        }
        return userService.updateByPrimaryKey(record);
    }
    //发送验证码
    @RequestMapping("user/sendVerificationCode")
    public void sendVerificationCode(String userCode){
        ValueOperations<String, Integer> operations = redisTemplate.opsForValue();
        int random=new Random().nextInt(9000)+1000;//为变量赋随机值1000-9999;
        try {
            EmailHelper.sendEmail("您的验证码为"+ random,userCode);
            operations.set(userCode, random);
            logger.log(Level.INFO,userCode+":"+((Integer)operations.get(userCode)).toString());
        } catch (Exception e) {
            e.printStackTrace();
            logger.warning(e.getMessage());
        }
    }


}
