package com.jxkj.fit_5a.conpoment.utils;

import android.content.Context;

import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.vod.upload.ResumableVODUploadCallback;
import com.alibaba.sdk.android.vod.upload.VODUploadCallback;
import com.alibaba.sdk.android.vod.upload.VODUploadClientImpl;
import com.alibaba.sdk.android.vod.upload.model.UploadFileInfo;
import com.alibaba.sdk.android.vod.upload.model.VodInfo;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.PostUser;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.base.ResultList;
import com.jxkj.fit_5a.conpoment.constants.ConstValues;
import com.jxkj.fit_5a.entity.CommentMomentBean;
import com.jxkj.fit_5a.entity.LoginInfo;
import com.jxkj.fit_5a.entity.OssInfoBean;
import com.jxkj.fit_5a.entity.StsTokenBean;
import com.jxkj.fit_5a.entity.SubmitFilesBean;
import com.jxkj.fit_5a.entity.VideoInfoBean;
import com.jxkj.fit_5a.view.activity.login_other.LoginActivity;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import cn.forward.androids.utils.LogUtil;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class HttpRequestUtils {

    public static void getVerifyAppEmpower(Context mContext, String accessToken, String openId, String registrationId, String inviteCode, LoginInterface loginInterface) {
        RetrofitUtil.getInstance().apiService()
                .verifyAppEmpower(accessToken, openId, 3, registrationId, inviteCode)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
//                        if (result.getStatus() == 0) {
//                            SharedUtils.singleton().put(ConstValues.TOKEN,result.getData().getToken());
//                            SharedUtils.singleton().put(ConstValues.USERID,result.getData().getId());
//                            loginInterface.succeed(result.getData());
//                        }else{
////                            Toast.makeText(mContext,result.get(),Toast.LENGTH_LONG).show();
//                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                    }
                });

    }

    public interface LoginInterface {
        void succeed(String path);
//        void failure();
    }

    public static void uploadFiles(String filePath,UploadFileInterface fileInterface) {
        if(StringUtil.isBlank(filePath)){
            fileInterface.succeed("-1");
            return;
        }
        File file = new File(filePath);
        Map<String, RequestBody> map = new HashMap<>();
//        map.put("dirtype", toRequestBody("3"));//头像：3，申诉 ：2 ，收款码：1
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        // MultipartBody.Part  和后端约定好Key，这里的name是用file
        MultipartBody.Part body = null;
        try {
            body = MultipartBody.Part.createFormData("file",  URLEncoder.encode(file.getName(), "UTF-8"), requestFile);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        RetrofitUtil.getInstance().apiService()
                .submitFiles(body, map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<SubmitFilesBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<SubmitFilesBean> result) {
                        if (result.getCode()==0  && result.getData()!=null
                                && StringUtil.isNotBlank(result.getData().getUrl())) {
                            fileInterface.succeed(result.getData().getUrl());
                        }else{
                            fileInterface.failure();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        fileInterface.failure();
                    }

                    @Override
                    public void onComplete() {
                    }
                });

    }

    public interface UploadFileInterface{
        void succeed(String path);
        void failure();
    }

    public static void userVerifyLogin() {
        RetrofitUtil.getInstance().apiService()
                .userVerifyLogin(3,SharedUtils.singleton().get(ConstValues.USER_PHONE,""),
                        SharedUtils.singleton().get(ConstValues.USER_PASSWORD,""),null)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<LoginInfo>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<LoginInfo> result) {
                        if(result.getCode()==0){
                            SharedUtils.singleton().put(ConstValues.TOKEN,"Bearer "+result.getData().getTokenId());
                            LoginActivity.saveUserInfo(result.getData());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public static void psotUserSportLog() {
        PostUser.SportLogInfo sportLogInfo= new PostUser.SportLogInfo();
//        sportLogInfo.setCal();
        RetrofitUtil.getInstance().apiService()
                .psotUserSportLog(sportLogInfo)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    /**
     * 去关注
     * @param followerId
     * @param mLoginInterface
     */
    public static void postfollow(String followerId,LoginInterface mLoginInterface) {
        RetrofitUtil.getInstance().apiService()
                .postfollow(followerId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if(result.getCode()==0){
                            mLoginInterface.succeed("0");
                        }else{
                            mLoginInterface.succeed("-1");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mLoginInterface.succeed("-1");
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    /**
     * 取消去关注
     * @param followerId
     * @param mLoginInterface
     */
    public static void postfollowCancel(String followerId,LoginInterface mLoginInterface) {
        RetrofitUtil.getInstance().apiService()
                .postfollowCancel(followerId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if(result.getCode()==0){
                            mLoginInterface.succeed("1");
                        }else{
                            mLoginInterface.succeed("-1");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mLoginInterface.succeed("-1");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 去收藏
     */
    public static void postFavorit(String circleId,String momentId,String momentPublisherId,LoginInterface mLoginInterface) {
        RetrofitUtil.getInstance().apiService()
                .postFavorit(circleId,momentId,momentPublisherId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if(result.getCode()==0){
                            mLoginInterface.succeed("0");
                        }else{
                            mLoginInterface.succeed("-1");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mLoginInterface.succeed("-1");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 取消收藏
     */
    public static void postFavoritCancel(String circleId,String momentId,LoginInterface mLoginInterface) {
        RetrofitUtil.getInstance().apiService()
                .postFavoritCancel(circleId,momentId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if(result.getCode()==0){
                            mLoginInterface.succeed("0");
                        }else{
                            mLoginInterface.succeed("-1");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mLoginInterface.succeed("-1");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public static void postLike1(String circleId,String momentId,String momentPublisherId,LoginInterface mLoginInterface) {
        if(StringUtil.isBlank(circleId) || circleId.equals("0")){
            postLike(momentId,momentPublisherId,mLoginInterface);
        }else{
            postLikeCircle(circleId,momentId,momentPublisherId,mLoginInterface);
        }
    }
    /**
     * 去点赞
     */
    public static void postLike(String momentId,String momentPublisherId,LoginInterface mLoginInterface) {
        RetrofitUtil.getInstance().apiService()
                .postLike(momentId,momentPublisherId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if(result.getCode()==0){
                            mLoginInterface.succeed("0");
                        }else{
                            mLoginInterface.succeed("-1");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mLoginInterface.succeed("-1");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 去点赞
     */
    public static void postLikeCircle(String circleId,String momentId,String momentPublisherId,LoginInterface mLoginInterface) {
        RetrofitUtil.getInstance().apiService()
                .postLikeCircle(circleId,momentId,momentPublisherId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if(result.getCode()==0){
                            mLoginInterface.succeed("0");
                        }else{
                            mLoginInterface.succeed("-1");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mLoginInterface.succeed("-1");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    public static void postLikeCancel1(String circleId,String momentId,String momentPublisherId,LoginInterface mLoginInterface) {
        if(StringUtil.isBlank(circleId) || circleId.equals("0")){
            postLikeCancel(momentId,momentPublisherId,mLoginInterface);
        }else{
            postLikeCancelCircle(circleId,momentId,momentPublisherId,mLoginInterface);
        }
    }
    /**
     * 取消点赞
     */
    public static void postLikeCancel(String momentId,String momentPublisherId,LoginInterface mLoginInterface) {
        RetrofitUtil.getInstance().apiService()
                .postLikeCancel(momentId,momentPublisherId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if(result.getCode()==0){
                            mLoginInterface.succeed("0");
                        }else{
                            mLoginInterface.succeed("-1");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mLoginInterface.succeed("-1");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 取消点赞
     */
    public static void postLikeCancelCircle(String circleId,String momentId,String momentPublisherId,LoginInterface mLoginInterface) {
        RetrofitUtil.getInstance().apiService()
                .postLikeCancelCircle(circleId,momentId,momentPublisherId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if(result.getCode()==0){
                            mLoginInterface.succeed("0");
                        }else{
                            mLoginInterface.succeed("-1");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mLoginInterface.succeed("-1");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    /**
     * 用户点赞动态评论
     */
    public static void postCommentLike(String commentId,String momentId,LoginInterface mLoginInterface) {
        RetrofitUtil.getInstance().apiService()
                .postCommentLike(commentId,momentId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if(result.getCode()==0){
                            mLoginInterface.succeed("0");
                        }else{
                            mLoginInterface.succeed("-1");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mLoginInterface.succeed("-1");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }



    /**
     * 用户取消点赞动态评论
     */
    public static void postCommentLikeCancel(String commentId,String momentId,LoginInterface mLoginInterface) {
        RetrofitUtil.getInstance().apiService()
                .postCommentLikeCancel(commentId,momentId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if(result.getCode()==0){
                            mLoginInterface.succeed("0");
                        }else{
                            mLoginInterface.succeed("-1");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mLoginInterface.succeed("-1");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    /**
     * 用户发布动态评论
     */
    public static void postCommentMoment1(String circleId,String content,String momentId,String momentPublisherId,
                                         String replyCommentId,LoginInterface mLoginInterface) {
        if(StringUtil.isBlank(circleId) || circleId.equals("0")){
            postCommentMoment(content,momentId,momentPublisherId,replyCommentId,mLoginInterface);
        }else{
            postCommentMomentCircle(circleId,content,momentId,momentPublisherId,replyCommentId,mLoginInterface);
        }
    }

    /**
     * 用户发布动态评论
     */
    public static void postCommentMoment(String content,String momentId,String momentPublisherId,
                                         String replyCommentId,LoginInterface mLoginInterface) {
        RetrofitUtil.getInstance().apiService()
                .postCommentMoment(content,1,momentId,momentPublisherId,replyCommentId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if(result.getCode()==0){
                            mLoginInterface.succeed("0");
                        }else{
                            mLoginInterface.succeed("-1");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mLoginInterface.succeed("-1");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    /**
     * 用户发布动态评论--圈子
     */
    public static void postCommentMomentCircle(String circleId,String content,String momentId,String momentPublisherId,
                                         String replyCommentId,LoginInterface mLoginInterface) {
        RetrofitUtil.getInstance().apiService()
                .postCommentMomentCircle(circleId,content,1,momentId,momentPublisherId,replyCommentId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if(result.getCode()==0){
                            mLoginInterface.succeed("0");
                        }else{
                            mLoginInterface.succeed("-1");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mLoginInterface.succeed("-1");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public static void getCommentMoment1(String circleId,String momentId,String momentPublisherId,int page,int pageSize,ResultInterface mResultInterface){
        if(StringUtil.isBlank(circleId) || circleId.equals("0")){
            getCommentMoment(momentId,momentPublisherId,page,pageSize,mResultInterface);
        }else{
            getCommentMomentCircle(circleId,momentId,momentPublisherId,page,pageSize,mResultInterface);
        }
    }

    /**
     * 获取动态下评论信息
     */
    public static void getCommentMoment(String momentId,String momentPublisherId,int page,int pageSize,ResultInterface mResultInterface) {
        RetrofitUtil.getInstance().apiService()
                .getCommentMoment(momentId,momentPublisherId,page,pageSize)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResultList<CommentMomentBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResultList<CommentMomentBean> result) {
                        mResultInterface.succeed(result);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mResultInterface.succeed(null);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    /**
     * 获取动态下评论信息--圈子
     */
    public static void getCommentMomentCircle(String circleId,String momentId,String momentPublisherId,int page,int pageSize,ResultInterface mResultInterface) {
        RetrofitUtil.getInstance().apiService()
                .getCommentMomentCircle(circleId,momentId,momentPublisherId,page,pageSize)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResultList<CommentMomentBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResultList<CommentMomentBean> result) {
                        mResultInterface.succeed(result);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mResultInterface.succeed(null);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    public static void getCommentQueryReply1(String circleId,String commentId,String momentId,String momentPublisherId,
                                            int page,int pageSize,ResultInterface mResultInterface) {
        if(StringUtil.isBlank(circleId) || circleId.equals("0")){
            getCommentQueryReply(commentId,momentId,momentPublisherId,page,pageSize,mResultInterface);
        }else{
            getCommentQueryReplyCircle(circleId,commentId,momentId,momentPublisherId,page,pageSize,mResultInterface);
        }

    }

    /**
     * 获取评论下的评论信息(回复评论的评论)
     */
    public static void getCommentQueryReply(String commentId,String momentId,String momentPublisherId,
                                            int page,int pageSize,ResultInterface mResultInterface) {
        RetrofitUtil.getInstance().apiService()
                .getCommentQueryReply(commentId,momentId,momentPublisherId,page,pageSize)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResultList<CommentMomentBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResultList<CommentMomentBean> result) {
                        mResultInterface.succeed(result);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mResultInterface.succeed(null);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 获取评论下的评论信息(回复评论的评论)--圈子
     */
    public static void getCommentQueryReplyCircle(String circleId,String commentId,String momentId,String momentPublisherId,
                                            int page,int pageSize,ResultInterface mResultInterface) {
        RetrofitUtil.getInstance().apiService()
                .getCommentQueryReplyCircle(circleId,commentId,momentId,momentPublisherId,page,pageSize)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResultList<CommentMomentBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResultList<CommentMomentBean> result) {
                        mResultInterface.succeed(result);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mResultInterface.succeed(null);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public interface ResultInterface {
        void succeed(ResultList<CommentMomentBean> result);
//        void failure();
    }
    public interface OSSClientInterface {
        void succeed(double pos);
//        void failure();
    }

    /**
     *
     * @param mResultInterface
     * @param type :0 用户相关 1动态相关 2圈子相关 3商城商品相关 4商城商品评论相关
     *             5商品封面(商品列表) 6商品轮播图 7商城商品详情 8商城商品规格封面 9商城商品分类
     *             10礼物 11任务 12勋章 13兴趣 14帮助 15广告 16运动
     */
    public static void postOSSFile(int type,OSSClientInterface mResultInterface){
        RetrofitUtil.getInstance().apiService()
                .getStsToken()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<StsTokenBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<StsTokenBean> result) {
                        if(result.getCode()==0) {
                            StsTokenBean data = result.getData();
                            SharedUtils.singleton().put(ConstValues.accessKeyId,data.getAccessKeyId());
                            SharedUtils.singleton().put(ConstValues.accessKeySecret,data.getAccessKeySecret());
                            SharedUtils.singleton().put(ConstValues.SecurityToken,data.getSecurityToken());
                            getOssInfo(mResultInterface,type);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mResultInterface.succeed(0);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    public static void getOssInfo(OSSClientInterface mResultInterface, int type){
        RetrofitUtil.getInstance().apiService()
                .getOssInfo()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<OssInfoBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<OssInfoBean> result) {
                        if(result.getCode()==0) {
                            OssInfoBean data = result.getData();
                            SharedUtils.singleton().put(ConstValues.endpoint,data.getEndpoint());
                            SharedUtils.singleton().put(ConstValues.host,data.getHost());
                            SharedUtils.singleton().put(ConstValues.bucketName,data.getBucket());
                            SharedUtils.singleton().put(ConstValues.dir,data.getDirUnits().get(type).getDir());
                            mResultInterface.succeed(1);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mResultInterface.succeed(0);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public static void getUploadVideo(String fileName,String title,String coverUrl,VideoInterface videoInterface){
        RetrofitUtil.getInstance().apiService()
                .getUploadVideo(fileName,title,coverUrl)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<VideoInfoBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<VideoInfoBean> result) {
                        if(result.getCode()==0) {
                            videoInterface.succeed(result.getData());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public interface VideoInterface {
        void succeed(VideoInfoBean result);
//        void failure();
    }
    /**
     * 创建请求体
     *
     * @param value
     * @return
     */
    public static  RequestBody toRequestBody(String value) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain"), value);
        return requestBody;
    }
    public static void getUpload_Video(String filePath,String fileName,String title,String coverUrl){

        File file = new File(filePath);
        //String fileName, @Query("title") String title, @Query("coverUrl") String coverUrl
        Map<String, RequestBody> map = new HashMap<>();
        map.put("fileName", toRequestBody(fileName));
        map.put("title", toRequestBody(title));
        map.put("coverUrl", toRequestBody(coverUrl));//头像：3，申诉 ：2 ，收款码：1
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        // MultipartBody.Part  和后端约定好Key，这里的name是用file
        MultipartBody.Part body = null;
        try {
            body = MultipartBody.Part.createFormData("file",  URLEncoder.encode(file.getName(), "UTF-8"), requestFile);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        RetrofitUtil.getInstance().apiService()
                .getUpload_Video(body,map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if(result.getCode()==0) {

                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    public static void initOSSClient(Context mContext,  String fileName,String filePath, OSSClientInterface mResultInterface){
        //初始化OssService类，参数分别是Content，accessKeyId，accessKeySecret，endpoint，bucketName（后4个参数是您自己阿里云Oss中参数）
        // String accessKeyId, String accessKeySecret, String endpoint,String bucketName,String dir, String SecurityToken
        OssService ossService = new OssService(mContext);
        //初始化OSSClient
        ossService.initOSSClient();
        //开始上传，参数分别为content，上传的文件名filename，上传的文件路径filePath

        ossService.beginupload(mContext, fileName, filePath);
        //上传的进度回调
        ossService.setProgressCallback(new OssService.ProgressCallback() {
            @Override
            public void onProgressCallback(final double progress) {
                mResultInterface.succeed(progress);
            }
        });
    }

    public static void initAcc(Context mContext,String filePath,String uploadAuth, String uploadAddress,String coverUrl) {
        VODUploadClientImpl uploader = new VODUploadClientImpl(mContext);
        VODUploadCallback callback = new VODUploadCallback() {

            @Override
            public void onUploadSucceed(UploadFileInfo info) {
                super.onUploadSucceed(info);
                System.out.println("onsucceed ------------------上传完成回调" + info.getFilePath());
            }

            @Override
            public void onUploadFailed(UploadFileInfo info, String code, String message) {
                super.onUploadFailed(info, code, message);
                System.out.println("onfailed ------------------ 上传失败回调 " + info.getFilePath() + " " + code + " " + message);
            }

            @Override
            public void onUploadProgress(UploadFileInfo info, long uploadedSize, long totalSize) {
                super.onUploadProgress(info, uploadedSize, totalSize);
                System.out.println("onProgress ------------------上传进度回调 " + info.getFilePath() + " " + uploadedSize + " " + totalSize);
            }

            @Override
            public void onUploadTokenExpired() {
                super.onUploadTokenExpired();
                System.out.println("onExpired ------------- token过期回调");
                //重新刷新上传凭证：RefreshUploadVideo
//                uploadAuth = "此处需要设置重新刷新凭证之后的值";
//                uploader.resumeWithAuth(uploadAuth);
            }

            @Override
            public void onUploadRetry(String code, String message) {
                super.onUploadRetry(code, message);
                System.out.println("onUploadRetry ------------- 上传开始重试回调");
            }

            @Override
            public void onUploadRetryResume() {
                super.onUploadRetryResume();
                System.out.println("onUploadRetryResume ------------- 上传结束重试，继续上传回调");
            }

            /**
             *
             * @param uploadFileInfo
             */
            @Override
            public void onUploadStarted(UploadFileInfo uploadFileInfo) {
                super.onUploadStarted(uploadFileInfo);
                System.out.println("onUploadStarted ------------- 开始上传回调");
                uploader.setUploadAuthAndAddress(uploadFileInfo, uploadAuth, uploadAddress);

            }
        };
        //上传初始化
        uploader.init(callback);

        File file = new File(filePath);
        System.out.println(file+"++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        VodInfo vodInfo = new VodInfo();
        vodInfo.setTitle("动态视频_Android");
        vodInfo.setDesc("描述信息");
        vodInfo.setCateId (0);
        vodInfo.setCoverUrl(coverUrl);
        vodInfo.setIsProcess(true);
//        String endpoint = "http://"+SharedUtils.singleton().get(ConstValues.endpoint,"");
//        String bucket = SharedUtils.singleton().get(ConstValues.bucketName,"");
//        uploader.addFile(filePath,endpoint,bucket,"123",vodInfo);
        uploader.addFile(filePath,vodInfo);
        uploader.start();

    }
}
