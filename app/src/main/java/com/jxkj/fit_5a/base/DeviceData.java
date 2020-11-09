package com.jxkj.fit_5a.base;

import java.util.List;

public class DeviceData {

    /**
     * list : [{"brand":0,"detail":"","id":0,"imgUrl":"","matchstr":"","model":"","name":"","status":0,"subTitle":"","type":0}]
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
         * brand : 0
         * detail :
         * id : 0
         * imgUrl :
         * matchstr :
         * model :
         * name :
         * status : 0
         * subTitle :
         * type : 0
         */

        private int brand;
        private String detail;
        private int id;
        private String imgUrl;
        private String matchstr;
        private String model;
        private String name;
        private int status;
        private String subTitle;
        private int type;

        public int getBrand() {
            return brand;
        }

        public void setBrand(int brand) {
            this.brand = brand;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
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

        public String getMatchstr() {
            return matchstr;
        }

        public void setMatchstr(String matchstr) {
            this.matchstr = matchstr;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
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

        public String getSubTitle() {
            return subTitle;
        }

        public void setSubTitle(String subTitle) {
            this.subTitle = subTitle;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
