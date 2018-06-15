package com.example.arpit.jioassignment.interfaces;

import com.example.arpit.jioassignment.model.DataModel;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by arpit on 15/6/18.
 */

public interface INetworkClient {
    @GET("todos")
    Call<List<DataModel>> getData();
}
