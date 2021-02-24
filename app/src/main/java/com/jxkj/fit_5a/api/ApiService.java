package com.jxkj.fit_5a.api;


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
import com.jxkj.fit_5a.base.ResultList;
import com.jxkj.fit_5a.base.SignLogData;
import com.jxkj.fit_5a.base.TaskListBase;
import com.jxkj.fit_5a.base.UserDetailData;
import com.jxkj.fit_5a.base.UserInfoData;
import com.jxkj.fit_5a.conpoment.constants.ConstValues;
import com.jxkj.fit_5a.entity.AdListData;
import com.jxkj.fit_5a.entity.AddressData;
import com.jxkj.fit_5a.entity.AddressModel;
import com.jxkj.fit_5a.entity.CircleDetailsBean;
import com.jxkj.fit_5a.entity.CircleQueryBean;
import com.jxkj.fit_5a.entity.CircleQueryJoinedBean;
import com.jxkj.fit_5a.entity.CircleTaskData;
import com.jxkj.fit_5a.entity.CommentListBean;
import com.jxkj.fit_5a.entity.CommentMomentBean;
import com.jxkj.fit_5a.entity.CommunityHomeInfoBean;
import com.jxkj.fit_5a.entity.CreateOrderBean;
import com.jxkj.fit_5a.entity.FavoriteQueryList;
import com.jxkj.fit_5a.entity.FollowFansList;
import com.jxkj.fit_5a.entity.HotTopicBean;
import com.jxkj.fit_5a.entity.LoginInfo;
import com.jxkj.fit_5a.entity.MapDetailsBean;
import com.jxkj.fit_5a.entity.MapListSposrt;
import com.jxkj.fit_5a.entity.MedalListData;
import com.jxkj.fit_5a.entity.MomentDetailsBean;
import com.jxkj.fit_5a.entity.OrderDetailsData;
import com.jxkj.fit_5a.entity.OssInfoBean;
import com.jxkj.fit_5a.entity.PostOrderInfo;
import com.jxkj.fit_5a.entity.ProductDetailsBean;
import com.jxkj.fit_5a.entity.ProductListBean;
import com.jxkj.fit_5a.entity.QueryPopularBean;
import com.jxkj.fit_5a.entity.RankDetailsData;
import com.jxkj.fit_5a.entity.RankListData;
import com.jxkj.fit_5a.entity.RankStatsData;
import com.jxkj.fit_5a.entity.ShowOrderInfo;
import com.jxkj.fit_5a.entity.SignatureBean;
import com.jxkj.fit_5a.entity.SpecListBaen;
import com.jxkj.fit_5a.entity.SportLogBean;
import com.jxkj.fit_5a.entity.SportLogStatsBean;
import com.jxkj.fit_5a.entity.StsTokenBean;
import com.jxkj.fit_5a.entity.SubmitFilesBean;
import com.jxkj.fit_5a.entity.TaskCircleQueryBean;
import com.jxkj.fit_5a.entity.TemplateBean;
import com.jxkj.fit_5a.entity.TopicAllBean;
import com.jxkj.fit_5a.entity.UserOwnInfo;
import com.jxkj.fit_5a.entity.VideoInfoBean;
import com.jxkj.fit_5a.entity.VideoPlayAuthBean;
import com.jxkj.fit_5a.entity.VideoPlayInfoBean;
import com.jxkj.fit_5a.entity.WalletDetailsBean;

import java.io.File;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

public interface ApiService {

    /**
     * 任务列表
     *
     * @return 任务类型1:圈子任务2:日常任务3签到任务4活动任务
     */
    @GET(ConstValues.PORT_1 + "api/v1/user/task/list")
    Observable<Result<TaskListBase>> getUserTaskList(@Query("type") Integer type);

    /**
     * 任务进度更新
     * userTaskId 用户任务id(仅圈子任务时使用)
     */
    @POST(ConstValues.PORT_1 + "api/v1/user/task/update/speed")
    Observable<Result> updateUserTask(@Query("paramId") int paramId, @Query("value") String value,
                                      @Query("userTaskId") String userTaskId);

    /**
     * 用户详情
     *
     * @return
     */
    @GET(ConstValues.PORT_1 + "api/v1/user/detail")
    Observable<Result<UserDetailData>> getUserDetail();

    /**
     * 个人主页数据
     *
     * @return
     */
    @GET(ConstValues.PORT_1 + "api/v1/user/statistic/my")
    Observable<Result<UserInfoData>> getUserStatistic();

