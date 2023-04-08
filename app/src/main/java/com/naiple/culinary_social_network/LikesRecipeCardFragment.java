package com.naiple.culinary_social_network;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.naiple.culinary_social_network.databinding.FragmentCommentsRecipeCardBinding;
import com.naiple.culinary_social_network.databinding.FragmentLikesRecipeCardBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LikesRecipeCardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LikesRecipeCardFragment extends Fragment {

    private static String TAG = "LikesRecipeCardFragment";
    FragmentLikesRecipeCardBinding binding;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LikesRecipeCardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LikesRecipeCardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LikesRecipeCardFragment newInstance(String param1, String param2) {
        LikesRecipeCardFragment fragment = new LikesRecipeCardFragment();
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
        binding = FragmentLikesRecipeCardBinding.inflate(inflater, container, false);
        buttonsBinding();

        binding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast toast = Toast.makeText(getContext(), "нажали на " + (position+1), Toast.LENGTH_LONG );
                toast.show();
                Log.i(TAG, "Нажали на странице с лайками");
            }
        });

        return binding.getRoot();
    }

    private void buttonsBinding() {
        binding.backToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeFragment homeFragment = new HomeFragment();
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentContainer, homeFragment).commit();
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListView listView = binding.listView;
        String[] names = new String[200];
        for(int i = 0; i < 200; i++){
            String like = "Лайк " + (i+1);
            names[i] = like;
        }
        CustomStringArrayListAdapter adapterListView = new CustomStringArrayListAdapter(getContext(), R.layout.fragment_list_element, names);
        listView.setAdapter(adapterListView);
    }
}