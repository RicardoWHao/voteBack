package com.lingling.domin.votecount;

/**
 * Created by Administrator on 2018/4/12.
 */
public class VoteResult {
    private String count;
    private String voteItemId;
    private String voteItemName;
    private String userId;
    private String voteItemDecrib;

    public String getVoteItemName() {
        return voteItemName;
    }

    public void setVoteItemName(String voteItemName) {
        this.voteItemName = voteItemName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getVoteItemDecrib() {
        return voteItemDecrib;
    }

    public void setVoteItemDecrib(String voteItemDecrib) {
        this.voteItemDecrib = voteItemDecrib;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getVoteItemId() {
        return voteItemId;
    }

    public void setVoteItemId(String voteItemId) {
        this.voteItemId = voteItemId;
    }
}