    /**
     * 用户更新
     *
     * @return
     */
    @POST(ConstValues.PORT_1 + "api/v1/user/update")
    Observable<Result> postUserUpdate(@Body PostUser.UserInfoUpdate userInfoUpdate);


    /**
     * 签到
     *
     * @return
     */
    @POST(ConstValues.PORT_1 + "api/v1/user/sign/add")
    Observable<Result> addUserSign();

    /**
     * 今日是否签到
     *
     * @return
     */
    @GET(ConstValues.PORT_1 + "api/v1/user/sign/log/today/sign")
    Observable<Result<Boolean>> addUserSignLog();


    /**
     * 会员规格列表
     *
     * @return
     */
    @GET(ConstValues.PORT_1 + "api/v1/level/spec/list")
    Observable<Result<SpecListBaen>> getSpecList(@Query("levelId") String levelId);

    /**
     * 创建会员升级订单
     *
     * @return
     */
    @POST(ConstValues.PORT_1 + "api/v1/user/order/level/create")
    Observable<Result> postCreateLevel(@Query("levelSpecId") String levelSpecId, @Query("hasAuto") boolean hasAuto);


    /**
     * 签到记录
     *
     * @return
     */

    @GET(ConstValues.PORT_1 + "api/v1/user/sign/log/list")
    Observable<Result<SignLogData>> getUserSignLog(@Query("beginCreateTime") String beginCreateTime,
                                                   @Query("endCreateTime") String endCreateTime);

    /**
     * 礼物背包
     * flag:true只显示余额false只显示收到的礼物余额
     *
     * @return
     */

    @GET(ConstValues.PORT_1 + "api/v1/user/gift/list")
    Observable<Result<GiftListData>> getUserGiftList(@Query("flag") boolean flag);


    /**
     * 礼物赠送记录
     * flag:true只显示余额false只显示收到的礼物余额
     * @return
     */

    @GET(ConstValues.PORT_1 + "api/v1/user/gift/log/list")
    Observable<Result<GiftLogListData>> getUserGiftLogList(@Query("giveFlag") boolean flag);

    /**
     * 礼券列表
     * 状态1待使用,2已使用,3已失效
     *
     * @return bugei
     */

    @GET(ConstValues.PORT_1 + "api/v1/user/prize/list")
    Observable<Result<PrizeListData>> getUserPrizeList(@Query("status") int status);

    /**
     * 礼物金
     */

    @GET(ConstValues.PORT_1 + "api/v1/user/medal/list")
    Observable<ResultList<MedalListData>> getUserMedalList1();

    /**
     * 勋章列表
     */

    @GET(ConstValues.PORT_1 + "api/v1/user/medal/list")
    Observable<ResultList<MedalListData>> getUserMedalList();

    /**
     * 圈子任务列表
     */
    @GET(ConstValues.PORT_1 + "api/v1/user/circle/task/list")
    Observable<ResultList<CircleTaskData>> getCircleTaskList();

    /**
     * 排行榜列表
     */
    @GET(ConstValues.PORT_1 + "api/v1/rank/list")
    Observable<Result<RankListData>> getRankList(@Query("type") int type);

    /**
     * 排行榜详情
     */
    @GET(ConstValues.PORT_1 + "api/v1/rank/details")
    Observable<Result<RankDetailsData>> getRankDetails(@Query("rankId") Integer rankId);

    /**
     * 排行榜排名
     */
    @GET(ConstValues.PORT_1 + "api/v1/rank/stats/rank")
    Observable<Result<RankStatsData>> getRankStatsList(@Query("type") int type);

    /**
     * 排行榜点赞
     */
    @GET(ConstValues.PORT_1 + "api/v1/user/stats/zan")
    Observable<Result> getStatsZan(@Query("calStatsId") int calStatsId, @Query("hasZan") boolean hasZan);


    /**
     * 注册成功后————兴趣列表
     *
     * @return
     */
    @GET(ConstValues.PORT_2 + "api/v1/interest/query")
    Observable<Result<InterestLists>> getInterestList();


    /**
     * 帮助列表
     *
     * @return
     */
    @GET(ConstValues.PORT_2 + "api/v1/help/query")
    Observable<Result<HelpListData>> getHelpList();

    /**
     * 广告列表
     *
     * @return
     */
    @GET(ConstValues.PORT_2 + "api/v1/ad/query")
    Observable<Result<AdListData>> getAdList();

