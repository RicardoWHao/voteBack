package com.lingling.domin.votediv;

public class VoteDiv {
    private String id;

    private String voteTopicId;

    private String voteItemName;

    private String userId;

    private String productionPic;

    private String voteItemDecrib;

    private Integer voteCount;

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getVoteTopicId() {
        return voteTopicId;
    }

    public void setVoteTopicId(String voteTopicId) {
        this.voteTopicId = voteTopicId;
    }

    public String getVoteItemName() {
        return voteItemName;
    }

    public void setVoteItemName(String voteItemName) {
        this.voteItemName = voteItemName;
    }

    public String getVoteItemDecrib() {
        return voteItemDecrib;
    }

    public void setVoteItemDecrib(String voteItemDecrib) {
        this.voteItemDecrib = voteItemDecrib;
    }

    public String getUserId() {
        return userId;
    }



    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getProductionPic() {
        return productionPic;
    }

    public void setProductionPic(String productionPic) {
        this.productionPic = productionPic == null ? null : productionPic.trim();
    }
}