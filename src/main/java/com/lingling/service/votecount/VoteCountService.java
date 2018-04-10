package com.lingling.service.votecount;

import java.util.List;

/**
 * Created by Administrator on 2018/4/10.
 */
public interface VoteCountService {
    int deleteByPrimaryKey(String id);

    int insert(com.lingling.domin.votecount.VoteCount record);

    com.lingling.domin.votecount.VoteCount selectByPrimaryKey(String id);

    List<com.lingling.domin.votecount.VoteCount> selectAll();

    int updateByPrimaryKey(com.lingling.domin.votecount.VoteCount record);
}
