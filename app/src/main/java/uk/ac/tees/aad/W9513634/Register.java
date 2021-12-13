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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity  {

    EditText email;
    EditText password;
    EditText name;
    EditText mobile;
    Button create_account;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("users");


        email = findViewById(R.id.signupEmail);
        password = findViewById(R.id.signupPassword);
        name = findViewById(R.id.signupName);
        mobile = findViewById(R.id.signupMobile);
        create_account  = findViewById(R.id.create_acc);

        Button login = findViewById(R.id.login_btn_sign);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivities(new Intent[]{new Intent(getApplicationContext(),Login.class)});
            }
        });

        create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateAndLogin();
            }
        });


    }
    @Override
    protected void onStart() {
        super.onStart();
        if(FirebaseAuth.getInstance().getCurrentUser() != null)
        {
            startActivity(new Intent(getApplicationContext(),Dashboard.class));
        }
    }

    public void validateAndLogin()
    {
        if(email.getText().toString().length()<5){
            email.setError("Enter Correct Email");
            return;
        }
        if(password.getText().toString().length()<6)
        {
            password.setError("Password Should be more than 6 letters");
        }
        if(name.getText().toString().length()<5)
        {
            name.setError("Name should be more than 4 Letters");
        }
        if(mobile.getText().toString().length()<10){
            mobile.setError("Enter Correct Mobile Number");
        }
        firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            firebaseUser = firebaseAuth.getCurrentUser();

                            databaseReference.child(firebaseUser.getUid()).setValue(
                                    new User(name.getText().toString(),email.getText().toString(),mobile.getText().toString())).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getApplicationContext(),"User Created",Toast.LENGTH_LONG).show();
                                    startActivities(new Intent[]{new Intent(getApplicationContext(),Dashboard.class)});
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {

                                }
                            });
                        } else {
                            Toast.makeText(getApplicationContext(),"Check Connection",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }


}
