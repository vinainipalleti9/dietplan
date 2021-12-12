package uk.ac.tees.aad.W9513634;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Dashboard extends AppCompatActivity  implements View.OnClickListener {

    Button byfood ;
    Button byexercise;
    Button trainer;
    Button Gym;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        byfood = findViewById(R.id.Cbyfood);
        byfood.setOnClickListener(this);
        byexercise = findViewById(R.id.byexercise);
        byexercise.setOnClickListener(this);
        trainer = findViewById(R.id.trainer);
        trainer.setOnClickListener(this);
        Gym = findViewById(R.id.gym);
        Gym.setOnClickListener(this);
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
                startActivities(new Intent[]{new Intent(getApplicationContext(),ByFood.class)});
                break;
            }
        }

    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
