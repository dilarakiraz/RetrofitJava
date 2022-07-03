package com.dilara.retrofitjava.service;

import com.dilara.retrofitjava.model.CryptoModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CryptoAPI {

    //get,post,update,delete

    @GET("atilsamancioglu/K21-JSONDataSet/master/crypto.json")
    Call<List<CryptoModel>> getData();//asenkron şekilde ne indireceğimizi ve hangi method iinde indireceğimizi

}
