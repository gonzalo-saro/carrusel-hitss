package com.saro.carruselhitss.service;

import com.saro.carruselhitss.model.MoviesModelRest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JSONService
{
    @GET("3/movie/{categoria}")
    Call<MoviesModelRest> getTopMovies(@Path("categoria") String categoria, @Query(value = "api_key") String api_key, @Query(value = "language") String idioma, @Query(value = "page") int paginas);
}
