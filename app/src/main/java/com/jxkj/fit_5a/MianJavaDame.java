package com.jxkj.fit_5a;

public class MianJavaDame {
    public static void main(String[] args) {

        System.out.println(":"+Math.ceil(26/10d));
    }

    public static int getP(int totalCount, int pageSize){
        return (totalCount + pageSize - 1)/pageSize;
    }
}
