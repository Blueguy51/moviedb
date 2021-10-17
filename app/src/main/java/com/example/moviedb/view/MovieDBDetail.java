package com.example.moviedb.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.moviedb.R;
import com.example.moviedb.helper.Const;
import com.example.moviedb.model.Movies;
import com.example.moviedb.viewmodel.MovieViewModel;

import java.util.ArrayList;

public class MovieDBDetail extends AppCompatActivity {

    private TextView title_dbdetail;
    private ImageView imageView_dbdetail;
    private TextView note_dbdetail;
    private TextView genre_dbdetail;
    private MovieViewModel model_view;
    private String movieid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_dbdetail);

        title_dbdetail = findViewById(R.id.title_dbdetail);
        imageView_dbdetail = findViewById(R.id.imageView_dbdetail);
        note_dbdetail = findViewById(R.id.note_dbdetail);
        genre_dbdetail = findViewById(R.id.genre_dbdetail);

        Intent intent = getIntent();
        movieid = intent.getStringExtra("movieid");
        model_view = new ViewModelProvider(MovieDBDetail.this).get(MovieViewModel.class);

        model_view.getMovieById(movieid);
        model_view.getResultGetMovieById().observe(MovieDBDetail.this, showresultmoviedb);
    }

    private Observer<Movies> showresultmoviedb = new Observer<Movies>() {
        @Override
        public void onChanged(Movies movies) {
            if (movies == null) {
                Log.e("Movie", "null");
            } else {
                ArrayList<Movies.Genres> genres = (ArrayList<Movies.Genres>) movies.getGenres();
                String genre="";
                for (int i = 0; i < genres.size(); i++) {
                    genre += genres.get(i).getName();
                    if (i != genres.size()-1){
                        genre += ", ";
                    }
                }
                title_dbdetail.setText(movies.getTitle());
                Glide.with(MovieDBDetail.this).load(Const.IMG_URL + movies.getPoster_path()).into(imageView_dbdetail);
                genre_dbdetail.setText(genre);
                note_dbdetail.setText(movies.getOverview());
            }
        }
    };
}