    /**
     * 用户设备列表 未完善
     * deviceId 设备id
     * deviceNo	设备编号
     *
     * @return
     */
    @GET(ConstValues.PORT_2 + "api/v1/user/device/query")
    Observable<Result> queryUserDeviceList();

    /**
     * 设备类型列表
     *
     * @return
     */
    @GET(ConstValues.PORT_2 + "api/v1/device/type/query")
    Observable<Result<DeviceTypeData>> queryDeviceTypeLists();

    /**
     * 设备品牌列表
     *
     * @return
     */
    @GET(ConstValues.PORT_2 + "api/v1/device/brand/query")
    Observable<Result<DeviceDrandData>> queryDeviceBrandLists();


    /**
     * 设备列表
     *
     * @return
     */
    @GET(ConstValues.PORT_2 + "api/v1/device/query")
    Observable<Result<DeviceData>> queryDeviceLists(@Query("brand") String brand, @Query("type") String type);

    /**
     * 添加用户设备
     * 未完善
     */
    @POST(ConstValues.PORT_2 + "api/v1/user/device/add")
    Observable<Result> userDeviceAdd(@Body PostUser.DeviceFormDTO postUser);


    /**
     * 设备课程类型列表
     */
    @GET(ConstValues.PORT_2 + "api/v1/device/course/query")
    Observable<Result<DeviceCourseData>> queryDeviceCourseList(@Query("level") String level, @Query("deviceId") String deviceId,
                                                               @Query("type") String type);

    /**
     * 设备课程类型列表
     */
    @GET(ConstValues.PORT_2 + "api/v1/device/course/type/query")
    Observable<Result<DeviceCourseTypeData>> queryDeviceCourseTypeList(@Query("deviceId") String deviceId);

    /**
     * 设备课程详情
     */
    @GET(ConstValues.PORT_2 + "api/v1/device/course/details")
    Observable<Result<DeviceCourseData.ListBean>> queryDeviceCourseTypeDetails(@Query("id") String id);



    /**
     * 获取sts-token
     */
    @GET(ConstValues.PORT_5 + "api/v1/oss/info")
    Observable<Result<OssInfoBean>> getOssInfo();


    /**
     * 获取sts-token
     */
    @GET(ConstValues.PORT_5 + "api/v1/oss/signature")
    Observable<Result<SignatureBean>> getSignature(@Query("dir") String dir);


    /**
     * 获取sts-token
     */
    @GET(ConstValues.PORT_5 + "api/v1/sts/token")
    Observable<Result<StsTokenBean>> getStsToken();
    /**
     * 获取短信验证码
     * @return type:类型0注册1修改密码2登录
     *
     */
    @GET(ConstValues.PORT_5 + "api/v1/user/verify/getVerifyCode")
    Observable<Result> getVerifyCode(@Query("mobile") String mobile, @Query("type") int type);

    /**
     * 注册
     *
     * @return type:类型0注册1修改密码2登录
     */
    @POST(ConstValues.PORT_5 + "api/v1/user/verify/register")
    Observable<Result<LoginInfo>> userVerifyRegister(@Query("clientType") int clientType,
                                          @Query("phone") String phone, @Query("password") String password,
                                          @Query("verify") String verify);

    /**
     * 登录
     *
     * @return clientType:客户端类型1web2IOS3安卓4微信
     */
    @POST(ConstValues.PORT_5 + "api/v1/user/verify/login")
    Observable<Result<LoginInfo>> userVerifyLogin(@Query("clientType") int clientType,
                                                  @Query("phone") String phone, @Query("password") String password,
                                                  @Query("verify") String verify);


    /**
     * 忘记密码
     *
     * @return type:类型0注册1修改密码2登录
     */
    @POST(ConstValues.PORT_5 + "api/v1/user/verify/forgetPassword")
    Observable<Result> userForgetPassword(@Query("password") String password,
                                          @Query("phone") String phone,
                                          @Query("verify") String verify);

    /**
     * 修改密码，只有绑定手机之后才能使用
     *
     * @return type:类型0注册1修改密码2登录
     */
    @POST(ConstValues.PORT_5 + "api/v1/user/bind/third/changePassword")
    Observable<Result> userChangePassword(@Query("oldPassword") String oldPassword,
                                          @Query("password") String password);

    /**
     *获取视频上传地址和凭证
     */
    @GET(ConstValues.PORT_5 + "api/v1/video/upload")
    Observable<Result<VideoInfoBean>> getUploadVideo(@Query("fileName") String fileName, @Query("title") String title, @Query("coverUrl") String coverUrl);

