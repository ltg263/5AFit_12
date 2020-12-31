package com.jxkj.fit_5a.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AddressData implements Serializable {
    /**
     * id : 6
     * userId : 0
     * provinceId : 330000
     * cityId : 330200
     * districtId : 330203
     * regions : 浙江省宁波市海曙区
     * location : Sdfsfsdf steward as
     * acceptName : fff
     * mobile : 17621682180
     * isDefult : 1
     * hasDel : 0
     * createTime : 2020-12-31 11:58:43
     */
    private String id;
    private String userId;
    private String provinceId;
    private String cityId;
    private String districtId;
    private String regions;
    private String location;
    private String acceptName;
    private String mobile;
    private String isDefult;
    private String hasDel;
    private String createTime;


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

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
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

    public String getRegions() {
        return regions;
    }

    public void setRegions(String regions) {
        this.regions = regions;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAcceptName() {
        return acceptName;
    }

    public void setAcceptName(String acceptName) {
        this.acceptName = acceptName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIsDefult() {
        return isDefult;
    }

    public void setIsDefult(String isDefult) {
        this.isDefult = isDefult;
    }

    public String getHasDel() {
        return hasDel;
    }

    public void setHasDel(String hasDel) {
        this.hasDel = hasDel;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
