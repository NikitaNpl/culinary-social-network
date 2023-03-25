package com.naiple.culinary_social_network;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.naiple.culinary_social_network.databinding.FragmentRecipeBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecipeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecipeFragment extends Fragment {
    FragmentRecipeBinding fragmentRecipeBinding;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RecipeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RecipeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RecipeFragment newInstance(String param1, String param2) {
        RecipeFragment fragment = new RecipeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentRecipeBinding = FragmentRecipeBinding.inflate(inflater, container, false);
        buttonsBinding(new Bundle());
        // Inflate the layout for this fragment
        return fragmentRecipeBinding.getRoot();
    }

    private void buttonsBinding(Bundle bundle) {
        fragmentRecipeBinding.backToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeFragment homeFragment = new HomeFragment();
                Bundle bundle = getArguments();
                String recipeName = "";
                int recipePicture = 0;

                if (bundle != null) {
                    recipeName = bundle.getString("recipeName");
                    recipePicture = bundle.getInt("recipePicture");
                }

                Bundle bundle1 = new Bundle();
                bundle.putString("recipeName", recipeName);
                bundle.putInt("recipePicture", recipePicture);
                homeFragment.setArguments(bundle1);
                FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragmentContainer, homeFragment).commit();
            }
        });
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            String recipeName = bundle.getString("recipeName");
            int recipePicture = bundle.getInt("recipePicture");
            TextView editRecipeName = view.findViewById(R.id.recipeNameFragment);
            ImageView editRecipePicture = view.findViewById(R.id.recipePictureFragment);
            editRecipeName.setText(recipeName);
            editRecipePicture.setImageDrawable(getResources().getDrawable(recipePicture));

        }
    }
}