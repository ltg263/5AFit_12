package com.jxkj.fit_5a.base;

import java.io.Serializable;
import java.util.List;

public class OrderInfoData implements Serializable {

    /**
     * list : [{"id":172,"userId":89,"orderNo":"200527112027057620","amount":18.9,"couponId":null,"couponAmount":0,"discountAmount":4,"deliveryAmount":0,"realAmount":14.9,"lunchBoxAmount":0,"remark":"（单）清蒸腊鸡腿+肉末茄子+干锅包菜等 1 件商品","payType":1,"payTime":"2020-05-27T11:20:45.367+0800","businessId":7,"receiptTime":"2020-05-27T11:20:45.382+0800","deliveryTime":null,"finishTime":null,"closeTime":"2020-05-28T11:20:45.383+0800","endCancelTime":"2020-05-27T11:21:45.367+0800","status":9,"createTime":"2020-05-27T11:20:27.859+0800","expireTime":"2020-05-27T11:50:27.859+0800","print":false,"delTf":false,"printNo":null,"reserveTime":null,"statusStr":"已完成","products":[{"id":263,"orderId":172,"productId":88,"imgUrl":"https://cnkj.nbyjdz.com/upload/0BB94F34E63F899786CFE979C9C1AEC4.jpg","name":"（单）清蒸腊鸡腿+肉末茄子+干锅包菜","num":1,"price":18.9,"realPrice":14.9,"lunchBoxPrice":0,"realNum":1,"refundId":null,"status":5,"createTime":"2020-05-27T11:20:27.000+0800","setMealProduct":null,"setMeal":null,"type":1,"categoryId":1,"categoryLevel":1}],"userAddressDTO":null,"coupons":null,"location":null,"regions":null,"acceptName":null,"mobile":null,"lat":null,"lng":null,"estimateTime":null,"cartStr":null,"active":null,"businessName":"科创美食","businessImgUrl":"https://cnkj.nbyjdz.com/upload/2F35A161A8F8A5E99B50C7BC102B898E.png","tel":"057487150666"}]
     * totalCount : 1
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

    public static class ListBean implements Serializable{
        /**
         * id : 172
         * userId : 89
         * orderNo : 200527112027057620
         * amount : 18.9
         * couponId : null
         * couponAmount : 0
         * discountAmount : 4
         * deliveryAmount : 0
         * realAmount : 14.9
         * lunchBoxAmount : 0
         * remark : （单）清蒸腊鸡腿+肉末茄子+干锅包菜等 1 件商品
         * payType : 1
         * payTime : 2020-05-27T11:20:45.367+0800
         * businessId : 7
         * receiptTime : 2020-05-27T11:20:45.382+0800
         * deliveryTime : null
         * finishTime : null
         * closeTime : 2020-05-28T11:20:45.383+0800
         * endCancelTime : 2020-05-27T11:21:45.367+0800
         * status : 9
         * createTime : 2020-05-27T11:20:27.859+0800
         * expireTime : 2020-05-27T11:50:27.859+0800
         * print : false
         * delTf : false
         * printNo : null
         * reserveTime : null
         * statusStr : 已完成
         * products : [{"id":263,"orderId":172,"productId":88,"imgUrl":"https://cnkj.nbyjdz.com/upload/0BB94F34E63F899786CFE979C9C1AEC4.jpg","name":"（单）清蒸腊鸡腿+肉末茄子+干锅包菜","num":1,"price":18.9,"realPrice":14.9,"lunchBoxPrice":0,"realNum":1,"refundId":null,"status":5,"createTime":"2020-05-27T11:20:27.000+0800","setMealProduct":null,"setMeal":null,"type":1,"categoryId":1,"categoryLevel":1}]
         * userAddressDTO : null
         * coupons : null
         * location : null
         * regions : null
         * acceptName : null
         * mobile : null
         * lat : null
         * lng : null
         * estimateTime : null
         * cartStr : null
         * active : null
         * businessName : 科创美食
         * businessImgUrl : https://cnkj.nbyjdz.com/upload/2F35A161A8F8A5E99B50C7BC102B898E.png
         * tel : 057487150666
         */
        private int id;
        private int userId;
        private String orderNo;
        private double amount;
        private Object couponId;
        private int couponAmount;
        private int discountAmount;
        private int deliveryAmount;
        private double realAmount;
        private int lunchBoxAmount;
        private String remark;
        private int payType;
        private String payTime;
        private int businessId;
        private String receiptTime;
        private Object deliveryTime;
        private Object finishTime;
        private String closeTime;
        private String endCancelTime;
        private String status;
        private String createTime;
        private String expireTime;
        private boolean print;
        private boolean delTf;
        private Object printNo;
        private Object reserveTime;
        private String statusStr;
        private Object userAddressDTO;
        private Object coupons;
        private Object location;
        private Object regions;
        private Object acceptName;
        private Object mobile;
        private Object lat;
        private Object lng;
        private Object estimateTime;
        private Object cartStr;
        private Object active;
        private String businessName;
        private String businessImgUrl;
        private String tel;
        private List<ProductsBean> products;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public Object getCouponId() {
            return couponId;
        }

