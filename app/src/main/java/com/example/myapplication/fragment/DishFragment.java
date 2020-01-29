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

import com.example.myapplication.DTO.DishDto;
import com.example.myapplication.R;
import com.example.myapplication.activity.MainActivity;
import com.example.myapplication.adapter.DishAdapter;
import com.example.myapplication.async.AsyncTaskResult;
import com.example.myapplication.async.DishesAsyncTask;
import com.example.myapplication.listeners.RecyclerTouchListener;

import java.util.List;

import lombok.SneakyThrows;

public class DishFragment extends Fragment implements View.OnClickListener {

    private RecyclerView recyclerView;
    private Button back;
    private DishAdapter dishAdapter;
    private String typeName;
    List<DishDto> dishDtos;

    public DishFragment(String typeName) {
        this.typeName = typeName;
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dish,container,false);
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        init();
        createDishes();
    }

    public void init(){
        back = getView().findViewById(R.id.dish_back);
        back.setOnClickListener(this);
        recyclerView = getView().findViewById(R.id.recycler_view_dishes);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        dishAdapter = new DishAdapter();
        recyclerView.setAdapter(dishAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView ,new RecyclerTouchListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new DishDetailFragment(dishDtos.get(position), typeName)).commit();
                    }

                    @Override public void onLongItemClick(View view, int position) {
                    }
                })
        );
    }

    @SneakyThrows
    private void createDishes(){
        AsyncTaskResult<List<DishDto>> result = new DishesAsyncTask().execute(typeName).get();
        if(result.getStatus() == 200 ){
            if(!result.getResult().isEmpty()){
                dishDtos = result.getResult();
                dishAdapter.setItems(dishDtos);
            }else {
                Toast.makeText(getContext(),"Empty type",Toast.LENGTH_SHORT).show();
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
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new TypesFragment()).commit();
    }
}
