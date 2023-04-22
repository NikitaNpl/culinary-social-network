package com.naiple.culinary_social_network.ui.fragments;

import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.naiple.culinary_social_network.R;
import com.naiple.culinary_social_network.databinding.FragmentHomeBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    public FragmentHomeBinding fragmentHomeBinding;


    private static final String CHANNEL_ID = "channel_HomeFragment";
    private static final int NOTIFICATION_ID = 1;
    private Bundle result;


    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        new Bundle();
        fragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false);
        buttonsBinding();
        return fragmentHomeBinding.getRoot();
    }

    private void buttonsBinding() {
        fragmentHomeBinding.recipeCard.recipeLikes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result = new Bundle();
                result.putString("arg", "First");
                Navigation.findNavController(view).navigate(R.id.action_homeFragment2_to_likesRecipeCardFragment, result);
            }
        });

        fragmentHomeBinding.recipeCard.recipeComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result = new Bundle();
                result.putString("arg", "First");
                Navigation.findNavController(view).navigate(R.id.action_homeFragment2_to_commentsRecipeCardFragment2, result);
            }
        });
    }

    private void showNotification(String name, String text) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(requireActivity(), CHANNEL_ID)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentTitle(name)
                .setContentText(text)
                .setSmallIcon(R.mipmap.ic_launcher_round);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(requireActivity());
        if (ActivityCompat.checkSelfPermission(requireActivity(),  android.Manifest.permission.
                POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions();
        }
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(requireActivity(), new String[]{ android.Manifest.permission.
                POST_NOTIFICATIONS }, 1);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            String recipeName = bundle.getString("recipeName");
            int recipePicture = bundle.getInt("recipePicture");
        }

        fragmentHomeBinding.recipeCard.recipeFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNotification("Example Recipe Name", "You added the recipe to your favorites");
            }
        });
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

    }

    @Override
    public void onStart() {
        super.onStart();
    }
}