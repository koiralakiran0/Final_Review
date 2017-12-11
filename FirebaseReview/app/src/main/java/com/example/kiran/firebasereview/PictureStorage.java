package com.example.kiran.firebasereview;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.storage.FirebaseStorage;

public class PictureStorage extends AppCompatActivity {


    private FirebaseStorage storage;
    ImageView imageView;
    Button buttonback, buttonUpload;
    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_storage);

        storage = FirebaseStorage.getInstance();
        imageView = (ImageView) findViewById(R.id.imageView);
        buttonback = (Button) findViewById(R.id.buttonTakePicture);
        buttonUpload = (Button) findViewById(R.id.buttonSave);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // dispatchTakePictureIntent();

            }
        });

    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);
        }
    }
}
