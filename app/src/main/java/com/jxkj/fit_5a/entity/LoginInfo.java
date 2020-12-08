package com.jxkj.fit_5a.entity;

public class LoginInfo {

    /**
     * userPermissionBaseDTO : {"id":80,"userNo":"13112345678","nickName":"请修改昵称_13112345678","level":0,"levelNo":"","levelExpireTime":"","avatar":"13123","gender":0,"status":1,"createTime":"","clientType":0,"loginType":0,"openId":"","sign":""}
     * tokenId : eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySW5mbyI6IntcImF2YXRhclwiOlwiMTMxMjNcIixcImdlbmRlclwiOjAsXCJpZFwiOjgwLFwibGV2ZWxcIjowLFwibGV2ZWxOb1wiOlwiXCIsXCJsb2dpblR5cGVcIjowLFwibmlja05hbWVcIjpcIuivt-S_ruaUueaYteensF8xMzExMjM0NTY3OFwiLFwib3BlbklkXCI6XCJcIixcInN0YXR1c1wiOjEsXCJ1c2VyTm9cIjpcIjEzMTEyMzQ1Njc4XCJ9IiwiZXhwIjoxNjA3NDAxMzg3LCJ1c2VyX25hbWUiOiJ7XCJ1c2VybmFtZVR5cGVcIjoxLFwidXNlcm5hbWVcIjpcIjEzMTEyMzQ1Njc4XCJ9IiwianRpIjoiLUV5NmZXZkE1Z05Qem9qMExFbUZBbHpfMVRBIiwiY2xpZW50X2lkIjoidGVzdF9jbGllbnQiLCJzY29wZSI6WyJyZWFkIl19.llr23aVsP68kodKygjDf6ADrL_Q0h0bsMnnnJIZcG4c
     */

    private UserPermissionBaseDTOBean userPermissionBaseDTO;
    private String tokenId;

    public UserPermissionBaseDTOBean getUserPermissionBaseDTO() {
        return userPermissionBaseDTO;
    }

    public void setUserPermissionBaseDTO(UserPermissionBaseDTOBean userPermissionBaseDTO) {
        this.userPermissionBaseDTO = userPermissionBaseDTO;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public static class UserPermissionBaseDTOBean {
        /**
         * id : 80
         * userNo : 13112345678
         * nickName : 请修改昵称_13112345678
         * level : 0
         * levelNo :
         * levelExpireTime :
         * avatar : 13123
         * gender : 0
         * status : 1
         * createTime :
         * clientType : 0
         * loginType : 0
         * openId :
         * sign :
         */

        private int id;
        private String userNo;
        private String nickName;
        private int level;
        private String levelNo;
        private String levelExpireTime;
        private String avatar;
        private int gender;
        private int status;
        private String createTime;
        private int clientType;
        private int loginType;
        private String openId;
        private String sign;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUserNo() {
            return userNo;
        }

        public void setUserNo(String userNo) {
            this.userNo = userNo;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public String getLevelNo() {
            return levelNo;
        }

        public void setLevelNo(String levelNo) {
            this.levelNo = levelNo;
        }

        public String getLevelExpireTime() {
            return levelExpireTime;
        }

        public void setLevelExpireTime(String levelExpireTime) {
            this.levelExpireTime = levelExpireTime;
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

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getClientType() {
            return clientType;
        }

        public void setClientType(int clientType) {
            this.clientType = clientType;
        }

        public int getLoginType() {
            return loginType;
        }

        public void setLoginType(int loginType) {
            this.loginType = loginType;
        }

        public String getOpenId() {
            return openId;
        }

        public void setOpenId(String openId) {
            this.openId = openId;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }
    }
}
