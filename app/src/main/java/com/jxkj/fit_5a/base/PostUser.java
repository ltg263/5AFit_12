package com.jxkj.fit_5a.base;

public class PostUser {


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


}
