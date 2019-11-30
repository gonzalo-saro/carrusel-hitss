package com.saro.carruselhitss.activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.saro.carruselhitss.R;
import com.saro.carruselhitss.adapter.ViewPagerAdapter;
import com.saro.carruselhitss.dialog.ErrorConexionDialog;
import com.saro.carruselhitss.model.MovieModelRest;
import com.saro.carruselhitss.model.MoviesModelRest;
import com.saro.carruselhitss.util.Constant;
import com.saro.carruselhitss.util.RetrofitBuild;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
{
    @BindView(R.id.viewPager_movies)
    ViewPager viewPagerMovies;
    ViewPagerAdapter viewPagerAdapter;
    MoviesModelRest moviesModelRest;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Call<MoviesModelRest> moviesTopRated = RetrofitBuild.apiService.getTopMovies(Constant.TOP_RATED, Constant.API_KEY, Constant.IDIOMA, Constant.PAGINAS);

        moviesTopRated.enqueue(new Callback<MoviesModelRest>()
        {
            @Override
            public void onResponse(Call<MoviesModelRest> call, Response<MoviesModelRest> response)
            {
                if (response.isSuccessful())
                {
                    if (response.code() == Constant.NOT_FOUND_HTTP || response.code() == Constant.INTERNAL_SERVER_HTTP)
                    {
                        mostrarError();
                    } else if (response.code() == Constant.NO_CONTENT_HTTP || response.body() == null || response.body().getResults().isEmpty())
                    {
                        Toast.makeText(getBaseContext(), "No se obtuvieron datos. Intente más tarde", Toast.LENGTH_SHORT).show();
                    } else if (response.body() != null && !response.body().getResults().isEmpty())
                    {
                        moviesModelRest = response.body();
                        List<MovieModelRest> moviesResult = new ArrayList<>();
                        for (int i = 0; i < 10; i++)
                        {
                            moviesResult.add(response.body().getResults().get(i));
                        }
                        moviesModelRest.setResults(moviesResult);

                        viewPagerAdapter = new ViewPagerAdapter(moviesModelRest.getResults(), MainActivity.this);
                        viewPagerMovies.setAdapter(viewPagerAdapter);
                        viewPagerMovies.setPadding(50, 0, 50, 0);
                        viewPagerMovies.setPageMargin(15);
                    }
                } else
                {
                    mostrarError();
                }
            }

            @Override
            public void onFailure(Call<MoviesModelRest> call, Throwable t)
            {
                mostrarError();
            }
        });
    }

    private void mostrarError()
    {
        ErrorConexionDialog errorConexionDialog = new ErrorConexionDialog();
        errorConexionDialog.show(getSupportFragmentManager(), "Error");
    }
}