    /**
     *上传视频文件
     * @Query("fileName") String fileName, @Query("title") String title, @Query("coverUrl") String coverUrl
     */
    @Multipart
    @POST(ConstValues.PORT_5 + "api/v1/video/upload_video")
    Observable<Result> getUpload_Video(@Part MultipartBody.Part file, @PartMap Map<String, RequestBody> map);

    /**
     * 账号绑定 未完善
     *
     * @return clientType:客户端1web2ios3安卓4微信】
     * 登录方式1手机号码2微信3QQ4新浪5iconsole
     */
    @POST(ConstValues.PORT_5 + "api/v1/user/bind/third/bind")
    Observable<Result> userThirdBind(@Query("clientType") int clientType, @Query("loginType") int loginType, @Query("phone") String phone,
                                     @Query("verify") String verify, @Query("gender") String gender, @Query("nickName") String nickName,
                                     @Query("portraitUri") String portraitUri);


    /**
     * 获取视频播放凭证
     */
    @GET(ConstValues.PORT_5 + "api/v1/video/play_auth")
    Observable<Result<VideoPlayAuthBean>> getPlay_auth(@Query("videoId") String videoId);

    /**
     * 获取视频播放地址
     */
    @GET(ConstValues.PORT_5 + "api/v1/video/play_info")
    Observable<Result<VideoPlayInfoBean>> getPlay_info(@Query("definition") String definition, @Query("videoId") String videoId);

    /**
     * app授权登录
     *
     * @return
     */
    @POST("api/v1/user/verify/appEmpower")
    @FormUrlEncoded
    Observable<Result> verifyAppEmpower(@Field("accessToken") String accessToken,
                                        @Field("openId") String openId,
                                        @Field("clientType") int clientType,
                                        @Field("registrationId") String registrationId,
                                        @Field("inviteCode") String inviteCode);
//


    /**
     * 商品列表
     */
    @GET(ConstValues.PORT_3 + "api/v1/product/list")
    Observable<Result<ProductListBean>> getProductList(@Query("hasHot") Integer hasHot);

    /**
     * 商品详情
     */
    @GET(ConstValues.PORT_3 + "api/v1/product/details")
    Observable<Result<ProductDetailsBean>> getProductDetails(@Query("id") String id);

    /**
     * 商品评论列表
     */
    @GET(ConstValues.PORT_3 + "api/v1/comment/list")
    Observable<Result<CommentListBean>> getCommentList(@Query("productId") String productId);


    /**
     * 获取用户所有收货地址列表
     *
     * @return
     */
    @GET(ConstValues.PORT_3 + "api/v1/user/address/list")
    Observable<Result<AddressModel>> getUserAddress(@Query("page") String page, @Query("pageSize") String pageSize);


    /**
     * 设置默认地址
     *
     * @return
     */

    @POST(ConstValues.PORT_3 + "api/v1/user/address/updateDefault")
    Observable<Result> getSetDefault(@Query("id") String id);


    /**
     * 删除地址
     */
    @POST(ConstValues.PORT_3 + "api/v1/user/address/delete")
    Observable<Result> getDeleteAddress(@Query("id") String id);


    /**
     * 新增地址
     *
     * @return
     */
    @POST(ConstValues.PORT_3 + "api/v1/user/address/save")
    Observable<Result> getAddAddress(@Body AddressData data);

    /**
     * 修改地址
     *
     * @return
     */
    @POST(ConstValues.PORT_3 +"api/v1/user/address/update")
    Observable<Result> getUpdateAddress(@Body AddressData data);

    /**
     * 下单预览
     */
    @POST(ConstValues.PORT_3 +"api/v1/user/order/showOrderInfo")
    Observable<Result<ShowOrderInfo>> postShowOrderInfo(@Body PostOrderInfo data);

    /**
     * 创建订单
     * @return
     */
    @POST(ConstValues.PORT_3 +"api/v1/user/order/createOrder")
    Observable<Result<CreateOrderBean>> postcreateOrder(@Body PostOrderInfo data);

    /**
     * 发货提醒
     * @return
     */
    @POST(ConstValues.PORT_3 +"api/v1/user/order/expediting")
    Observable<Result> postExpediting(@Body PostUser.Expediting expediting);

    /**
     * 取消订单
     * @return
     */
    @POST(ConstValues.PORT_3 +"api/v1/user/order/cancelOrder")
    Observable<Result> postCancelOrder(@Body PostUser.Expediting expediting);

