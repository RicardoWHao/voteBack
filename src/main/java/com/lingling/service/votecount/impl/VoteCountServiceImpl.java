package com.lingling.service.votecount.impl;

import com.lingling.common.BizException;
import com.lingling.dao.votecount.VoteCountDao;
import com.lingling.domin.user.User;
import com.lingling.domin.votecount.VoteCount;
import com.lingling.domin.votecount.VoteResult;
import com.lingling.domin.votediv.VoteDiv;
import com.lingling.domin.votediv.VoteDivDTO;
import com.lingling.service.baseservice.BaseService;
import com.lingling.service.user.UserService;
import com.lingling.service.votecount.VoteCountService;
import com.lingling.service.votediv.VoteDivService;
import com.lingling.utils.IdGenerator;
import com.lingling.utils.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/10.
 */
@Service
public class VoteCountServiceImpl extends BaseService implements VoteCountService{
    @Autowired
    VoteCountDao voteCountDao;
    @Autowired
    UserService userService;
    @Autowired
    VoteDivService voteDivService;

    @Override
    public int deleteByPrimaryKey(String id) {
        return voteCountDao.deleteByPrimaryKey(id);
    }

    /**
     * 根据参与投票选项id查询所有投当前选项的用户信息
     * @author wanghao
     * @param voteId
     * @return List<user>
     */
    @Override
    public Result getUserByVoteId(String voteId) {
        Result result = new Result(false);
        List voteCountList = voteCountDao.getUserIDByVoteId(voteId);
        List userList = userService.getUserList(voteCountList);
        result.setSuccess(true);
        result.addDefaultModel("voteId",voteId);
        result.addDefaultModel("userList",userList);
        return result;
    }

    @Override
    public List getVoteResult(String voteTopicId) {
        List<VoteDiv> voteDivList = voteDivService.selectAll();
        List<VoteResult> voteResultList = voteCountDao.getVoteResult(voteDivList);
        HashMap<String,VoteDiv> map = new HashMap<String,VoteDiv>();
        for (VoteDiv voteDiv:voteDivList) {
            map.put(voteDiv.getId(),voteDiv);
        }
        for (VoteResult voteResult:voteResultList) {
            VoteDiv voteDiv = map.get(voteResult.getVoteItemId());
            voteResult.setUserId(voteDiv.getUserId());
            voteResult.setVoteItemName(voteDiv.getVoteItemName());
            voteResult.setVoteItemDecrib(voteDiv.getVoteItemDecrib());
        }
        return voteResultList;
    }

    @Override
    public Result getMyVote(String voteTopicId, HttpSession httpSession) {
        Result result = new Result(false);
        VoteCount voteCount = new VoteCount();
        voteCount.setVoteUserId((String) httpSession.getAttribute("userId"));
        //查出来所有投过的票
        List<VoteCount> voteCountList = voteCountDao.selectAll(voteCount);
        if (voteCountList.size()==0){
            result.setErrorMessage("请先投票");
            return result;
        }
        List<String> voteIds = new ArrayList<>();
        List<String> userList = new ArrayList<>();
        //查出投过的票的人都是谁
        for(VoteCount voteCountItem : voteCountList){
            voteIds.add(voteCountItem.getItemId());
            userList.add(voteCountItem.getVoteUserId());
        }
        List<VoteDiv> voteDivList = voteDivService.getVoteItemByIds(voteIds);
        List<User> users = userService.getUserByIds(userList);

        Map<String , User> map = new HashMap<>();
        for(User user : users){
            map.put(user.getId(),user);
        }
        List<VoteDivDTO> voteDivDTOList = new ArrayList<>();
        for(VoteDiv voteDiv : voteDivList){
            VoteDivDTO voteDivDTO = new VoteDivDTO();
            BeanUtils.copyProperties(voteDiv,voteDivDTO);
            if(map.get(voteDiv.getUserId())!=null) {
                voteDivDTO.setUserName(map.get(voteDiv.getUserId()).getUserCode());
            }
            voteDivDTOList.add(voteDivDTO);
        }
        result.setSuccess(true);
        result.addDefaultModel(voteDivDTOList);
        return result;
    }

    /*
    *
    * 投票
    */
    @Override
    public int insert(VoteCount record) {
        List<VoteCount> voteCountList = this.selectAll(record);
        if(voteCountList.size()>0){
            throw new BizException("请勿重复投票");
        }
        record.setId(IdGenerator.getId());
        VoteDiv voteDiv = voteDivService.selectByPrimaryKey(record.getItemId());
        voteDiv.setVoteCount(voteDiv.getVoteCount()+1);
        voteDivService.updateByPrimaryKey(voteDiv);
        return voteCountDao.insert(record);
    }

    @Override
    public VoteCount selectByPrimaryKey(String id) {
        return voteCountDao.selectByPrimaryKey(id);
    }

    @Override
    public List<VoteCount> selectAll(VoteCount voteCount) {
        return voteCountDao.selectAll(voteCount);
    }

    @Override
    public int updateByPrimaryKey(VoteCount record) {
        return voteCountDao.updateByPrimaryKey(record);
    }
}
