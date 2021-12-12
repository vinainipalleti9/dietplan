package uk.ac.tees.aad.W9513634;

import  androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONObject;

public class ByFood extends AppCompatActivity {


    ListView listView;
    String mTitle[] ;
    String mDescription[];
    String images[];
    int id[];

    Button search;
    EditText field;
    MyAdapter adapter;

    @Override
    public boolean isDeviceProtectedStorage() {
        return super.isDeviceProtectedStorage();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_by_food);

        this.mTitle = new String[0];
        this.mDescription = new String[0];
        this.images = new String[0];
        this.id = new int[0];

        field = findViewById(R.id.editText2);
        search = findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(field.getText().toString().length()>2)
                {
                    makeReqest(field.getText().toString());
                }

            }
        });

        listView = findViewById(R.id.listview);
        // now create an adapter class

        adapter = new MyAdapter(this, mTitle, mDescription, images);
        listView.setAdapter(adapter);
        // there is my mistake...
        // now again check this..

        // now set item click on list view
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long idd) {
                Intent intent = new Intent(getApplicationContext(),FoodItem.class);
                intent.putExtra("id",id[position]);
                startActivity(intent);
            }
        });
    }


    public void makeReqest(String value)
    {

        String API = "09b120a52b214939bff8d2a68314316f";
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://api.spoonacular.com/recipes/complexSearch?apiKey="+API+"&query="+value;

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject obj = new JSONObject(response.toString());
                            JSONArray array = obj.getJSONArray("results");
                            int length = array.length();

                            mTitle = new String[length];
                            mDescription = new String[length];
                            images = new String[length];
                            id = new int[length];
                            for (int i =0; i<length;i++)
                            {
                                mTitle[i] = array.getJSONObject(i).getString("title");
                                mDescription[i] = array.getJSONObject(i).getString("title");
                                images[i] = array.getJSONObject(i).getString("image");
                                id[i] = array.getJSONObject(i).getInt("id");
                            }
                            adapter = new MyAdapter(getApplicationContext(), mTitle, mDescription, images);
                            listView.setAdapter(adapter);
                        }catch (Exception e)
                        {
                            Toast.makeText(getApplicationContext(),"Failed to fetch data",Toast.LENGTH_LONG).show();
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


class MyAdapter extends ArrayAdapter<String> {

    Context context;
    String rTitle[];
    String rDescription[];
    String rImgs[];

    MyAdapter (Context c, String title[], String description[], String imgs[]) {
        super(c, R.layout.single_row, R.id.textView1, title);
        this.context = c;
        this.rTitle = title;
        this.rDescription = description;
        this.rImgs = imgs;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater)getContext().getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = layoutInflater.inflate(R.layout.single_row, parent, false);
        ImageView images = row.findViewById(R.id.image);
        TextView myTitle = row.findViewById(R.id.textView1);
        TextView myDescription = row.findViewById(R.id.textView2);

        Glide.with(getContext().getApplicationContext()).load(rImgs[position]).into(images);
        myTitle.setText(rTitle[position]);
        myDescription.setText(rDescription[position]);
        return row;
    }
}
