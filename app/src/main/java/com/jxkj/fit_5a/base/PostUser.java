package com.jxkj.fit_5a.base;

public class PostUser {


    public static class CreateCircle{

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
         * cal : {}
         * datestr : 0
         * deviceId : 0
         * deviceImg :
         * deviceName :
         * deviceNo :
         * deviceType : 0
         * duration : 0
         * heartRate : {}
         * id :
         * journey : {}
         * logDetails :
         * maxHeartRate : {}
         * minHeartRate : {}
         * pai : {}
         * type : 0
         * uploadTime :
         */

        private CalBean cal;
        private int datestr;
        private int deviceId;
        private String deviceImg;
        private String deviceName;
        private String deviceNo;
        private int deviceType;
        private int duration;
        private HeartRateBean heartRate;
        private String id;
        private JourneyBean journey;
        private String logDetails;
        private MaxHeartRateBean maxHeartRate;
        private MinHeartRateBean minHeartRate;
        private PaiBean pai;
        private int type;
        private String uploadTime;

        public CalBean getCal() {
            return cal;
        }

        public void setCal(CalBean cal) {
            this.cal = cal;
        }

        public int getDatestr() {
            return datestr;
        }

        public void setDatestr(int datestr) {
            this.datestr = datestr;
        }

        public int getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(int deviceId) {
            this.deviceId = deviceId;
        }

        public String getDeviceImg() {
            return deviceImg;
        }

        public void setDeviceImg(String deviceImg) {
            this.deviceImg = deviceImg;
        }

        public String getDeviceName() {
            return deviceName;
        }

        public void setDeviceName(String deviceName) {
            this.deviceName = deviceName;
        }

        public String getDeviceNo() {
            return deviceNo;
        }

        public void setDeviceNo(String deviceNo) {
            this.deviceNo = deviceNo;
        }

        public int getDeviceType() {
            return deviceType;
        }

        public void setDeviceType(int deviceType) {
            this.deviceType = deviceType;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public HeartRateBean getHeartRate() {
            return heartRate;
        }

        public void setHeartRate(HeartRateBean heartRate) {
            this.heartRate = heartRate;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public JourneyBean getJourney() {
            return journey;
        }

        public void setJourney(JourneyBean journey) {
            this.journey = journey;
        }

        public String getLogDetails() {
            return logDetails;
        }

        public void setLogDetails(String logDetails) {
            this.logDetails = logDetails;
        }

        public MaxHeartRateBean getMaxHeartRate() {
            return maxHeartRate;
        }

        public void setMaxHeartRate(MaxHeartRateBean maxHeartRate) {
            this.maxHeartRate = maxHeartRate;
        }

        public MinHeartRateBean getMinHeartRate() {
            return minHeartRate;
        }

        public void setMinHeartRate(MinHeartRateBean minHeartRate) {
            this.minHeartRate = minHeartRate;
        }

        public PaiBean getPai() {
            return pai;
        }

        public void setPai(PaiBean pai) {
            this.pai = pai;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getUploadTime() {
            return uploadTime;
        }

        public void setUploadTime(String uploadTime) {
            this.uploadTime = uploadTime;
        }

        public static class CalBean {
        }

        public static class HeartRateBean {
        }

        public static class JourneyBean {
        }

        public static class MaxHeartRateBean {
        }

        public static class MinHeartRateBean {
        }

        public static class PaiBean {
        }
    }


    public static class Expediting {
        String orderId;

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getOrderId() {
            return orderId;
        }
    }
}
