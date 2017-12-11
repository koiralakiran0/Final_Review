package com.example.kiran.firebasereview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SelectAvatarActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_avatar);

        findViewById(R.id.imageView_f1).setOnClickListener(this);
        findViewById(R.id.imageView_f2).setOnClickListener(this);
        findViewById(R.id.imageView_f3).setOnClickListener(this);
        findViewById(R.id.imageView_m1).setOnClickListener(this);
        findViewById(R.id.imageView_m2).setOnClickListener(this);
        findViewById(R.id.imageView_m3).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        if(view.getId() == R.id.imageView_f1){
            intent.putExtra("ICON", R.drawable.avatar_f_1);
        } else if(view.getId() == R.id.imageView_f2){
            intent.putExtra("ICON", R.drawable.avatar_f_2);
        } else if(view.getId() == R.id.imageView_f3){
            intent.putExtra("ICON", R.drawable.avatar_f_3);
        } else if(view.getId() == R.id.imageView_m1){
            intent.putExtra("ICON", R.drawable.avatar_m_1);
        } else if(view.getId() == R.id.imageView_m2){
            intent.putExtra("ICON", R.drawable.avatar_m_2);
        } else if(view.getId() == R.id.imageView_m3){
            intent.putExtra("ICON", R.drawable.avatar_m_3);
        }
        setResult(RESULT_OK, intent);
        finish();
    }
}
