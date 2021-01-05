package com.jxkj.fit_5a.entity;

import java.util.List;

public class ShowOrderInfo {

    /**
     * userId : 80
     * orderType : 2
     * totalQuantity : 1
     * totalAmount : 0
     * totalDelivery : 0
     * totalIntegral : 120
     * useableIntegral : 0
     * StringegralFlag : 0
     * StringegralRate : 0
     * realAmount : 0
     * discountAmount : 0
     * discount : 0
     * remark :
     * groupId : 0
     * orderFlag : 0
     * balance : 0
     * userAddress : {"id":7,"userId":80,"provinceId":440000,"cityId":440100,"districtId":440103,"regions":"广东省,广州市,荔湾区","location":"广东省,广州市,荔湾区 -110112113114115","acceptName":"测试","mobile":"13111111111","isDefult":1,"hasDel":0,"createTime":"2020-12-31 16:42:04"}
     * orderProducts : [{"id":0,"orderId":0,"type":0,"hasSku":0,"productId":3,"skuId":8,"imgUrl":"https://haide.nbqichen.com/haide/upload/887C27F0CA135ADCF20EF8686D1162D4.png","name":"海德肌肉贴KT06","num":1,"price":0,"disPrice":300,"deductIntegral":120,"realNum":0,"createTime":"","skuName":"颜色:黑色;容量:20ml","sumAmount":0,"stock":11,"sumIntegral":120,"count":0}]
     */

    private String userId;
    private String orderType;
    private String totalQuantity;
    private String totalAmount;
    private String totalDelivery;
    private String totalIntegral;
    private String useableIntegral;
    private String StringegralFlag;
    private String StringegralRate;
    private String realAmount;
    private String discountAmount;
    private String discount;
    private String remark;
    private String groupId;
    private String orderFlag;
    private String balance;
    private UserAddressBean userAddress;
    private List<OrderProductsBean> orderProducts;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(String totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getTotalDelivery() {
        return totalDelivery;
    }

    public void setTotalDelivery(String totalDelivery) {
        this.totalDelivery = totalDelivery;
    }

    public String getTotalIntegral() {
        return totalIntegral;
    }

    public void setTotalIntegral(String totalIntegral) {
        this.totalIntegral = totalIntegral;
    }

    public String getUseableIntegral() {
        return useableIntegral;
    }

    public void setUseableIntegral(String useableIntegral) {
        this.useableIntegral = useableIntegral;
    }

    public String getIntegralFlag() {
        return StringegralFlag;
    }

    public void setIntegralFlag(String StringegralFlag) {
        this.StringegralFlag = StringegralFlag;
    }

    public String getIntegralRate() {
        return StringegralRate;
    }

    public void setIntegralRate(String StringegralRate) {
        this.StringegralRate = StringegralRate;
    }

    public String getRealAmount() {
        return realAmount;
    }

    public void setRealAmount(String realAmount) {
        this.realAmount = realAmount;
    }

    public String getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(String discountAmount) {
        this.discountAmount = discountAmount;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getOrderFlag() {
        return orderFlag;
    }

    public void setOrderFlag(String orderFlag) {
        this.orderFlag = orderFlag;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public UserAddressBean getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(UserAddressBean userAddress) {
        this.userAddress = userAddress;
    }

    public List<OrderProductsBean> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<OrderProductsBean> orderProducts) {
        this.orderProducts = orderProducts;
    }

    public static class UserAddressBean {
        /**
         * id : 7
         * userId : 80
         * provinceId : 440000
         * cityId : 440100
         * districtId : 440103
         * regions : 广东省,广州市,荔湾区
         * location : 广东省,广州市,荔湾区 -110112113114115
         * acceptName : 测试
         * mobile : 13111111111
         * isDefult : 1
         * hasDel : 0
         * createTime : 2020-12-31 16:42:04
         */

        private String id;
        private String userId;
        private String provinceId;
        private String cityId;
        private String districtId;
        private String regions;
        private String location;
        private String acceptName;
        private String mobile;
        private String isDefult;
        private String hasDel;
        private String createTime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getProvinceId() {
            return provinceId;
        }

        public void setProvinceId(String provinceId) {
            this.provinceId = provinceId;
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

        public String getRegions() {
            return regions;
        }

        public void setRegions(String regions) {
            this.regions = regions;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getAcceptName() {
            return acceptName;
        }

        public void setAcceptName(String acceptName) {
            this.acceptName = acceptName;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getIsDefult() {
            return isDefult;
        }

        public void setIsDefult(String isDefult) {
            this.isDefult = isDefult;
        }

        public String getHasDel() {
            return hasDel;
        }

        public void setHasDel(String hasDel) {
            this.hasDel = hasDel;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }
    }

    public static class OrderProductsBean {
        /**
         * id : 0
         * orderId : 0
         * type : 0
         * hasSku : 0
         * productId : 3
         * skuId : 8
         * imgUrl : https://haide.nbqichen.com/haide/upload/887C27F0CA135ADCF20EF8686D1162D4.png
         * name : 海德肌肉贴KT06
         * num : 1
         * price : 0
         * disPrice : 300
         * deductIntegral : 120
         * realNum : 0
         * createTime :
         * skuName : 颜色:黑色;容量:20ml
         * sumAmount : 0
         * stock : 11
         * sumIntegral : 120
         * count : 0
         */

        private String id;
        private String orderId;
        private String type;
        private String hasSku;
        private String productId;
        private String skuId;
        private String imgUrl;
        private String name;
        private String num;
        private String price;
        private String disPrice;
        private String deductIntegral;
        private String realNum;
        private String createTime;
        private String skuName;
        private String sumAmount;
        private String stock;
        private String sumIntegral;
        private String count;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getHasSku() {
            return hasSku;
        }

        public void setHasSku(String hasSku) {
            this.hasSku = hasSku;
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public String getSkuId() {
            return skuId;
        }

        public void setSkuId(String skuId) {
            this.skuId = skuId;
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

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getDisPrice() {
            return disPrice;
        }

        public void setDisPrice(String disPrice) {
            this.disPrice = disPrice;
        }

        public String getDeductIntegral() {
            return deductIntegral;
        }

        public void setDeductIntegral(String deductIntegral) {
            this.deductIntegral = deductIntegral;
        }

        public String getRealNum() {
            return realNum;
        }

        public void setRealNum(String realNum) {
            this.realNum = realNum;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getSkuName() {
            return skuName;
        }

        public void setSkuName(String skuName) {
            this.skuName = skuName;
        }

        public String getSumAmount() {
            return sumAmount;
        }

        public void setSumAmount(String sumAmount) {
            this.sumAmount = sumAmount;
        }

        public String getStock() {
            return stock;
        }

        public void setStock(String stock) {
            this.stock = stock;
        }

        public String getSumIntegral() {
            return sumIntegral;
        }

        public void setSumIntegral(String sumIntegral) {
            this.sumIntegral = sumIntegral;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }
    }
}
