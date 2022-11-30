package com.fariz.travel.activity.api;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.fariz.travel.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class listapi extends AppCompatActivity {
    RecyclerView rv;
    ArrayList<Response> mainModels;
    Adapterthis distributorListAdapterku;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listapi);

        mainModels = new ArrayList<>();
        rv = findViewById(R.id.dl_main_recycler);
        loadfp();
    }

    private void loadfp() {

        listInterface api = retrofitapi.getClient(listapi.this).create(listInterface.class);
        Toast.makeText(getApplicationContext(),"Loading...",Toast.LENGTH_SHORT).show();
        Call<List<Response>>call=api.getdata();
        call.enqueue(new Callback<List<Response>>() {
            @Override
            public void onResponse(Call<List<Response>> call, retrofit2.Response<List<Response>> response) {


                if(response.code() == 200){
                    List<Response> results = response.body();
                    Toast.makeText(getApplicationContext(),"Succes",Toast.LENGTH_SHORT).show();
                    for (int i=0;i<results.size();i++){
                        Log.d("location",results.get(i).getId());
                        Response model = new Response(results.get(i).getId(), results.get(i).getName(), results.get(i).getImg());
                        mainModels.add(model);
                    }
                    callx();
                }else {
                    Toast.makeText(getApplicationContext(),"Fail to get data!",Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<List<Response>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Error!",Toast.LENGTH_SHORT).show();
            }
        });
//        call.enqueue(new Callback<distributorListResponse>() {
//            @Override
//            public void onResponse(Call<distributorListResponse> call, Response<distributorListResponse> response) {
//                Toast.makeText(getApplicationContext(),"SUSS",Toast.LENGTH_SHORT).show();
//                try {
//                    if(response.code() == 200){
//                        List<distributorListRequestData> results = response.body().getResults();
//
//                        Toast.makeText(getApplicationContext(),"SUSS",Toast.LENGTH_SHORT).show();
//                        for (int i=0;i<results.size();i++){
//                            Log.d("location",results.get(i).getId());
//                        }
//                    }else {
//                        Toast.makeText(listapi.this, "FAILED", Toast.LENGTH_SHORT).show();
//                    }
//
//
//
//                }catch (Exception e){
//                    Toast.makeText(listapi.this, e.getLocalizedMessage().toString(), Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<distributorListResponse> call, Throwable t) {
//
//            }
//        });
    }

    private void callx() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(
                listapi.this, LinearLayoutManager.VERTICAL, false
        );
        rv.setLayoutManager(layoutManager);
        rv.setItemAnimator(new DefaultItemAnimator());
        distributorListAdapterku = new Adapterthis(listapi.this, mainModels);
        rv.setAdapter(distributorListAdapterku);
    }
}