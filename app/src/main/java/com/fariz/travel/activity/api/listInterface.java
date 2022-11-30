package com.fariz.travel.activity.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface listInterface {


    @GET("drinks")
    Call<List<Response>> getdata();
}
