package com.jxkj.fit_5a.api;


import com.jxkj.fit_5a.base.AddressData;
import com.jxkj.fit_5a.base.DeviceCourseData;
import com.jxkj.fit_5a.base.DeviceCourseTypeData;
import com.jxkj.fit_5a.base.DeviceData;
import com.jxkj.fit_5a.base.DeviceDrandData;
import com.jxkj.fit_5a.base.DeviceTypeData;
import com.jxkj.fit_5a.base.GiftListData;
import com.jxkj.fit_5a.base.GiftLogListData;
import com.jxkj.fit_5a.base.HelpListData;
import com.jxkj.fit_5a.base.InterestLists;
import com.jxkj.fit_5a.base.OrderInfoData;
import com.jxkj.fit_5a.base.PostUser;
import com.jxkj.fit_5a.base.PrizeListData;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.base.SignLogData;
import com.jxkj.fit_5a.base.TaskListBase;
import com.jxkj.fit_5a.base.UserDetailData;
import com.jxkj.fit_5a.base.UserInfoData;
import com.jxkj.fit_5a.conpoment.constants.ConstValues;
import com.jxkj.fit_5a.entity.SpecListBaen;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    /**
     * 任务列表
     * @return
     * 	任务类型1:圈子任务2:日常任务3签到任务4活动任务
     */
    @GET(ConstValues.PORT_1 + "api/v1/user/task/list")
    Observable<Result<TaskListBase>> getUserTaskList(@Query("type") int type);
    /**
     * 任务进度更新
     * userTaskId 用户任务id(仅圈子任务时使用)
     *
     */
    @POST(ConstValues.PORT_1 + "api/v1/user/task/update/speed")
    Observable<Result>updateUserTask(@Query("paramId") int paramId,@Query("value") String value,
                                     @Query("userTaskId") String userTaskId);

    /**
     * 用户详情
     * @return
     */
    @GET(ConstValues.PORT_1+"api/v1/user/detail")
    Observable<Result<UserDetailData>> getUserDetail();
    /**
     * 个人主页数据
     * @return
     */
    @GET(ConstValues.PORT_1+"api/v1/user/statistic/my")
    Observable<Result<UserInfoData>> getUserStatistic();

    /**
     * 用户更新
     * @return
     */
    @POST(ConstValues.PORT_1+"api/v1/user/update")
    Observable<Result> postUserUpdate(@Body PostUser.UserInfoUpdate userInfoUpdate);


    /**
     * 签到
     *
     * @return
     */
    @POST(ConstValues.PORT_1+"api/v1/user/sign/add")
    Observable<Result> addUserSign();


    /**
     * 会员规格列表
     * @return
     */
    @GET(ConstValues.PORT_1+"api/v1/level/spec/list")
    Observable<Result<SpecListBaen>> getSpecList(@Query("levelId") String levelId);

    /**
     * 创建会员升级订单
     * @return
     */
    @POST(ConstValues.PORT_1+"api/v1/user/order/level/create")
    Observable<Result> postCreateLevel(@Query("levelSpecId") String levelSpecId,@Query("hasAuto") boolean hasAuto);



    /**
     * 签到记录
     *
     * @return
     */

    @GET(ConstValues.PORT_1+"api/v1/user/sign/log/list")
    Observable<Result<SignLogData>> getUserSignLog(@Query("beginCreateTime") String beginCreateTime,
                                                   @Query("endCreateTime") String endCreateTime);
    /**
     * 礼物背包
     * flag:true只显示余额false只显示收到的礼物余额
     * @return
     */

    @GET(ConstValues.PORT_1+"api/v1/user/gift/list")
    Observable<Result<GiftListData>> getUserGiftList(@Query("flag") boolean flag);


    /**
     * 礼物赠送记录
     * flag:true只显示余额false只显示收到的礼物余额
     * @return
     */

    @GET(ConstValues.PORT_1+"api/v1/user/gift/log/list")
    Observable<Result<GiftLogListData>> getUserGiftLogList(@Query("giveFlag") boolean flag);

    /**
     * 礼券列表
     *状态1待使用,2已使用,3已失效
     * @return bugei
     */

    @GET(ConstValues.PORT_1+"api/v1/user/prize/list")
    Observable<Result<PrizeListData>> getUserPrize(@Query("status") int status);


    /**
     * 设备类型列表
     *
     * @return
     */
    @GET(ConstValues.PORT_2+"api/v1/device/type/query")
    Observable<Result<DeviceTypeData>> queryDeviceTypeLists();

    /**
     *设备列表
     * @return
     */
    @GET("api/v1/device/query")
    Observable<Result<DeviceData>> queryDeviceLists(@Query("brand") String brand,@Query("type") String type);

    /**
     * 设备品牌列表
     *
     * @return
     */
    @GET("api/v1/device/brand/query")
    Observable<Result<DeviceDrandData>> queryDeviceBrandLists();

    /**
     * 添加用户设备
     * @return
     */
    @POST("api/v1/user/device/add")
    Observable<Result> userDeviceAdd(@Body PostUser.DeviceFormDTO postUser);

    /**
     *用户设备列表
     * deviceId 设备id
     * deviceNo	设备编号
     * @return
     */
    @GET("api/v1/user/device/query")
    Observable<Result> queryUserDeviceList();

    /**
     * 获取短信验证码
     * @return type:类型0注册1修改密码2登录
     */
    @GET(ConstValues.PORT_5+"api/v1/user/verify/getVerifyCode")
    Observable<Result> getVerifyCode(@Query("mobile") String mobile,@Query("type") int type);

    /**
     * 注册
     * @return type:类型0注册1修改密码2登录
     */
    @POST(ConstValues.PORT_5+"api/v1/user/verify/register")
    Observable<Result> userVerifyRegister(@Query("clientType") int clientType,
                                          @Query("phone") String phone,@Query("password") String password,
                                          @Query("verify") String verify);
    /**
     * app授权登录
     * @return
     */
    @POST("api/v1/user/verify/appEmpower")
    @FormUrlEncoded
    Observable<Result> verifyAppEmpower(@Field("accessToken") String accessToken,
                                                   @Field("openId") String openId,
                                                   @Field("clientType") int clientType,
                                                   @Field("registrationId") String registrationId,
                                                   @Field("inviteCode") String inviteCode);
    /**
     * 登录
     * @return clientType:客户端类型1web2IOS3安卓4微信
     */
    @POST(ConstValues.PORT_5+"api/v1/user/verify/login")
    Observable<Result> userVerifyLogin(@Query("clientType") int clientType,
                                       @Query("phone") String phone,@Query("password") String password,
                                       @Query("verify") String verify);


    /**
     * 登录
     * @return type:类型0注册1修改密码2登录
     */
    @POST(ConstValues.PORT_5+"api/v1/user/verify/forgetPassword")
    Observable<Result> userForgetPassword(@Query("password") String password,
                                          @Query("phone") String phone,
                                          @Query("verify") String verify);


    /**
     * 修改密码，只有绑定手机之后才能使用
     * @return type:类型0注册1修改密码2登录
     */
    @POST(ConstValues.PORT_5+"api/v1/user/bind/third/changePassword")
    Observable<Result> userChangePassword(@Query("oldPassword") String oldPassword,
                                          @Query("password") String password);



    /**
     * 账号绑定
     * @return clientType:客户端1web2ios3安卓4微信】
     * 登录方式1手机号码2微信3QQ4新浪5iconsole
     */
    @POST(ConstValues.PORT_5+"api/v1/user/bind/third/bind")
    Observable<Result> userThirdBind(@Query("clientType") int clientType,@Query("loginType") int loginType,@Query("phone") String phone,
                                     @Query("verify") String verify,@Query("gender") String gender,@Query("nickName") String nickName,
                                     @Query("portraitUri") String portraitUri);

    /**
     * 注册成功后————兴趣列表
     * @return
     */
    @GET("api/v1/interest/query")
    Observable<Result<InterestLists>> getInterestList();


    /**
     * 帮助列表
     * @return
     */
    @GET("api/v1/help/query")
    Observable<Result<HelpListData>> getHelpList();

    /**
     * 订单列表
     * @param page
     * @param pageSize
     * @param status
     * @return
     */
    @GET("order/api/v1/user/order/query")
    Observable<Result<OrderInfoData>> getOrderAll(@Query("page") int page,
                                                      @Query("pageSize") int pageSize,
                                                      @Query("status") String status);
    /**
     * 设备课程类型列表
     */
    @GET("api/v1/device/course/type/query")
    Observable<Result<DeviceCourseTypeData>> queryDeviceCourseTypeList(@Query("deviceId") String deviceId);


    /**
     * 设备课程类型列表
     */
    @GET("api/v1/device/course/query")
    Observable<Result<DeviceCourseData>> queryDeviceCourseList(@Query("level") String level, @Query("deviceId") String deviceId,
                                                               @Query("type")String type);



    /**
     * 获取用户所有收货地址列表
     * @return
     */
    @GET("user/api/v1/user/address/query")
    Observable<Result<String>> getUserAddress(@Query("page") String page, @Query("pageSize") String pageSize);

    /**
     * 新增地址
     *
     * @return
     */
    @POST("user/api/v1/user/address/add")
    Observable<Result> getAddAddress(@Body AddressData data);

    /**
     * 删除地址
     */
    @POST("user/api/v1/user/address/delete")
    Observable<Result> getDeleteAddress(@Query("id") String id);


    /**
     * 修改地址
     *
     * @return
     */
    @POST("user/api/v1/user/address/update")
    Observable<Result> getUpdateAddress(@Body AddressData data);

    /**
     * 设置默认地址
     *
     * @return
     */

    @POST("user/api/v1/user/address/setDefault")
    Observable<Result> getSetDefault(@Query("id") String id);





}
