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
    private ParamBean param;
    private List<List<Float>> info;
    private List<?> boxs;

    public void setParam(ParamBean param) {
        this.param = param;
    }

    public ParamBean getParam() {
        return param;
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

    public List<List<Float>> getInfo() {
        return info;
    }

    public void setInfo(List<List<Float>> info) {
        this.info = info;
    }

    public List<?> getBoxs() {
        return boxs;
    }

    public void setBoxs(List<?> boxs) {
        this.boxs = boxs;
    }

    public class ParamBean{
        float width;
        float height;
        float referenceWidth;
        float referenceHeight;

        public float getWidth() {
            return width;
        }

        public void setWidth(float width) {
            this.width = width;
        }

        public float getHeight() {
            return height;
        }

        public void setHeight(float height) {
            this.height = height;
        }

        public float getReferenceWidth() {
            return referenceWidth;
        }

        public void setReferenceWidth(float referenceWidth) {
            this.referenceWidth = referenceWidth;
        }

        public float getReferenceHeight() {
            return referenceHeight;
        }

        public void setReferenceHeight(float referenceHeight) {
            this.referenceHeight = referenceHeight;
        }
    }
}
