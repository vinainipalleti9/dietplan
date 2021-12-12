package uk.ac.tees.aad.W9513634;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Success extends AppCompatActivity {

    String message;
    ImageView imageView;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        imageView = findViewById(R.id.success_image);
        textView = findViewById(R.id.message_final_success);


        message = getIntent().getStringExtra("message");

        if(getIntent().getBooleanExtra("type",true))
        {
            textView.setText(message);
        }
        else {
            imageView.setImageDrawable(getResources().getDrawable(getResources().getIdentifier("@drawable/wrong",null,getPackageName())));
            textView.setText(message);
        }
    }
}
