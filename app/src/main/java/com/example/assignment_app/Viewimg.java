package com.example.assignment_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Viewimg extends AppCompatActivity implements ViewAdapter.OnItemClickListener {
    public static final String EXTRA_URL = "imageUrl";
    public static final String EXTRA_NAME = "imageText";

    private Toolbar toolbar;

    ArrayList<ViewModel> viewModels = new ArrayList<>();
    private ViewAdapter viewAdapter;
    private RecyclerView item_recyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewimg);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        item_recyclerview = (RecyclerView)findViewById(R.id.item_recyclerview);
        item_recyclerview.setLayoutManager(new LinearLayoutManager(this));
        getItemResponse();
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    private void getItemResponse() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface requestInterface = retrofit.create(RequestInterface.class);
        Call<List<ViewModel>> call = requestInterface.getItemJson();

        call.enqueue(new Callback<List<ViewModel>>() {
            @Override
            public void onResponse(Call<List<ViewModel>> call, Response<List<ViewModel>> response) {
                viewModels = new ArrayList<>(response.body());
                viewAdapter = new ViewAdapter(Viewimg.this, viewModels);
                item_recyclerview.setAdapter(viewAdapter);

                viewAdapter.setOnItemClickListener(Viewimg.this);
                Toast.makeText(Viewimg.this,"Success", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<List<ViewModel>> call, Throwable t) {
                Toast.makeText(Viewimg.this,"Failed", Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public void onItemClick(int position) {
        Intent detailIntent = new Intent(this, DetailedImage.class);
        ViewModel clickedItem = viewModels.get(position);

        detailIntent.putExtra(EXTRA_URL, clickedItem.getUrl());
        detailIntent.putExtra(EXTRA_NAME, clickedItem.getTitle());

        startActivity(detailIntent);
    }



}
