package com.bawei.dianshangjin05.activity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bawei.dianshangjin05.R;
import com.bawei.dianshangjin05.bean.Category;
import com.bawei.dianshangjin05.bean.Commodity;
import com.bawei.dianshangjin05.bean.DataBean;
import com.bawei.dianshangjin05.bean.HomeList;
import com.bawei.dianshangjin05.bean.LoginInfo;
import com.bawei.dianshangjin05.util.IRequest;
import com.bawei.dianshangjin05.util.RetrofitUtil;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    //定义
    @BindView(R.id.result_show)
    protected TextView resultShow;
    private Handler handler = new Handler();
    private boolean isLogin;
    private int userId;
    private String sessionId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }
    //注册
    @OnClick(R.id.register)
    protected void click01(){
        //发起请求
        IRequest iRequest = RetrofitUtil.getRetrofitUtil().create(IRequest.class);
        iRequest.register("17156891458","123456aa").enqueue(new Callback<DataBean>() {
            @Override
            public void onResponse(Call<DataBean> call, Response<DataBean> response) {
                //获取数据
                DataBean dataBean = response.body();
                final String toJson = new Gson().toJson(dataBean);
                //反馈
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        resultShow.setText(toJson);
                    }
                });
            }
            @Override
            public void onFailure(Call<DataBean> call, Throwable t) {
                //反馈
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        resultShow.setText("发生错误了，未接收到任何数据！");
                    }
                });
            }
        });
    }
    //登录
    @OnClick(R.id.login)
    protected void click02(){
        //发起请求
        IRequest iRequest = RetrofitUtil.getRetrofitUtil().create(IRequest.class);
        iRequest.login("17156891458","123456aa").enqueue(new Callback<DataBean<LoginInfo>>() {
            @Override
            public void onResponse(Call<DataBean<LoginInfo>> call, Response<DataBean<LoginInfo>> response) {
                //获取数据
                DataBean<LoginInfo> dataBean = response.body();
                sessionId = dataBean.getResult().getSessionId();
                userId = (int) dataBean.getResult().getUserId();
                isLogin = true;
                final String toJson = new Gson().toJson(dataBean);
                //反馈
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        resultShow.setText(toJson);
                    }
                });
            }
            @Override
            public void onFailure(Call<DataBean<LoginInfo>> call, Throwable t) {
                //反馈
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        resultShow.setText("发生错误了，未接收到任何数据！");
                    }
                });
            }
        });
    }
    //首页展示接口
    @OnClick(R.id.home_list)
    protected void click03(){
        //发起请求
        IRequest iRequest = RetrofitUtil.getRetrofitUtil().create(IRequest.class);
        iRequest.commodityList().enqueue(new Callback<DataBean<HomeList>>() {
            @Override
            public void onResponse(Call<DataBean<HomeList>> call, Response<DataBean<HomeList>> response) {
                //得到数据
                DataBean<HomeList> dataBean = response.body();
                final String toJson = new Gson().toJson(dataBean);
                //反馈
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        resultShow.setText(toJson);
                    }
                });
            }
            @Override
            public void onFailure(Call<DataBean<HomeList>> call, Throwable t) {
                //反馈
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        resultShow.setText("发生错误了，未接收到任何数据！");
                    }
                });
            }
        });
    }
    //查找商品接口
    @OnClick(R.id.search)
    protected void click04(){
        //发起请求
        IRequest iRequest = RetrofitUtil.getRetrofitUtil().create(IRequest.class);
        iRequest.findCommodityByKeyword("手机",1,10).enqueue(new Callback<DataBean<List<Commodity>>>() {
            @Override
            public void onResponse(Call<DataBean<List<Commodity>>> call, Response<DataBean<List<Commodity>>> response) {
                //得到数据
                DataBean<List<Commodity>> dataBean = response.body();
                final String toJson = new Gson().toJson(dataBean);
                //反馈
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        resultShow.setText(toJson);
                    }
                });
            }
            @Override
            public void onFailure(Call<DataBean<List<Commodity>>> call, Throwable t) {
                //反馈
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        resultShow.setText("发生错误了，未接收到任何数据！");
                    }
                });
            }
        });
    }
    //查询购物车接口
    @OnClick(R.id.search_goods)
    protected void click05(){
        if(isLogin){
            //发起请求
            IRequest iRequest = RetrofitUtil.getRetrofitUtil().create(IRequest.class);
            iRequest.findShoppingCart(userId,sessionId).enqueue(new Callback<DataBean<List<Category>>>() {
                @Override
                public void onResponse(Call<DataBean<List<Category>>> call, Response<DataBean<List<Category>>> response) {
                    //得到数据
                    DataBean<List<Category>> dataBean = response.body();
                    final String toJson = new Gson().toJson(dataBean);
                    //反馈
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            resultShow.setText(toJson);
                        }
                    });
                }
                @Override
                public void onFailure(Call<DataBean<List<Category>>> call, Throwable t) {
                    //反馈
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            resultShow.setText("发生错误了，未接收到任何数据！");
                        }
                    });
                }
            });
        } else {
            resultShow.setText("请先执行登录操作！");
        }
    }
}
