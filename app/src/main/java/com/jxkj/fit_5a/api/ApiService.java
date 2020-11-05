package com.jxkj.fit_5a.api;


import com.jxkj.fit_5a.base.AddressData;
import com.jxkj.fit_5a.base.HelpListData;
import com.jxkj.fit_5a.base.OrderInfoData;
import com.jxkj.fit_5a.base.Result;

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
     * 获取token
     *
     * @return
     */
    @GET("api/v1/user/task/list")
    Observable<Result> getUserTaskList();

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
     * 注册成功后————兴趣列表
     * @return
     */
    @GET("api/v1/interest/query")
    Observable<Result> getInterestList();


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
     * 再来一单
     */
    @POST("order/api/v1/user/order/again")
    Observable<Result> getAgainOrder(@Query("id") String orderId);
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
