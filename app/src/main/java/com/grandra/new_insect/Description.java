package com.grandra.new_insect;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Description extends AppCompatActivity {

    private static final String SERVICE_KEY = "SEyTD6N9tYDcENXw1Yd08q3Snfv%2BdPJOGaXAv74WZInaJlTQ3ZAGiEbcb4PbpVOwH7y5WEuEoTHmP1GmlT6%2B6w%3D%3D";

    private ImageView imageView;
    private String insect_num;
    private String des_imageUrl;
    private Context context;

    private FungiApiClient fungiApiClient;
    private AdView mAdview; //애드뷰 변수 선언

    private TextView title;
    private TextView btnc;
    private TextView cont1;
    private TextView cont10;
    private TextView cont11;
    private TextView Cont2;
    private TextView Cont3;
    private TextView Cont4;
    private TextView Cont5;
    private TextView Cont6;
    private TextView Cont7;
    private TextView Cont8;
    private TextView Cont9;
    private TextView cprtCtnt;
    private TextView emrgcCnt;
    private TextView emrgcEraDscrt;
    private TextView fmlyKorNm;
    private TextView fmlyNm;
    private TextView genusKorNm;
    private TextView genusNm;
    private TextView imgUrl;
    private TextView insctAthrNm;
    private TextView insctEngNm;
    private TextView insctOfnmKrlngNm;
    private TextView insctPilbkNo;
    private TextView insctSpecsNm;
    private TextView mnmmOccrrCnt;
    private TextView mxmmOccrrCnt;
    private TextView ordKorNm;
    private TextView ordNm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.description);

        this.Init();
        this.image();

        MobileAds.initialize(this, new OnInitializationCompleteListener() { //광고 초기화
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdview = findViewById(R.id.des_adView); //배너광고 레이아웃 가져오기
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdview.loadAd(adRequest);
        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER); //광고 사이즈는 배너 사이즈로 설정
        adView.setAdUnitId("\n" + "ca-app-pub-4268507364131475/2554965512");

        // Retrofit 및 API 클라이언트 초기화
        fungiApiClient = new FungiApiClient();

        // API 호출
        Call<FungiResponse> call = fungiApiClient.createService().getFungiInfo(SERVICE_KEY, insect_num);
        call.enqueue(new Callback<FungiResponse>() {
            @Override
            public void onResponse(@NonNull Call<FungiResponse> call, @NonNull Response<FungiResponse> response) {

                if (response.isSuccessful()) {
                    FungiResponse fungiResponse = response.body();
                    if (fungiResponse != null) {
                        FungiItem fungiItem = fungiResponse.getBody().getItem();
                        title.setText(fungiItem.getinsctOfnmKrlngNm());
                        btnc.setText(fungiItem.getbtnc());
                        cont1.setText(fungiItem.getcont1());
                        cont10.setText(fungiItem.getcont10());
                        cont11.setText(fungiItem.getcont11());
                        Cont2.setText(fungiItem.getcont2());
                        Cont3.setText(fungiItem.getcont3());
                        Cont4.setText(fungiItem.getcont4());
                        Cont5.setText(fungiItem.getcont5());
                        Cont6.setText(fungiItem.getcont6());
                        Cont7.setText(fungiItem.getcont7());
                        Cont8.setText(fungiItem.getcont8());
                        Cont9.setText(fungiItem.getcont9());
                        cprtCtnt.setText(fungiItem.getcprtCtnt());
                        emrgcCnt.setText(fungiItem.getemrgcCnt());
                        emrgcEraDscrt.setText(fungiItem.getemrgcEraDscrt());
                        fmlyKorNm.setText(fungiItem.getfmlyKorNm());
                        fmlyNm.setText(fungiItem.getfmlyNm());
                        genusKorNm.setText(fungiItem.getgenusKorNm());
                        genusNm.setText(fungiItem.getgenusNm());
                        imgUrl.setText(fungiItem.getimgUrl());
                        insctAthrNm.setText(fungiItem.getinsctAthrNm());
                        insctEngNm.setText(fungiItem.getinsctEngNm());
                        insctOfnmKrlngNm.setText(fungiItem.getinsctOfnmKrlngNm());
                        insctPilbkNo.setText(fungiItem.getinsctPilbkNo());
                        insctSpecsNm.setText(fungiItem.getinsctSpecsNm());
                        mnmmOccrrCnt.setText(fungiItem.getmnmmOccrrCnt());
                        mxmmOccrrCnt.setText(fungiItem.getmxmmOccrrCnt());
                        ordKorNm.setText(fungiItem.getordKorNm());
                        ordNm.setText(fungiItem.getordNm());

                    }
                } else {
                    // API 호출 실패 처리
                    ResponseBody errorBody = response.errorBody();
                    if (errorBody != null) {
                        try {
                            String errorResponse = errorBody.string();
                            Log.e("API Error", "Error Response: " + errorResponse);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Log.e("API Error", "Unknown error occurred.");
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<FungiResponse> call, @NonNull Throwable t) {
                // 네트워크 오류 처리
                Log.e("MainActivity", "Network error: " + t.getMessage());
            }
        });
    }

    private void Init() {
        title = findViewById(R.id.insect_title);
        imageView = findViewById(R.id.des_imageView);
        insect_num = getIntent().getStringExtra("insect_num");
        des_imageUrl = getIntent().getStringExtra("insect_image");

        btnc = findViewById(R.id.btnc);
        cont1 = findViewById(R.id.cont1);
        cont10 = findViewById(R.id.cont10);
        cont11 = findViewById(R.id.cont11);
        Cont2 = findViewById(R.id.Cont2);
        Cont3 = findViewById(R.id.Cont3);
        Cont4 = findViewById(R.id.Cont4);
        Cont5 = findViewById(R.id.Cont5);
        Cont6 = findViewById(R.id.Cont6);
        Cont7 = findViewById(R.id.Cont7);
        Cont8 = findViewById(R.id.Cont8);
        Cont9 = findViewById(R.id.Cont9);
        cprtCtnt = findViewById(R.id.cprtCtnt);
        emrgcCnt = findViewById(R.id.emrgcCnt);
        emrgcEraDscrt = findViewById(R.id.emrgcEraDscrt);
        fmlyKorNm = findViewById(R.id.fmlyKorNm);
        fmlyNm = findViewById(R.id.fmlyNm);
        genusKorNm = findViewById(R.id.genusKorNm);
        genusNm = findViewById(R.id.genusNm);
        imgUrl = findViewById(R.id.imgUrl);
        insctAthrNm = findViewById(R.id.insctAthrNm);
        insctEngNm = findViewById(R.id.insctEngNm);
        insctOfnmKrlngNm = findViewById(R.id.insctOfnmKrlngNm);
        insctPilbkNo = findViewById(R.id.insctPilbkNo);
        insctSpecsNm = findViewById(R.id.insctSpecsNm);
        mnmmOccrrCnt = findViewById(R.id.mnmmOccrrCnt);
        mxmmOccrrCnt = findViewById(R.id.mxmmOccrrCnt);
        ordKorNm = findViewById(R.id.ordKorNm);
        ordNm = findViewById(R.id.ordNm);
    }

    private void image(){
        RequestOptions requestOptions = new RequestOptions()
                .transform(new RoundedCorners(20)) // 둥글게 처리를 위한 RoundedCorners 변환 적용
                .diskCacheStrategy(DiskCacheStrategy.ALL); // 디스크 캐싱 전략 설정

        Glide.with(this)
                .load(des_imageUrl)
                .apply(requestOptions)
                .transition(DrawableTransitionOptions.withCrossFade()) // 이미지 로딩 시 CrossFade 효과 적용
                .diskCacheStrategy(DiskCacheStrategy.ALL) // 디스크 캐싱 전략 설정
                .into(imageView);
    }
}