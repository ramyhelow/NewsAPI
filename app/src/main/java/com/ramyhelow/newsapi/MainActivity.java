package com.ramyhelow.newsapi;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.ramyhelow.newsapi.Adapter.ArticleAdapter;
import com.ramyhelow.newsapi.Controller.AppController;
import com.ramyhelow.newsapi.Model.Article;
import com.ramyhelow.newsapi.URL.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Article> data;
    ArticleAdapter articleAdapter;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getAllArticles();
    }


    private void showDialog() {
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Loading... ");
        progressDialog.show();
    }

    private void hideDialog() {
        progressDialog.hide();
    }

    public void getAllArticles() {
        showDialog();

        data = new ArrayList<>();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL.URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArrayArticles = response.getJSONArray("articles");


                    for (int i = 0; i < jsonArrayArticles.length(); i++) {


                        JSONObject jsonArticle = jsonArrayArticles.getJSONObject(i);


                        String title = jsonArticle.getString("title");
                        String image = jsonArticle.getString("urlToImage");
                        String description = jsonArticle.getString("content");

                        data.add(new Article(image, title, description));

                        hideDialog();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    hideDialog();
                }

                recyclerView = findViewById(R.id.articles_recyclerView);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                articleAdapter = new ArticleAdapter(data, MainActivity.this);
                recyclerView.setAdapter(articleAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("hzm", error.getMessage());
                hideDialog();
            }
        });

        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    }
}
