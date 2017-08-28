package com.example.hp.movieexplorer;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.ParsedRequestListener;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class PostersFragment extends Fragment {


    RecyclerView posterRV;
    RecyclerView.LayoutManager mLayoutManager ;

    public PostersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_posters, container, false);

        posterRV = view.findViewById(R.id.poster_RV);
        mLayoutManager = new GridLayoutManager(getActivity() , 2 );

        AndroidNetworking.get("https://api.themoviedb.org/3/movie/top_rated")
        .addQueryParameter("api_key", "0f167dcd1f0e20a46b7d6d95658fc544")
        .build()
                .getAsObject(MoviePage.class, new ParsedRequestListener() {
                    @Override
                    public void onResponse(Object response) {
                     posterRV.setLayoutManager(mLayoutManager);
                        RecyclerAdapter adapter =  new RecyclerAdapter((MoviePage) response , getActivity());
                        posterRV.setAdapter(adapter);
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(getActivity(), anError.getErrorDetail(), Toast.LENGTH_LONG).show();
                    }
                });


        return view;
    }


}
