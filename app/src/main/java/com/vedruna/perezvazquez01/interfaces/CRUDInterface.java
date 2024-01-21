package com.vedruna.perezvazquez01.interfaces;

import com.vedruna.perezvazquez01.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CRUDInterface {
    @GET("product")
    Call<List<Product>> getAll();

}
