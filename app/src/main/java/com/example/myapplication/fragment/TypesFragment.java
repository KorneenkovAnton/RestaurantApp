package com.example.myapplication.fragment;

import android.content.SharedPreferences;
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

import com.example.myapplication.DTO.TypeDto;
import com.example.myapplication.R;
import com.example.myapplication.adapter.TypeAdapter;
import com.example.myapplication.async.AsyncTaskResult;
import com.example.myapplication.async.TypesAsyncTask;
import com.example.myapplication.listeners.RecyclerTouchListener;

import java.util.List;

import lombok.SneakyThrows;

public class TypesFragment extends Fragment implements View.OnClickListener {

    private RecyclerView recyclerView;
    private Button back;
    private TypeAdapter typeAdapter = new TypeAdapter();
    private String token;
    private List<TypeDto> typeDtos;
    private SharedPreferences sharedPreferences;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        typeAdapter = new TypeAdapter();
        createTypes();
    }

    public TypesFragment(String token) {
        this.token = token;
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dish_type,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        init();
    }

    public void init(){
        back = getView().findViewById(R.id.type_back);
        back.setOnClickListener(this);
        recyclerView = getView().findViewById(R.id.recycler_view_types);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(typeAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView ,new RecyclerTouchListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new DishFragment(token, typeDtos.get(position).getName())).commit();
                    }

                    @Override public void onLongItemClick(View view, int position) {
                    }
                })
        );
    }

    @SneakyThrows
    private void createTypes(){
        AsyncTaskResult<List<TypeDto>> result = new TypesAsyncTask().execute(token).get();
        System.out.println(result.getStatus());
        typeDtos = result.getResult();
        typeAdapter.setItems(typeDtos);
    }

    @Override
    public void onClick(View v) {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment(token)).commit();
    }
}
