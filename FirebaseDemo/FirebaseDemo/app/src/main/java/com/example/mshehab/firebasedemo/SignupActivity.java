package com.example.mshehab.firebasedemo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class SignupActivity extends AppCompatActivity {
    EditText editTextUsername, editTextPassword, editTextFirstName, editTextLastName, editTextPasswordConfirm;
    private FirebaseAuth mAuth;
    final String TAG = "demo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();

        editTextUsername = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextPasswordConfirm = findViewById(R.id.editTextPasswordConfirm);
        editTextFirstName = findViewById(R.id.editTextName);
        editTextLastName = findViewById(R.id.editTextPhone);


        findViewById(R.id.buttonCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        findViewById(R.id.buttonSignup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editTextUsername.getText().toString();
                String passwordFirst = editTextPassword.getText().toString();
                String passwordSecond = editTextPasswordConfirm.getText().toString();
                final String firstName = editTextFirstName.getText().toString();
                final String lastName = editTextLastName.getText().toString();

                if(firstName.equals("")){
                    Toast.makeText(SignupActivity.this, "Enter First Name", Toast.LENGTH_SHORT).show();
                } else if (lastName.equals("")){
                    Toast.makeText(SignupActivity.this, "Enter Last Name", Toast.LENGTH_SHORT).show();
                } else if (email.equals("")){
                    Toast.makeText(SignupActivity.this, "Enter Email", Toast.LENGTH_SHORT).show();
                } else if (passwordFirst.equals("")){
                    Toast.makeText(SignupActivity.this, "Enter Password", Toast.LENGTH_SHORT).show();
                } else if (passwordSecond.equals("")){
                    Toast.makeText(SignupActivity.this, "Enter Confirm Password", Toast.LENGTH_SHORT).show();
                } else if (!passwordSecond.equals(passwordFirst)){
                    Toast.makeText(SignupActivity.this, "Enter Passwords Don't Match", Toast.LENGTH_SHORT).show();
                } else{
                    mAuth.createUserWithEmailAndPassword(email, passwordFirst)
                            .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d(TAG, "signUpWithEmail:success");
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                                .setDisplayName(firstName+" "+lastName)
                                                .build();

                                        user.updateProfile(profileUpdates)
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if (task.isSuccessful()) {
                                                            Log.d(TAG, "User profile updated");
                                                            Intent i = new Intent(SignupActivity.this, ContactsActivity.class);
                                                            startActivity(i);
                                                            finish();
                                                        }
                                                    }
                                                });
                                        
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w(TAG, "signUpWithEmail:failure", task.getException());
                                        Toast.makeText(SignupActivity.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();

                                    }


                                }
                            });

                }
            }
        });

    }
}
