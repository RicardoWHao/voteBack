package com.lingling.service.votediv.impl;

import com.lingling.dao.votediv.VoteDivDao;
import com.lingling.domin.votediv.VoteDiv;
import com.lingling.service.votediv.VoteDivService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Administrator on 2018/4/10.
 */
public class VoteDivServiceImpl implements VoteDivService{
    @Autowired
    VoteDivDao voteDivDao;

    @Override
    public int deleteByPrimaryKey(String id) {
        return voteDivDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(VoteDiv record) {
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
}
