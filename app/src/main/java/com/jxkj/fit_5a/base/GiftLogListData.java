package com.jxkj.fit_5a.base;

import java.util.List;

public class GiftLogListData {

    /**
     * list : [{"amount":0,"avatar":"","count":0,"createTime":"","giftId":0,"id":0,"imgUrl":"","name":"","nickName":"","realPrice":0,"scoreType":0,"toUserId":0,"type":0,"userId":0,"worth":0}]
     * totalCount : 0
     */

    private int totalCount;
    private List<ListBean> list;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * amount : 0
         * avatar :
         * count : 0
         * createTime :
         * giftId : 0
         * id : 0
         * imgUrl :
         * name :
         * nickName :
         * realPrice : 0
         * scoreType : 0
         * toUserId : 0
         * type : 0
         * userId : 0
         * worth : 0
         */

        private int amount;
        private String avatar;
        private int count;
        private String createTime;
        private int giftId;
        private int id;
        private String imgUrl;
        private String name;
        private String nickName;
        private int realPrice;
        private int scoreType;
        private int toUserId;
        private int type;
        private int userId;
        private int worth;

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getGiftId() {
            return giftId;
        }

        public void setGiftId(int giftId) {
            this.giftId = giftId;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public int getRealPrice() {
            return realPrice;
        }

        public void setRealPrice(int realPrice) {
            this.realPrice = realPrice;
        }

        public int getScoreType() {
            return scoreType;
        }

        public void setScoreType(int scoreType) {
            this.scoreType = scoreType;
        }

        public int getToUserId() {
            return toUserId;
        }

        public void setToUserId(int toUserId) {
            this.toUserId = toUserId;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getWorth() {
            return worth;
        }

        public void setWorth(int worth) {
            this.worth = worth;
        }
    }
}
