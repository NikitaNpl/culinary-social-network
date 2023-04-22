package com.naiple.culinary_social_network.ui.activities;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.naiple.culinary_social_network.ui.fragments.HomeFragment;
import com.naiple.culinary_social_network.R;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    HomeFragment homeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_main);
    }
}