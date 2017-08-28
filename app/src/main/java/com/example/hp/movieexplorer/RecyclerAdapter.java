package com.example.hp.movieexplorer;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by HP on 8/14/2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerHolder> {
    private MoviePage list;
    private Context mContext;

    public RecyclerAdapter(MoviePage list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
    }


    @Override
    public RecyclerAdapter.RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.movie_poster, parent, false);
        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.RecyclerHolder holder, final int position) {

        Results movie = list.getResults()[position];
        Picasso.with(mContext)
                .load("https://image.tmdb.org/t/p/w500/" + movie.getPoster_path())
                .placeholder(R.drawable.index)
                .error(R.drawable.error)
                .into(holder.image);
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext , DetailsActivity.class);
                        intent.putExtra("details" , list.getResults()[position]);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.getResults().length;
    }

    class RecyclerHolder extends RecyclerView.ViewHolder {

        private ImageView image;

        public RecyclerHolder(View view) {
            super(view);
            this.image = view.findViewById(R.id.movie_poster);

        }
    }
}

