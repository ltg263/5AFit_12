package com.jxkj.fit_5a.base;

import java.util.List;

public class DeviceTypeData {

    /**
     * list : [{"id":2,"parentId":0,"name":"设备类型二","brandIds":"4","hasDel":0,"subTitle":"xx","img":"https://haide.nbqichen.com/haide/upload/3E4AF99151356675D4565C313C6E7474.png"},{"id":1,"parentId":0,"name":"设备类型一","brandIds":"4,3,1","hasDel":0,"subTitle":"xxxxxx","img":"https://haide.nbqichen.com/haide/upload/CEC73BA7C3B06E9A55B7982189236DD7.png"}]
     * totalCount : 2
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
         * parentId : 0
         * name : 设备类型二
         * brandIds : 4
         * hasDel : 0
         * subTitle : xx
         * img : https://haide.nbqichen.com/haide/upload/3E4AF99151356675D4565C313C6E7474.png
         */

        private int id;
        private int parentId;
        private String name;
        private String brandIds;
        private int hasDel;
        private String subTitle;
        private String img;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getParentId() {
            return parentId;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getBrandIds() {
            return brandIds;
        }

        public void setBrandIds(String brandIds) {
            this.brandIds = brandIds;
        }

        public int getHasDel() {
            return hasDel;
        }

        public void setHasDel(int hasDel) {
            this.hasDel = hasDel;
        }

        public String getSubTitle() {
            return subTitle;
        }

        public void setSubTitle(String subTitle) {
            this.subTitle = subTitle;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }
}
