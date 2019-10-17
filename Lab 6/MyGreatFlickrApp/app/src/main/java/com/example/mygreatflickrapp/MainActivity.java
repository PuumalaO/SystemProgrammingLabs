package com.example.mygreatflickrapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {

    private String apiKey="a88920e31d8eb8d6279c71fddce191c5";
    private ListView listView;
    private ArrayList<String> imageUrls = new ArrayList<String>();
    private Button searchButton;
    private EditText searchField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchField = findViewById(R.id.searchField);
        searchButton = findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchTerm = searchField.getText().toString();
                searchImages(searchTerm);
            }
        });
        listView = findViewById(R.id.flickrImageList);
        updateAdapter();
    }

    public void searchImages(String searchTerm){
        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "https://www.flickr.com/services/rest/?method=flickr.photos.search&api_key="+apiKey+"&text="+searchTerm+"&per_page=10&format=json&nojsoncallback=1";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Log.d("debug", "onResponse: "+response);
                        try {
                            imageUrls.clear();
                            JSONObject jsonRes = new JSONObject(response);
                            JSONArray photos = jsonRes.getJSONObject("photos").getJSONArray("photo");
                            for (int i=0; i < photos.length(); i++) {
                                String farm = photos.getJSONObject(i).getString("farm");
                                String server = photos.getJSONObject(i).getString("server");
                                String id = photos.getJSONObject(i).getString("id");
                                String secret = photos.getJSONObject(i).getString("secret");

                                String newUrl = "https://farm"+farm+".staticflickr.com/"+server+"/"+id+"_"+secret+".jpg";
                                imageUrls.add(newUrl);

                            }
                            updateAdapter();
                            Log.d("debug", "onResponse: array"+photos.get(1));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("debug", "onErrorResponse: ");
                Toast.makeText(MainActivity.this,"Oops! Something went wrong.",Toast.LENGTH_LONG).show();
            }
        });
        queue.add(stringRequest);
    }

    public void updateAdapter(){
        ImageAdapter imageAdapter = new ImageAdapter(this,R.layout.flickr_image ,imageUrls);
        listView.setAdapter(imageAdapter);
    }
}
