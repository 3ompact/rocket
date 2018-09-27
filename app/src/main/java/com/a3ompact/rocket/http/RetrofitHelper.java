package com.a3ompact.rocket.http;


import com.google.gson.Gson;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 3ompact on 2018-9-26.
 */
public class RetrofitHelper {

    private static Retrofit retrofit = null;

    public static Retrofit getInstance(){
        if(retrofit == null ){
            retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl("")
                    .build();
        }
        return retrofit;
    }

    public void getBaseInfo(){
        HelperServicer helperServicer = retrofit.create(HelperServicer.class);
        Call<String> call = helperServicer.getBaseInfo(1);
        //进行异步请求
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }


}
