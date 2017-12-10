package com.example.koira.firebasefinalreview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;

public class NewContactActivity extends AppCompatActivity {

    ImageView imageView;
    EditText editTextName, editTextPhone, editTextEmail;
    RadioGroup radioGroup;
    Button btnSubmit, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);

        imageView = findViewById(R.id.imageViewSelect);
        editTextName = findViewById(R.id.editTextName);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextEmail = findViewById(R.id.editTextEmail);
        radioGroup = findViewById(R.id.radioGroup);
        btnSubmit = findViewById(R.id.buttonSubmit);
        btnCancel = findViewById(R.id.buttonCancel);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }
}
