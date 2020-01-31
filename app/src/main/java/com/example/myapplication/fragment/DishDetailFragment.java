package com.example.myapplication.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.DTO.DishDto;
import com.example.myapplication.DTO.OrderDetailsDto;
import com.example.myapplication.R;
import com.example.myapplication.entity.Cart;

public class DishDetailFragment extends Fragment implements View.OnClickListener {

    private DishDto dishDto;
    private Button back;
    private Button get;
    private String typeName;
    private TextView dishName;
    private TextView dishDescr;
    private Button minus;
    private Button plus;
    private EditText count;

    public DishDetailFragment(DishDto dishDto, String typeName) {
        this.dishDto = dishDto;
        this.typeName = typeName;
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dfragment_dish_detail,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        init();
    }

    public void init(){
        dishName = getView().findViewById(R.id.dish_detail_dish_name);
        dishDescr = getView().findViewById(R.id.dish_detail_dish_descr);
        back = getView().findViewById(R.id.dish_detail_back);
        get = getView().findViewById(R.id.dish_detail_get);
        plus = getView().findViewById(R.id.plus);
        minus = getView().findViewById(R.id.minus);
        count = getView().findViewById(R.id.count);

        count.setText("1");
        dishName.setText(dishDto.getName());
        dishDescr.append(dishDto.getDescription());
        back.setOnClickListener(this);

        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OrderDetailsDto orderDetailsDto = new OrderDetailsDto(Integer.parseInt(count.getText().toString()),
                        null,dishDto);
                if(Cart.getInstance().getDishes().contains(orderDetailsDto)){
                    int index = Cart.getInstance().getDishes().indexOf(orderDetailsDto);
                    Cart.getInstance().getDishes().get(index).setNum(Cart.getInstance().getDishes()
                            .get(index).getNum()+orderDetailsDto.getNum());

                }else {
                    Cart.getInstance().getDishes().add(orderDetailsDto);
                }
            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count.setText(String.valueOf(Integer.parseInt(count.getText().toString())+1));
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(count.getText().toString())>1){
                    count.setText(String.valueOf(Integer.parseInt(count.getText().toString())-1));
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new DishFragment(typeName)).commit();
    }
}
