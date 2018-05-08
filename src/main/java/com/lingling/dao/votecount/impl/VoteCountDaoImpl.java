package com.lingling.dao.votecount.impl;

import com.lingling.dao.votecount.VoteCountDao;
import com.lingling.domin.votecount.VoteCount;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wanghao on 2018/4/10.
 *
 */
@Repository
public class VoteCountDaoImpl implements VoteCountDao{
    @Resource(name="sqlSessionTemplate")
    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    public int deleteByPrimaryKey(String id) {
        return sqlSessionTemplate.delete("VoteCountMapper.deleteByPrimaryKey",id);
    }

    @Override
    public int insert(VoteCount record) {
        return sqlSessionTemplate.insert("VoteCountMapper.insert",record);
    }

    @Override
    public VoteCount selectByPrimaryKey(String id) {
        return sqlSessionTemplate.selectOne("VoteCountMapper.selectByPrimaryKey",id);
    }

    @Override
    public List<VoteCount> selectAll(VoteCount voteCount) {
        return sqlSessionTemplate.selectList("VoteCountMapper.selectAll");
    }

    @Override
    public int updateByPrimaryKey(VoteCount record) {
        return sqlSessionTemplate.update("VoteCountMapper.updateByPrimaryKey",record);
    }

    @Override
    public List getUserIDByVoteId(String voteId) {
        return sqlSessionTemplate.selectList("VoteCountMapper.getUserByVoteId",voteId);
    }

    @Override
    public List getVoteResult(List voteDivList) {
        return sqlSessionTemplate.selectList("VoteCountMapper.getVoteResult",voteDivList);
    }



}
