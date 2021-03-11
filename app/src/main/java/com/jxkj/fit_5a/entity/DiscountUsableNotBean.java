package com.jxkj.fit_5a.entity;

import java.util.List;

public class DiscountUsableNotBean {

    /**
     * page : 1
     * pageSize : 10
     * total : 7
     * totalCount : 7
     * list : [{"id":23,"productId":0,"productBase":null,"type":1,"validityDays":24,"couponName":"满1000减200","imgUrl":"http://5a-fit.oss-cn-hangzhou.aliyuncs.com/portrait/jCP0orK69oxsbAIFQmlmKQ.jpg","remark":"","limitAmount":1000,"reliefAmount":200,"details":""},{"id":24,"productId":0,"productBase":null,"type":1,"validityDays":30,"couponName":"满500减50","imgUrl":"http://5a-fit.oss-cn-hangzhou.aliyuncs.com/portrait/0wgsT89W78OFxUIKQYpQQA.jpg","remark":"","limitAmount":500,"reliefAmount":50,"details":""},{"id":27,"productId":0,"productBase":null,"type":1,"validityDays":80,"couponName":"满300减30","imgUrl":"http://5a-fit.oss-cn-hangzhou.aliyuncs.com/portrait/Grstw9djEZRPb6ZgPuq3Q.jpg","remark":"","limitAmount":300,"reliefAmount":30,"details":""},{"id":25,"productId":0,"productBase":null,"type":1,"validityDays":50,"couponName":"满200减20","imgUrl":"http://5a-fit.oss-cn-hangzhou.aliyuncs.com/portrait/FdRC8qic7kD3MzYDnxEbmA.jpg","remark":"","limitAmount":200,"reliefAmount":20,"details":""},{"id":11,"productId":0,"productBase":null,"type":1,"validityDays":12,"couponName":"测试","imgUrl":"http://5a-fit.oss-cn-hangzhou.aliyuncs.com/portrait/SjAQct7GtqSQUOWylM15gw.png","remark":"","limitAmount":120,"reliefAmount":12,"details":""},{"id":4,"productId":0,"productBase":null,"type":1,"validityDays":-1,"couponName":"测试优惠券","imgUrl":"http://5a-fit.oss-cn-hangzhou.aliyuncs.com/portrait/SjAQct7GtqSQUOWylM15gw.png","remark":"","limitAmount":100,"reliefAmount":10,"details":""},{"id":26,"productId":0,"productBase":null,"type":1,"validityDays":12,"couponName":"满100减10","imgUrl":"http://5a-fit.oss-cn-hangzhou.aliyuncs.com/portrait/5gVfUK1cF1lZXeQBxtdbAg.jpg","remark":"","limitAmount":100,"reliefAmount":10,"details":""}]
     */

    private int page;
    private int pageSize;
    private int total;
    private int totalCount;
    private List<ListBean> list;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

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
         * id : 23
         * productId : 0
         * productBase : null
         * type : 1
         * validityDays : 24
         * couponName : 满1000减200
         * imgUrl : http://5a-fit.oss-cn-hangzhou.aliyuncs.com/portrait/jCP0orK69oxsbAIFQmlmKQ.jpg
         * remark :
         * limitAmount : 1000
         * reliefAmount : 200
         * details :
         */

        private int id;
        private int productId;
        private Object productBase;
        private int type;
        private int validityDays;
        private String couponName;
        private String imgUrl;
        private String remark;
        private int limitAmount;
        private int reliefAmount;
        private String details;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getProductId() {
            return productId;
        }

        public void setProductId(int productId) {
            this.productId = productId;
        }

        public Object getProductBase() {
            return productBase;
        }

        public void setProductBase(Object productBase) {
            this.productBase = productBase;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getValidityDays() {
            return validityDays;
        }

        public void setValidityDays(int validityDays) {
            this.validityDays = validityDays;
        }

        public String getCouponName() {
            return couponName;
        }

        public void setCouponName(String couponName) {
            this.couponName = couponName;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
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

        public String getDetails() {
            return details;
        }

        public void setDetails(String details) {
            this.details = details;
        }
    }
}
