package com.naiple.culinary_social_network;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Recipe extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        Intent intent = getIntent();
        String myData = intent.getStringExtra("Data_Card");

        Log.d("RECIPE", myData);

        Button button = findViewById(R.id.back_to_home_page);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                Log.d("test", "test");
                intent.putExtra("resultData", "result_from_card");
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }
}