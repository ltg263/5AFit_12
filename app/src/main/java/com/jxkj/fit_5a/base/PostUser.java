package com.jxkj.fit_5a.base;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class PostUser {


    public static class CreateCircle{
        @Override
        public String toString() {
            return "CreateCircle{" +
                    "bgImg='" + bgImg + '\'' +
                    ", deviceType=" + deviceType +
                    ", explain='" + explain + '\'' +
                    ", imgUrl='" + imgUrl + '\'' +
                    ", interestId=" + interestId +
                    ", name='" + name + '\'' +
                    ", taskCircleFormDTO=" + taskCircleFormDTO +
                    '}';
        }

        /**
         * bgImg :
         * deviceType : 0
         * explain :
         * imgUrl :
         * interestId : 0
         * name :
         * taskCircleFormDTO : {"circleTargetId":0,"cycle":0,"explain":"","imgUrl":""}
         */

        private String bgImg;
        private int deviceType;
        private String explain;
        private String imgUrl;
        private int interestId;
        private String name;
        private TaskCircleFormDTOBean taskCircleFormDTO;

        public String getBgImg() {
            return bgImg;
        }

        public void setBgImg(String bgImg) {
            this.bgImg = bgImg;
        }

        public int getDeviceType() {
            return deviceType;
        }

        public void setDeviceType(int deviceType) {
            this.deviceType = deviceType;
        }

        public String getExplain() {
            return explain;
        }

        public void setExplain(String explain) {
            this.explain = explain;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public int getInterestId() {
            return interestId;
        }

        public void setInterestId(int interestId) {
            this.interestId = interestId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public TaskCircleFormDTOBean getTaskCircleFormDTO() {
            return taskCircleFormDTO;
        }

        public void setTaskCircleFormDTO(TaskCircleFormDTOBean taskCircleFormDTO) {
            this.taskCircleFormDTO = taskCircleFormDTO;
        }

        public static class TaskCircleFormDTOBean {
            /**
             * circleTargetId : 0
             * cycle : 0
             * explain :
             * imgUrl :
             */

            private int circleTargetId;
            private int cycle;
            private String explain;
            private String imgUrl;

            public int getCircleTargetId() {
                return circleTargetId;
            }

            public void setCircleTargetId(int circleTargetId) {
                this.circleTargetId = circleTargetId;
            }

            public int getCycle() {
                return cycle;
            }

            public void setCycle(int cycle) {
                this.cycle = cycle;
            }

            public String getExplain() {
                return explain;
            }

            public void setExplain(String explain) {
                this.explain = explain;
            }

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }

            @Override
            public String toString() {
                return "TaskCircleFormDTOBean{" +
                        "circleTargetId=" + circleTargetId +
                        ", cycle=" + cycle +
                        ", explain='" + explain + '\'' +
                        ", imgUrl='" + imgUrl + '\'' +
                        '}';
            }
        }
    }

    public static class DeviceFormDTO{
        /**
         * deviceId : 0
         * deviceNo :
         * id : 0
         * userId : 0
         */

        private String deviceId;
        private String deviceNo;
        private String id;
        private String userId;

        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }

        public String getDeviceNo() {
            return deviceNo;
        }

        public void setDeviceNo(String deviceNo) {
            this.deviceNo = deviceNo;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
    }

    public static class UserInfoUpdate{


        /**
         * avatar : 
         * backImg : 
         * birthDay : 
         * cityId : 0
         * districtId : 0
         * explain : 
         * gender : 0
         * Stringerest : 
         * nickName : 
         * personMail : 
         * provinceId : 0
         * region : 
         */

        private String avatar;
        private String backImg;
        private String birthDay;
        private String cityId;
        private String districtId;
        private String explain;
        private String gender;
        private String Stringerest;
        private String nickName;
        private String personMail;
        private String provinceId;
        private String region;
        private String height;
        private String weight;

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public String getHeight() {
            return height;
        }

        public String getWeight() {
            return weight;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getBackImg() {
            return backImg;
        }

        public void setBackImg(String backImg) {
            this.backImg = backImg;
        }

        public String getBirthDay() {
            return birthDay;
        }

        public void setBirthDay(String birthDay) {
            this.birthDay = birthDay;
        }

        public String getCityId() {
            return cityId;
        }

        public void setCityId(String cityId) {
            this.cityId = cityId;
        }

        public String getDistrictId() {
            return districtId;
        }

        public void setDistrictId(String districtId) {
            this.districtId = districtId;
        }

        public String getExplain() {
            return explain;
        }

        public void setExplain(String explain) {
            this.explain = explain;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getInterest() {
            return Stringerest;
        }

        public void setInterest(String Stringerest) {
            this.Stringerest = Stringerest;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getPersonMail() {
            return personMail;
        }

        public void setPersonMail(String personMail) {
            this.personMail = personMail;
        }

        public String getProvinceId() {
            return provinceId;
        }

        public void setProvinceId(String provinceId) {
            this.provinceId = provinceId;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }
    }

    public static class SportLogInfo{

        /**
         * bai : 0
         * calories : 0
         * details : {"logs":[{"calories":0,"distance":0,"heartRate":0,"incline":0,"intensity":0,"resistanceLevel":0,"rpm":0,"speed":0,"timeStamp":0,"watt":0}],"motionTrajectories":[{"pointX":0,"pointY":0,"timestamp":0}]}
         * deviceBrandId : 0
         * deviceModelId : 0
         * deviceTypeId : 0
         * distance : 0
         * duration : 0
         * endTimestamp : 0
         * heartRateSource : 0
         * mapId :
         * protocolDeviceBrandParamId :
         * protocolDeviceModelParamId :
         * protocolDeviceTypeParamId :
         * protocolName :
         * startTimestamp : 0
         * trainingMode :
         */

        private String bai;
        private String calories;
        private DetailsBean details;
        private String deviceBrandId;
        private String deviceModelId;
        private String deviceTypeId;
        private String distance;
        private String duration;
        private String endTimestamp;
        private String heartRateSource;
        private String mapId;
        private String protocolDeviceBrandParamId;
        private String protocolDeviceModelParamId;
        private String protocolDeviceTypeParamId;
        private String protocolName;
        private String startTimestamp;
        private String trainingMode;

        public String getBai() {
            return bai;
        }

        public void setBai(String bai) {
            this.bai = bai;
        }

        public String getCalories() {
            return calories;
        }

        public void setCalories(String calories) {
            this.calories = calories;
        }

        public DetailsBean getDetails() {
            return details;
        }

        public void setDetails(DetailsBean details) {
            this.details = details;
        }

        public String getDeviceBrandId() {
            return deviceBrandId;
        }

        public void setDeviceBrandId(String deviceBrandId) {
            this.deviceBrandId = deviceBrandId;
        }

        public String getDeviceModelId() {
            return deviceModelId;
        }

        public void setDeviceModelId(String deviceModelId) {
            this.deviceModelId = deviceModelId;
        }

        public String getDeviceTypeId() {
            return deviceTypeId;
        }

        public void setDeviceTypeId(String deviceTypeId) {
            this.deviceTypeId = deviceTypeId;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getEndTimestamp() {
            return endTimestamp;
        }

        public void setEndTimestamp(String endTimestamp) {
            this.endTimestamp = endTimestamp;
        }

        public String getHeartRateSource() {
            return heartRateSource;
        }

        public void setHeartRateSource(String heartRateSource) {
            this.heartRateSource = heartRateSource;
        }

        public String getMapId() {
            return mapId;
        }

        public void setMapId(String mapId) {
            this.mapId = mapId;
        }

        public String getProtocolDeviceBrandParamId() {
            return protocolDeviceBrandParamId;
        }

        public void setProtocolDeviceBrandParamId(String protocolDeviceBrandParamId) {
            this.protocolDeviceBrandParamId = protocolDeviceBrandParamId;
        }

        public String getProtocolDeviceModelParamId() {
            return protocolDeviceModelParamId;
        }

        public void setProtocolDeviceModelParamId(String protocolDeviceModelParamId) {
            this.protocolDeviceModelParamId = protocolDeviceModelParamId;
        }

        public String getProtocolDeviceTypeParamId() {
            return protocolDeviceTypeParamId;
        }

        public void setProtocolDeviceTypeParamId(String protocolDeviceTypeParamId) {
            this.protocolDeviceTypeParamId = protocolDeviceTypeParamId;
        }

        public String getProtocolName() {
            return protocolName;
        }

        public void setProtocolName(String protocolName) {
            this.protocolName = protocolName;
        }

        public String getStartTimestamp() {
            return startTimestamp;
        }

        public void setStartTimestamp(String startTimestamp) {
            this.startTimestamp = startTimestamp;
        }

        public String getTrainingMode() {
            return trainingMode;
        }

        public void setTrainingMode(String trainingMode) {
            this.trainingMode = trainingMode;
        }

        public static class DetailsBean {
            private List<LogsBean> logs;
            private List<MotionTrajectoriesBean> motionTrajectories;

            public List<LogsBean> getLogs() {
                return logs;
            }

            public void setLogs(List<LogsBean> logs) {
                this.logs = logs;
            }

            public List<MotionTrajectoriesBean> getMotionTrajectories() {
                return motionTrajectories;
            }

            public void setMotionTrajectories(List<MotionTrajectoriesBean> motionTrajectories) {
                this.motionTrajectories = motionTrajectories;
            }

            public static class LogsBean implements Parcelable {
                /**
                 * calories : 0
                 * distance : 0
                 * heartRate : 0
                 * incline : 0
                 * intensity : 0
                 * resistanceLevel : 0
                 * rpm : 0
                 * speed : 0
                 * timeStamp : 0
                 * watt : 0
                 */

                private String calories;
                private String distance;
                private String heartRate;
                private String incline;
                private String intensity;
                private String resistanceLevel;
                private String rpm;
                private String speed;
                private String timeStamp;
                private String watt;

                @Override
                public String toString() {
                    return "LogsBean{" +
                            "calories='" + calories + '\'' +
                            ", distance='" + distance + '\'' +
                            ", heartRate='" + heartRate + '\'' +
                            ", incline='" + incline + '\'' +
                            ", intensity='" + intensity + '\'' +
                            ", resistanceLevel='" + resistanceLevel + '\'' +
                            ", rpm='" + rpm + '\'' +
                            ", speed='" + speed + '\'' +
                            ", timeStamp='" + timeStamp + '\'' +
                            ", watt='" + watt + '\'' +
                            '}';
                }

                public LogsBean(String calories, String distance, String heartRate, String incline, String intensity, String resistanceLevel, String rpm, String speed, String timeStamp, String watt) {
                    this.calories = calories;
                    this.distance = distance;
                    this.heartRate = heartRate;
                    this.incline = incline;
                    this.intensity = intensity;
                    this.resistanceLevel = resistanceLevel;
                    this.rpm = rpm;
                    this.speed = speed;
                    this.timeStamp = timeStamp;
                    this.watt = watt;
                }



                public String getCalories() {
                    return calories;
                }

                public void setCalories(String calories) {
                    this.calories = calories;
                }

                public String getDistance() {
                    return distance;
                }

                public void setDistance(String distance) {
                    this.distance = distance;
                }

                public String getHeartRate() {
                    return heartRate;
                }

                public void setHeartRate(String heartRate) {
                    this.heartRate = heartRate;
                }

                public String getIncline() {
                    return incline;
                }

                public void setIncline(String incline) {
                    this.incline = incline;
                }

                public String getIntensity() {
                    return intensity;
                }

                public void setIntensity(String intensity) {
                    this.intensity = intensity;
                }

                public String getResistanceLevel() {
                    return resistanceLevel;
                }

                public void setResistanceLevel(String resistanceLevel) {
                    this.resistanceLevel = resistanceLevel;
                }

                public String getRpm() {
                    return rpm;
                }

                public void setRpm(String rpm) {
                    this.rpm = rpm;
                }

                public String getSpeed() {
                    return speed;
                }

                public void setSpeed(String speed) {
                    this.speed = speed;
                }

                public String getTimeStamp() {
                    return timeStamp;
                }

                public void setTimeStamp(String timeStamp) {
                    this.timeStamp = timeStamp;
                }

                public String getWatt() {
                    return watt;
                }

                public void setWatt(String watt) {
                    this.watt = watt;
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(this.calories);
                    dest.writeString(this.distance);
                    dest.writeString(this.heartRate);
                    dest.writeString(this.incline);
                    dest.writeString(this.intensity);
                    dest.writeString(this.resistanceLevel);
                    dest.writeString(this.rpm);
                    dest.writeString(this.speed);
                    dest.writeString(this.timeStamp);
                    dest.writeString(this.watt);
                }

                protected LogsBean(Parcel in) {
                    this.calories = in.readString();
                    this.distance = in.readString();
                    this.heartRate = in.readString();
                    this.incline = in.readString();
                    this.intensity = in.readString();
                    this.resistanceLevel = in.readString();
                    this.rpm = in.readString();
                    this.speed = in.readString();
                    this.timeStamp = in.readString();
                    this.watt = in.readString();
                }

                public static final Parcelable.Creator<LogsBean> CREATOR = new Parcelable.Creator<LogsBean>() {
                    @Override
                    public LogsBean createFromParcel(Parcel source) {
                        return new LogsBean(source);
                    }

                    @Override
                    public LogsBean[] newArray(int size) {
                        return new LogsBean[size];
                    }
                };
            }

            public static class MotionTrajectoriesBean {
                /**
                 * pointX : 0
                 * pointY : 0
                 * timestamp : 0
                 */

                private String pointX;
                private String pointY;
                private String timestamp;

                public String getPointX() {
                    return pointX;
                }

                public void setPointX(String pointX) {
                    this.pointX = pointX;
                }

                public String getPointY() {
                    return pointY;
                }

                public void setPointY(String pointY) {
                    this.pointY = pointY;
                }

                public String getTimestamp() {
                    return timestamp;
                }

                public void setTimestamp(String timestamp) {
                    this.timestamp = timestamp;
                }
            }
        }
    }


    public static class Comment {

        /**
         * orderId : 0
         * productComments : [{"content":"","deductIntegral":0,"imgStr":"","isShow":0,"productContent":"","productId":0,"productImg":"","productName":"","productPrice":0,"productQuantity":0,"productScore":0,"specText":""}]
         * userId : 0
         */

        private String orderId;
        private String userId;
        private String deliveryScore;
        private String serviceScore;
        private List<ProductCommentsBean> productComments;

        public String getDeliveryScore() {
            return deliveryScore;
        }

        public void setDeliveryScore(String deliveryScore) {
            this.deliveryScore = deliveryScore;
        }

        public String getServiceScore() {
            return serviceScore;
        }

        public void setServiceScore(String serviceScore) {
            this.serviceScore = serviceScore;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public List<ProductCommentsBean> getProductComments() {
            return productComments;
        }

        public void setProductComments(List<ProductCommentsBean> productComments) {
            this.productComments = productComments;
        }

        public static class ProductCommentsBean {


            private String content;
            private String imgStr;
            private String isShow;
            private String productId;
            private String productScore;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getImgStr() {
                return imgStr;
            }

            public void setImgStr(String imgStr) {
                this.imgStr = imgStr;
            }

            public String getIsShow() {
                return isShow;
            }

            public void setIsShow(String isShow) {
                this.isShow = isShow;
            }

            public String getProductId() {
                return productId;
            }

            public void setProductId(String productId) {
                this.productId = productId;
            }

            public String getProductScore() {
                return productScore;
            }

            public void setProductScore(String productScore) {
                this.productScore = productScore;
            }
        }
    }
}
