package com.dilara.retrofitjava.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.dilara.retrofitjava.R;
import com.dilara.retrofitjava.adapter.RecyclerViewAdapter;
import com.dilara.retrofitjava.model.CryptoModel;
import com.dilara.retrofitjava.service.CryptoAPI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ArrayList<CryptoModel> cryptoModels;
    private String BASE_URL="https://raw.githubusercontent.com/";
    Retrofit retrofit;
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // https://raw.githubusercontent.com/atilsamancioglu/K21-JSONDataSet/master/crypto.json

        recyclerView=findViewById(R.id.recyclerView);


        //Retrofit&json
        Gson gson=new GsonBuilder().setLenient().create();
        retrofit=new Retrofit.Builder().baseUrl(BASE_URL) //nereden bağlanacağı
                .addConverterFactory(GsonConverterFactory.create(gson)) //json
                .build();

        loadData();
    }
    private void loadData(){
        CryptoAPI cryptoAPI=retrofit.create(CryptoAPI.class);//servis oluşturuldu
        Call<List<CryptoModel>> call=cryptoAPI.getData();  //veri çekme işlemi call ile yapılr

        call.enqueue(new Callback<List<CryptoModel>>() { //asenkron şekilde geiek cevaba göre işlem yapar
            @Override
            public void onResponse(Call<List<CryptoModel>> call, Response<List<CryptoModel>> response) {
                if(response.isSuccessful()){
                    List<CryptoModel> responseList =response.body();
                    cryptoModels=new ArrayList<>(responseList);

                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    recyclerViewAdapter=new RecyclerViewAdapter(cryptoModels);
                    recyclerView.setAdapter(recyclerViewAdapter);


                }
            }

            @Override
            public void onFailure(Call<List<CryptoModel>> call, Throwable t) {

                t.printStackTrace();
            }
        });

    }
}