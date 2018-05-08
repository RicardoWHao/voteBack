package com.lingling.dao.votecount;

import com.lingling.domin.votecount.VoteCount;

import java.util.List;

public interface VoteCountDao {
    int deleteByPrimaryKey(String id);

    int insert(VoteCount record);

    VoteCount selectByPrimaryKey(String id);

    List<VoteCount> selectAll(VoteCount voteCount);

    int updateByPrimaryKey(VoteCount record);

    List getUserIDByVoteId(String voteId);

    List getVoteResult(List voteDivList);



}