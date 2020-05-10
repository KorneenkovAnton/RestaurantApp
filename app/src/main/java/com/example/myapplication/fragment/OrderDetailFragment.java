package com.example.myapplication.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.DTO.DishDto;
import com.example.myapplication.DTO.OrderDetailsDto;
import com.example.myapplication.DTO.OrderDto;
import com.example.myapplication.R;
import com.example.myapplication.adapter.OrderAdapter;
import com.example.myapplication.async.SaveOrderAsyncTask;

import java.util.Date;
import java.util.concurrent.ExecutionException;

public class OrderDetailFragment extends Fragment implements View.OnClickListener {

    private Button back;
    private Button repeat;
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
        repeat = getView().findViewById(R.id.repeat_button);
        back.setOnClickListener(this);
        date = getView().findViewById(R.id.order_details_date);
        descr = getView().findViewById(R.id.order_details_descr);
        amount = getView().findViewById(R.id.order_details_amount);

        date.append(" " + new OrderAdapter().new OrderViewHolder(getView()).getFormatedDate(orderDto.getDate()));

        for (OrderDetailsDto dish:orderDto.getDishes()
             ) {
            descr.append("\n" + dish.getDish().getName()+" x"+dish.getNum());
        }

        amount.append(" " + orderDto.getAmount() +"$");

        repeat.setOnClickListener(V->{
            try {
                orderDto.setDate(new Date());
                if(new SaveOrderAsyncTask().execute(orderDto).get().getStatus() == 200){
                    Toast.makeText(getContext(),"Done",Toast.LENGTH_SHORT).show();
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container
                            ,new HomeFragment(null)).commit();
                }else {
                    Toast.makeText(getContext(),"Not available now",Toast.LENGTH_SHORT).show();
                }
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
                Toast.makeText(getContext(),"Something went wrong",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new OrderFragment()).commit();
    }
}
