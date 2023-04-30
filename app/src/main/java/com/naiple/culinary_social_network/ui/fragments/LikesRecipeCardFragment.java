package com.naiple.culinary_social_network.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.naiple.culinary_social_network.ui.adapters.CustomStringArrayListAdapter;
import com.naiple.culinary_social_network.R;
import com.naiple.culinary_social_network.databinding.FragmentLikesRecipeCardBinding;
import com.naiple.culinary_social_network.ui.viewmodels.RecipeCommentsViewModel;

public class LikesRecipeCardFragment extends Fragment {

    FragmentLikesRecipeCardBinding binding;

    private RecipeCommentsViewModel recipeCommentsViewModel;

    private ListView listView;
    private CustomStringArrayListAdapter adapter;

    private Bundle result;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLikesRecipeCardBinding.inflate(getLayoutInflater());
        listView = binding.listView;
        adapter = new CustomStringArrayListAdapter(getContext(), R.layout.fragment_list_element, new String[0]);
        listView.setAdapter(adapter);

        buttonsBinding();

        binding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast toast = Toast.makeText(getContext(), "нажали на " + (position+1), Toast.LENGTH_LONG );
                toast.show();
            }
        });

        return binding.getRoot();
    }

    private void buttonsBinding() {
        binding.backToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result = new Bundle();
                result.putString("arg", "Second");
                Navigation.findNavController(view).navigate(R.id.action_likesRecipeCardFragment_to_homeFragment2, result);
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recipeCommentsViewModel = new ViewModelProvider(this).get(RecipeCommentsViewModel.class);
        recipeCommentsViewModel.getRecipeCommentsLive().observe(getViewLifecycleOwner(), comments -> {
            if (comments != null) {
                String[] items = new String[0];
                for (int i = 0; i < comments.size(); i += 1) {
                    items[i] = comments.get(i).getName();
                }
                adapter.setItems(items);
            }
        });
    }
}