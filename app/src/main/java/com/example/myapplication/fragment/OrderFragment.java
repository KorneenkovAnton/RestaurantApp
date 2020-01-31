package com.example.myapplication.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.DTO.OrderDto;
import com.example.myapplication.R;
import com.example.myapplication.adapter.OrderAdapter;
import com.example.myapplication.async.AsyncTaskResult;
import com.example.myapplication.async.GetUserOrdersAsyncTask;
import com.example.myapplication.listeners.RecyclerTouchListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class OrderFragment extends Fragment {
    private RecyclerView recyclerView;
    private OrderAdapter orderAdapter;
    private List<OrderDto> orderDtos;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_orders,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        init();
        loadOrders();
    }

    public void init(){
        recyclerView = getView().findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        orderAdapter = new OrderAdapter();
        recyclerView.setAdapter(orderAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView ,
                new RecyclerTouchListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new OrderDetailFragment(orderDtos.get(position))).commit();
                    }

                    @Override public void onLongItemClick(View view, int position) {
                    }
                })
        );
    }

    public void loadOrders(){

        try {
            AsyncTaskResult<Collection<OrderDto>> result = new GetUserOrdersAsyncTask().execute().get();
            if(result.getStatus() == 200){
                orderDtos = (List<OrderDto>) result.getResult();

            }else {
                orderDtos = new ArrayList<>();
            }

        } catch (ExecutionException | InterruptedException e) {
            Toast.makeText(getContext(),"Something went wrong",Toast.LENGTH_SHORT).show();
            orderDtos = new ArrayList<>();
            e.printStackTrace();
        }
        orderAdapter.setItems(orderDtos);
    }
}
