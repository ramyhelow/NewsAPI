package com.ramyhelow.newsapi.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.ramyhelow.newsapi.Model.Article;
import com.ramyhelow.newsapi.R;
import com.squareup.picasso.Picasso;
import com.thefinestartist.finestwebview.FinestWebView;

import java.util.ArrayList;

public class Article_RecyclerViewAdapter extends RecyclerView.Adapter<Article_RecyclerViewAdapter.MyViewHolder> {
    ArrayList<Article> data;
    Activity activity;

    public Article_RecyclerViewAdapter(ArrayList<Article> data, Activity activity) {
        this.data = data;
        this.activity = activity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(activity).inflate(R.layout.article_list_item,viewGroup,false);
        return new Article_RecyclerViewAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, int i) {
        myViewHolder.article_title.setText(data.get(i).getArticle_title());
        myViewHolder.article_description.setText(data.get(i).getArticle_description());

        if(!data.get(i).getArticle_image().isEmpty()){
            Picasso.get().load(data.get(i).getArticle_image()).placeholder(R.drawable.loading).into(myViewHolder.article_image);
        }else{
            Picasso.get().load(R.drawable.no_image_available).into(myViewHolder.article_image);
        }

        final String url = data.get(i).getArticle_url();
        myViewHolder.article_item_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new FinestWebView.Builder(activity).show(url);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public CardView article_item_layout;

        public ImageView article_image;
        public TextView article_title;
        public TextView article_description;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            article_item_layout = itemView.findViewById(R.id.article_item_layout);

            article_image = itemView.findViewById(R.id.article_image);
            article_title = itemView.findViewById(R.id.article_title);
            article_description = itemView.findViewById(R.id.article_description);


        }
    }
}
