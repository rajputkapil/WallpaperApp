package com.wallpapers.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.wallpapers.app.adapters.WallpapersAdapter;
import com.wallpapers.app.api.GetApiResponse;
import com.wallpapers.app.api.ApiInterface;
import com.wallpapers.app.model.Photo;
import com.wallpapers.app.model.Wallpapers;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    int loadPageNo = 1;
    WallpapersAdapter wallpapersAdapter;
    RecyclerView recyclerView;
    List<Photo> wallpapers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view_wallpapers);
        wallpapers =new ArrayList<>();
        wallpapersAdapter = new WallpapersAdapter(MainActivity.this);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(wallpapersAdapter);
        getData(loadPageNo);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!recyclerView.canScrollVertically(1)) {
                    getData(loadPageNo);
                }
            }
        });
    }

    void getData(int pageNo) {
        ApiInterface apiInterface = GetApiResponse.getClient().create(ApiInterface.class);
        Call<Wallpapers> wallpapersCall = apiInterface.getWallpapers(15, pageNo);
        wallpapersCall.enqueue(new Callback<Wallpapers>() {
            @Override
            public void onResponse(Call<Wallpapers> call, Response<Wallpapers> response) {
                if (response.isSuccessful()) {
                    List<Photo> wallpapers = response.body().getPhotos();
                    MainActivity.this.wallpapers.addAll(wallpapers);
                    wallpapersAdapter.setAdapterDatasetAdapterData(MainActivity.this.wallpapers);
                    wallpapersAdapter.notifyDataSetChanged();
                    loadPageNo++;
                }
            }

            @Override
            public void onFailure(Call<Wallpapers> call, Throwable t) {
                Log.d(MainActivity.class.getSimpleName(), "error ");
            }
        });
    }


}
