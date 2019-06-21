package com.ramyhelow.newsapi.Adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ramyhelow.newsapi.Model.Article;
import com.ramyhelow.newsapi.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.MyViewHolder> {
    ArrayList<Article> data;
    Activity activity;

    public ArticleAdapter(ArrayList<Article> data, Activity activity) {
        this.data = data;
        this.activity = activity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(activity).inflate(R.layout.article_list_item,viewGroup,false);
        return new ArticleAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, int i) {
        myViewHolder.article_title.setText(data.get(i).getArticle_title());
        myViewHolder.article_summary.setText(data.get(i).getArticle_description());
        Picasso.get().load(data.get(i).getArticle_image()).placeholder(R.drawable.ic_launcher_background).into(myViewHolder.article_image);


        myViewHolder.article_item_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity,"asda", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public LinearLayout article_item_layout;

        public ImageView article_image;
        public TextView article_title;
        public TextView article_summary;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            article_item_layout = itemView.findViewById(R.id.article_item_layout);

            article_image = itemView.findViewById(R.id.article_image);
            article_title = itemView.findViewById(R.id.article_title);
            article_summary = itemView.findViewById(R.id.article_summary);


        }
    }
}
