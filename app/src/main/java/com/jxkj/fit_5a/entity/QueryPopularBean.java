package com.jxkj.fit_5a.entity;

import java.util.List;

public class QueryPopularBean {

    /**
     * code : 000000
     * mesg : 处理成功
     * sub_code : 000000
     * sub_mesg : 处理成功
     * time : 2020-12-21T11:11:05.262Z
     * data : [{"publisherId":95,"momentId":1608101070473000,"user":{"userId":95,"relation":0,"nickName":"请修改昵称_17212312311","avatar":"13123","gender":0},"isLike":false,"likeCount":1,"commentCount":0,"topicArr":"[\"划船机\",\"如何减肥\"]","pageviews":3,"contentType":1,"simpleContent":"测试","shareType":1,"timestamp":1608101073476,"position":"","location":"","media":""},{"publisherId":95,"momentId":1608099987540000,"user":{"userId":95,"relation":0,"nickName":"请修改昵称_17212312311","avatar":"13123","gender":0},"isLike":false,"likeCount":0,"commentCount":1,"topicArr":"","pageviews":0,"contentType":1,"simpleContent":"测试","shareType":1,"timestamp":1608099990387,"position":"","location":"","media":""},{"publisherId":80,"momentId":1608547648519000,"user":{"userId":80,"relation":4,"nickName":"哈巴","avatar":"https://avatar.csdnimg.cn/C/5/9/2_ltg263.jpg","gender":0},"isLike":false,"likeCount":0,"commentCount":0,"topicArr":"","pageviews":0,"contentType":2,"simpleContent":"1334566","shareType":1,"timestamp":1608547649965,"position":"","location":"","media":"https://haide.nbqichen.com/haide/upload/3E4AF99151356675D4565C313C6E7474.png,https://haide.nbqichen.com/haide/upload/3E4AF99151356675D4565C313C6E7474.png"},{"publisherId":95,"momentId":1608101038381000,"user":{"userId":95,"relation":0,"nickName":"请修改昵称_17212312311","avatar":"13123","gender":0},"isLike":false,"likeCount":0,"commentCount":0,"topicArr":"[\"划船机\",\"如何减肥\"]","pageviews":0,"contentType":1,"simpleContent":"测试","shareType":1,"timestamp":1608101041383,"position":"","location":"","media":""},{"publisherId":95,"momentId":1608101017822000,"user":{"userId":95,"relation":0,"nickName":"请修改昵称_17212312311","avatar":"13123","gender":0},"isLike":false,"likeCount":0,"commentCount":0,"topicArr":"[\"划船机\"]","pageviews":0,"contentType":1,"simpleContent":"测试","shareType":1,"timestamp":1608101020824,"position":"","location":"","media":""},{"publisherId":95,"momentId":1608100978417000,"user":{"userId":95,"relation":0,"nickName":"请修改昵称_17212312311","avatar":"13123","gender":0},"isLike":false,"likeCount":0,"commentCount":0,"topicArr":"[\"划船机\"]","pageviews":0,"contentType":1,"simpleContent":"测试","shareType":1,"timestamp":1608100981415,"position":"","location":"","media":""},{"publisherId":95,"momentId":1608100940721000,"user":{"userId":95,"relation":0,"nickName":"请修改昵称_17212312311","avatar":"13123","gender":0},"isLike":false,"likeCount":0,"commentCount":0,"topicArr":"[\"划船机\"]","pageviews":0,"contentType":1,"simpleContent":"测试","shareType":1,"timestamp":1608100943721,"position":"","location":"","media":""},{"publisherId":95,"momentId":1608100899396000,"user":{"userId":95,"relation":0,"nickName":"请修改昵称_17212312311","avatar":"13123","gender":0},"isLike":false,"likeCount":0,"commentCount":0,"topicArr":"[\"划船机\"]","pageviews":0,"contentType":1,"simpleContent":"测试","shareType":1,"timestamp":1608100902393,"position":"","location":"","media":""}]
     */

    private int code;
    private String mesg;
    private String sub_code;
    private String sub_mesg;
    private String time;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMesg() {
        return mesg;
    }

    public void setMesg(String mesg) {
        this.mesg = mesg;
    }

    public String getSub_code() {
        return sub_code;
    }

    public void setSub_code(String sub_code) {
        this.sub_code = sub_code;
    }

    public String getSub_mesg() {
        return sub_mesg;
    }

    public void setSub_mesg(String sub_mesg) {
        this.sub_mesg = sub_mesg;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
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

        public static class UserBean {
            /**
             * userId : 95
             * relation : 0
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
