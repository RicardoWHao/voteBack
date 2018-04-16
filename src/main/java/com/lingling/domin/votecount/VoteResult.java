package com.lingling.domin.votecount;

/**
 * Created by Administrator on 2018/4/12.
 */
public class VoteResult {
    private String count;
    private String voteItemId;
    private String vateItemName;
    private String userId;
    private String vateItemDecrib;

    public String getVateItemName() {
        return vateItemName;
    }

    public void setVateItemName(String vateItemName) {
        this.vateItemName = vateItemName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getVateItemDecrib() {
        return vateItemDecrib;
    }

    public void setVateItemDecrib(String vateItemDecrib) {
        this.vateItemDecrib = vateItemDecrib;
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
