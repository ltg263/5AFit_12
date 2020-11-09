package com.jxkj.fit_5a.base;

import java.util.List;

public class SignLogData {

    /**
     * list : [{"createTime":"","id":0,"integral":0,"signDate":0,"userId":0}]
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
         * createTime :
         * id : 0
         * integral : 0
         * signDate : 0
         * userId : 0
         */

        private String createTime;
        private int id;
        private int integral;
        private int signDate;
        private int userId;
        private String sj;
        private boolean isSig;

        public void setSig(boolean sig) {
            isSig = sig;
        }

        public boolean isSig() {
            return isSig;
        }

        public void setSj(String sj) {
            this.sj = sj;
        }

        public String getSj() {
            return sj;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIntegral() {
            return integral;
        }

        public void setIntegral(int integral) {
            this.integral = integral;
        }

        public int getSignDate() {
            return signDate;
        }

        public void setSignDate(int signDate) {
            this.signDate = signDate;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
