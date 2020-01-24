package com.example.myapplication.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.DTO.DishDto;
import com.example.myapplication.R;
import com.example.myapplication.adapter.CartAdapter;
import com.example.myapplication.listeners.RecyclerTouchListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collection;

public class CartFragment extends Fragment implements View.OnClickListener {
    private RecyclerView recyclerView;
    private Button back;
    private CartAdapter cartAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cart,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        init();
        createDishes();
    }

    public void init(){
        recyclerView = getView().findViewById(R.id.recycler_view_cart);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        back = getView().findViewById(R.id.cart_back);
        back.setOnClickListener(this);
        cartAdapter = new CartAdapter();
        recyclerView.setAdapter(cartAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView ,new RecyclerTouchListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        cartAdapter.deleteItem(position);
                    }

                    @Override public void onLongItemClick(View view, int position) {
                    }
                })
        );

    }

    private void createDishes(){
        Collection<DishDto> dishDtos = new ArrayList<>();
        dishDtos.add(new DishDto("Dish 1"));
        dishDtos.add(new DishDto("dish 2"));
        dishDtos.add(new DishDto("dish 3"));
        cartAdapter.setItems(dishDtos);
    }

    @Override
    public void onClick(View v) {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new TypesFragment("")).commit();
    }
}
