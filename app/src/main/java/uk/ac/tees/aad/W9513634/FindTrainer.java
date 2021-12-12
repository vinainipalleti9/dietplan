package uk.ac.tees.aad.W9513634;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class FindTrainer extends AppCompatActivity {

    Spinner spinner1;
    Spinner spinner2;
    Spinner spinner3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_trainer);

        spinner1 = findViewById(R.id.spinner);
        spinner2 = findViewById(R.id.spinner2);
        spinner3 = findViewById(R.id.spinner3);

        String[] res1 = {"Select Option","Weight Loss", "Weight Gain"};

        String[] res2 = {"Select Option","Diet Management","Exercise Management"};

        String[] res3 = {"Select Option","Cardio Management","Gain Management"};

        ArrayAdapter ad = new ArrayAdapter(this,android.R.layout.simple_spinner_item, res1);
        ArrayAdapter ad1 = new ArrayAdapter(this,android.R.layout.simple_spinner_item, res2);
        ArrayAdapter ad2 = new ArrayAdapter(this,android.R.layout.simple_spinner_item, res3);

        spinner1.setAdapter(ad);
        spinner2.setAdapter(ad1);
        spinner3.setAdapter(ad2);

        Button button  = findViewById(R.id.submit_trainer);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                FirebaseDatabase.getInstance().getReference("trainer").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue().
               Intent intent = new Intent(getApplicationContext(),Success.class);
               intent.putExtra("type",false);
               intent.putExtra("message","Consulted Trainer get back to you");
               startActivity(intent);
            }
        });

    }
}
