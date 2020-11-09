package com.jxkj.fit_5a.base;

import java.util.List;

public class GiftListData {
    /**
     * list : [{"balance":0,"giftId":0,"imgUrl":"","name":"","realPrice":0,"receiveBalance":0,"type":0,"userId":0,"worth":0}]
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
         * balance : 0
         * giftId : 0
         * imgUrl :
         * name :
         * realPrice : 0
         * receiveBalance : 0
         * type : 0
         * userId : 0
         * worth : 0
         */

        private int balance;
        private int giftId;
        private String imgUrl;
        private String name;
        private int realPrice;
        private int receiveBalance;
        private int type;
        private int userId;
        private int worth;

        public int getBalance() {
            return balance;
        }

        public void setBalance(int balance) {
            this.balance = balance;
        }

        public int getGiftId() {
            return giftId;
        }

        public void setGiftId(int giftId) {
            this.giftId = giftId;
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

        public int getRealPrice() {
            return realPrice;
        }

        public void setRealPrice(int realPrice) {
            this.realPrice = realPrice;
        }

        public int getReceiveBalance() {
            return receiveBalance;
        }

        public void setReceiveBalance(int receiveBalance) {
            this.receiveBalance = receiveBalance;
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
