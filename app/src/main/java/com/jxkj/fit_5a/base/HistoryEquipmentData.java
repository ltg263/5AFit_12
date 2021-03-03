package com.jxkj.fit_5a.base;

public class HistoryEquipmentData {
    String img;
    String name;
    String time;
    String state;
    String serviceUUid;
    String LyAddress;
    int id;

    public String getLyAddress() {
        return LyAddress;
    }

    public void setLyAddress(String lyAddress) {
        LyAddress = lyAddress;
    }

    public void setServiceUUid(String serviceUUid) {
        this.serviceUUid = serviceUUid;
    }

    public String getServiceUUid() {
        return serviceUUid;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "HistoryEquipmentData{" +
                "img='" + img + '\'' +
                ", name='" + name + '\'' +
                ", time='" + time + '\'' +
                ", state='" + state + '\'' +
                ", serviceUUid='" + serviceUUid + '\'' +
                ", LyAddress='" + LyAddress + '\'' +
                ", id=" + id +
                '}';
    }
}
