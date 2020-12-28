package com.jxkj.fit_5a.entity;

public class FavoriteQueryList {

    /**
     * userId : 80
     * circleId : 0
     * momentId : 1608694802238000
     * momentPublisherId : 95
     * moment : {"circleId":0,"publisherId":95,"momentId":1608694802238000,"user":{"userId":95,"relation":1,"nickName":"请修改昵称_17212312311","avatar":"13123","gender":0},"isLike":false,"likeCount":0,"commentCount":0,"topicArr":"[\"划船机\",\"如何减肥\"]","pageviews":4,"contentType":1,"simpleContent":"测试沈彪，你好啊","shareType":1,"timestamp":1608694803187,"position":"","location":"","media":"","delete":false,"violation":false}
     */

    private int userId;
    private int circleId;
    private long momentId;
    private int momentPublisherId;
    private MomentBean moment;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCircleId() {
        return circleId;
    }

    public void setCircleId(int circleId) {
        this.circleId = circleId;
    }

    public long getMomentId() {
        return momentId;
    }

    public void setMomentId(long momentId) {
        this.momentId = momentId;
    }

    public int getMomentPublisherId() {
        return momentPublisherId;
    }

    public void setMomentPublisherId(int momentPublisherId) {
        this.momentPublisherId = momentPublisherId;
    }

    public MomentBean getMoment() {
        return moment;
    }

    public void setMoment(MomentBean moment) {
        this.moment = moment;
    }

    public static class MomentBean {
        /**
         * circleId : 0
         * publisherId : 95
         * momentId : 1608694802238000
         * user : {"userId":95,"relation":1,"nickName":"请修改昵称_17212312311","avatar":"13123","gender":0}
         * isLike : false
         * likeCount : 0
         * commentCount : 0
         * topicArr : ["划船机","如何减肥"]
         * pageviews : 4
         * contentType : 1
         * simpleContent : 测试沈彪，你好啊
         * shareType : 1
         * timestamp : 1608694803187
         * position :
         * location :
         * media :
         * delete : false
         * violation : false
         */

        private int circleId;
        private int publisherId;
        private long momentId;
        private UserBean user;
        private boolean isLike;
        private int likeCount;
        private int commentCount;
        private String topicArr;
        private int pageviews;
        private int contentType;
        private String simpleContent;
        private int shareType;
        private long timestamp;
        private String position;
        private String location;
        private String media;
        private boolean delete;
        private boolean violation;

        public int getCircleId() {
            return circleId;
        }

        public void setCircleId(int circleId) {
            this.circleId = circleId;
        }

        public int getPublisherId() {
            return publisherId;
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

        public boolean isDelete() {
            return delete;
        }

        public void setDelete(boolean delete) {
            this.delete = delete;
        }

        public boolean isViolation() {
            return violation;
        }

        public void setViolation(boolean violation) {
            this.violation = violation;
        }

        public static class UserBean {
            /**
             * userId : 95
             * relation : 1
             * nickName : 请修改昵称_17212312311
             * avatar : 13123
             * gender : 0
             */

            private int userId;
            private int relation;
            private String nickName;
            private String avatar;
            private int gender;

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
        }
    }
}
