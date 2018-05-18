package com.lingling.controller.votediv;

import com.lingling.controller.base.BaseController;
import com.lingling.domin.votediv.VoteDiv;
import com.lingling.service.votediv.VoteDivService;
import com.lingling.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Administrator on 2018/4/11.
 */
@RestController
public class VoteDivController extends BaseController{
    @Autowired
    VoteDivService voteDivService;

    public int deleteByPrimaryKey(String id){
        return voteDivService.deleteByPrimaryKey(id);
    }
    //报名参赛
    @RequestMapping("votediv/insert")
    public Result insert(VoteDiv record,HttpSession httpSession){
        return voteDivService.insert(record,httpSession);
    }
    @RequestMapping("votediv/selectById")
    public VoteDiv selectByPrimaryKey(String id){
        return voteDivService.selectByPrimaryKey(id);
    }
    //根据id查询参赛作品明细
    @RequestMapping("votediv/selectAll")
    public List<VoteDiv> selectAll(){
        return voteDivService.selectAll();
    }
    //搜索
    @RequestMapping("votediv/selectVoteDivByQuery")
    public Result selectVoteDivByQuery(VoteDiv voteDivQuery){
        return voteDivService.selectVoteDivByQuery(voteDivQuery);
    }

    public int updateByPrimaryKey(VoteDiv record){
        return voteDivService.updateByPrimaryKey(record);
    }
}
