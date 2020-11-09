package com.jxkj.fit_5a.base;

import java.util.List;

public class DeviceCourseTypeData {

    /**
     * list : [{"id":1,"deviceId":0,"name":"设备课程类型一","sort":0},{"id":2,"deviceId":0,"name":"设备课程类型二","sort":0}]
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
         * id : 1
         * deviceId : 0
         * name : 设备课程类型一
         * sort : 0
         */

        private int id;
        private int deviceId;
        private String name;
        private int sort;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(int deviceId) {
            this.deviceId = deviceId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }
    }
}
