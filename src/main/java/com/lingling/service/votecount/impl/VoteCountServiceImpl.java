package com.lingling.service.votecount.impl;

import com.lingling.dao.votecount.VoteCountDao;
import com.lingling.domin.votecount.VoteCount;
import com.lingling.domin.votecount.VoteResult;
import com.lingling.domin.votediv.VoteDiv;
import com.lingling.service.baseservice.BaseService;
import com.lingling.service.user.UserService;
import com.lingling.service.votecount.VoteCountService;
import com.lingling.service.votediv.VoteDivService;
import com.lingling.utils.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

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
    public List getVoteResultByUserId(String userId) {
        List<VoteResult> voteResultList = voteCountDao.getVoteResultByUserId(userId);
        List<VoteDiv> voteDivList = voteDivService.getVoteItemByIds(voteResultList);
        return voteDivList;
    }

    @Override
    public List getVoteResult(String voteTopicId) {
        List<VoteDiv> voteDivList = voteDivService.selectAll();
        List<VoteResult> voteResultList = voteCountDao.getVoteResult(voteDivList);
        HashMap<String,VoteDiv> map = new HashMap();
        for (VoteDiv voteDiv:voteDivList) {
            map.put(voteDiv.getId(),voteDiv);
        }
        for (VoteResult voteResult:voteResultList) {
            VoteDiv voteDiv = map.get(voteResult.getVoteItemId());
            voteResult.setUserId(voteDiv.getUserId());
            voteResult.setVateItemName(voteDiv.getVateItemName());
            voteResult.setVateItemDecrib(voteDiv.getVateItemDecrib());
        }
        return voteResultList;
    }

    @Override
    public int insert(VoteCount record) {
        record.setId(IdGenerator.getId());
        return voteCountDao.insert(record);
    }

    @Override
    public VoteCount selectByPrimaryKey(String id) {
        return voteCountDao.selectByPrimaryKey(id);
    }

    @Override
    public List<VoteCount> selectAll() {
        return voteCountDao.selectAll();
    }

    @Override
    public int updateByPrimaryKey(VoteCount record) {
        return voteCountDao.updateByPrimaryKey(record);
    }
}
