package com.example.rtrft;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class MainActivity extends AppCompatActivity {
    String API_URL = "https://pixabay.com/";
    String q = "bad dog";
    String key = "16115131-f2ac4e59ef4204b7d06f11215";
    String image_type;
    Picasso picasso;
    ListView iv;
    ImageList adapter;
    ArrayList<Hit> hts = new ArrayList<>();
    Spinner spinner;

    interface PixabayAPI {
        @GET("/api")
        Call<Response> search(@Query("q") String q, @Query("key") String key, @Query("image_type") String image_type);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = findViewById(R.id.spin);
        picasso = new Picasso.Builder(this).build();
        iv = findViewById(R.id.image);
        adapter = new ImageList(this, hts, picasso);
        iv.setAdapter(adapter);
    }

    public void startSearch(String text) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PixabayAPI api = retrofit.create(PixabayAPI.class);

        Call<Response> call = api.search(text, key, image_type);

        Callback<Response> callback = new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                Response r = response.body();
                displayResults(r.hits);
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
            }
        };
        call.enqueue(callback);

    }

    public void displayResults(Hit[] hits) {
        hts.clear();
        Collections.addAll(hts, hits);
        adapter.notifyDataSetChanged();
    }

    public void onSearchClick(View v) {
        EditText etSearch = findViewById(R.id.text);
        String text = etSearch.getText().toString();
        image_type = spinner.getSelectedItem().toString();
        startSearch(text);
    }
}