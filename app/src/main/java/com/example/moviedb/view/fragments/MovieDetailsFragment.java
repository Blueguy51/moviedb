package com.example.moviedb.view.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.moviedb.R;
import com.example.moviedb.adapter.CompanyAdapter;
import com.example.moviedb.helper.Const;
import com.example.moviedb.model.Movies;
import com.example.moviedb.view.activity.MovieDBDetail;
import com.example.moviedb.viewmodel.MovieViewModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MovieDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MovieDetailsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MovieDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MovieDetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MovieDetailsFragment newInstance(String param1, String param2) {
        MovieDetailsFragment fragment = new MovieDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private TextView title_dbdetail;
    private ImageView imageView_dbdetail;
    private TextView note_dbdetail;
    private TextView genre_dbdetail;
    private MovieViewModel model_view;
    private String movieid;
    private TextView minititle_dbdetail;
    private TextView popularity_dbdetail;
    private TextView tagline_dbdetail;
    private RecyclerView company_dbdetail;
    private TextView avg_dbdetail;
    private TextView release_date_dbdetail;
    private TextView vote_dbdetail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie_details, container, false);
        title_dbdetail = view.findViewById(R.id.title_dbdetail);
        imageView_dbdetail = view.findViewById(R.id.imageView_dbdetail);
        note_dbdetail = view.findViewById(R.id.note_dbdetail);
        genre_dbdetail = view.findViewById(R.id.genre_dbdetail);

        minititle_dbdetail = view.findViewById(R.id.minitittle_dbdetail);
        popularity_dbdetail = view.findViewById(R.id.popularity_dbdetail);
        tagline_dbdetail = view.findViewById(R.id.tagline_dbdetail);
        avg_dbdetail = view.findViewById(R.id.rating_dbdetail);
        release_date_dbdetail = view.findViewById(R.id.release_date_dbdetail);
        vote_dbdetail = view.findViewById(R.id.vote_dbdetail);
        company_dbdetail = view.findViewById(R.id.company_dbdetail);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        company_dbdetail.setLayoutManager(manager);

        model_view = new ViewModelProvider(MovieDetailsFragment.this).get(MovieViewModel.class);
        movieid = getArguments().getString("movieId");
        model_view.getMovieById(movieid);
        model_view.getResultGetMovieById().observe(getActivity(), showresultmoviedb);
        return view;
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
                    ArrayList<Movies.ProductionCompanies> productionCompanies = (ArrayList<Movies.ProductionCompanies>) movies.getProduction_companies();
                    CompanyAdapter adapter = new CompanyAdapter(getActivity());
                    adapter.setProductionCompanies(productionCompanies);
                    company_dbdetail.setAdapter(adapter);

                    title_dbdetail.setText(movies.getTitle());
                    Glide.with(MovieDetailsFragment.this).load(Const.IMG_URL + movies.getPoster_path()).into(imageView_dbdetail);
                    genre_dbdetail.setText(genre);
                    note_dbdetail.setText(movies.getOverview());
                    minititle_dbdetail.setText(movies.getTitle());
                    popularity_dbdetail.setText(String.valueOf(movies.getPopularity()));
                    tagline_dbdetail.setText("\""+movies.getTagline()+"\"");
                    avg_dbdetail.setText(String.valueOf(movies.getVote_average()));
                    release_date_dbdetail.setText(movies.getRelease_date());
                    vote_dbdetail.setText(String.valueOf(movies.getVote_count()));
                }
            }
        };
    }

