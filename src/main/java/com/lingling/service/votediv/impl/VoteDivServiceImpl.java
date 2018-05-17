package com.lingling.service.votediv.impl;

import com.lingling.dao.votediv.VoteDivDao;
import com.lingling.domin.votediv.VoteDiv;
import com.lingling.service.baseservice.BaseService;
import com.lingling.service.votediv.VoteDivService;
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
public class VoteDivServiceImpl extends BaseService implements VoteDivService{
    @Autowired
    VoteDivDao voteDivDao;

    @Override
    public int deleteByPrimaryKey(String id) {
        return voteDivDao.deleteByPrimaryKey(id);
    }

    @Override
    public Result insert(VoteDiv record,HttpSession httpSession) {
        Result result = new Result(false);
        record.setId(IdGenerator.getId());
        record.setVoteCount(0);
        if (httpSession.getAttribute("userId")==null){
            result.setErrorMessage("请先登录");
            return result;
        }
        record.setUserId((String)httpSession.getAttribute("userId"));
        voteDivDao.insert(record);
        result.setSuccess(true);
        return result;
    }

    @Override
    public VoteDiv selectByPrimaryKey(String id) {
        return voteDivDao.selectByPrimaryKey(id);
    }

    @Override
    public List<VoteDiv> selectAll() {
        return voteDivDao.selectAll();
    }

    @Override
    public int updateByPrimaryKey(VoteDiv record) {
        return voteDivDao.updateByPrimaryKey(record);
    }

    @Override
    public List<VoteDiv> getVoteItemByIds(List ids) {
        return voteDivDao.getVoteItemByIds(ids);
    }

    @Override
    public Result selectVoteDivByQuery(VoteDiv voteDivQuery) {
        Result result = new Result(false);
        List<VoteDiv> voteDivList = voteDivDao.selectVoteDivByQuery(voteDivQuery);
        result.setSuccess(true);
        result.addDefaultModel(voteDivList);
        return result;
    }
}
