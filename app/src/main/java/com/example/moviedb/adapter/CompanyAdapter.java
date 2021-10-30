package com.example.moviedb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviedb.R;
import com.example.moviedb.helper.Const;
import com.example.moviedb.model.Movies;
import com.example.moviedb.model.NowPlaying;

import java.util.List;

public class CompanyAdapter extends RecyclerView.Adapter<CompanyAdapter.CardViewViewHolder> {

    private Context context;
    private List<Movies.ProductionCompanies> productionCompanies;

    private List<Movies.ProductionCompanies> getProductionCompanies() {
        return productionCompanies;
    }

    public void setProductionCompanies(List<Movies.ProductionCompanies> productionCompanies) {
        this.productionCompanies = productionCompanies;
    }

    public CompanyAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_production_company, parent, false);
        return new CompanyAdapter.CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewViewHolder holder, int position) {
        final Movies.ProductionCompanies results = getProductionCompanies().get(position);
        Glide.with(context)
                .load(Const.IMG_URL + results.getLogo_path())
                .into(holder.company_img);
        holder.company_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, results.getName(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return getProductionCompanies().size();

    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder {
        ImageView company_img;

        public CardViewViewHolder(@NonNull View itemView) {
            super(itemView);
            company_img = itemView.findViewById(R.id.card_company);
        }
    }
}

