package com.jxkj.fit_5a.entity;

import java.util.List;

public class CommentMomentBean {

    /**
     * momentId : 1608101070473000
     * commentId : 1609132916162000
     * publisherId : 88
     * circleId : 0
     * momentPublisherId : 95
     * user : {"userId":88,"relation":0,"nickName":"YW-2188","avatar":"13123","gender":2}
     * isLike : false
     * replyType : 1
     * replyCommentId : 0
     * replyCommentPublisherId : 0
     * authorPublish : false
     * authorReplyCount : 0
     * likeCount : 0
     * commentCount : 1
     * contentType : 1
     * content : Daddy’s Cheshire 测试测试 速度 速度是的 是东方风格受到非法给我发新增长点的大额风水大师的恶得瑟发生的时代风格哥哥哥哥
     * timestamp : 1609132916215
     * replyComment : null
     * authorReplyComments : []
     */

    private long momentId;
    private long commentId;
    private int publisherId;
    private int circleId;
    private int momentPublisherId;
    private UserBean user;
    private boolean isLike;
    private int replyType;
    private long replyCommentId;
    private int replyCommentPublisherId;
    private boolean authorPublish;
    private int authorReplyCount;
    private int likeCount;
    private int commentCount;
    private int contentType;
    private String content;
    private long timestamp;
    private String replyComment;
    private List<CommentMomentBean> authorReplyComments;

    public long getMomentId() {
        return momentId;
    }

    public void setMomentId(long momentId) {
        this.momentId = momentId;
    }

    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }

    public int getCircleId() {
        return circleId;
    }

    public void setCircleId(int circleId) {
        this.circleId = circleId;
    }

    public int getMomentPublisherId() {
        return momentPublisherId;
    }

    public void setMomentPublisherId(int momentPublisherId) {
        this.momentPublisherId = momentPublisherId;
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

    public int getReplyType() {
        return replyType;
    }

    public void setReplyType(int replyType) {
        this.replyType = replyType;
    }

    public long getReplyCommentId() {
        return replyCommentId;
    }

    public void setReplyCommentId(long replyCommentId) {
        this.replyCommentId = replyCommentId;
    }

    public int getReplyCommentPublisherId() {
        return replyCommentPublisherId;
    }

    public void setReplyCommentPublisherId(int replyCommentPublisherId) {
        this.replyCommentPublisherId = replyCommentPublisherId;
    }

    public boolean isAuthorPublish() {
        return authorPublish;
    }

    public void setAuthorPublish(boolean authorPublish) {
        this.authorPublish = authorPublish;
    }

    public int getAuthorReplyCount() {
        return authorReplyCount;
    }

    public void setAuthorReplyCount(int authorReplyCount) {
        this.authorReplyCount = authorReplyCount;
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

    public int getContentType() {
        return contentType;
    }

    public void setContentType(int contentType) {
        this.contentType = contentType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getReplyComment() {
        return replyComment;
    }

    public void setReplyComment(String replyComment) {
        this.replyComment = replyComment;
    }

    public List<CommentMomentBean> getAuthorReplyComments() {
        return authorReplyComments;
    }

    public void setAuthorReplyComments(List<CommentMomentBean> authorReplyComments) {
        this.authorReplyComments = authorReplyComments;
    }

    public static class UserBean {
        /**
         * userId : 88
         * relation : 0
         * nickName : YW-2188
         * avatar : 13123
         * gender : 2
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
