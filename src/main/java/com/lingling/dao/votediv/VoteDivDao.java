package com.lingling.dao.votediv;

import com.lingling.domin.votediv.VoteDiv;
import java.util.List;

public interface VoteDivDao {
    int deleteByPrimaryKey(String id);

    int insert(VoteDiv record);

    VoteDiv selectByPrimaryKey(String id);

    List<VoteDiv> selectAll();

    int updateByPrimaryKey(VoteDiv record);
}