package com.example.arpit.jioassignment.view;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.arpit.jioassignment.R;
import com.example.arpit.jioassignment.interfaces.IClickListener;
import com.example.arpit.jioassignment.interfaces.INetworkClient;
import com.example.arpit.jioassignment.model.DataModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by arpit on 15/6/18.
 */

public class MainActivity extends AppCompatActivity implements IClickListener {

    RecyclerView recyclerView;
    List<DataModel> dataModel;
    MyAdapter myAdapter;
    int positionClicked;
    ImageView imageView;
    TextView fetch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        imageView = (ImageView)findViewById(R.id.loading);
        fetch = (TextView) findViewById(R.id.fetch);
        imageView.setImageResource(R.drawable.loading);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(MainActivity.this,
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);


        //getData from network
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com").addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();

        INetworkClient networkClient = retrofit.create(INetworkClient.class);
        Call<List<DataModel>> call = networkClient.getData();
        call.enqueue(new Callback<List<DataModel>>() {
            @Override
            public void onResponse(Call<List<DataModel>> call, Response<List<DataModel>> response) {
                imageView.setVisibility(View.GONE);
                fetch.setVisibility(View.GONE);
                dataModel = response.body();
                //Set the data in adapter
                myAdapter = new MyAdapter(getApplicationContext(),dataModel);
                recyclerView.setAdapter(myAdapter);
                myAdapter.setClickListener(MainActivity.this);

            }

            @Override
            public void onFailure(Call<List<DataModel>> call, Throwable t) {
                imageView.setVisibility(View.GONE);
                fetch.setText("Unable to fetch data because of No Internet ");
                //Toast.makeText(getApplicationContext(),"Error in Fetching data",Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onClick(int position) {
        positionClicked = position;
        //Send intent to open next page
        Intent intent = new Intent(MainActivity.this,NextPage.class);
        intent.putExtra("status",dataModel.get(position).getCompleted());
        startActivityForResult(intent,1);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1:
               if(data != null && data.getBooleanExtra("status",true)) {
                   dataModel.get(positionClicked).setCompleted();
                   myAdapter.notifyItemChanged(positionClicked);
               }
               break;
            default:
                break;

        }
    }
}
