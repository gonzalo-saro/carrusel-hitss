package com.saro.carruselhitss.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.saro.carruselhitss.R;
import com.saro.carruselhitss.model.MovieModelRest;
import com.saro.carruselhitss.util.Constant;

import java.util.List;

public class ViewPagerAdapter extends PagerAdapter
{
    private List<MovieModelRest> movies;
    private LayoutInflater layoutInflater;
    private Context context;

    public ViewPagerAdapter(List<MovieModelRest> movies, Context context)
    {
        this.movies = movies;
        this.context = context;
    }

    @Override
    public int getCount()
    {
        int size = 0;
        if (movies != null && !movies.isEmpty())
        {
            size = movies.size();
        }
        return size;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object)
    {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position)
    {
        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.cardview_movie, container, false);

        ImageView imageView;
        imageView = view.findViewById(R.id.poster_movie);

        String url = movies.get(position).getPoster_path();
        Glide.with(context).load(Constant.BASE_URL_IMG + url).centerCrop().into(imageView);

        imageView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                builder.setTitle("Resumen")
                        .setMessage(movies.get(position).getOverview())
                        .setNegativeButton("Cerrar", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {

                            }
                        });

                AlertDialog alertDialog = builder.create();
                alertDialog.setCancelable(false);
                alertDialog.show();
            }
        });

        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object)
    {
        container.removeView((View) object);
    }
}
