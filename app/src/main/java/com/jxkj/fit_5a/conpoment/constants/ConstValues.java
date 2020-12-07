package com.jxkj.fit_5a.conpoment.constants;

/**
 * Created by Administrator on 2017/9/1.
 */

public class ConstValues {
    /**
     * 应用名称
     */
    public static String APPNAME_ENGLISH = "5AFit";

    /**
     * 文件夹父路径
     */
    public static final String FILE_ROOT_DIRECTORY = "/yzdg";
    public static final String FILE_DIRECTORY_IMG = FILE_ROOT_DIRECTORY+"/img";

    /**sharedpreference 判断是否已登录字段*/
    public static final String ISLOGIN = "islogin";
    public static final String USERID = "user_id";
    public static final String TOKEN = "token";
    public static final String BALANCE = "balance";
    public static final String USER_PHONE = "user_phone";
    public static final String USER_NAME = "user_name";
    public static final String USER_CREATETIME = "user_createtime";
    public static final String USER_PORTRAIT = "user_portrait";
    public static final String PHONE = "phone";
    public static final String LAT = "lat";
    public static final String LON = "lon";
    public static final String NICKNAME = "nickname";
    public static final String AVATAR = "avatar";
    public static final String IS_REALNAME = "is_realname";
    public static final String INTEGRALJUMP = "integral_jump";
    public static final String OPENSTART = "openstart";
    public static final String AGREEMENT = "agreement";

    /**
     * 服务器后台地址
     */
//    public static final String BASE_URL = "http://api.zjduon.com/zulin/";
//    public static final String BASE_URL = "http://192.168.2.130:8087/gtbl/";
    public static final String BASE_URL = "http://5afit.jianxuan.com";
    public static final String BASE_URL_DETAIL = "http://admin.zjduon.com/";

    public static final String WX_APP_ID = "wxf2824afc51ad69a0";
    public static final String REGISTRATION_ID = "registration_id";

    //http://192.168.2.21:9502/
    public static final String PORT_1 = "http://192.168.2.21:9501/";//用户 user
    public static final String PORT_2 = "http://192.168.2.21:9502/";//系统相关设置 sysconfig
    public static final String PORT_5 = "http://192.168.2.21:9505/";//第三方登录 third
    public static final String PORT_8 = "http://192.168.2.21:9508/";//支付相关 pay
    public static final String PORT_9 = "http://192.168.2.175:9505/";//积分商城


//    public static final String PORT_1 = "user/";//用户 user
//    public static final String PORT_2 = "sysconfig/";//系统相关设置 sysconfig
//    public static final String PORT_5 = "third/";//第三方登录 third
//    public static final String PORT_8 = "pay/";//支付相关 pay



//api/v1/user/getQrCode?url=pages/start/start&scene=page-authorize,code-17774004352
    //默认连接超时时间
    public static final int DEFAULT_TIMEOUT = 30;
    public static final int PAGE_SIZE =10;
}