    /**
     * 发货提醒
     * @return
     */
    @POST(ConstValues.PORT_3 +"api/v1/user/order/delete")
    Observable<Result> postDelete(@Body PostUser.Expediting expediting);

    /**
     * 完成订单
     * @return
     */
    @POST(ConstValues.PORT_3 +"api/v1/user/order/finishOrder")
    Observable<Result> postFinishOrder(@Body PostUser.Expediting expediting);

    /**
     * 订单评论
     * @return
     */
    @POST(ConstValues.PORT_3 +"api/v1/user/order/comment")
    Observable<Result> postCommentOrder(@Body PostUser.Comment comment);


    /**
     * 订单列表
     * @param page
     * @param pageSize
     * @param status 单个订单状态1,待支付;2,待发货;3,待收货;4,待评价;5,已完成;6,已取消;7,已过期;8,已结束
     * @return
     */
    @GET(ConstValues.PORT_3 +"api/v1/user/order/list")
    Observable<Result<OrderInfoData>> getOrderAll(@Query("page") int page,
                                                  @Query("pageSize") int pageSize,
                                                  @Query("status") String status);


    /**
     * 订单详情
     */
    @GET(ConstValues.PORT_3 +"api/v1/user/order/details")
    Observable<Result<OrderDetailsData>> getOrderDetails(@Query("id") String id);

    /**
     * 地图列表
     */
    @GET(ConstValues.PORT_4 + "api/v1/sport/map/list")
    Observable<Result<MapListSposrt>> getSportMapList(@Query("page") int page, @Query("pageSize") int pageSize);

    /**
     * 地图详情
     *
     * @param id
     */
    @GET(ConstValues.PORT_4 + "api/v1/user/sport/map/details")
    Observable<Result<MapDetailsBean>> getMapDetails(@Query("id") String id);

    /**
     * 运动记录统计
     */
    @GET(ConstValues.PORT_4 + "api/v1/user/sport/log/stats")
    Observable<Result<SportLogStatsBean>> getSportLogStats(@Query("beignCreateTimestamp") String beignCreateTimestamp,
                                                           @Query("endCreateTimestamp") String endCreateTimestamp, @Query("deviceType") String deviceType);

    /**
     * 宝箱领取
     * @param
     */
    @GET(ConstValues.PORT_4 + "api/v1/user/box/receive")
    Observable<Result> getBoxReceive(@Query("boxId") String boxId, @Query("mapId") String mapId);


    /**
     * 文字模板列表
     */
    @GET(ConstValues.PORT_4 + "api/v1/str/template/query")
    Observable<Result<TemplateBean>> getTemplateList();


    /**
     * 运动记录添加
     */
    @POST(ConstValues.PORT_4 + "api/v1/user/sport/log/add")
    Observable<Result> psotUserSportLog(@Body PostUser.SportLogInfo postUser);


    /**
     * 运动记录列表
     */
    @GET(ConstValues.PORT_4 + "api/v1/user/sport/log/list")
    Observable<Result<SportLogBean>> geSportLogList(@Query("page") int page, @Query("pageSize") int pageSize);


    /**
     * 余额详情  	余额类型1金豆,2卡路里
     */
    @GET(ConstValues.PORT_8 + "api/v1/user/wallet/details")
    Observable<Result<WalletDetailsBean>> getWalletDetails(@Query("type") int type);


    /**
     * 上传文件
     *
     * @return
     */
    @Multipart
    @POST(ConstValues.POPT_LS + "region/api/v1/files")
    Observable<Result<SubmitFilesBean>> submitFiles(@Part MultipartBody.Part file, @PartMap Map<String, RequestBody> map);


    /**
     * 创建圈子
     *
     * @param deviceType
     * @return 2226.21
     */
    @GET(ConstValues.PORT_21 + "api/v1/circle/query")
    Observable<Result<CircleQueryBean>> getCircleQuery(@Query("deviceType") int deviceType);

    /**
     * 创建圈子
     *
     * @return
     */
    @POST(ConstValues.PORT_21 + "api/v1/circle/create")
    Observable<Result> getCircleCreate(@Body PostUser.CreateCircle postUser);

    /**
     * 获取圈子详情
     *
     * @param id
     * @return
     */
    @GET(ConstValues.PORT_21 + "api/v1/circle/details")
    Observable<Result<CircleDetailsBean>> getCircleDetails(@Query("id") int id);

    /**
     * 用户加入圈子
     *
     * @param circleId
     * @return
     */
    @POST(ConstValues.PORT_21 + "api/v1/cricle/member/join")
    Observable<Result> getCircleJoin(@Query("circleId") int circleId);

