package com.jxkj.fit_5a.base;

import java.io.Serializable;
import java.util.List;

public class OrderInfoData implements Serializable {

    /**
     * list : [{"acceptName":"","amount":0,"closeTime":"","couponAmount":0,"couponId":0,"createTime":"","deductIntegral":0,"deliveryAmount":0,"deliveryTime":"","deliveryType":0,"detail":{"acceptName":"","cityId":0,"districtId":0,"location":"","logistics":"","logisticsNo":"","mobile":"","orderId":0,"provinceId":0,"regions":"","remark":""},"disCountamount":0,"expireTime":"","explain":"","finishTime":"","hasClear":0,"hasDel":0,"hasExpediting":0,"id":0,"location":"","mobile":"","orderNo":"","orderType":0,"orderTypeStr":"","payTime":"","payType":0,"payTypeStr":"","productList":[{"count":0,"createTime":"","deductIntegral":0,"disPrice":0,"hasSku":0,"id":0,"imgUrl":"","name":"","num":0,"orderId":0,"price":0,"productId":0,"realNum":0,"skuId":0,"skuName":"","stock":0,"sumAmount":0,"sumIntegral":0,"type":0}],"realAmount":0,"regions":"","remarks":"","status":0,"statusStr":"","totalNum":0,"userId":0}]
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
         * acceptName :
         * amount : 0
         * closeTime :
         * couponAmount : 0
         * couponId : 0
         * createTime :
         * deductIntegral : 0
         * deliveryAmount : 0
         * deliveryTime :
         * deliveryType : 0
         * detail : {"acceptName":"","cityId":0,"districtId":0,"location":"","logistics":"","logisticsNo":"","mobile":"","orderId":0,"provinceId":0,"regions":"","remark":""}
         * disCountamount : 0
         * expireTime :
         * explain :
         * finishTime :
         * hasClear : 0
         * hasDel : 0
         * hasExpediting : 0
         * id : 0
         * location :
         * mobile :
         * orderNo :
         * orderType : 0
         * orderTypeStr :
         * payTime :
         * payType : 0
         * payTypeStr :
         * productList : [{"count":0,"createTime":"","deductIntegral":0,"disPrice":0,"hasSku":0,"id":0,"imgUrl":"","name":"","num":0,"orderId":0,"price":0,"productId":0,"realNum":0,"skuId":0,"skuName":"","stock":0,"sumAmount":0,"sumIntegral":0,"type":0}]
         * realAmount : 0
         * regions :
         * remarks :
         * status : 0
         * statusStr :
         * totalNum : 0
         * userId : 0
         */

        private String acceptName;
        private String amount;
        private String closeTime;
        private String couponAmount;
        private String couponId;
        private String createTime;
        private String deductIntegral;
        private String deliveryAmount;
        private String deliveryTime;
        private String deliveryType;
        private DetailBean detail;
        private String disCountamount;
        private String expireTime;
        private String explain;
        private String finishTime;
        private String hasClear;
        private String hasDel;
        private String hasExpediting;
        private String id;
        private String location;
        private String mobile;
        private String orderNo;
        private String orderType;
        private String orderTypeStr;
        private String payTime;
        private String payType;
        private String payTypeStr;
        private String realAmount;
        private String regions;
        private String remarks;
        private String status;
        private String statusStr;
        private String totalNum;
        private String userId;
        private List<ProductListBean> productList;

        public String getAcceptName() {
            return acceptName;
        }

