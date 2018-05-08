package com.lingling.service.votediv;

import com.lingling.domin.votediv.VoteDiv;
import com.lingling.utils.Result;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Administrator on 2018/4/10.
 */
public interface VoteDivService {
    int deleteByPrimaryKey(String id);

    Result insert(VoteDiv record,HttpSession httpSession);

    VoteDiv selectByPrimaryKey(String id);

    List<VoteDiv> selectAll();

    int updateByPrimaryKey(VoteDiv record);

    List<VoteDiv> getVoteItemByIds(List voteResultList);
}
