package com.jxkj.fit_5a.api;


import com.jxkj.fit_5a.base.AddressData;
import com.jxkj.fit_5a.base.OrderInfoData;
import com.jxkj.fit_5a.base.Result;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    /**
     * 获取token
     *
     * @return
     */
    @GET("api/v1/user/getSecuritytoken")
    Observable<Result> getSecuritytoken();

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
