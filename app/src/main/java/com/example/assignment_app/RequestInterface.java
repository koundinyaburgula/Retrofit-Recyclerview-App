package com.example.assignment_app;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

interface RequestInterface {
    @GET("photos")
    Call<List<ViewModel>> getItemJson();




}
