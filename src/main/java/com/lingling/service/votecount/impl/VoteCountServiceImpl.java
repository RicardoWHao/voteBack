package com.lingling.service.votecount.impl;

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
    public List getUserByVoteId(String voteId) {
        List voteCountList = voteCountDao.getUserIDByVoteId(voteId);
        return userService.getUserList(voteCountList);
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
    public List<VoteDivDTO> getMyVote(String voteTopicId, HttpSession httpSession) {
        VoteCount voteCount = new VoteCount();
        voteCount.setVoteUserId((String) httpSession.getAttribute("userId"));
        List<VoteCount> voteCountList = voteCountDao.selectAll(voteCount);
        List<VoteResult> voteResultList = new ArrayList<>();
        List<String> userList = new ArrayList<>();
        for(VoteCount voteCountItem : voteCountList){
            VoteResult voteResult = new VoteResult();
            voteResult.setVoteItemId(voteCountItem.getItemId());
            voteResultList.add(voteResult);
            userList.add(voteCountItem.getVoteUserId());
        }
        List<VoteDiv> voteDivList = voteDivService.getVoteItemByIds(voteResultList);
        List<User> users = userService.getUserByIds(userList);
        Map<String , User> map = new HashMap<>();
        for(User user : users){
            map.put(user.getId(),user);
        }
        List<VoteDivDTO> voteDivDTOList = new ArrayList<>();
        for(VoteDiv voteDiv : voteDivList){
            VoteDivDTO voteDivDTO = new VoteDivDTO();
            BeanUtils.copyProperties(voteDiv,voteDivDTO);
            voteDivDTO.setUserName(map.get(voteDiv.getUserId()).getUserNickname());
            voteDivDTOList.add(voteDivDTO);
        }
        return voteDivDTOList;
    }

    /*
    *
    * 投票
    */
    @Override
    public int insert(VoteCount record) {
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
