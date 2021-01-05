package com.jxkj.fit_5a.entity;

import java.io.Serializable;
import java.util.List;

public class PostOrderInfo implements Serializable {

    /**
     * addressId : 0
     * agentId : 0
     * entityList : [{"agentId":0,"groupId":0,"id":0,"productId":0,"quantity":0,"shareFlag":0,"skuId":0,"spikeId":0}]
     * groupId : 0
     * StringegralFlag : 0
     * levelMessage :
     * orderType : 0
     * redId : 0
     * userId : 0
     */

    private String addressId;
    private String agentId;
    private String groupId;
    private String StringegralFlag;
    private String levelMessage;
    private String orderType;
    private String redId;
    private String userId;
    private List<EntityListBean> entityList;

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getIntegralFlag() {
        return StringegralFlag;
    }

    public void setIntegralFlag(String StringegralFlag) {
        this.StringegralFlag = StringegralFlag;
    }

    public String getLevelMessage() {
        return levelMessage;
    }

    public void setLevelMessage(String levelMessage) {
        this.levelMessage = levelMessage;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getRedId() {
        return redId;
    }

    public void setRedId(String redId) {
        this.redId = redId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<EntityListBean> getEntityList() {
        return entityList;
    }

    public void setEntityList(List<EntityListBean> entityList) {
        this.entityList = entityList;
    }

    public static class EntityListBean implements Serializable{
        /**
         * agentId : 0
         * groupId : 0
         * id : 0
         * productId : 0
         * quantity : 0
         * shareFlag : 0
         * skuId : 0
         * spikeId : 0
         */

        private String agentId;
        private String groupId;
        private String id;
        private String productId;
        private String quantity;
        private String shareFlag;
        private String skuId;
        private String spikeId;

        public String getAgentId() {
            return agentId;
        }

        public void setAgentId(String agentId) {
            this.agentId = agentId;
        }

        public String getGroupId() {
            return groupId;
        }

        public void setGroupId(String groupId) {
            this.groupId = groupId;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }

        public String getShareFlag() {
            return shareFlag;
        }

        public void setShareFlag(String shareFlag) {
            this.shareFlag = shareFlag;
        }

        public String getSkuId() {
            return skuId;
        }

        public void setSkuId(String skuId) {
            this.skuId = skuId;
        }

        public String getSpikeId() {
            return spikeId;
        }

        public void setSpikeId(String spikeId) {
            this.spikeId = spikeId;
        }

        @Override
        public String toString() {
            return "EntityListBean{" +
                    "agentId='" + agentId + '\'' +
                    ", groupId='" + groupId + '\'' +
                    ", id='" + id + '\'' +
                    ", productId='" + productId + '\'' +
                    ", quantity='" + quantity + '\'' +
                    ", shareFlag='" + shareFlag + '\'' +
                    ", skuId='" + skuId + '\'' +
                    ", spikeId='" + spikeId + '\'' +
                    '}';
        }
    }
}
