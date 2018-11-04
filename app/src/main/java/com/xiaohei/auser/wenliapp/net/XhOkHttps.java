package com.xiaohei.auser.wenliapp.net;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.xiaohei.auser.wenliapp.minterface.XhHttpInterface;
import com.xiaohei.auser.wenliapp.minterface.XhNetInterface;
import com.xiaohei.auser.wenliapp.sp.SpConstants;
import com.xiaohei.auser.wenliapp.utils.JsonReturnData;
import com.xiaohei.auser.wenliapp.utils.XhLogUtil;
import com.xiaohei.auser.wenliapp.wenlientity.Result;

import java.io.IOException;
import java.lang.reflect.Type;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by machenike on 2018/10/16.
 * 用于网络请求
 */

public class XhOkHttps {

    public static  void OkHttpPostRequest(String url, RequestBody requestBody, final Type mtype, final XhNetInterface xhNetInterface){
        Request request = new Request.Builder().url(url).post(requestBody).
                cacheControl(CacheControl.FORCE_NETWORK).build();
        OkHttpClient okHttpClient = new OkHttpClient().
                newBuilder().
                hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        //强行返回true 即验证成功
                        return true;
                    }
                }).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                xhNetInterface.fail();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                String str = response.body().string();
                    Gson gson = new Gson();
                    JsonReturnData jsonReturnData = gson.fromJson(str, mtype);
                    xhNetInterface.complied(jsonReturnData);
            }
        });
    }

    public static  void PostRequest(String url, RequestBody requestBody, final Type mtype, final XhHttpInterface xhNetInterface){

        Request request = new Request.Builder()
                .url(url).post(requestBody).cacheControl(CacheControl.FORCE_NETWORK).build();
        OkHttpClient okHttpClient = new OkHttpClient().
                newBuilder().
                hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        //强行返回true 即验证成功
                        return true;
                    }
                }).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                xhNetInterface.fail();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                String str = response.body().string();
                Gson gson = new Gson();
                Result result = gson.fromJson(str, mtype);
                xhNetInterface.complied(result);
            }
        });
    }

    public static  void PostRequestWithToken(String url, RequestBody requestBody,String token, final Type mtype, final XhHttpInterface xhNetInterface){

        Request request = new Request.Builder()
                .url(url)
                .addHeader(SpConstants.TOKEN, token)
                .cacheControl(CacheControl.FORCE_NETWORK)
                .post(requestBody)
                .build();
        OkHttpClient okHttpClient = new OkHttpClient().
                newBuilder().
                hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        //强行返回true 即验证成功
                        return true;
                    }
                }).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                xhNetInterface.fail();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                String str = response.body().string();
                Gson gson = new Gson();
                Result result = gson.fromJson(str, mtype);
                xhNetInterface.complied(result);
            }
        });
    }

    public static void GetRequest(String url, final String token, final Type mtype, final XhHttpInterface httpInterface){
        Request request = new Request.Builder().url(url)
                .addHeader(SpConstants.TOKEN, token)
                .cacheControl(CacheControl.FORCE_NETWORK)
                .build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                httpInterface.fail();
            }
            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                String str;
                try {
                    str = response.body().string();
                    XhLogUtil.d(str+":"+token);
                    if(str == null || TextUtils.isEmpty(str)){
                        httpInterface.fail();
                    }else {
                        Gson gson = new Gson();
                        Result result = gson.fromJson(str, mtype);
                        httpInterface.complied(result);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    httpInterface.fail();
                }
            }
        });
    }

    public static void PutRequest(String url, String token, final Type mtype, FormBody body, final XhHttpInterface httpInterface){
        OkHttpClient okHttpClient = new OkHttpClient();
        Request build1 = new Request.Builder()
                .url(url)
                .addHeader(SpConstants.TOKEN, token)
                .put(body)
                .cacheControl(CacheControl.FORCE_NETWORK)
                .build();
        okHttpClient.newCall(build1).enqueue(
                new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        httpInterface.fail();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String str;
                        try {
                            str = response.body().string();
                            XhLogUtil.d(str);
                            if(str == null || TextUtils.isEmpty(str)){
                                httpInterface.fail();
                            }else {
                                Gson gson = new Gson();
                                Result result = gson.fromJson(str, mtype);
                                httpInterface.complied(result);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            httpInterface.fail();
                        }
                        }
                });
    }

    public static Result getErrorResult(){
        Result result_error = new Result();
        result_error.setCode(-1);
        return result_error;
    }


}