        public void setCouponId(Object couponId) {
            this.couponId = couponId;
        }

        public int getCouponAmount() {
            return couponAmount;
        }

        public void setCouponAmount(int couponAmount) {
            this.couponAmount = couponAmount;
        }

        public int getDiscountAmount() {
            return discountAmount;
        }

        public void setDiscountAmount(int discountAmount) {
            this.discountAmount = discountAmount;
        }

        public int getDeliveryAmount() {
            return deliveryAmount;
        }

        public void setDeliveryAmount(int deliveryAmount) {
            this.deliveryAmount = deliveryAmount;
        }

        public double getRealAmount() {
            return realAmount;
        }

        public void setRealAmount(double realAmount) {
            this.realAmount = realAmount;
        }

        public int getLunchBoxAmount() {
            return lunchBoxAmount;
        }

        public void setLunchBoxAmount(int lunchBoxAmount) {
            this.lunchBoxAmount = lunchBoxAmount;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getPayType() {
            return payType;
        }

        public void setPayType(int payType) {
            this.payType = payType;
        }

        public String getPayTime() {
            return payTime;
        }

        public void setPayTime(String payTime) {
            this.payTime = payTime;
        }

        public int getBusinessId() {
            return businessId;
        }

        public void setBusinessId(int businessId) {
            this.businessId = businessId;
        }

        public String getReceiptTime() {
            return receiptTime;
        }

        public void setReceiptTime(String receiptTime) {
            this.receiptTime = receiptTime;
        }

        public Object getDeliveryTime() {
            return deliveryTime;
        }

        public void setDeliveryTime(Object deliveryTime) {
            this.deliveryTime = deliveryTime;
        }

        public Object getFinishTime() {
            return finishTime;
        }

        public void setFinishTime(Object finishTime) {
            this.finishTime = finishTime;
        }

        public String getCloseTime() {
            return closeTime;
        }

        public void setCloseTime(String closeTime) {
            this.closeTime = closeTime;
        }

        public String getEndCancelTime() {
            return endCancelTime;
        }

        public void setEndCancelTime(String endCancelTime) {
            this.endCancelTime = endCancelTime;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getExpireTime() {
            return expireTime;
        }

        public void setExpireTime(String expireTime) {
            this.expireTime = expireTime;
        }

        public boolean isPrint() {
            return print;
        }

        public void setPrint(boolean print) {
            this.print = print;
        }

        public boolean isDelTf() {
            return delTf;
        }

        public void setDelTf(boolean delTf) {
            this.delTf = delTf;
        }

        public Object getPrintNo() {
            return printNo;
        }

        public void setPrintNo(Object printNo) {
            this.printNo = printNo;
        }

        public Object getReserveTime() {
            return reserveTime;
        }

        public void setReserveTime(Object reserveTime) {
            this.reserveTime = reserveTime;
        }

        public String getStatusStr() {
            return statusStr;
        }

        public void setStatusStr(String statusStr) {
            this.statusStr = statusStr;
        }

        public Object getUserAddressDTO() {
            return userAddressDTO;
        }

        public void setUserAddressDTO(Object userAddressDTO) {
            this.userAddressDTO = userAddressDTO;
        }

        public Object getCoupons() {
            return coupons;
        }

        public void setCoupons(Object coupons) {
            this.coupons = coupons;
        }

        public Object getLocation() {
            return location;
        }

        public void setLocation(Object location) {
            this.location = location;
        }

        public Object getRegions() {
            return regions;
        }

        public void setRegions(Object regions) {
            this.regions = regions;
        }

        public Object getAcceptName() {
            return acceptName;
        }

        public void setAcceptName(Object acceptName) {
            this.acceptName = acceptName;
        }

        public Object getMobile() {
            return mobile;
        }

        public void setMobile(Object mobile) {
            this.mobile = mobile;
        }

        public Object getLat() {
            return lat;
        }

        public void setLat(Object lat) {
            this.lat = lat;
        }

        public Object getLng() {
            return lng;
        }

        public void setLng(Object lng) {
            this.lng = lng;
        }

        public Object getEstimateTime() {
            return estimateTime;
        }

        public void setEstimateTime(Object estimateTime) {
            this.estimateTime = estimateTime;
        }

        public Object getCartStr() {
            return cartStr;
        }

        public void setCartStr(Object cartStr) {
            this.cartStr = cartStr;
        }

        public Object getActive() {
            return active;
        }

        public void setActive(Object active) {
            this.active = active;
        }

        public String getBusinessName() {
            return businessName;
        }

        public void setBusinessName(String businessName) {
            this.businessName = businessName;
        }

        public String getBusinessImgUrl() {
            return businessImgUrl;
        }

        public void setBusinessImgUrl(String businessImgUrl) {
            this.businessImgUrl = businessImgUrl;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public List<ProductsBean> getProducts() {
            return products;
        }

        public void setProducts(List<ProductsBean> products) {
            this.products = products;
        }

        public static class ProductsBean implements Serializable {
            /**
             * id : 263
             * orderId : 172
             * productId : 88
             * imgUrl : https://cnkj.nbyjdz.com/upload/0BB94F34E63F899786CFE979C9C1AEC4.jpg
             * name : （单）清蒸腊鸡腿+肉末茄子+干锅包菜
             * num : 1
             * price : 18.9
             * realPrice : 14.9
             * lunchBoxPrice : 0
             * realNum : 1
             * refundId : null
             * status : 5
             * createTime : 2020-05-27T11:20:27.000+0800
             * setMealProduct : null
             * setMeal : null
             * type : 1
             * categoryId : 1
             * categoryLevel : 1
             */

            private int id;
            private int orderId;
            private int productId;
            private String imgUrl;
            private String name;
            private int num;
            private double price;
            private double realPrice;
            private int lunchBoxPrice;
            private int realNum;
            private Object refundId;
            private int status;
            private String createTime;
            private Object setMealProduct;
            private Object setMeal;
            private int type;
            private int categoryId;
            private int categoryLevel;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getOrderId() {
                return orderId;
            }

            public void setOrderId(int orderId) {
                this.orderId = orderId;
            }

            public int getProductId() {
                return productId;
            }

            public void setProductId(int productId) {
                this.productId = productId;
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

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public double getRealPrice() {
                return realPrice;
            }

            public void setRealPrice(double realPrice) {
                this.realPrice = realPrice;
            }

            public int getLunchBoxPrice() {
                return lunchBoxPrice;
            }

            public void setLunchBoxPrice(int lunchBoxPrice) {
                this.lunchBoxPrice = lunchBoxPrice;
            }

            public int getRealNum() {
                return realNum;
            }

            public void setRealNum(int realNum) {
                this.realNum = realNum;
            }

            public Object getRefundId() {
                return refundId;
            }

            public void setRefundId(Object refundId) {
                this.refundId = refundId;
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

            public Object getSetMealProduct() {
                return setMealProduct;
            }

            public void setSetMealProduct(Object setMealProduct) {
                this.setMealProduct = setMealProduct;
            }

            public Object getSetMeal() {
                return setMeal;
            }

            public void setSetMeal(Object setMeal) {
                this.setMeal = setMeal;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getCategoryId() {
                return categoryId;
            }

            public void setCategoryId(int categoryId) {
                this.categoryId = categoryId;
            }

            public int getCategoryLevel() {
                return categoryLevel;
            }

            public void setCategoryLevel(int categoryLevel) {
                this.categoryLevel = categoryLevel;
            }
        }
    }
}
