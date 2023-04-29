package com.naiple.culinary_social_network.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.naiple.culinary_social_network.R;
import com.naiple.culinary_social_network.ui.adapters.RecyclerViewAdapter;
import com.naiple.culinary_social_network.databinding.FragmentRecipesBinding;
import com.naiple.culinary_social_network.ui.viewmodels.RecipeViewModel;

public class RecipesFragment extends Fragment {
    private FragmentRecipesBinding binding;
    private RecipeViewModel recipeViewModel;

    public RecipesFragment() {
        super(R.layout.fragment_recipes);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recipeViewModel = new ViewModelProvider(this).get(RecipeViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRecipesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getContext());

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        binding.recipesList.setLayoutManager(layoutManager);
        binding.recipesList.setAdapter(adapter);
        recipeViewModel.getRecipeLive().observe(getViewLifecycleOwner(), adapter::updateRecipes);
    }
}