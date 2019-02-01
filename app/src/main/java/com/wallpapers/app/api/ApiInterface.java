package com.wallpapers.app.api;

import com.wallpapers.app.model.Wallpapers;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @Headers({"Authorization:563492ad6f91700001000001af0780584a554fd3862c7f35b0d35943n"})
    @GET("curated?")
    Call<Wallpapers> getWallpapers(
            @Query("per_page") int perPageCount,
            @Query("page") int pageNo
    );
}