    /**
     * 用户退出圈子
     *
     * @param circleId
     * @return
     */
    @POST(ConstValues.PORT_21 + "api/v1/cricle/member/quit")
    Observable<Result> getCircleQuit(@Query("circleId") int circleId);

    /**
     * 获取圈子预设任务列表
     *
     * @return
     */
    @GET(ConstValues.PORT_21 + "api/v1/circle/task/query")
    Observable<Result<TaskCircleQueryBean>> getTaskCircleQuery(@Query("deviceType") int deviceType);


    /**
     * 创建圈子
     */
    @GET(ConstValues.PORT_21 + "api/v1/topic/all")
    Observable<Result<TopicAllBean>> getTopicAll();


    /**
     * 用户发布动态--社区
     *
     * @return
     */
    @POST(ConstValues.PORT_21 + "api/v1/moment/publish")
    Observable<Result> postPublishMoment(@Query("content") String content, @Query("contentType") String contentType,
                                         @Query("shareType") String shareType, @Query("media") String media,
                                         @Query("position") String position, @Query("location") String location,
                                         @Query("topics") String topics);

    /**
     * 用户发布动态--圈子
     *
     * @return
     */
    @POST(ConstValues.PORT_21 + "api/v1/circle/moment/publish")
    Observable<Result> postPublishMomentCircle(@Query("circleId") int circleId, @Query("content") String content,
                                               @Query("contentType") String contentType, @Query("shareType") String shareType,
                                               @Query("location") String location, @Query("media") String media,
                                               @Query("position") String position, @Query("topics") String topics);

    /**
     * 用户发布动态--删除圈子
     *
     * @return
     */
    @POST(ConstValues.PORT_21 + "api/v1/circle/moment/delete")
    Observable<Result> postDeleteMomentCircle(@Query("circleId") int circleId,  @Query("momentId") String momentId);

    /**
     * 获取热门(推荐)动态信息
     */
    @GET(ConstValues.PORT_21 + "api/v1/moment/query_popular")
    Observable<ResultList<QueryPopularBean>> getMomentQueryPopular();//首页1


    /**
     * 根据内容搜索发布的动态信息==圈子
     */
    @GET(ConstValues.PORT_21 + "api/v1/circle/moment/query_by_keyword")
    Observable<ResultList<QueryPopularBean>> getQueryByKeyword(@Query("keyword") String keyword,@Query("circleId") String circleId);

    /**
     * 获取自己发布的动态信息
     */
    @GET(ConstValues.PORT_21 + "api/v1/moment/query_own")
    Observable<ResultList<QueryPopularBean>> getQueryByPublisherOwn(@Query("momentLocalMinId") int momentLocalMinId
            , @Query("contentType") int contentType);

    /**
     * 根据发布人获取动态信息
     */
    @GET(ConstValues.PORT_21 + "api/v1/moment/query_by_publisher")
    Observable<ResultList<QueryPopularBean>> getQueryByPublisher(@Query("momentLocalMinId") int momentLocalMinId
            , @Query("publisherId") String publisherId
            , @Query("contentType") int contentType);


    /**
     * 获取最近动态信息
     */
    @GET(ConstValues.PORT_21 + "api/v1/circle/moment/query_lately")
    Observable<ResultList<QueryPopularBean>> getQguery_lately(@Query("circleId") int circleId, @Query("contentType") int contentType);
    /**
     * 获取最近动态信息--topic
     */
    @GET(ConstValues.PORT_21 + "api/v1/topic/moment/query_lately")
    Observable<ResultList<QueryPopularBean>> getQguery_lately_topic(@Query("topicId") String topicId, @Query("contentType") int contentType);

    /**
     * 获取热门(推荐)动态信息
     */
    @GET(ConstValues.PORT_21 + "api/v1/circle/moment/query_popular")
    Observable<ResultList<QueryPopularBean>> getQuery_popular(@Query("circleId") int circleId, @Query("contentType") int contentType);

    /**
     * 社群首页
     */
    @GET(ConstValues.PORT_21 + "api/v1/community/home/info")
    Observable<Result<CommunityHomeInfoBean>> getCommunityHomeInfo();


    /**
     * 获取用户已经加入的圈子列表
     */
    @GET(ConstValues.PORT_21 + "api/v1/circle/query_joined")
    Observable<Result<CircleQueryJoinedBean>> getCircleQueryJoined(@Query("userId")String userId,@Query("page") int page, @Query("pageSize") int pageSize);


