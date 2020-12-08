package com.jxkj.fit_5a.entity;

import java.util.List;

public class MedalListData {

    /**
     * code : 000000
     * mesg : 处理成功
     * sub_code : 000000
     * sub_mesg : 处理成功
     * time : 2020-12-08T07:36:42.514Z
     * data : [{"id":1,"name":"系统勋章","sort":1111,"medals":[{"medalId":3,"userId":0,"hasHave":false,"createTime":"","type":1,"name":"系统勋章2","imgUrl":"https://haide.nbqichen.com/haide/upload/85618D73DA5ADE0E0DC0DC16638309F6.png","imgUrlNo":"https://haide.nbqichen.com/haide/upload/6C10B69342B870EDB3FFE517980C67AD.png","explain":"系统勋章测试2","sort":2}]},{"id":2,"name":"运动勋章","sort":22,"medals":[{"medalId":4,"userId":0,"hasHave":false,"createTime":"","type":2,"name":"运动勋章2","imgUrl":"https://haide.nbqichen.com/haide/upload/16DE93DA14652329FAE327130C97A89F.png","imgUrlNo":"https://haide.nbqichen.com/haide/upload/7307CB09EFAE01DCFE6FB32D3AF444D2.png","explain":"运动勋章测试2","sort":2},{"medalId":5,"userId":0,"hasHave":false,"createTime":"","type":2,"name":"运动勋章1","imgUrl":"https://haide.nbqichen.com/haide/upload/2867E5C151E0486CA047DF29E3DC00A7.png","imgUrlNo":"https://haide.nbqichen.com/haide/upload/E9E4478CD8ABB789C16B967DD554EC3C.png","explain":"运动勋章测试1","sort":1}]},{"id":3,"name":"其他勋章","sort":3,"medals":[{"medalId":7,"userId":0,"hasHave":false,"createTime":"","type":3,"name":"其他勋章","imgUrl":"https://haide.nbqichen.com/haide/upload/DE191771998027E5EE2E54FB3E60955F.png","imgUrlNo":"https://haide.nbqichen.com/haide/upload/2AAFDA22A04ACD90B0BD19F211A5F574.png","explain":"1231","sort":122}]}]
     */

    private int code;
    private String mesg;
    private String sub_code;
    private String sub_mesg;
    private String time;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMesg() {
        return mesg;
    }

    public void setMesg(String mesg) {
        this.mesg = mesg;
    }

    public String getSub_code() {
        return sub_code;
    }

    public void setSub_code(String sub_code) {
        this.sub_code = sub_code;
    }

    public String getSub_mesg() {
        return sub_mesg;
    }

    public void setSub_mesg(String sub_mesg) {
        this.sub_mesg = sub_mesg;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * name : 系统勋章
         * sort : 1111
         * medals : [{"medalId":3,"userId":0,"hasHave":false,"createTime":"","type":1,"name":"系统勋章2","imgUrl":"https://haide.nbqichen.com/haide/upload/85618D73DA5ADE0E0DC0DC16638309F6.png","imgUrlNo":"https://haide.nbqichen.com/haide/upload/6C10B69342B870EDB3FFE517980C67AD.png","explain":"系统勋章测试2","sort":2}]
         */

        private int id;
        private String name;
        private int sort;
        private List<MedalsBean> medals;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public List<MedalsBean> getMedals() {
            return medals;
        }

        public void setMedals(List<MedalsBean> medals) {
            this.medals = medals;
        }

        public static class MedalsBean {
            /**
             * medalId : 3
             * userId : 0
             * hasHave : false
             * createTime :
             * type : 1
             * name : 系统勋章2
             * imgUrl : https://haide.nbqichen.com/haide/upload/85618D73DA5ADE0E0DC0DC16638309F6.png
             * imgUrlNo : https://haide.nbqichen.com/haide/upload/6C10B69342B870EDB3FFE517980C67AD.png
             * explain : 系统勋章测试2
             * sort : 2
             */

            private int medalId;
            private int userId;
            private boolean hasHave;
            private String createTime;
            private int type;
            private String name;
            private String imgUrl;
            private String imgUrlNo;
            private String explain;
            private int sort;

            public int getMedalId() {
                return medalId;
            }

            public void setMedalId(int medalId) {
                this.medalId = medalId;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public boolean isHasHave() {
                return hasHave;
            }

            public void setHasHave(boolean hasHave) {
                this.hasHave = hasHave;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }

            public String getImgUrlNo() {
                return imgUrlNo;
            }

            public void setImgUrlNo(String imgUrlNo) {
                this.imgUrlNo = imgUrlNo;
            }

            public String getExplain() {
                return explain;
            }

            public void setExplain(String explain) {
                this.explain = explain;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }
        }
    }
}
