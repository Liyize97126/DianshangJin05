package com.bawei.dianshangjin05.util;

import com.bawei.dianshangjin05.bean.Category;
import com.bawei.dianshangjin05.bean.Commodity;
import com.bawei.dianshangjin05.bean.DataBean;
import com.bawei.dianshangjin05.bean.HomeList;
import com.bawei.dianshangjin05.bean.LoginInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * IRequest接口，用于描述网络请求
 */
public interface IRequest {
    //注册接口
    @POST("small/user/v1/register")
    @FormUrlEncoded
    Call<DataBean> register(@Field("phone") String phone,@Field("pwd") String pwd);
    //登录接口
    @POST("small/user/v1/login")
    @FormUrlEncoded
    Call<DataBean<LoginInfo>> login(@Field("phone") String phone,@Field("pwd") String pwd);
    //首页展示接口
    @GET("small/commodity/v1/commodityList")
    Call<DataBean<HomeList>> commodityList();
    //查找商品接口
    @GET("small/commodity/v1/findCommodityByKeyword")
    Call<DataBean<List<Commodity>>> findCommodityByKeyword(@Query("keyword") String keyword,@Query("page") int page,@Query("count") int count);
    //查找购物车接口
    @GET("small/order/verify/v1/findShoppingCart")
    Call<DataBean<List<Category>>> findShoppingCart(@Header("userId") int userId,@Header("sessionId") String sessionId);
}
