package com.jxkj.fit_5a.entity;

import java.util.List;

public class CircleQueryJoinedBean {

    /**
     * list : [{"completedCount":0,"createTime":"","explain":"","id":0,"imgUrl":"","join":true,"memberCount":0,"momentCount":0,"name":""}]
     * page : 0
     * pageSize : 0
     * total : 0
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
         * completedCount : 0
         * createTime :
         * explain :
         * id : 0
         * imgUrl :
         * join : true
         * memberCount : 0
         * momentCount : 0
         * name :
         */

        private int completedCount;
        private String createTime;
        private String explain;
        private int id;
        private String imgUrl;
        private boolean join;
        private int memberCount;
        private int momentCount;
        private String name;

        public int getCompletedCount() {
            return completedCount;
        }

        public void setCompletedCount(int completedCount) {
            this.completedCount = completedCount;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getExplain() {
            return explain;
        }

        public void setExplain(String explain) {
            this.explain = explain;
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

        public boolean isJoin() {
            return join;
        }

        public void setJoin(boolean join) {
            this.join = join;
        }

        public int getMemberCount() {
            return memberCount;
        }

        public void setMemberCount(int memberCount) {
            this.memberCount = memberCount;
        }

        public int getMomentCount() {
            return momentCount;
        }

        public void setMomentCount(int momentCount) {
            this.momentCount = momentCount;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
