package com.example.myapplication.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.DTO.DishDto;
import com.example.myapplication.DTO.OrderDetailsDto;
import com.example.myapplication.DTO.OrderDto;
import com.example.myapplication.R;

public class OrderDetailFragment extends Fragment implements View.OnClickListener {

    private Button back;
    private OrderDto orderDto;
    private TextView date;
    private TextView descr;
    private TextView amount;

    public OrderDetailFragment(OrderDto orderDto) {
        this.orderDto = orderDto;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_order_detail,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        init();
    }

    public void init(){
        back = getView().findViewById(R.id.back_button);
        back.setOnClickListener(this);
        date = getView().findViewById(R.id.order_details_date);
        descr = getView().findViewById(R.id.order_details_descr);
        amount = getView().findViewById(R.id.order_details_amount);

        date.append(" " + orderDto.getDate().toString());

        for (OrderDetailsDto dish:orderDto.getDishes()
             ) {
            descr.append("\n" + dish.getDish().getName()+" x"+dish.getNum());
        }

        amount.append(" " + orderDto.getAmount());
    }

    @Override
    public void onClick(View v) {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new OrderFragment()).commit();
    }
}
