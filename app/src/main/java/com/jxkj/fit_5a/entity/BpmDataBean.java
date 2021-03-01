package com.jxkj.fit_5a.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class BpmDataBean implements Parcelable {

    String name;
    double startV;
    double endV;
    long time;

    public BpmDataBean(String name, double startV, double endV,long time) {
        this.name = name;
        this.startV = startV;
        this.endV = endV;
        this.time = time;
    }

    @Override
    public String toString() {
        return "BpmDataBean{" +
                "name='" + name + '\'' +
                ", startV=" + startV +
                ", endV=" + endV +
                ", time=" + time +
                '}';
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getStartV() {
        return startV;
    }

    public void setStartV(double startV) {
        this.startV = startV;
    }

    public double getEndV() {
        return endV;
    }

    public void setEndV(double endV) {
        this.endV = endV;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeDouble(this.startV);
        dest.writeDouble(this.endV);
        dest.writeLong(this.time);
    }

    protected BpmDataBean(Parcel in) {
        this.name = in.readString();
        this.startV = in.readDouble();
        this.endV = in.readDouble();
        this.time = in.readLong();
    }

    public static final Parcelable.Creator<BpmDataBean> CREATOR = new Parcelable.Creator<BpmDataBean>() {
        @Override
        public BpmDataBean createFromParcel(Parcel source) {
            return new BpmDataBean(source);
        }

        @Override
        public BpmDataBean[] newArray(int size) {
            return new BpmDataBean[size];
        }
    };
}

