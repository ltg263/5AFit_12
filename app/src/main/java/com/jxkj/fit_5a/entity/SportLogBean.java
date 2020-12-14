package com.jxkj.fit_5a.entity;

import java.util.List;

public class SportLogBean {

    /**
     * list : [{"boxs":[{"distance":{},"id":"","latlng":[],"sportBox":{"aliasArray":[{}],"hasDel":true,"id":"","name":"","probArray":[{}],"rewardList":[{"detail":"","explain":"","id":0,"imgUrl":"","name":"","rate":{},"type":0}]}}],"distance":{},"id":"","imgUrl":"","info":[],"minLevel":0,"name":""}]
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
         * boxs : [{"distance":{},"id":"","latlng":[],"sportBox":{"aliasArray":[{}],"hasDel":true,"id":"","name":"","probArray":[{}],"rewardList":[{"detail":"","explain":"","id":0,"imgUrl":"","name":"","rate":{},"type":0}]}}]
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
             * id :
             * latlng : []
             * sportBox : {"aliasArray":[{}],"hasDel":true,"id":"","name":"","probArray":[{}],"rewardList":[{"detail":"","explain":"","id":0,"imgUrl":"","name":"","rate":{},"type":0}]}
             */

            private DistanceBeanX distance;
            private String id;
            private SportBoxBean sportBox;
            private List<?> latlng;

            public DistanceBeanX getDistance() {
                return distance;
            }

            public void setDistance(DistanceBeanX distance) {
                this.distance = distance;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public SportBoxBean getSportBox() {
                return sportBox;
            }

            public void setSportBox(SportBoxBean sportBox) {
                this.sportBox = sportBox;
            }

            public List<?> getLatlng() {
                return latlng;
            }

            public void setLatlng(List<?> latlng) {
                this.latlng = latlng;
            }

            public static class DistanceBeanX {
            }

            public static class SportBoxBean {
                /**
                 * aliasArray : [{}]
                 * hasDel : true
                 * id :
                 * name :
                 * probArray : [{}]
                 * rewardList : [{"detail":"","explain":"","id":0,"imgUrl":"","name":"","rate":{},"type":0}]
                 */

                private boolean hasDel;
                private String id;
                private String name;
                private List<AliasArrayBean> aliasArray;
                private List<ProbArrayBean> probArray;
                private List<RewardListBean> rewardList;

                public boolean isHasDel() {
                    return hasDel;
                }

                public void setHasDel(boolean hasDel) {
                    this.hasDel = hasDel;
                }

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

                public List<AliasArrayBean> getAliasArray() {
                    return aliasArray;
                }

                public void setAliasArray(List<AliasArrayBean> aliasArray) {
                    this.aliasArray = aliasArray;
                }

                public List<ProbArrayBean> getProbArray() {
                    return probArray;
                }

                public void setProbArray(List<ProbArrayBean> probArray) {
                    this.probArray = probArray;
                }

                public List<RewardListBean> getRewardList() {
                    return rewardList;
                }

                public void setRewardList(List<RewardListBean> rewardList) {
                    this.rewardList = rewardList;
                }

                public static class AliasArrayBean {
                }

                public static class ProbArrayBean {
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
        }
    }
}
