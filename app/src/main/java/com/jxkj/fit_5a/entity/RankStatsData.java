package com.jxkj.fit_5a.entity;

import java.util.List;

public class RankStatsData {

    /**
     * list : [{"avatar":"","cal":0,"hasZan":true,"id":0,"nickName":"","ranking":0,"type":0,"userId":0,"zan":0}]
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
         * avatar :
         * cal : 0
         * hasZan : true
         * id : 0
         * nickName :
         * ranking : 0
         * type : 0
         * userId : 0
         * zan : 0
         */

        private String avatar;
        private int cal;
        private boolean hasZan;
        private int id;
        private String nickName;
        private int ranking;
        private int type;
        private int userId;
        private int zan;

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public int getCal() {
            return cal;
        }

        public void setCal(int cal) {
            this.cal = cal;
        }

        public boolean isHasZan() {
            return hasZan;
        }

        public void setHasZan(boolean hasZan) {
            this.hasZan = hasZan;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public int getRanking() {
            return ranking;
        }

        public void setRanking(int ranking) {
            this.ranking = ranking;
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

        public int getZan() {
            return zan;
        }

        public void setZan(int zan) {
            this.zan = zan;
        }
    }
}
