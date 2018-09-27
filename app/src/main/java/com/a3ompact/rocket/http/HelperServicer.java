package com.a3ompact.rocket.http;

import com.a3ompact.rocket.bean.User;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;

/**
 * Created by 3ompact on 2018-9-26.
 */
public interface HelperServicer {

    //使用 path为id提供值
    @GET("path/{id}")
    Call<String> getBaseInfo(@Path("id") int id);

    //使用 HTTP注解
    @HTTP(method = "GET", path = "", hasBody = false)
    Call<String> getTest1();

    @POST("/form")
    @FormUrlEncoded
    Call<String> postTest1(@Field("username") String name, @Field("age") int age);

    /**
     * 使用map作为表单的键值对
     */
    @POST
    @FormUrlEncoded
    Call<String> getPost2(@FieldMap Map<String, String> map);

    /**
     * part 后面支持三中类型
     * <p>
     * 该处的RequestBody 使用 RequestBody.create()方法来创建参数
     * MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", "test.txt", file);
     * mediatype介绍
     * http://www.iana.org/assignments/media-types/media-types.xhtml
     * MIME类型介绍
     * http://www.w3school.com.cn/media/media_mimeref.asp
     *
     * 还有一个注解@Streaming 表示响应体的数据会以数据流的形式进行返回
     * 如果没有该注解，默认会把所有数据全部载入内存之后通过流获取的也只是
     * 读取内存中的数据，所以当数据比较大的时候，你需要使用这个注解
     */

    @POST
    @Multipart
    Call<String> postTest3(@Part("name") RequestBody name, @Part("age") RequestBody age, @Part MultipartBody.Part file);

    /**
     * partmap的使用 MultpartBody.part 不适用于该出的map
     *
     * @param map
     * @param file
     * @return
     */
    @POST
    @Multipart
    Call<String> postTest4(@PartMap Map<String, RequestBody> map, @Part MultipartBody.Part file);



    @POST
    @Headers({"custom1 : value1", "custom2 : value2" })
    Call<String> postTest5(@Header("custom4") String value);



}
