package com.lingling.controller.votecount;

import com.lingling.controller.base.BaseController;
import com.lingling.domin.votecount.VoteCount;
import com.lingling.domin.votediv.VoteDivDTO;
import com.lingling.service.votecount.VoteCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
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

    //投票
    @RequestMapping("voteCount/insert")
    public int insert(VoteCount record,HttpSession httpSession){
        record.setId((String)httpSession.getAttribute("userId"));
        return voteCountService.insert(record);
    }

    public VoteCount selectByPrimaryKey(String id){
        return voteCountService.selectByPrimaryKey(id);
    }

    public List<VoteCount> selectAll(VoteCount voteCount){
        return voteCountService.selectAll(voteCount);
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

    //查看我的投票
    @RequestMapping("voteCount/getMyVote")
    public List<VoteDivDTO> getMyVote(String voteTopicId,HttpSession httpSession){
        return voteCountService.getMyVote(voteTopicId, httpSession);
    }
}
