package com.example.hp.movieexplorer;

import android.graphics.Movie;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

/**
 * Created by HP on 8/24/2017.
 */

public class PostersFragment1 extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager mLayoutManger;

    public PostersFragment1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_posters, container, false);
        recyclerView = view.findViewById(R.id.poster_RV);
        mLayoutManger = new GridLayoutManager(getActivity(), 2);

        AndroidNetworking.get("https://api.themoviedb.org/3/movie/popular")
                .addQueryParameter("api_key", "0f167dcd1f0e20a46b7d6d95658fc544")
                .build()
                .getAsObject(MoviePage.class, new ParsedRequestListener() {
                    @Override
                    public void onResponse(Object response) {
                        recyclerView.setLayoutManager(mLayoutManger);
                        RecyclerAdapter adapter = new RecyclerAdapter((MoviePage) response, getActivity());
                        recyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void onError(ANError anError) {
                    }
                });
        return view;
    }
}
