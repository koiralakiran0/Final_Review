package com.example.kiran.firebasereview;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

public class NewContactActivity extends AppCompatActivity {

    ImageView imageViewSelect;
    EditText editTextName, editTextPhone, editTextEmail;
    RadioGroup radioGroup;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    int id;

    private FirebaseStorage storage;

    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        storage = FirebaseStorage.getInstance();

        imageViewSelect = (ImageView) findViewById(R.id.imageView_select);
        editTextName = (EditText) findViewById(R.id.editText_Name);
        editTextPhone = (EditText) findViewById(R.id.editText_phone);
        editTextEmail = (EditText) findViewById(R.id.editText_email);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        StorageReference iconReference = storage.getReference().child("icon");




        findViewById(R.id.imageView_select).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewContactActivity.this, SelectAvatarActivity.class);
                startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
            }
        });

        findViewById(R.id.button_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextName.getText().toString();
                String email = editTextEmail.getText().toString();
                String phone = editTextPhone.getText().toString();
                String dept = "SIS";

                if(radioGroup.getCheckedRadioButtonId() == R.id.radioButton_SIS){
                    dept = "SIS";
                } else if(radioGroup.getCheckedRadioButtonId() == R.id.radioButton_CS){
                    dept = "CS";
                } else if(radioGroup.getCheckedRadioButtonId() == R.id.radioButton_BIO){
                    dept = "BIO";
                }

                if(name.equals("")){
                    Toast.makeText(NewContactActivity.this, "Enter name", Toast.LENGTH_SHORT).show();
                } else if(email.equals("")){
                    Toast.makeText(NewContactActivity.this, "Enter email", Toast.LENGTH_SHORT).show();
                } else if(phone.equals("")){
                    Toast.makeText(NewContactActivity.this, "Enter phone", Toast.LENGTH_SHORT).show();
                } else{
                    Person person = new Person(name, email, phone, dept);
                    person.picID = id;
                    mDatabase.child("contacts")
                            .child(mAuth.getCurrentUser().getUid())
                            .push()
                            .setValue(person);
                    finish();
                }
            }
        });

        findViewById(R.id.button_takePicture).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(REQUEST_IMAGE_CAPTURE)
                Intent intent = new Intent(NewContactActivity.this, PictureStorage.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK && data != null) {
            Bundle extras = data.getExtras();
            int iconId = data.getExtras().getInt("ICON");
            id = iconId;

            imageViewSelect.setImageResource(iconId);


            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), iconId);
            //imageViewSelect.setImageBitmap(bitmap);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] bytes = baos.toByteArray();

            StorageReference iconReference = storage.getReference().child("icon");
            UploadTask uploadTask = iconReference.putBytes(bytes);

            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(NewContactActivity.this, "Icon Upload Successful", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(NewContactActivity.this, "Failure" + e.toString(), Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
}
