package uk.ac.tees.aad.W9513634;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ByExercise extends AppCompatActivity {


    ListView listView;
    String mTitle[] ;
    String mDescription[];
    String images[];
    int id[];

    Button search;
    EditText field;
    MyAdapter adapter;

    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    FirebaseUser firebaseUser;

    ArrayList<Exercises> exercises;

    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(getApplicationContext(),Dashboard.class));
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_by_exercise);


        this.mTitle = new String[0];
        this.mDescription = new String[0];
        this.images = new String[0];

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        exercises = new ArrayList<Exercises>();


        field = findViewById(R.id.serachItem);

        listView = findViewById(R.id.listview);

        makeReqest();
        field.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                   filter(s.toString());
                   if(s.toString().equals(""))
                   {
                       showall();
                   }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void filter(String s)
    {
        ArrayList<Exercises> list = new ArrayList<>();

        for (int i =0; i<exercises.size();i++)
        {
            if(exercises.get(i).getName().toLowerCase().contains(s.toLowerCase()))
            {
               list.add(exercises.get(i));
            }
        }
        this.mTitle = new String[list.size()];
        this.mDescription = new String[list.size()];
        this.images = new String[list.size()];

        for (int i =0; i< list.size();i++)
        {
            this.mTitle[i] = list.get(i).getName();
            this.mDescription[i] = list.get(i).getCalories();
            this.images[i] =list.get(i).getImage();
        }

        adapter = new MyAdapter(getApplicationContext(), mTitle, mDescription, images);
        listView.setAdapter(adapter);
    }

    public void showall()
    {
        ArrayList<Exercises> list = new ArrayList<>();

        for (int i =0; i<exercises.size();i++)
        {

                list.add(exercises.get(i));

        }
        this.mTitle = new String[list.size()];
        this.mDescription = new String[list.size()];
        this.images = new String[list.size()];

        for (int i =0; i< list.size();i++)
        {
            this.mTitle[i] = list.get(i).getName();
            this.mDescription[i] = list.get(i).getCalories();
            this.images[i] =list.get(i).getImage();
        }

        adapter = new MyAdapter(getApplicationContext(), mTitle, mDescription, images);
        listView.setAdapter(adapter);
    }

    public void makeReqest()
    {
        firebaseDatabase.getReference("Exercise").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data: dataSnapshot.getChildren()) {
                    Exercises ex = new Exercises();
                    ex.setName(data.getKey());
                    Exercises exe = data.getValue(Exercises.class);
                        ex.setCalories( exe.getCalories());
                        ex.setImage(exe.getImage());
                        exercises.add(ex);
                }
                mTitle = new String[exercises.size()];
                mDescription = new String[exercises.size()];
                images = new String[exercises.size()];
                            for (int i =0; i<exercises.size();i++)
                            {
                                mTitle[i] = exercises.get(i).getName();
                                mDescription[i] = exercises.get(i).getCalories();
                                images[i] = exercises.get(i).getImage();
                            }
                            adapter = new MyAdapter(getApplicationContext(), mTitle, mDescription, images);
                            listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });


    }
}


