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
import com.example.myapplication.R;

public class DishDetailFragment extends Fragment implements View.OnClickListener {

    private DishDto dishDto;
    private String token;
    private Button back;
    private String typeName;
    private TextView dishName;
    private TextView dishDescr;

    public DishDetailFragment(DishDto dishDto, String token, String typeName) {
        this.dishDto = dishDto;
        this.token = token;
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

        dishName.setText(dishDto.getName());
        dishDescr.append(dishDto.getDescription());
        //dishDescr.append(dishDto.getCoast().toString());
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new DishFragment(token,typeName)).commit();
    }
}
