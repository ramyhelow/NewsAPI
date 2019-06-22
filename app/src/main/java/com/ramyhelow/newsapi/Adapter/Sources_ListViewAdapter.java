package com.ramyhelow.newsapi.Adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.ramyhelow.newsapi.Controller.AppController;
import com.ramyhelow.newsapi.Model.Article;
import com.ramyhelow.newsapi.Model.Source;
import com.ramyhelow.newsapi.R;
import com.ramyhelow.newsapi.URL.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Sources_ListViewAdapter extends BaseAdapter {

    Activity activity;
    ArrayList<Source> sources_data;
    LayoutInflater layoutInflater;
    public Sources_ListViewAdapter(Activity activity, ArrayList<Source> sources_data) {
        this.activity = activity;
        this.sources_data = sources_data;
        layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return sources_data.size();
    }

    @Override
    public Object getItem(int position) {
        return sources_data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View root = convertView;
        if(root == null){
            root = layoutInflater.inflate(R.layout.source_list_item,null);
        }

        TextView product_title = root.findViewById(R.id.source_header_title);
        final RecyclerView recyclerView = root.findViewById(R.id.articles_recyclerView);

        product_title.setText(sources_data.get(position).getSource_name());

        //
        final View myRoot = root;
        final ArrayList<Article> articles_data = new ArrayList<>();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL.getHeadlinesFromSourceURL(sources_data.get(position).getSource_id()), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArrayArticles = response.getJSONArray("articles");


                    for (int i = 0; i < jsonArrayArticles.length(); i++) {


                        JSONObject jsonArticle = jsonArrayArticles.getJSONObject(i);


                        String title = jsonArticle.getString("title");
                        String image = jsonArticle.getString("urlToImage");
                        String description = jsonArticle.getString("description");
                        String url = jsonArticle.getString("url");

                        articles_data.add(new Article(image, title, description, url));

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                recyclerView.setLayoutManager(new LinearLayoutManager(myRoot.getContext(),RecyclerView.HORIZONTAL,false));
                Article_RecyclerViewAdapter articleAdapter = new Article_RecyclerViewAdapter(articles_data, (Activity) myRoot.getContext());
                recyclerView.setAdapter(articleAdapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("hzm", error.getMessage());
            }
        });

        AppController.getInstance().addToRequestQueue(jsonObjectRequest);

        //

        return root;
    }
}