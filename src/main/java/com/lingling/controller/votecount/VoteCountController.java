package com.lingling.controller.votecount;

import com.lingling.controller.base.BaseController;
import com.lingling.domin.votecount.VoteCount;
import com.lingling.service.votecount.VoteCountService;
import com.lingling.utils.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Administrator on 2018/4/11.
 */
@RestController
public class VoteCountController extends BaseController{
    @Autowired
    VoteCountService voteCountService;

    public int deleteByPrimaryKey(String id){
        return voteCountService.deleteByPrimaryKey(id);
    }

    public int insert(VoteCount record){

        return voteCountService.insert(record);
    }

    public VoteCount selectByPrimaryKey(String id){
        return voteCountService.selectByPrimaryKey(id);
    }

    public List<VoteCount> selectAll(){
        return voteCountService.selectAll();
    }

    public int updateByPrimaryKey(VoteCount record){
        return voteCountService.updateByPrimaryKey(record);
    }

    //根据投票项目id查询所有投过票的用户
    @RequestMapping("voteCount/getUserByVoteId")
    public List getUserByVoteId(String voteId){
        return voteCountService.getUserByVoteId(voteId);
    }

    //查看投票结果
    @RequestMapping("voteCount/getVoteResult")
    public List getVoteResult(String voteTopicId){
        return voteCountService.getVoteResult(voteTopicId);
    }
}
