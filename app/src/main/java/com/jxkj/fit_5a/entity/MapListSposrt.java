package com.jxkj.fit_5a.entity;

import java.util.List;

public class MapListSposrt {

    /**
     * list : [{"id":"1111","name":"123","imgUrl":"","minLevel":0,"distance":100,"info":[{"lat":1,"lng":1},{"lat":2,"lng":2}],"boxs":[{"distance":10,"latlng":{"lat":1,"lng":1},"sportBox":null,"sportBoxId":""}]},{"id":"9","name":"123123","imgUrl":"111","minLevel":1,"distance":11,"info":[{"lat":1,"lng":1}],"boxs":[{"distance":122,"latlng":{"lat":1,"lng":1},"sportBox":null,"sportBoxId":""}]},{"id":"123","name":"123123","imgUrl":"111","minLevel":1,"distance":11.2,"info":[{"lat":1,"lng":1}],"boxs":[{"distance":0,"latlng":null,"sportBox":null,"sportBoxId":""}]}]
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
         * id : 1111
         * name : 123
         * imgUrl :
         * minLevel : 0
         * distance : 100.0
         * info : [{"lat":1,"lng":1},{"lat":2,"lng":2}]
         * boxs : [{"distance":10,"latlng":{"lat":1,"lng":1},"sportBox":null,"sportBoxId":""}]
         */

        private String id;
        private String name;
        private String imgUrl;
        private int minLevel;
        private double distance;
        private List<InfoBean> info;
        private List<BoxsBean> boxs;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public int getMinLevel() {
            return minLevel;
        }

        public void setMinLevel(int minLevel) {
            this.minLevel = minLevel;
        }

        public double getDistance() {
            return distance;
        }

        public void setDistance(double distance) {
            this.distance = distance;
        }

        public List<InfoBean> getInfo() {
            return info;
        }

        public void setInfo(List<InfoBean> info) {
            this.info = info;
        }

        public List<BoxsBean> getBoxs() {
            return boxs;
        }

        public void setBoxs(List<BoxsBean> boxs) {
            this.boxs = boxs;
        }

        public static class InfoBean {
            /**
             * lat : 1.0
             * lng : 1.0
             */

            private double lat;
            private double lng;

            public double getLat() {
                return lat;
            }

            public void setLat(double lat) {
                this.lat = lat;
            }

            public double getLng() {
                return lng;
            }

            public void setLng(double lng) {
                this.lng = lng;
            }
        }

        public static class BoxsBean {
            /**
             * distance : 10.0
             * latlng : {"lat":1,"lng":1}
             * sportBox : null
             * sportBoxId :
             */

            private double distance;
            private LatlngBean latlng;
            private Object sportBox;
            private String sportBoxId;

            public double getDistance() {
                return distance;
            }

            public void setDistance(double distance) {
                this.distance = distance;
            }

            public LatlngBean getLatlng() {
                return latlng;
            }

            public void setLatlng(LatlngBean latlng) {
                this.latlng = latlng;
            }

            public Object getSportBox() {
                return sportBox;
            }

            public void setSportBox(Object sportBox) {
                this.sportBox = sportBox;
            }

            public String getSportBoxId() {
                return sportBoxId;
            }

            public void setSportBoxId(String sportBoxId) {
                this.sportBoxId = sportBoxId;
            }

            public static class LatlngBean {
                /**
                 * lat : 1.0
                 * lng : 1.0
                 */

                private double lat;
                private double lng;

                public double getLat() {
                    return lat;
                }

                public void setLat(double lat) {
                    this.lat = lat;
                }

                public double getLng() {
                    return lng;
                }

                public void setLng(double lng) {
                    this.lng = lng;
                }
            }
        }
    }
}
