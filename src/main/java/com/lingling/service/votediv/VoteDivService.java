package com.lingling.service.votediv;

import com.lingling.domin.votediv.VoteDiv;

import java.util.List;

/**
 * Created by Administrator on 2018/4/10.
 */
public interface VoteDivService {
    int deleteByPrimaryKey(String id);

    int insert(VoteDiv record);

    VoteDiv selectByPrimaryKey(String id);

    List<VoteDiv> selectAll();

    int updateByPrimaryKey(VoteDiv record);

    List<VoteDiv> getVoteItemByIds(List voteResultList);
}
