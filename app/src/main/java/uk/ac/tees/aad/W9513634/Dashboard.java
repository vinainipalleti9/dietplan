package uk.ac.tees.aad.W9513634;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

public class Dashboard extends AppCompatActivity  implements View.OnClickListener {

    Button byfood ;
    Button byexercise;
    Button trainer;
    Button Gym;

    FusedLocationProviderClient fusedLocation ;
    private final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        fusedLocation =LocationServices.getFusedLocationProviderClient(this);
        byfood = findViewById(R.id.Cbyfood);
        byfood.setOnClickListener(this);
        byexercise = findViewById(R.id.byexercise);
        byexercise.setOnClickListener(this);
        trainer = findViewById(R.id.trainer);
        trainer.setOnClickListener(this);
        Gym = findViewById(R.id.gym);
        Gym.setOnClickListener(this);




        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
        }


    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.Cbyfood: {
                startActivities(new Intent[]{new Intent(getApplicationContext(),ByFood.class)});
                break;
            }
            case R.id.byexercise: {
                startActivities(new Intent[]{new Intent(getApplicationContext(),ByExercise.class)});
                break;
            }
            case R.id.trainer: {
                startActivities(new Intent[]{new Intent(getApplicationContext(),FindTrainer.class)});
                break;
            }
            case R.id.gym: {

                gotoGym();

                break;
            }
        }

    }

    private void gotoGym() {

        fusedLocation.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(@NonNull Location location) {
                if (location != null) {

                    SharedPreferences sharedPreferences = getSharedPreferences("location",MODE_PRIVATE);

                    SharedPreferences.Editor myEdit = sharedPreferences.edit();


                    myEdit.putString("latitude", String.valueOf(location.getLatitude()));
                    myEdit.putString("longitude", String.valueOf(location.getLongitude()));
                    Toast.makeText(getApplicationContext(),location.getLongitude()+" "+location.getLatitude(),Toast.LENGTH_LONG).show();

                    myEdit.commit();

                    Intent intent = new Intent(getApplicationContext(),Gyms.class);
                    startActivity(intent);
                }
            }
        });

        startActivity(new Intent(this,Gyms.class));

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(FirebaseAuth.getInstance().getCurrentUser() == null)
        {
            startActivity(new Intent(getApplicationContext(),Login.class));
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
