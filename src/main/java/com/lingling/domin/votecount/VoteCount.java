package com.lingling.domin.votecount;

public class VoteCount {
    private String id;

    private String itemId;

    private String voteUserId;

    private String voteTopicId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId == null ? null : itemId.trim();
    }

    public String getVoteUserId() {
        return voteUserId;
    }

    public void setVoteUserId(String voteUserId) {
        this.voteUserId = voteUserId == null ? null : voteUserId.trim();
    }

    public String getVoteTopicId() {
        return voteTopicId;
    }

    public void setVoteTopicId(String voteTopicId) {
        this.voteTopicId = voteTopicId == null ? null : voteTopicId.trim();
    }
}