package com.naiple.culinary_social_network;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Window;

public class MainActivity extends AppCompatActivity {

    HomeFragment homeFragment;
    RecipeFragment recipeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        homeFragment = new HomeFragment();

        fragmentTransaction.add(R.id.fragmentContainer, homeFragment, "homeFragment");
        fragmentTransaction.commit();
    }
}