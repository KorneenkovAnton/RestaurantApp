package com.example.myapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.DTO.TypeDto;
import com.example.myapplication.R;
import com.example.myapplication.activity.MainActivity;
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
    private List<TypeDto> typeDtos;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        typeAdapter = new TypeAdapter();
        createTypes();
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
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView ,
                new RecyclerTouchListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new DishFragment(typeDtos.get(position).getName())).commit();
                    }

                    @Override public void onLongItemClick(View view, int position) {
                    }
                })
        );
    }

    @SneakyThrows
    private void createTypes(){
        AsyncTaskResult<List<TypeDto>> result = new TypesAsyncTask().execute().get();
        if(result.getStatus() == 200){
            if(!result.getResult().isEmpty()){
                typeDtos = result.getResult();
                typeAdapter.setItems(typeDtos);
            }else {
                Toast.makeText(getContext(),"No available types",Toast.LENGTH_SHORT).show();
                onClick(getView());
            }
        }else {
            if(result.getStatus() == 401){
                startActivity(new Intent(getContext(), MainActivity.class));
            }else {
                Toast.makeText(getContext(),R.string.err_msg_server_error,Toast.LENGTH_SHORT).show();
                onClick(getView());
            }
        }
    }

    @Override
    public void onClick(View v) {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment(null)).commit();
    }
}
