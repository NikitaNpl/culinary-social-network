package com.naiple.culinary_social_network.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.naiple.culinary_social_network.R;
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
        recipeCommentsViewModel = new ViewModelProvider(this).get(RecipeCommentsViewModel.class);
        recipeCommentsViewModel.init(requireActivity().getApplicationContext());
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
                Item comment = new Item("testComment");
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