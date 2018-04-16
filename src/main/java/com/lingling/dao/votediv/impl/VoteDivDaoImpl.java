package com.lingling.dao.votediv.impl;

import com.lingling.dao.votediv.VoteDivDao;
import com.lingling.domin.votediv.VoteDiv;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wanghao on 2018/4/10.
 * email wanghaonepu@gamil.com
 */
@Repository
public class VoteDivDaoImpl implements VoteDivDao{
    @Resource(name="sqlSessionTemplate")
    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    public int deleteByPrimaryKey(String id) {
        return sqlSessionTemplate.delete("VoteDivMapper.deleteByPrimaryKey",id);
    }

    @Override
    public int insert(VoteDiv record) {
        return sqlSessionTemplate.insert("VoteDivMapper.insert",record);
    }

    @Override
    public VoteDiv selectByPrimaryKey(String id) {
        return sqlSessionTemplate.selectOne("VoteDivMapper.selectByPrimaryKey",id);
    }

    @Override
    public List<VoteDiv> selectAll() {
        return sqlSessionTemplate.selectList("VoteDivMapper.selectAll");
    }

    @Override
    public int updateByPrimaryKey(VoteDiv record) {
        return sqlSessionTemplate.update("VoteDivMapper.updateByPrimaryKey",record);
    }

    @Override
    public List<VoteDiv> getVoteItemByIds(List voteResultList) {
        return sqlSessionTemplate.selectList("VoteDivMapper.getVoteItemByIds",voteResultList);
    }
}
