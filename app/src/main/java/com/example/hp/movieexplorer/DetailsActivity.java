package com.example.hp.movieexplorer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.poster_details)
    ImageView posterDetails;
    @BindView(R.id.movie_details)
    TextView movieDetails;
    @BindView(R.id.Long_details)
    TextView LongDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //  onBackPressed();

        Results result = (Results) getIntent().getExtras().get("details");
        Picasso.with(this)
                .load("https://image.tmdb.org/t/p/w500/" + result.getPoster_path())
                .into(posterDetails);
        movieDetails.setText("Title : "+result.getOriginal_title() + "\n" + "Release Date : "+result.getRelease_date()
                + "\n" +"Original Languange : "+ result.getOriginal_language() + "\n" + "Popularity : "+result.getPopularity());
         LongDetails.setText(" OverView :"+result.getOverview());

    }
}
