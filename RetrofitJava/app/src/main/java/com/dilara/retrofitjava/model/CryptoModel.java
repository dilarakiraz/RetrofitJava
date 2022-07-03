package com.dilara.retrofitjava.model;

import com.google.gson.annotations.SerializedName;

public class CryptoModel {
    @SerializedName("currency")//jsona ulaşmamızı sağlar
    public String currency;
    @SerializedName("price")
    public String price;

}
