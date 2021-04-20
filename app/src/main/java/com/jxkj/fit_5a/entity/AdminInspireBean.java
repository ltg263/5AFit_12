package com.jxkj.fit_5a.entity;

import java.util.List;

public class AdminInspireBean {

    /**
     * list : [{"id":2,"title":"1","backImg":"https://haide.nbqichen.com/haide/upload/D343E0B0DBB8643B137E466A9ACBDE05.jpg","sort":1,"status":1,"creaetTime":"2020-10-13 09:02:46"}]
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

    public static class ListBean {
        /**
         * id : 2
         * title : 1
         * backImg : https://haide.nbqichen.com/haide/upload/D343E0B0DBB8643B137E466A9ACBDE05.jpg
         * sort : 1
         * status : 1
         * creaetTime : 2020-10-13 09:02:46
         */

        private int id;
        private String title;
        private String backImg;
        private int sort;
        private int status;
        private String creaetTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getBackImg() {
            return backImg;
        }

        public void setBackImg(String backImg) {
            this.backImg = backImg;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getCreaetTime() {
            return creaetTime;
        }

        public void setCreaetTime(String creaetTime) {
            this.creaetTime = creaetTime;
        }
    }
}
