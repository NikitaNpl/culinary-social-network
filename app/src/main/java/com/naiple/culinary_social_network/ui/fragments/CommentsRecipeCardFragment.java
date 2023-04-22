package com.naiple.culinary_social_network.ui.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.naiple.culinary_social_network.R;
import com.naiple.culinary_social_network.data.model.Item;
import com.naiple.culinary_social_network.databinding.FragmentCommentsRecipeCardBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CommentsRecipeCardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CommentsRecipeCardFragment extends Fragment {
    private static String TAG = "CommentsRecipeCardFragment";
    RecyclerView recyclerView;
    FragmentCommentsRecipeCardBinding binding;

    private Bundle result;
    private final List<Item> items = new ArrayList<>();
    private final RecyclerView.Adapter adapter_recycle = new ItemAdapter(this.items);

    public CommentsRecipeCardFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCommentsRecipeCardBinding.inflate(inflater, container, false);

        if (this.getArguments() != null) {
            result = this.getArguments();
            String textArg = result.getString("arg");
        }

        buttonsBinding();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        recyclerView = binding.recycler;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter_recycle);
        for(int i = 0; i < 200; i++){

            this.items.add(new Item("Комментарий " + i));
            adapter_recycle.notifyItemInserted(this.items.size() - 1);
        }
    }

    private void buttonsBinding() {
        binding.backToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result = new Bundle();
                result.putString("arg", "Third");
                Navigation.findNavController(view).navigate(R.id.action_commentsRecipeCardFragment2_to_homeFragment2, result);
            }
        });
    }

    private static final class ItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private final List<Item> items;
        public ItemAdapter(List<Item> items) {
            this.items = items;
        }
        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int index) {

            return new RecyclerView.ViewHolder(
                    LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.fragment_list_element, parent, false)
            ) {};
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int index) {
            TextView name = holder.itemView.findViewById(R.id.listTextValue);
            name.setText(String.format("%s",  this.items.get(index).getName()));
            holder.itemView.setOnClickListener (new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i(TAG, "Нажали на странице с комментариями");
                    Toast toast = Toast.makeText(v.getContext(), "нажали на " +
                            (index+1), Toast.LENGTH_LONG );
                    toast.show();
                }});
        }

        @Override
        public int getItemCount() {
            return this.items.size();
        }
    }
}