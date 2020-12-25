package com.jxkj.fit_5a.conpoment.utils;

import android.content.Context;

import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.PostUser;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.constants.ConstValues;
import com.jxkj.fit_5a.entity.LoginInfo;
import com.jxkj.fit_5a.entity.SubmitFilesBean;
import com.jxkj.fit_5a.view.activity.login_other.LoginActivity;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

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
}
