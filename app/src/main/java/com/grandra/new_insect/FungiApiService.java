package com.grandra.new_insect;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FungiApiService {
    @GET("isctIlstrInfo")
    Call<FungiResponse> getFungiInfo(
            @Query("serviceKey") String serviceKey,
            @Query("q1") String q1
    );
}
