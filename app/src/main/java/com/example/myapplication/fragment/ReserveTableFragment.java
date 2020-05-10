package com.example.myapplication.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.DTO.TableDto;
import com.example.myapplication.R;
import com.example.myapplication.activity.MainActivity;
import com.example.myapplication.async.AsyncTaskResult;
import com.example.myapplication.async.GetTablesAsyncTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ReserveTableFragment extends Fragment {

    public static ReserveTableFragment newInstance() {
        return new ReserveTableFragment();
    }

    private TableDto reservedTable;
    private Dialog reserveDialog;
    private List<TableDto> tableDtos;
    private Spinner tableSpinner;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.reserve_table_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        getReservedTable();
        createTables();
        init();
        if(reservedTable == null){
            reserveDialog.show();
        }
    }

    private void init(){
        reserveDialog = new Dialog(getContext());
        reserveDialog.setContentView(R.layout.pop_up_table);
        ArrayAdapter<TableDto> adapter = new ArrayAdapter<>(getContext(), R.layout.support_simple_spinner_dropdown_item, tableDtos);
        tableSpinner = reserveDialog.findViewById(R.id.table_spinner);
        tableSpinner.setAdapter(adapter);
    }
    private void getReservedTable(){

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
