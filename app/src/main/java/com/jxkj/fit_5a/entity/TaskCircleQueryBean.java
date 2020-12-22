package com.jxkj.fit_5a.entity;

import java.util.List;

public class TaskCircleQueryBean {

    /**
     * page : 0
     * pageSize : 0
     * total : 2
     * list : [{"id":1,"deviceType":2,"deviceTypeStr":"设备类型二","paramName":"运动时长","unit":"分钟","target":10,"maxCycle":100,"minCycle":1,"reward":"{\"detail\":\"{\\\"incrementValue\\\":101,\\\"initialValue\\\":10,\\\"maxValue\\\":100}\",\"explain\":\"\",\"hasDel\":0,\"id\":3,\"imgUrl\":\"\",\"name\":\"积分10递增（max-100）\",\"status\":1,\"type\":1}","expireTime":""},{"id":2,"deviceType":2,"deviceTypeStr":"设备类型二","paramName":"运动时长","unit":"分钟","target":10,"maxCycle":100,"minCycle":1,"reward":"{\"detail\":\"{\\\"incrementValue\\\":101,\\\"initialValue\\\":10,\\\"maxValue\\\":100}\",\"explain\":\"\",\"hasDel\":0,\"id\":3,\"imgUrl\":\"\",\"name\":\"积分10递增（max-100）\",\"status\":1,\"type\":1}","expireTime":""}]
     */

    private int page;
    private int pageSize;
    private int total;
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

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 1
         * deviceType : 2
         * deviceTypeStr : 设备类型二
         * paramName : 运动时长
         * unit : 分钟
         * target : 10
         * maxCycle : 100
         * minCycle : 1
         * reward : {"detail":"{\"incrementValue\":101,\"initialValue\":10,\"maxValue\":100}","explain":"","hasDel":0,"id":3,"imgUrl":"","name":"积分10递增（max-100）","status":1,"type":1}
         * expireTime :
         */

        private int id;
        private int deviceType;
        private String deviceTypeStr;
        private String paramName;
        private String unit;
        private int target;
        private int maxCycle;
        private int minCycle;
        private String reward;
        private String expireTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getDeviceType() {
            return deviceType;
        }

        public void setDeviceType(int deviceType) {
            this.deviceType = deviceType;
        }

        public String getDeviceTypeStr() {
            return deviceTypeStr;
        }

        public void setDeviceTypeStr(String deviceTypeStr) {
            this.deviceTypeStr = deviceTypeStr;
        }

        public String getParamName() {
            return paramName;
        }

        public void setParamName(String paramName) {
            this.paramName = paramName;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public int getTarget() {
            return target;
        }

        public void setTarget(int target) {
            this.target = target;
        }

        public int getMaxCycle() {
            return maxCycle;
        }

        public void setMaxCycle(int maxCycle) {
            this.maxCycle = maxCycle;
        }

        public int getMinCycle() {
            return minCycle;
        }

        public void setMinCycle(int minCycle) {
            this.minCycle = minCycle;
        }

        public String getReward() {
            return reward;
        }

        public void setReward(String reward) {
            this.reward = reward;
        }

        public String getExpireTime() {
            return expireTime;
        }

        public void setExpireTime(String expireTime) {
            this.expireTime = expireTime;
        }
    }
}

