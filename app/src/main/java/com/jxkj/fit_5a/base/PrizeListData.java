package com.jxkj.fit_5a.base;

import java.util.List;

public class PrizeListData {

    /**
     * list : [{"couponId":0,"couponName":"","createTime":"","details":"","expireTime":"","id":0,"imgUrl":"","limitAmount":0,"reliefAmount":0,"remark":"","source":0,"status":0,"type":0,"usageTime":"","userId":0}]
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
         * couponId : 0
         * couponName :
         * createTime :
         * details :
         * expireTime :
         * id : 0
         * imgUrl :
         * limitAmount : 0
         * reliefAmount : 0
         * remark :
         * source : 0
         * status : 0
         * type : 0
         * usageTime :
         * userId : 0
         */

        private int couponId;
        private String couponName;
        private String createTime;
        private String expireDate;
        private String details;
        private String expireTime;
        private int id;
        private String imgUrl;
        private int limitAmount;
        private int reliefAmount;
        private String remark;
        private int source;
        private int status;
        private int type;
        private String usageTime;
        private int userId;

        public void setExpireDate(String expireDate) {
            this.expireDate = expireDate;
        }

        public String getExpireDate() {
            return expireDate;
        }

        public int getCouponId() {
            return couponId;
        }

        public void setCouponId(int couponId) {
            this.couponId = couponId;
        }

        public String getCouponName() {
            return couponName;
        }

        public void setCouponName(String couponName) {
            this.couponName = couponName;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getDetails() {
            return details;
        }

        public void setDetails(String details) {
            this.details = details;
        }

        public String getExpireTime() {
            return expireTime;
        }

        public void setExpireTime(String expireTime) {
            this.expireTime = expireTime;
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

        public int getLimitAmount() {
            return limitAmount;
        }

        public void setLimitAmount(int limitAmount) {
            this.limitAmount = limitAmount;
        }

        public int getReliefAmount() {
            return reliefAmount;
        }

        public void setReliefAmount(int reliefAmount) {
            this.reliefAmount = reliefAmount;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getSource() {
            return source;
        }

        public void setSource(int source) {
            this.source = source;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getUsageTime() {
            return usageTime;
        }

        public void setUsageTime(String usageTime) {
            this.usageTime = usageTime;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