    /**
     * 获取自己已经加入的圈子列表
     */
    @GET(ConstValues.PORT_21 + "api/v1/circle/query_own_joined")
    Observable<Result<CircleQueryJoinedBean>> getCircleQueryJoinedOwn(@Query("page") int page, @Query("pageSize") int pageSize);

    /**
     * 获取用户自身资料信息
     */
    @GET(ConstValues.PORT_21 + "api/v1/user/profile/own")
    Observable<Result<UserOwnInfo>> getUserProfileOwn();

    /**
     * 获取用户资料信息
     */
    @GET(ConstValues.PORT_21 + "api/v1/user/profile")
    Observable<Result<UserOwnInfo>> getUserProfile(@Query("userId") String userId);

    /**
     * 获取用户的粉丝列表
     * @return
     */
    @GET(ConstValues.PORT_21 + "api/v1/follow/fans")
    Observable<FollowFansList> getFollowFansList(@Query("userId") String userId,@Query("page") int page, @Query("pageSize") int pageSize);


    /**
     *获取用户的关注列表
     * @return
     */
    @GET(ConstValues.PORT_21 + "api/v1/follow/followers")
    Observable<FollowFansList> getFollowFollowers(@Query("userId") String userId,@Query("page") int page, @Query("pageSize") int pageSize);

    /**
     * 关注用户
     * @param followerId
     * @return
     */
    @POST(ConstValues.PORT_21+"api/v1/follow")
    Observable<Result> postfollow(@Query("followerId") String followerId);

    /**
     * 取消关注用户
     * @param followerId
     * @return
     */
    @POST(ConstValues.PORT_21+"api/v1/follow/cancel")
    Observable<Result> postfollowCancel(@Query("followerId") String followerId);

    /**
     * 获取用户的收藏列表
     * @return
     */
    @GET(ConstValues.PORT_21+"api/v1/favorite/query")
    Observable<ResultList<FavoriteQueryList>>getFavoriteQueryOwn(@Query("userId") String userId);

    /**
     * 收藏
     * 	圈子id(如果收藏的不是圈子里的动态，传0)
     */
    @POST(ConstValues.PORT_21 + "api/v1/favorite")
    Observable<Result> postFavorit(@Query("circleId") String circleId,
                                   @Query("momentId") String momentId,
                                   @Query("momentPublisherId") String momentPublisherId);


    /**
     * 取消收藏
     * 	圈子id(如果收藏的不是圈子里的动态，传0)
     */
    @POST(ConstValues.PORT_21 + "api/v1/favorite/cancel")
    Observable<Result> postFavoritCancel(@Query("circleId") String circleId,
                                         @Query("momentId") String momentId);

    /**
     * 用户点赞动态
     */
    @POST(ConstValues.PORT_21 + "api/v1/moment/like")
    Observable<Result> postLike(@Query("momentId") String momentId,
                                   @Query("momentPublisherId") String momentPublisherId);

    /**
     * 用户点赞动态--圈子
     */
    @POST(ConstValues.PORT_21 + "api/v1/circle/moment/like")
    Observable<Result> postLikeCircle(@Query("circleId") String circleId,@Query("momentId") String momentId,
                                   @Query("momentPublisherId") String momentPublisherId);


    /**
     * 用户取消点赞动态
     */
    @POST(ConstValues.PORT_21 + "api/v1/moment/like/cancel")
    Observable<Result> postLikeCancel(@Query("momentId") String momentId,
                                         @Query("momentPublisherId") String momentPublisherId);


    /**
     * 用户取消点赞动态--圈子
     */
    @POST(ConstValues.PORT_21 + "api/v1/circle/moment/like/cancel")
    Observable<Result> postLikeCancelCircle(@Query("circleId") String circleId,@Query("momentId") String momentId,
                                         @Query("momentPublisherId") String momentPublisherId);

    /**
     * 用户点赞动态评论
     */
    @POST(ConstValues.PORT_21 + "api/v1/moment/comment/like")
    Observable<Result> postCommentLike(@Query("commentId") String commentId,
                                   @Query("momentId") String momentId);


    /**
     * 用户取消点赞动态评论
     */
    @POST(ConstValues.PORT_21 + "api/v1/moment/comment/like/cancel")
    Observable<Result> postCommentLikeCancel(@Query("commentId") String commentId,
                                         @Query("momentId") String momentId);

