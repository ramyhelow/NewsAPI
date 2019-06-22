package com.ramyhelow.newsapi;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.ramyhelow.newsapi.Adapter.Sources_ListViewAdapter;
import com.ramyhelow.newsapi.Controller.AppController;
import com.ramyhelow.newsapi.Model.Article;
import com.ramyhelow.newsapi.Model.Source;
import com.ramyhelow.newsapi.URL.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Source> data_sources;
    Sources_ListViewAdapter sources_ListViewAdapter;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getTopHeadlines();
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

    public void getTopHeadlines() {
        showDialog();

        data_sources = new ArrayList<>();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL.URL_SOURCES, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArrayArticles = response.getJSONArray("sources");


                    for (int i = 0; i < jsonArrayArticles.length(); i++) {


                        JSONObject jsonSource = jsonArrayArticles.getJSONObject(i);


                        String source_id = jsonSource.getString("id");
                        String source_name = jsonSource.getString("name");

                        data_sources.add(new Source(source_id,source_name));

                        hideDialog();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    hideDialog();
                }

                ListView sources_listView = findViewById(R.id.sources_listView);
                Sources_ListViewAdapter sources_listViewAdapter = new Sources_ListViewAdapter(MainActivity.this,data_sources);
                sources_listView.setAdapter(sources_listViewAdapter);

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
