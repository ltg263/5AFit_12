package com.jxkj.fit_5a.entity;


public class QueryPopularBean {


    /**
     * publisherId : 95
     * momentId : 1608101070473000
     * user : {"userId":95,"relation":0,"nickName":"请修改昵称_17212312311","avatar":"13123","gender":0}
     * isLike : false
     * likeCount : 1
     * commentCount : 0
     * topicArr : ["划船机","如何减肥"]
     * pageviews : 3
     * contentType : 1
     * simpleContent : 测试
     * shareType : 1
     * timestamp : 1608101073476
     * position :
     * location :
     * media :
     */

    private String publisherId;
    private String momentId;
    private UserBean user;
    private boolean isLike;
    private String likeCount;
    private String commentCount;
    private String topicArr;
    private String pageviews;
    private String contentType;
    private String simpleContent;
    private String shareType;
    private long timestamp;
    private String position;
    private String location;
    private String media;

    public String getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(String publisherId) {
        this.publisherId = publisherId;
    }

    public String getMomentId() {
        return momentId;
    }

    public void setMomentId(String momentId) {
        this.momentId = momentId;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public boolean isIsLike() {
        return isLike;
    }

    public void setIsLike(boolean isLike) {
        this.isLike = isLike;
    }

    public String getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(String likeCount) {
        this.likeCount = likeCount;
    }

    public String getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(String commentCount) {
        this.commentCount = commentCount;
    }

    public String getTopicArr() {
        return topicArr;
    }

    public void setTopicArr(String topicArr) {
        this.topicArr = topicArr;
    }

    public String getPageviews() {
        return pageviews;
    }

    public void setPageviews(String pageviews) {
        this.pageviews = pageviews;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getSimpleContent() {
        return simpleContent;
    }

    public void setSimpleContent(String simpleContent) {
        this.simpleContent = simpleContent;
    }

    public String getShareType() {
        return shareType;
    }

    public void setShareType(String shareType) {
        this.shareType = shareType;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public static class UserBean {
        /**
         * userId : 95
         * relation : 0
         * nickName : 请修改昵称_17212312311
         * avatar : 13123
         * gender : 0
         */

        private String userId;
        private int relation;
        private String nickName;
        private String avatar;
        private String gender;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public int getRelation() {
            return relation;
        }

        public void setRelation(int relation) {
            this.relation = relation;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }
    }

}