    /**
     * 获取热门话题
     * @return
     */
    @GET(ConstValues.PORT_21 +"api/v1/topic/hot")
    Observable<ResultList<HotTopicBean>> getHotTopicList(@Query("keyword") String keyword,@Query("page") int page,@Query("pageSize")int pageSize);

    /**
     * 获取自己参与过的话题
     * @return
     */
    @GET(ConstValues.PORT_21+"api/v1/topic/participated")
    Observable<ResultList<HotTopicBean>> getTopicParticipated(@Query("keyword") String keyword,@Query("page") int page,@Query("pageSize")int pageSize);


    /**
     * 获取所有话题
     * @param keyword
     * @param page
     * @param pageSize
     * @return
     */
    @GET(ConstValues.PORT_21+"api/v1/topic/all_topic")
    Observable<ResultList<HotTopicBean>> getAllTopic(@Query("keyword") String keyword,@Query("page") int page,@Query("pageSize")int pageSize);

    /**
     *获取动态信息--社群
     * @param momentId
     * @param publisherId
     * @return
     */
    @GET(ConstValues.PORT_21+"api/v1/moment/details")
    Observable<Result<MomentDetailsBean>> getMomentDetails(@Query("momentId") String momentId,@Query("publisherId") String publisherId);


    /**
     *获取动态信息--圈子
     * @param momentId
     * @param publisherId
     * @return
     */
    @GET(ConstValues.PORT_21+"api/v1/circle/moment/details")
    Observable<Result<MomentDetailsBean>> getMomentDetailsCircle(@Query("circleId") String circleId,@Query("momentId") String momentId,@Query("publisherId") String publisherId);

    /**
     * 用户发布动态评论
     * @param content
     * @param contentType
     * @param momentId
     * @param momentPublisherId
     * @param replyCommentId
     * @return
     */
    @POST(ConstValues.PORT_21+"api/v1/moment/comment/publish")
    Observable<Result> postCommentMoment(@Query("content")String content,@Query("contentType")int contentType,
                                         @Query("momentId") String momentId,@Query("momentPublisherId") String momentPublisherId,
                                         @Query("replyCommentId") String replyCommentId);

    /**
     * 用户发布动态评论--圈子
     * @param content
     * @param contentType
     * @param momentId
     * @param momentPublisherId
     * @param replyCommentId
     * @return
     */
    @POST(ConstValues.PORT_21+"api/v1/circle/moment/comment/publish")
    Observable<Result> postCommentMomentCircle(@Query("circleId") String circleId,@Query("content")String content,@Query("contentType")int contentType,
                                         @Query("momentId") String momentId,@Query("momentPublisherId") String momentPublisherId,
                                         @Query("replyCommentId") String replyCommentId);

    /**
     * 获取动态下评论信息
     * @return
     */
    @GET(ConstValues.PORT_21+"api/v1/moment/comment/query")
    Observable<ResultList<CommentMomentBean>> getCommentMoment(@Query("momentId") String momentId,
                                                               @Query("momentPublisherId") String momentPublisherId,
                                                               @Query("page")int page, @Query("pageSize")int pageSize);

    /**
     * 获取动态下评论信息--圈子
     * @return
     */
    @GET(ConstValues.PORT_21+"api/v1/circle/moment/comment/query")
    Observable<ResultList<CommentMomentBean>> getCommentMomentCircle(@Query("circleId") String circleId,@Query("momentId") String momentId,
                                                               @Query("momentPublisherId") String momentPublisherId,
                                                               @Query("page")int page, @Query("pageSize")int pageSize);

    /**
     * 获取评论下的评论信息(回复评论的评论)
     * @return
     */
    @GET(ConstValues.PORT_21+"api/v1/moment/comment/query_reply")
    Observable<ResultList<CommentMomentBean>> getCommentQueryReply(@Query("commentId") String commentId,
                                                                    @Query("momentId") String momentId,
                                                                    @Query("momentPublisherId")String momentPublisherId,
                                                                    @Query("page")int page, @Query("pageSize")int pageSize);

    /**
     * 获取评论下的评论信息(回复评论的评论)--圈子
     * @return
     */
    @GET(ConstValues.PORT_21+"api/v1/circle/moment/comment/query_reply")
    Observable<ResultList<CommentMomentBean>> getCommentQueryReplyCircle(@Query("circleId") String circleId,
                                                                         @Query("commentId") String commentId,
                                                                    @Query("momentId") String momentId,
                                                                    @Query("momentPublisherId")String momentPublisherId,
                                                                    @Query("page")int page, @Query("pageSize")int pageSize);

}
