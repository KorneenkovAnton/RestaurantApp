package com.example.myapplication.fragment;

import android.app.Dialog;
import android.app.IntentService;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.DTO.TableDto;
import com.example.myapplication.R;
import com.example.myapplication.activity.BarcodeScannActivity;
import com.example.myapplication.activity.MainActivity;
import com.example.myapplication.async.AsyncTaskResult;
import com.example.myapplication.async.GetTablesAsyncTask;
import com.example.myapplication.entity.Cart;

import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class HomeFragment extends Fragment {

    private Button buttonHere;
    private Button buttonDelivery;
    private Dialog chooseTableDialog;
    private Spinner tableSpinner;
    private Button tableChooseButton;
    private List<TableDto> tableDtos;
    private TextView txtClose;
    private TextView tableInfo;
    private TableDto table;

    public HomeFragment(String tableName) {
        if(tableName != null){
            this.table = new TableDto(tableName,"Reserved",null);
            Cart.getInstance().setTable(table);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        createTables();
        init();
    }

    public void init(){
        tableInfo = getView().findViewById(R.id.table_info);
        chooseTableDialog = new Dialog(getContext());
        chooseTableDialog.setContentView(R.layout.pop_up_table);
        ArrayAdapter<TableDto> adapter = new ArrayAdapter<>(getContext(), R.layout.support_simple_spinner_dropdown_item, tableDtos);
        tableSpinner = chooseTableDialog.findViewById(R.id.table_spinner);
        tableSpinner.setAdapter(adapter);
        tableChooseButton = chooseTableDialog.findViewById(R.id.table_choose_button);
        buttonHere = getView().findViewById(R.id.here_button);
        buttonDelivery = getView().findViewById(R.id.delivery_button);
        txtClose = chooseTableDialog.findViewById(R.id.txt_close_table);
        tableSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Cart.getInstance().setTable(tableDtos.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        buttonDelivery.setOnClickListener(v -> {
            Cart.getInstance().setTable(null);
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new TypesFragment()).commit();
        });

        buttonHere.setOnClickListener(v -> {
//            chooseTableDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//            chooseTableDialog.show();
            startActivity(new Intent(getContext(), BarcodeScannActivity.class));
        });

        tableChooseButton.setOnClickListener(v -> {
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new TypesFragment()).commit();
            chooseTableDialog.dismiss();
        });

        txtClose.setOnClickListener(v -> {
            Cart.getInstance().setTable(null);
            chooseTableDialog.dismiss();
        });

        if(tableDtos.isEmpty()){
            buttonHere.setClickable(false);
            buttonDelivery.setClickable(false);
        }

        if(table != null){
            buttonHere.setText("Continue");
            tableInfo.setText("Your table is " + table.getName());
            buttonHere.setOnClickListener(v ->{
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new TypesFragment()).commit();
            });
        }
    }

    private void createTables(){

        try {
            AsyncTaskResult<List<TableDto>> result = new GetTablesAsyncTask().execute().get();
            if(result.getStatus() == 200){
                tableDtos = result.getResult();

            }else if(result.getStatus() == 500){
                Toast.makeText(getContext(),"Server error",Toast.LENGTH_SHORT).show();
                tableDtos = new ArrayList<>();
                startActivity(new Intent(getContext(), MainActivity.class));
            }else{
                Toast.makeText(getContext(),"Empty",Toast.LENGTH_SHORT).show();
                tableDtos = new ArrayList<>();
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            Toast.makeText(getContext(),"No available tables",Toast.LENGTH_SHORT).show();
        }
    }
}
