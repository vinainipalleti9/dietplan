package uk.ac.tees.aad.W9513634;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONObject;

public class FoodItem extends AppCompatActivity {

    TextView heading;
    TextView summary;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.heading = findViewById(R.id.heading_final);
        this.summary = findViewById(R.id.summery_final);
        this.img = findViewById(R.id.head_image);

        int id = getIntent().getIntExtra("id",0);
        RequestQueue queue = Volley.newRequestQueue(this);

        String url ="https://api.spoonacular.com/recipes/"+id+"/information?apiKey=09b120a52b214939bff8d2a68314316f";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject obj = new JSONObject(response.toString());

                            heading.setText(obj.get("title").toString());
                            summary.setText(obj.get("summary").toString());
                            Glide.with(getApplicationContext()).load(obj.get("image").toString()).into(img);

                        }catch (Exception e)
                        {
                            Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(stringRequest);
    }
}
