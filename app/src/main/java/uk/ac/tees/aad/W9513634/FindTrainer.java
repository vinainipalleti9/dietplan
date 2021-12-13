package uk.ac.tees.aad.W9513634;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Date;

public class FindTrainer extends AppCompatActivity {

    Spinner spinner1;
    Spinner spinner2;
    Spinner spinner3;

    Date date;
    Calendar calendar;
    String option1;
    String option2;
    String option3;

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

        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                option3 = res3[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                option2 = res2[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                option1 = res1[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        Button button  = findViewById(R.id.submit_trainer);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(option1.equals("Select Option") || option2.equals("Select Option") || option3.equals("Select Option"))
                {
                    Toast.makeText(getApplicationContext(),"Select all values",Toast.LENGTH_LONG).show();
                    return;
                }
                TrainerRequest req = new TrainerRequest();
                date = new Date();
                calendar = Calendar.getInstance();
                calendar.setTime(date);
                req.setDate(calendar.getTime().toString());
                req.setOption1(option1);
                req.setOption2(option2);
                req.setOption3(option3);
                FirebaseDatabase.getInstance().getReference("trainer").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .child(calendar.getTime().toString()).setValue(req).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Intent intent = new Intent(getApplicationContext(),Success.class);
                        intent.putExtra("type",true);
                        intent.putExtra("message","Consulted Trainer get back to you");
                        startActivity(intent);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Intent intent = new Intent(getApplicationContext(),Success.class);
                        intent.putExtra("type",false);
                        intent.putExtra("message","Unable to make request");
                        startActivity(intent);
                    }
                });

            }
        });

    }

    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(getApplicationContext(),Dashboard.class));
    }
}
