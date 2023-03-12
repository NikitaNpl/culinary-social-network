package com.naiple.culinary_social_network;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;

import com.naiple.culinary_social_network.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;
    private ActivityResultLauncher<Intent> launcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

        launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        Log.d("Active Result", data.getStringExtra("resultData"));
                    }
                }
        );

        activityMainBinding.includeHomeHeader.headerSlogan.setText(R.string.slogan);
        activityMainBinding.includeHomeHeader.buttonMenu.setImageResource(R.drawable.menu);
        activityMainBinding.recipeCard.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Recipe.class);
                intent.putExtra("Data_Card", "ID_CARD");
                launcher.launch(intent);
            }
        });
    }

    public void onClickSearchFilters(View v) {
        Log.d("App", "SearchBar");
    }
}