        public void setAcceptName(String acceptName) {
            this.acceptName = acceptName;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getCloseTime() {
            return closeTime;
        }

        public void setCloseTime(String closeTime) {
            this.closeTime = closeTime;
        }

        public String getCouponAmount() {
            return couponAmount;
        }

        public void setCouponAmount(String couponAmount) {
            this.couponAmount = couponAmount;
        }

        public String getCouponId() {
            return couponId;
        }

        public void setCouponId(String couponId) {
            this.couponId = couponId;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getDeductIntegral() {
            return deductIntegral;
        }

        public void setDeductIntegral(String deductIntegral) {
            this.deductIntegral = deductIntegral;
        }

        public String getDeliveryAmount() {
            return deliveryAmount;
        }

        public void setDeliveryAmount(String deliveryAmount) {
            this.deliveryAmount = deliveryAmount;
        }

        public String getDeliveryTime() {
            return deliveryTime;
        }

        public void setDeliveryTime(String deliveryTime) {
            this.deliveryTime = deliveryTime;
        }

        public String getDeliveryType() {
            return deliveryType;
        }

        public void setDeliveryType(String deliveryType) {
            this.deliveryType = deliveryType;
        }

        public DetailBean getDetail() {
            return detail;
        }

        public void setDetail(DetailBean detail) {
            this.detail = detail;
        }

        public String getDisCountamount() {
            return disCountamount;
        }

        public void setDisCountamount(String disCountamount) {
            this.disCountamount = disCountamount;
        }

        public String getExpireTime() {
            return expireTime;
        }

        public void setExpireTime(String expireTime) {
            this.expireTime = expireTime;
        }

        public String getExplain() {
            return explain;
        }

        public void setExplain(String explain) {
            this.explain = explain;
        }

        public String getFinishTime() {
            return finishTime;
        }

        public void setFinishTime(String finishTime) {
            this.finishTime = finishTime;
        }

        public String getHasClear() {
            return hasClear;
        }

        public void setHasClear(String hasClear) {
            this.hasClear = hasClear;
        }

        public String getHasDel() {
            return hasDel;
        }

        public void setHasDel(String hasDel) {
            this.hasDel = hasDel;
        }

        public String getHasExpediting() {
            return hasExpediting;
        }

        public void setHasExpediting(String hasExpediting) {
            this.hasExpediting = hasExpediting;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public String getOrderType() {
            return orderType;
        }

        public void setOrderType(String orderType) {
            this.orderType = orderType;
        }

        public String getOrderTypeStr() {
            return orderTypeStr;
        }

        public void setOrderTypeStr(String orderTypeStr) {
            this.orderTypeStr = orderTypeStr;
        }

        public String getPayTime() {
            return payTime;
        }

        public void setPayTime(String payTime) {
            this.payTime = payTime;
        }

        public String getPayType() {
            return payType;
        }

        public void setPayType(String payType) {
            this.payType = payType;
        }

        public String getPayTypeStr() {
            return payTypeStr;
        }

        public void setPayTypeStr(String payTypeStr) {
            this.payTypeStr = payTypeStr;
        }

        public String getRealAmount() {
            return realAmount;
        }

        public void setRealAmount(String realAmount) {
            this.realAmount = realAmount;
        }

        public String getRegions() {
            return regions;
        }

        public void setRegions(String regions) {
            this.regions = regions;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getStatusStr() {
            return statusStr;
        }

        public void setStatusStr(String statusStr) {
            this.statusStr = statusStr;
        }

        public String getTotalNum() {
            return totalNum;
        }

        public void setTotalNum(String totalNum) {
            this.totalNum = totalNum;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public List<ProductListBean> getProductList() {
            return productList;
        }

        public void setProductList(List<ProductListBean> productList) {
            this.productList = productList;
        }

        public static class DetailBean {
            /**
             * acceptName :
             * cityId : 0
             * districtId : 0
             * location :
             * logistics :
             * logisticsNo :
             * mobile :
             * orderId : 0
             * provinceId : 0
             * regions :
             * remark :
             */

            private String acceptName;
            private String cityId;
            private String districtId;
            private String location;
            private String logistics;
            private String logisticsNo;
            private String mobile;
            private String orderId;
            private String provinceId;
            private String regions;
            private String remark;

            public String getAcceptName() {
                return acceptName;
            }

            public void setAcceptName(String acceptName) {
                this.acceptName = acceptName;
            }

            public String getCityId() {
                return cityId;
            }

            public void setCityId(String cityId) {
                this.cityId = cityId;
            }

            public String getDistrictId() {
                return districtId;
            }

            public void setDistrictId(String districtId) {
                this.districtId = districtId;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public String getLogistics() {
                return logistics;
            }

            public void setLogistics(String logistics) {
                this.logistics = logistics;
            }

            public String getLogisticsNo() {
                return logisticsNo;
            }

            public void setLogisticsNo(String logisticsNo) {
                this.logisticsNo = logisticsNo;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getOrderId() {
                return orderId;
            }

            public void setOrderId(String orderId) {
                this.orderId = orderId;
            }

            public String getProvinceId() {
                return provinceId;
            }

            public void setProvinceId(String provinceId) {
                this.provinceId = provinceId;
            }

            public String getRegions() {
                return regions;
            }

            public void setRegions(String regions) {
                this.regions = regions;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }
        }

        public static class ProductListBean {
            /**
             * count : 0
             * createTime :
             * deductIntegral : 0
             * disPrice : 0
             * hasSku : 0
             * id : 0
             * imgUrl :
             * name :
             * num : 0
             * orderId : 0
             * price : 0
             * productId : 0
             * realNum : 0
             * skuId : 0
             * skuName :
             * stock : 0
             * sumAmount : 0
             * sumIntegral : 0
             * type : 0
             */

            private String count;
            private String createTime;
            private String deductIntegral;
            private String disPrice;
            private String hasSku;
            private String id;
            private String imgUrl;
            private String name;
            private String num;
            private String orderId;
            private String price;
            private String productId;
            private String realNum;
            private String skuId;
            private String skuName;
            private String stock;
            private String sumAmount;
            private String sumIntegral;
            private String type;

            public String getCount() {
                return count;
            }

            public void setCount(String count) {
                this.count = count;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getDeductIntegral() {
                return deductIntegral;
            }

            public void setDeductIntegral(String deductIntegral) {
                this.deductIntegral = deductIntegral;
            }

            public String getDisPrice() {
                return disPrice;
            }

            public void setDisPrice(String disPrice) {
                this.disPrice = disPrice;
            }

            public String getHasSku() {
                return hasSku;
            }

            public void setHasSku(String hasSku) {
                this.hasSku = hasSku;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
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

            public String getNum() {
                return num;
            }

            public void setNum(String num) {
                this.num = num;
            }

            public String getOrderId() {
                return orderId;
            }

            public void setOrderId(String orderId) {
                this.orderId = orderId;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getProductId() {
                return productId;
            }

            public void setProductId(String productId) {
                this.productId = productId;
            }

            public String getRealNum() {
                return realNum;
            }

            public void setRealNum(String realNum) {
                this.realNum = realNum;
            }

            public String getSkuId() {
                return skuId;
            }

            public void setSkuId(String skuId) {
                this.skuId = skuId;
            }

            public String getSkuName() {
                return skuName;
            }

            public void setSkuName(String skuName) {
                this.skuName = skuName;
            }

            public String getStock() {
                return stock;
            }

            public void setStock(String stock) {
                this.stock = stock;
            }

            public String getSumAmount() {
                return sumAmount;
            }

            public void setSumAmount(String sumAmount) {
                this.sumAmount = sumAmount;
            }

            public String getSumIntegral() {
                return sumIntegral;
            }

            public void setSumIntegral(String sumIntegral) {
                this.sumIntegral = sumIntegral;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }
    }
}
