package com.grandra.new_insect;

import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory;

import retrofit2.Retrofit;


public class FungiApiClient {
    private static final String BASE_URL = "http://openapi.nature.go.kr/openapi/service/rest/InsectService/";

    public FungiApiService createService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(TikXmlConverterFactory.create()) // TikXmlConverterFactory 추가
                .build();

        return retrofit.create(FungiApiService.class);
    }
}
