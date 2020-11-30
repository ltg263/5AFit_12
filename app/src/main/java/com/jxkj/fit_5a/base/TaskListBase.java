package com.jxkj.fit_5a.base;

import java.util.List;

public class TaskListBase {

    /**
     * list : [{"count":0,"curCount":0,"curRound":0,"cycle":0,"expireTime":"","explain":"","finishTime":"","hasDisplay":0,"id":"","img":"","name":"","resetTime":"","resetType":0,"rewards":[{"detail":"","explain":"","hasDel":0,"id":0,"imgUrl":"","name":"","status":0,"type":0}],"roundCount":0,"speeds":[{"id":0,"paramId":0,"paramName":"","round":0,"speed":0,"status":0,"target":0,"unit":"","updateTime":"","userTaskId":""}],"status":0,"taskId":0,"type":0,"userId":0}]
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
         * count : 0
         * curCount : 0
         * curRound : 0
         * cycle : 0
         * expireTime :
         * explain :
         * finishTime :
         * hasDisplay : 0
         * id :
         * img :
         * name :
         * resetTime :
         * resetType : 0
         * rewards : [{"detail":"","explain":"","hasDel":0,"id":0,"imgUrl":"","name":"","status":0,"type":0}]
         * roundCount : 0
         * speeds : [{"id":0,"paramId":0,"paramName":"","round":0,"speed":0,"status":0,"target":0,"unit":"","updateTime":"","userTaskId":""}]
         * status : 0
         * taskId : 0
         * type : 0
         * userId : 0
         */

        private int count;
        private int curCount;
        private int curRound;
        private int cycle;
        private String expireTime;
        private String explain;
        private String finishTime;
        private int hasDisplay;
        private String id;
        private String img;
        private String name;
        private String resetTime;
        private int resetType;
        private int roundCount;
        private int status;
        private int taskId;
        private int type;
        private int userId;
        private List<RewardsBean> rewards;
        private List<SpeedsBean> speeds;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getCurCount() {
            return curCount;
        }

        public void setCurCount(int curCount) {
            this.curCount = curCount;
        }

        public int getCurRound() {
            return curRound;
        }

        public void setCurRound(int curRound) {
            this.curRound = curRound;
        }

        public int getCycle() {
            return cycle;
        }

        public void setCycle(int cycle) {
            this.cycle = cycle;
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

        public int getHasDisplay() {
            return hasDisplay;
        }

        public void setHasDisplay(int hasDisplay) {
            this.hasDisplay = hasDisplay;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getResetTime() {
            return resetTime;
        }

        public void setResetTime(String resetTime) {
            this.resetTime = resetTime;
        }

        public int getResetType() {
            return resetType;
        }

        public void setResetType(int resetType) {
            this.resetType = resetType;
        }

        public int getRoundCount() {
            return roundCount;
        }

        public void setRoundCount(int roundCount) {
            this.roundCount = roundCount;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getTaskId() {
            return taskId;
        }

        public void setTaskId(int taskId) {
            this.taskId = taskId;
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

        public List<RewardsBean> getRewards() {
            return rewards;
        }

        public void setRewards(List<RewardsBean> rewards) {
            this.rewards = rewards;
        }

        public List<SpeedsBean> getSpeeds() {
            return speeds;
        }

        public void setSpeeds(List<SpeedsBean> speeds) {
            this.speeds = speeds;
        }

        public static class RewardsBean {
            /**
             * detail :
             * explain :
             * hasDel : 0
             * id : 0
             * imgUrl :
             * name :
             * status : 0
             * type : 0
             */

            private String detail;
            private String explain;
            private int hasDel;
            private int id;
            private String imgUrl;
            private String name;
            private int status;
            private int type;

            public String getDetail() {
                return detail;
            }

            public void setDetail(String detail) {
                this.detail = detail;
            }

            public String getExplain() {
                return explain;
            }

            public void setExplain(String explain) {
                this.explain = explain;
            }

            public int getHasDel() {
                return hasDel;
            }

            public void setHasDel(int hasDel) {
                this.hasDel = hasDel;
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
        }

        public static class SpeedsBean {
            /**
             * id : 0
             * paramId : 0
             * paramName :
             * round : 0
             * speed : 0
             * status : 0
             * target : 0
             * unit :
             * updateTime :
             * userTaskId :
             */

            private int id;
            private int paramId;
            private String paramName;
            private int round;
            private int speed;
            private int status;
            private int target;
            private String unit;
            private String updateTime;
            private String userTaskId;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getParamId() {
                return paramId;
            }

            public void setParamId(int paramId) {
                this.paramId = paramId;
            }

            public String getParamName() {
                return paramName;
            }

            public void setParamName(String paramName) {
                this.paramName = paramName;
            }

            public int getRound() {
                return round;
            }

            public void setRound(int round) {
                this.round = round;
            }

            public int getSpeed() {
                return speed;
            }

            public void setSpeed(int speed) {
                this.speed = speed;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getTarget() {
                return target;
            }

            public void setTarget(int target) {
                this.target = target;
            }

            public String getUnit() {
                return unit;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public String getUserTaskId() {
                return userTaskId;
            }

            public void setUserTaskId(String userTaskId) {
                this.userTaskId = userTaskId;
            }
        }
    }
}
