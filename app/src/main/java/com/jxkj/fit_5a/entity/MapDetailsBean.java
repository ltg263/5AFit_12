package com.jxkj.fit_5a.entity;

import java.util.List;


public class MapDetailsBean {

    /**
     * id : 16
     * name : 绕湖一圈
     * imgUrl : http://5a-fit.oss-cn-hangzhou.aliyuncs.com/sport/WcEXC07ksKEmtBtM7IrCw.jpg
     * minLevel : 1
     * distance : 12000
     * info : [[572,480],[936,188],[1264,112],[1512,232],[1412,368],[1664,456],[1688,588],[1436,684],[1164,772],[1028,960],[1008,1148],[1260,1240],[1228,1420],[928,1444],[520,1472],[344,1364],[216,1108],[248,844],[404,572]]
     * boxs : []
     */

    private String id;
    private String name;
    private String imgUrl;
    private String minLevel;
    private String distance;
    private List<List<Double>> info;
    private List<?> boxs;

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

    public String getMinLevel() {
        return minLevel;
    }

    public void setMinLevel(String minLevel) {
        this.minLevel = minLevel;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public List<List<Double>> getInfo() {
        return info;
    }

    public void setInfo(List<List<Double>> info) {
        this.info = info;
    }

    public List<?> getBoxs() {
        return boxs;
    }

    public void setBoxs(List<?> boxs) {
        this.boxs = boxs;
    }
}
