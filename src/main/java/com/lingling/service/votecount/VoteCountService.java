package com.lingling.service.votecount;

import com.lingling.domin.votecount.VoteCount;
import com.lingling.utils.Result;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Administrator on 2018/4/10.
 */
public interface VoteCountService {
    int deleteByPrimaryKey(String id);

    int insert(VoteCount record);

    VoteCount selectByPrimaryKey(String id);

    List<VoteCount> selectAll(VoteCount voteCount);

    int updateByPrimaryKey(VoteCount record);

    List getUserByVoteId(String voteId);

    List getVoteResult(String voteTopicId);

    Result getMyVote(String voteTopicId, HttpSession httpSession);
}
