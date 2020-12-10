package com.jxkj.fit_5a.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class MapListSposrt implements Parcelable {

    /**
     * list : [{"id":"11","name":"测试","imgUrl":"1","minLevel":0,"distance":10,"info":[[1,1],[1,3]],"boxs":[{"distance":1,"latlng":[1,1],"sportBox":{"id":"0","rewardList":[{"id":0,"name":"","imgUrl":"","explain":"","type":0,"detail":"","rate":0}],"probArray":[1],"aliasArray":[],"hasDel":false},"sportBoxId":"0"}]}]
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

    public static class ListBean implements Parcelable {
        /**
         * id : 11
         * name : 测试
         * imgUrl : 1
         * minLevel : 0
         * distance : 10
         * info : [[1,1],[1,3]]
         * boxs : [{"distance":1,"latlng":[1,1],"sportBox":{"id":"0","rewardList":[{"id":0,"name":"","imgUrl":"","explain":"","type":0,"detail":"","rate":0}],"probArray":[1],"aliasArray":[],"hasDel":false},"sportBoxId":"0"}]
         */

        private String id;
        private String name;
        private String imgUrl;
        private int minLevel;
        private int distance;
        private List<List<Integer>> info;
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

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public List<List<Integer>> getInfo() {
            return info;
        }

        public void setInfo(List<List<Integer>> info) {
            this.info = info;
        }

        public List<BoxsBean> getBoxs() {
            return boxs;
        }

        public void setBoxs(List<BoxsBean> boxs) {
            this.boxs = boxs;
        }

        public static class BoxsBean implements Parcelable {
            /**
             * distance : 1
             * latlng : [1,1]
             * sportBox : {"id":"0","rewardList":[{"id":0,"name":"","imgUrl":"","explain":"","type":0,"detail":"","rate":0}],"probArray":[1],"aliasArray":[],"hasDel":false}
             * sportBoxId : 0
             */

            private int distance;
            private SportBoxBean sportBox;
            private String sportBoxId;
            private List<Integer> latlng;

            public int getDistance() {
                return distance;
            }

            public void setDistance(int distance) {
                this.distance = distance;
            }

            public SportBoxBean getSportBox() {
                return sportBox;
            }

            public void setSportBox(SportBoxBean sportBox) {
                this.sportBox = sportBox;
            }

            public String getSportBoxId() {
                return sportBoxId;
            }

            public void setSportBoxId(String sportBoxId) {
                this.sportBoxId = sportBoxId;
            }

            public List<Integer> getLatlng() {
                return latlng;
            }

            public void setLatlng(List<Integer> latlng) {
                this.latlng = latlng;
            }

            public static class SportBoxBean implements Parcelable {
                /**
                 * id : 0
                 * rewardList : [{"id":0,"name":"","imgUrl":"","explain":"","type":0,"detail":"","rate":0}]
                 * probArray : [1]
                 * aliasArray : []
                 * hasDel : false
                 */

                private String id;
                private boolean hasDel;
                private List<RewardListBean> rewardList;
                private List<String> probArray;
                private List<String> aliasArray;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public boolean isHasDel() {
                    return hasDel;
                }

                public void setHasDel(boolean hasDel) {
                    this.hasDel = hasDel;
                }

                public List<RewardListBean> getRewardList() {
                    return rewardList;
                }

                public void setRewardList(List<RewardListBean> rewardList) {
                    this.rewardList = rewardList;
                }

                public List<String> getProbArray() {
                    return probArray;
                }

                public void setProbArray(List<String> probArray) {
                    this.probArray = probArray;
                }

                public List<String> getAliasArray() {
                    return aliasArray;
                }

                public void setAliasArray(List<String> aliasArray) {
                    this.aliasArray = aliasArray;
                }

                public static class RewardListBean implements Parcelable {
                    /**
                     * id : 0
                     * name :
                     * imgUrl :
                     * explain :
                     * type : 0
                     * detail :
                     * rate : 0
                     */

                    private int id;
                    private String name;
                    private String imgUrl;
                    private String explain;
                    private int type;
                    private String detail;
                    private String rate;

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

                    public String getImgUrl() {
                        return imgUrl;
                    }

                    public void setImgUrl(String imgUrl) {
                        this.imgUrl = imgUrl;
                    }

                    public String getExplain() {
                        return explain;
                    }

                    public void setExplain(String explain) {
                        this.explain = explain;
                    }

                    public int getType() {
                        return type;
                    }

                    public void setType(int type) {
                        this.type = type;
                    }

                    public String getDetail() {
                        return detail;
                    }

                    public void setDetail(String detail) {
                        this.detail = detail;
                    }

                    public String getRate() {
                        return rate;
                    }

                    public void setRate(String rate) {
                        this.rate = rate;
                    }

                    @Override
                    public int describeContents() {
                        return 0;
                    }

                    @Override
                    public void writeToParcel(Parcel dest, int flags) {
                        dest.writeInt(this.id);
                        dest.writeString(this.name);
                        dest.writeString(this.imgUrl);
                        dest.writeString(this.explain);
                        dest.writeInt(this.type);
                        dest.writeString(this.detail);
                        dest.writeString(this.rate);
                    }

                    public RewardListBean() {
                    }

                    protected RewardListBean(Parcel in) {
                        this.id = in.readInt();
                        this.name = in.readString();
                        this.imgUrl = in.readString();
                        this.explain = in.readString();
                        this.type = in.readInt();
                        this.detail = in.readString();
                        this.rate = in.readString();
                    }

                    public static final Parcelable.Creator<RewardListBean> CREATOR = new Parcelable.Creator<RewardListBean>() {
                        @Override
                        public RewardListBean createFromParcel(Parcel source) {
                            return new RewardListBean(source);
                        }

                        @Override
                        public RewardListBean[] newArray(int size) {
                            return new RewardListBean[size];
                        }
                    };
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(this.id);
                    dest.writeByte(this.hasDel ? (byte) 1 : (byte) 0);
                    dest.writeTypedList(this.rewardList);
                    dest.writeStringList(this.probArray);
                    dest.writeStringList(this.aliasArray);
                }

                public SportBoxBean() {
                }

                protected SportBoxBean(Parcel in) {
                    this.id = in.readString();
                    this.hasDel = in.readByte() != 0;
                    this.rewardList = in.createTypedArrayList(RewardListBean.CREATOR);
                    this.probArray = in.createStringArrayList();
                    this.aliasArray = in.createStringArrayList();
                }

                public static final Parcelable.Creator<SportBoxBean> CREATOR = new Parcelable.Creator<SportBoxBean>() {
                    @Override
                    public SportBoxBean createFromParcel(Parcel source) {
                        return new SportBoxBean(source);
                    }

                    @Override
                    public SportBoxBean[] newArray(int size) {
                        return new SportBoxBean[size];
                    }
                };
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.distance);
                dest.writeParcelable(this.sportBox, flags);
                dest.writeString(this.sportBoxId);
                dest.writeList(this.latlng);
            }

            public BoxsBean() {
            }

            protected BoxsBean(Parcel in) {
                this.distance = in.readInt();
                this.sportBox = in.readParcelable(SportBoxBean.class.getClassLoader());
                this.sportBoxId = in.readString();
                this.latlng = new ArrayList<>();
                in.readList(this.latlng, Integer.class.getClassLoader());
            }

            public static final Parcelable.Creator<BoxsBean> CREATOR = new Parcelable.Creator<BoxsBean>() {
                @Override
                public BoxsBean createFromParcel(Parcel source) {
                    return new BoxsBean(source);
                }

                @Override
                public BoxsBean[] newArray(int size) {
                    return new BoxsBean[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeString(this.name);
            dest.writeString(this.imgUrl);
            dest.writeInt(this.minLevel);
            dest.writeInt(this.distance);
            dest.writeList(this.info);
            dest.writeTypedList(this.boxs);
        }

        public ListBean() {
        }

        protected ListBean(Parcel in) {
            this.id = in.readString();
            this.name = in.readString();
            this.imgUrl = in.readString();
            this.minLevel = in.readInt();
            this.distance = in.readInt();
            this.info = new ArrayList<>();
            in.readList(this.info, ArrayList.class.getClassLoader());
            this.boxs = in.createTypedArrayList(BoxsBean.CREATOR);
        }

        public static final Parcelable.Creator<ListBean> CREATOR = new Parcelable.Creator<ListBean>() {
            @Override
            public ListBean createFromParcel(Parcel source) {
                return new ListBean(source);
            }

            @Override
            public ListBean[] newArray(int size) {
                return new ListBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.totalCount);
        dest.writeTypedList(this.list);
    }

    public MapListSposrt() {
    }

    protected MapListSposrt(Parcel in) {
        this.totalCount = in.readInt();
        this.list = in.createTypedArrayList(ListBean.CREATOR);
    }

    public static final Parcelable.Creator<MapListSposrt> CREATOR = new Parcelable.Creator<MapListSposrt>() {
        @Override
        public MapListSposrt createFromParcel(Parcel source) {
            return new MapListSposrt(source);
        }

        @Override
        public MapListSposrt[] newArray(int size) {
            return new MapListSposrt[size];
        }
    };
}
