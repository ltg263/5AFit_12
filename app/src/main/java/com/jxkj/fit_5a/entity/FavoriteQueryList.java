package com.jxkj.fit_5a.entity;

public class FavoriteQueryList {

    /**
     * circleId : 0
     * moment : {"circleId":0,"commentCount":0,"contentType":0,"delete":true,"isLike":true,"likeCount":0,"location":"","media":"","momentId":0,"pageviews":0,"position":"","publisherId":0,"shareType":0,"simpleContent":"","timestamp":0,"topicArr":"","user":{"avatar":"","gender":0,"nickName":"","relation":0,"userId":0},"violation":true}
     * momentId : 0
     * momentPublisherId : 0
     * userId : 0
     */

    private int circleId;
    private MomentBean moment;
    private int momentId;
    private int momentPublisherId;
    private int userId;

    public int getCircleId() {
        return circleId;
    }

    public void setCircleId(int circleId) {
        this.circleId = circleId;
    }

    public MomentBean getMoment() {
        return moment;
    }

    public void setMoment(MomentBean moment) {
        this.moment = moment;
    }

    public int getMomentId() {
        return momentId;
    }

    public void setMomentId(int momentId) {
        this.momentId = momentId;
    }

    public int getMomentPublisherId() {
        return momentPublisherId;
    }

    public void setMomentPublisherId(int momentPublisherId) {
        this.momentPublisherId = momentPublisherId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public static class MomentBean {
        /**
         * circleId : 0
         * commentCount : 0
         * contentType : 0
         * delete : true
         * isLike : true
         * likeCount : 0
         * location :
         * media :
         * momentId : 0
         * pageviews : 0
         * position :
         * publisherId : 0
         * shareType : 0
         * simpleContent :
         * timestamp : 0
         * topicArr :
         * user : {"avatar":"","gender":0,"nickName":"","relation":0,"userId":0}
         * violation : true
         */

        private int circleId;
        private int commentCount;
        private int contentType;
        private boolean delete;
        private boolean isLike;
        private int likeCount;
        private String location;
        private String media;
        private int momentId;
        private int pageviews;
        private String position;
        private int publisherId;
        private int shareType;
        private String simpleContent;
        private int timestamp;
        private String topicArr;
        private UserBean user;
        private boolean violation;

        public int getCircleId() {
            return circleId;
        }

        public void setCircleId(int circleId) {
            this.circleId = circleId;
        }

        public int getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(int commentCount) {
            this.commentCount = commentCount;
        }

        public int getContentType() {
            return contentType;
        }

        public void setContentType(int contentType) {
            this.contentType = contentType;
        }

        public boolean isDelete() {
            return delete;
        }

        public void setDelete(boolean delete) {
            this.delete = delete;
        }

        public boolean isIsLike() {
            return isLike;
        }

        public void setIsLike(boolean isLike) {
            this.isLike = isLike;
        }

        public int getLikeCount() {
            return likeCount;
        }

        public void setLikeCount(int likeCount) {
            this.likeCount = likeCount;
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

        public int getMomentId() {
            return momentId;
        }

        public void setMomentId(int momentId) {
            this.momentId = momentId;
        }

        public int getPageviews() {
            return pageviews;
        }

        public void setPageviews(int pageviews) {
            this.pageviews = pageviews;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public int getPublisherId() {
            return publisherId;
        }

        public void setPublisherId(int publisherId) {
            this.publisherId = publisherId;
        }

        public int getShareType() {
            return shareType;
        }

        public void setShareType(int shareType) {
            this.shareType = shareType;
        }

        public String getSimpleContent() {
            return simpleContent;
        }

        public void setSimpleContent(String simpleContent) {
            this.simpleContent = simpleContent;
        }

        public int getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(int timestamp) {
            this.timestamp = timestamp;
        }

        public String getTopicArr() {
            return topicArr;
        }

        public void setTopicArr(String topicArr) {
            this.topicArr = topicArr;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public boolean isViolation() {
            return violation;
        }

        public void setViolation(boolean violation) {
            this.violation = violation;
        }

        public static class UserBean {
            /**
             * avatar :
             * gender : 0
             * nickName :
             * relation : 0
             * userId : 0
             */

            private String avatar;
            private int gender;
            private String nickName;
            private int relation;
            private int userId;

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

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }

            public int getRelation() {
                return relation;
            }

            public void setRelation(int relation) {
                this.relation = relation;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }
        }
    }
}
