package com.lingling.service.votecount.impl;

import com.lingling.dao.votecount.VoteCountDao;
import com.lingling.domin.votecount.VoteCount;
import com.lingling.service.votecount.VoteCountService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Administrator on 2018/4/10.
 */
public class VoteCountServiceImpl implements VoteCountService{
    @Autowired
    VoteCountDao voteCountDao;

    @Override
    public int deleteByPrimaryKey(String id) {
        return voteCountDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(VoteCount record) {
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
