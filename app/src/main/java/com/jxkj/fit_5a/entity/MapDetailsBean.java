package com.jxkj.fit_5a.entity;

import java.util.List;


public class MapDetailsBean {

    /**
     * boxs : [{"distance":{},"having":true,"id":"","latlng":[],"rewardList":[{"detail":"","explain":"","id":0,"imgUrl":"","name":"","rate":{},"type":0}],"userBoxLog":{"boxId":"","createTime":"","datestr":0,"id":"","mapId":"","reward":{"detail":"","explain":"","id":0,"imgUrl":"","name":"","rate":{},"type":0},"userId":0}}]
     * distance : {}
     * id :
     * imgUrl :
     * info : []
     * minLevel : 0
     * name :
     */

    private DistanceBean distance;
    private String id;
    private String imgUrl;
    private int minLevel;
    private String name;
    private List<BoxsBean> boxs;
    private List<?> info;

    public DistanceBean getDistance() {
        return distance;
    }

    public void setDistance(DistanceBean distance) {
        this.distance = distance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BoxsBean> getBoxs() {
        return boxs;
    }

    public void setBoxs(List<BoxsBean> boxs) {
        this.boxs = boxs;
    }

    public List<?> getInfo() {
        return info;
    }

    public void setInfo(List<?> info) {
        this.info = info;
    }

    public static class DistanceBean {
    }

    public static class BoxsBean {
        /**
         * distance : {}
         * having : true
         * id :
         * latlng : []
         * rewardList : [{"detail":"","explain":"","id":0,"imgUrl":"","name":"","rate":{},"type":0}]
         * userBoxLog : {"boxId":"","createTime":"","datestr":0,"id":"","mapId":"","reward":{"detail":"","explain":"","id":0,"imgUrl":"","name":"","rate":{},"type":0},"userId":0}
         */

        private DistanceBeanX distance;
        private boolean having;
        private String id;
        private UserBoxLogBean userBoxLog;
        private List<?> latlng;
        private List<RewardListBean> rewardList;

        public DistanceBeanX getDistance() {
            return distance;
        }

        public void setDistance(DistanceBeanX distance) {
            this.distance = distance;
        }

        public boolean isHaving() {
            return having;
        }

        public void setHaving(boolean having) {
            this.having = having;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public UserBoxLogBean getUserBoxLog() {
            return userBoxLog;
        }

        public void setUserBoxLog(UserBoxLogBean userBoxLog) {
            this.userBoxLog = userBoxLog;
        }

        public List<?> getLatlng() {
            return latlng;
        }

        public void setLatlng(List<?> latlng) {
            this.latlng = latlng;
        }

        public List<RewardListBean> getRewardList() {
            return rewardList;
        }

        public void setRewardList(List<RewardListBean> rewardList) {
            this.rewardList = rewardList;
        }

        public static class DistanceBeanX {
        }

        public static class UserBoxLogBean {
            /**
             * boxId :
             * createTime :
             * datestr : 0
             * id :
             * mapId :
             * reward : {"detail":"","explain":"","id":0,"imgUrl":"","name":"","rate":{},"type":0}
             * userId : 0
             */

            private String boxId;
            private String createTime;
            private int datestr;
            private String id;
            private String mapId;
            private RewardBean reward;
            private int userId;

            public String getBoxId() {
                return boxId;
            }

            public void setBoxId(String boxId) {
                this.boxId = boxId;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public int getDatestr() {
                return datestr;
            }

            public void setDatestr(int datestr) {
                this.datestr = datestr;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getMapId() {
                return mapId;
            }

            public void setMapId(String mapId) {
                this.mapId = mapId;
            }

            public RewardBean getReward() {
                return reward;
            }

            public void setReward(RewardBean reward) {
                this.reward = reward;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public static class RewardBean {
                /**
                 * detail :
                 * explain :
                 * id : 0
                 * imgUrl :
                 * name :
                 * rate : {}
                 * type : 0
                 */

                private String detail;
                private String explain;
                private int id;
                private String imgUrl;
                private String name;
                private RateBean rate;
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

                public RateBean getRate() {
                    return rate;
                }

                public void setRate(RateBean rate) {
                    this.rate = rate;
                }

                public int getType() {
                    return type;
                }

                public void setType(int type) {
                    this.type = type;
                }

                public static class RateBean {
                }
            }
        }

        public static class RewardListBean {
            /**
             * detail :
             * explain :
             * id : 0
             * imgUrl :
             * name :
             * rate : {}
             * type : 0
             */

            private String detail;
            private String explain;
            private int id;
            private String imgUrl;
            private String name;
            private RateBeanX rate;
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

            public RateBeanX getRate() {
                return rate;
            }

            public void setRate(RateBeanX rate) {
                this.rate = rate;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public static class RateBeanX {
            }
        }
    }
}
