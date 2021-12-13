package uk.ac.tees.aad.W9513634;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity implements View.OnClickListener {

    FirebaseUser firebaseUser;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    EditText email;

    EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase  = FirebaseDatabase.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        email = findViewById(R.id.signupEmail);
        password  = findViewById(R.id.signupPassword);

        Button login  = findViewById(R.id.create_acc);
        Button createAccount = findViewById(R.id.createAccount);

        createAccount.setOnClickListener(this);login.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.create_acc: {
                login();
                break;
            }
            case R.id.createAccount: {
                startActivities(new Intent[]{new Intent(getApplicationContext(),Register.class)});
                break;
            }

        }

    }
    @Override
    protected void onStart() {
        super.onStart();
        if(FirebaseAuth.getInstance().getCurrentUser() != null)
        {
            startActivity(new Intent(getApplicationContext(),Dashboard.class));
        }
    }

    public void login()
    {

        if(email.getText().toString().length()<5)
        {
            email.setError("Enter Email");
            return;
        }
        if( password.getText().toString().length()<=6)
        {
            password.setError("Password Shoud be more that 6 letters");
            return;
        }

        firebaseAuth
                .signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                          firebaseUser = firebaseAuth.getCurrentUser();
                          startActivities(new Intent[]{new Intent(getApplicationContext(),Dashboard.class)});
                        } else {
                            Toast.makeText(getApplicationContext(),task.getException().toString(),Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
