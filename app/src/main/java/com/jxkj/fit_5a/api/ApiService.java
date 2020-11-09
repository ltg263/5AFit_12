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
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.base.SignLogData;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

public interface ApiService {

    /**
     * 任务列表
     * @return
     */
    @GET("api/v1/user/task/list")
    Observable<Result> getUserTaskList();


    /**
     * 设备类型列表
     *
     * @return
     */
    @GET("api/v1/device/type/query")
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
    @GET("api/v1/user/verify/getVerifyCode")
    Observable<Result> getVerifyCode(@Query("mobile") String mobile,@Query("type") int type);

    /**
     * 注册
     * @return type:类型0注册1修改密码2登录
     */
    @POST("api/v1/user/verify/register")
    Observable<Result> userVerifyRegister(@Query("clientType") int clientType,
                                          @Query("phone") String phone,@Query("password") String password,
                                          @Query("verify") String verify);

    /**
     * 注册
     * @return type:类型0注册1修改密码2登录
     */
    @POST("api/v1/user/verify/login")
    Observable<Result> userVerifyLogin(@Query("clientType") int clientType,
                                          @Query("phone") String phone,@Query("password") String password,
                                          @Query("verify") String verify);

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

    /**
     * 礼物背包
     * flag:true只显示余额false只显示收到的礼物余额
     * @return
     */

    @GET("api/v1/user/gift/list")
    Observable<Result<GiftListData>> getUserGiftList(@Query("flag") boolean flag);

    /**
     * 礼物赠送记录
     * flag:true只显示余额false只显示收到的礼物余额
     * @return
     */

    @GET("api/v1/user/gift/log/list")
    Observable<Result<GiftLogListData>> getUserGiftLogList(@Query("giveFlag") boolean flag);



    /**
     * 设置默认地址
     *
     * @return
     */

    @GET("api/v1/user/sign/log/list")
    Observable<Result<SignLogData>> getUserSignLog(@Query("beginCreateTime") String beginCreateTime,
                                                   @Query("endCreateTime") String endCreateTime);
}
