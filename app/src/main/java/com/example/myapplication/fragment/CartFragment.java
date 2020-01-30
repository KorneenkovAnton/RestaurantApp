package com.example.myapplication.fragment;

import android.os.AsyncTask;
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

import com.example.myapplication.DTO.OrderDetailsDto;
import com.example.myapplication.DTO.OrderDto;
import com.example.myapplication.DTO.TableDto;
import com.example.myapplication.DTO.UserDto;
import com.example.myapplication.R;
import com.example.myapplication.adapter.CartAdapter;
import com.example.myapplication.async.SaveOrderAsyncTask;
import com.example.myapplication.async.UserAsyncTask;
import com.example.myapplication.entity.Cart;
import com.example.myapplication.listeners.RecyclerTouchListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class CartFragment extends Fragment implements View.OnClickListener {
    private RecyclerView recyclerView;
    private Button back;
    private Button get;
    private CartAdapter cartAdapter;
    private Cart cart;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cart,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        init();
        createDishes();
    }

    public void init(){
        cart = Cart.getInstance();
        recyclerView = getView().findViewById(R.id.recycler_view_cart);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        get = getView().findViewById(R.id.cart_get);
        back = getView().findViewById(R.id.cart_back);
        back.setOnClickListener(this);
        cartAdapter = new CartAdapter();
        recyclerView.setAdapter(cartAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView ,
                new RecyclerTouchListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        cart.getDishes().remove(position);
                        cartAdapter.clearItems();
                        cartAdapter.setItems(cart.getDishes());
                    }

                    @Override public void onLongItemClick(View view, int position) {
                    }
                })
        );

        get.setOnClickListener(v -> {
            OrderDto orderDto = new OrderDto();
            orderDto.setDate(new Date());
            int sum = 0;
            for (OrderDetailsDto details: cart.getDishes()
                 ) {
                sum+=details.getNum()*details.getDishDto().getCost();
            }
            orderDto.setAmount(sum);
            orderDto.setStatus("Preparing");
            orderDto.setInfo("Delivery");
            orderDto.setTable(null);
            orderDto.setDishes(cart.getDishes());

            new SaveOrderAsyncTask().execute(orderDto);
        });
    }

    private void createDishes(){
        Collection<OrderDetailsDto> dishDtos = cart.getDishes();
        if(dishDtos != null) {
            cartAdapter.setItems(dishDtos);
        }else {
            dishDtos = new ArrayList<>();
            dishDtos.add(new OrderDetailsDto());
        }
    }

    @Override
    public void onClick(View v) {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new TypesFragment()).commit();
    }
}
