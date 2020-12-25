package com.jxkj.fit_5a.entity;

public class MomentDetailsBean {

    /**
     * publisherId : 95
     * momentId : 1608101070473000
     * user : {"userId":95,"relation":1,"nickName":"请修改昵称_17212312311","avatar":"13123","gender":0,"fansCount":3,"followCount":2,"likeCount":1,"momentLikeCount":1,"commentLikeCount":0,"momentCount":12,"favoriteCount":2,"lastUpdateMomentTimestamp":0}
     * isLike : false
     * isFavorite : false
     * likeCount : 1
     * commentCount : 0
     * topicArr : ["划船机","如何减肥"]
     * topicTypeArr : ["健身器材","热门"]
     * pageviews : 15
     * contentType : 1
     * simpleContent : 测试
     * content : 测试
     * shareType : 1
     * timestamp : 1608101073476
     * position :
     * location :
     * media :
     * violation : false
     * delete : false
     */

    private int publisherId;
    private long momentId;
    private UserBean user;
    private boolean isLike;
    private boolean isFavorite;
    private int likeCount;
    private int commentCount;
    private String topicArr;
    private String topicTypeArr;
    private int pageviews;
    private int contentType;
    private String simpleContent;
    private String content;
    private int shareType;
    private int favoriteCount;
    private long timestamp;
    private String position;
    private String location;
    private String media;
    private boolean violation;
    private boolean delete;

    public int getPublisherId() {
        return publisherId;
    }

    public void setFavoriteCount(int favoriteCount) {
        this.favoriteCount = favoriteCount;
    }

    public int getFavoriteCount() {
        return favoriteCount;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }

    public long getMomentId() {
        return momentId;
    }

    public void setMomentId(long momentId) {
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

    public boolean isIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public String getTopicArr() {
        return topicArr;
    }

    public void setTopicArr(String topicArr) {
        this.topicArr = topicArr;
    }

    public String getTopicTypeArr() {
        return topicTypeArr;
    }

    public void setTopicTypeArr(String topicTypeArr) {
        this.topicTypeArr = topicTypeArr;
    }

    public int getPageviews() {
        return pageviews;
    }

    public void setPageviews(int pageviews) {
        this.pageviews = pageviews;
    }

    public int getContentType() {
        return contentType;
    }

    public void setContentType(int contentType) {
        this.contentType = contentType;
    }

    public String getSimpleContent() {
        return simpleContent;
    }

    public void setSimpleContent(String simpleContent) {
        this.simpleContent = simpleContent;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getShareType() {
        return shareType;
    }

    public void setShareType(int shareType) {
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

    public boolean isViolation() {
        return violation;
    }

    public void setViolation(boolean violation) {
        this.violation = violation;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    public static class UserBean {
        /**
         * userId : 95
         * relation : 1
         * nickName : 请修改昵称_17212312311
         * avatar : 13123
         * gender : 0
         * fansCount : 3
         * followCount : 2
         * likeCount : 1
         * momentLikeCount : 1
         * commentLikeCount : 0
         * momentCount : 12
         * favoriteCount : 2
         * lastUpdateMomentTimestamp : 0
         */

        private int userId;
        private int relation;
        private String nickName;
        private String avatar;
        private int gender;
        private int fansCount;
        private int followCount;
        private int likeCount;
        private int momentLikeCount;
        private int commentLikeCount;
        private int momentCount;
        private int favoriteCount;
        private long lastUpdateMomentTimestamp;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
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

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public int getFansCount() {
            return fansCount;
        }

        public void setFansCount(int fansCount) {
            this.fansCount = fansCount;
        }

        public int getFollowCount() {
            return followCount;
        }

        public void setFollowCount(int followCount) {
            this.followCount = followCount;
        }

        public int getLikeCount() {
            return likeCount;
        }

        public void setLikeCount(int likeCount) {
            this.likeCount = likeCount;
        }

        public int getMomentLikeCount() {
            return momentLikeCount;
        }

        public void setMomentLikeCount(int momentLikeCount) {
            this.momentLikeCount = momentLikeCount;
        }

        public int getCommentLikeCount() {
            return commentLikeCount;
        }

        public void setCommentLikeCount(int commentLikeCount) {
            this.commentLikeCount = commentLikeCount;
        }

        public int getMomentCount() {
            return momentCount;
        }

        public void setMomentCount(int momentCount) {
            this.momentCount = momentCount;
        }

        public int getFavoriteCount() {
            return favoriteCount;
        }

        public void setFavoriteCount(int favoriteCount) {
            this.favoriteCount = favoriteCount;
        }

        public long getLastUpdateMomentTimestamp() {
            return lastUpdateMomentTimestamp;
        }

        public void setLastUpdateMomentTimestamp(long lastUpdateMomentTimestamp) {
            this.lastUpdateMomentTimestamp = lastUpdateMomentTimestamp;
        }
    }
}
