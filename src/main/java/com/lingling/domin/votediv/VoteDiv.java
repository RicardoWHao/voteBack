package com.lingling.domin.votediv;

public class VoteDiv {
    private String id;

    private String vateTopicId;

    private String vateItemName;

    private String userId;

    private String productionPic;

    private String vateItemDecrib;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getVateTopicId() {
        return vateTopicId;
    }

    public void setVateTopicId(String vateTopicId) {
        this.vateTopicId = vateTopicId == null ? null : vateTopicId.trim();
    }

    public String getVateItemName() {
        return vateItemName;
    }

    public void setVateItemName(String vateItemName) {
        this.vateItemName = vateItemName == null ? null : vateItemName.trim();
    }

    public String getUserId() {
        return userId;
    }

    public String getVateItemDecrib() {
        return vateItemDecrib;
    }

    public void setVateItemDecrib(String vateItemDecrib) {
        this.vateItemDecrib = vateItemDecrib;
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