package com.bawei.dianshangjin05.util;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * RetrofitUtil工具类
 */
public class RetrofitUtil {
    //定义
    private Retrofit retrofit;
    //单例
    private static final RetrofitUtil RETROFIT_UTIL = new RetrofitUtil();
    private RetrofitUtil() {
        //日志拦截器
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //自定义初始化okhttp
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5,TimeUnit.SECONDS)
                .writeTimeout(5,TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();
        //设置retrofit
        retrofit = new Retrofit.Builder()
                //设置公用域名
                .baseUrl("http://mobile.bwstudent.com/")
                //设置Gson解析器
                .addConverterFactory(GsonConverterFactory.create())
                //设置client，默认有okhttp对象，不用调用该方法，如果你想加拦截器，需要自定义okhttp
                .client(okHttpClient)
                .build();
    }
    public static RetrofitUtil getRetrofitUtil() {
        return RETROFIT_UTIL;
    }
    //发起请求
    public <T> T create(final Class<T> service){
        return retrofit.create(service);
    }
}
