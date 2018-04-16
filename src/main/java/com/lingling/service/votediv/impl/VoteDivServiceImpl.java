package com.lingling.service.votediv.impl;

import com.lingling.dao.votediv.VoteDivDao;
import com.lingling.domin.votediv.VoteDiv;
import com.lingling.service.baseservice.BaseService;
import com.lingling.service.votediv.VoteDivService;
import com.lingling.utils.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public int insert(VoteDiv record) {
        record.setId(IdGenerator.getId());
        return voteDivDao.insert(record);
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
    public List<VoteDiv> getVoteItemByIds(List voteResultList) {
        return voteDivDao.getVoteItemByIds(voteResultList);
    }
}
