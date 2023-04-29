package com.naiple.culinary_social_network.ui.fragments;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.naiple.culinary_social_network.R;
import com.naiple.culinary_social_network.data.database.EntityItem;
import com.naiple.culinary_social_network.data.model.Item;
import com.naiple.culinary_social_network.databinding.FragmentCommentsRecipeCardBinding;
import com.naiple.culinary_social_network.ui.adapters.RecipeCommentsViewAdapter;
import com.naiple.culinary_social_network.ui.viewmodels.RecipeCommentsViewModel;

public class CommentsRecipeCardFragment extends Fragment {
    private static String TAG = "CommentsRecipeCardFragment";
    RecipeCommentsViewModel recipeCommentsViewModel;
    FragmentCommentsRecipeCardBinding binding;

    public CommentsRecipeCardFragment() {
        super(R.layout.fragment_comments_recipe_card);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPrefs = requireActivity().getSharedPreferences(
                getString(R.string.preference_file_key),
                Context.MODE_PRIVATE);
        recipeCommentsViewModel = new ViewModelProvider(this).get(RecipeCommentsViewModel.class);
        recipeCommentsViewModel.init(requireActivity().getApplicationContext(), sharedPrefs);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (!checkPermission()) {
                requestPermission();
            }
        }
    }

    boolean checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            return Environment.isExternalStorageManager();
        }
        return ContextCompat.checkSelfPermission(requireContext(), WRITE_EXTERNAL_STORAGE) ==
                PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (!Environment.isExternalStorageManager()) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                Uri uri = Uri.fromParts("package", requireContext().getPackageName(), null);
                intent.setData(uri);
                requireContext().startActivity(intent);
            }
        } else {
            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(requireActivity(),
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        1);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCommentsRecipeCardBinding.inflate(inflater, container, false);
        binding.backToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_commentsRecipeCardFragment2_to_homeFragment2);
            }
        });

        binding.addComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EntityItem comment = new EntityItem("testComment");
                recipeCommentsViewModel.addRecipeComment(comment);
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecipeCommentsViewAdapter recipeCommentsViewAdapter = new RecipeCommentsViewAdapter(requireActivity().getApplicationContext());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        binding.recycler.setLayoutManager(layoutManager);
        binding.recycler.setAdapter(recipeCommentsViewAdapter);
        recipeCommentsViewModel.getRecipeCommentsLive().observe(getViewLifecycleOwner(), recipeCommentsViewAdapter::updateComments);
    }

}