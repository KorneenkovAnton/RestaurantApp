package com.example.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.DTO.DishDto;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DishAdapter extends RecyclerView.Adapter<DishAdapter.DishViewHolder>{

    private List<DishDto> dishDtos = new ArrayList<>();

    @NonNull
    @Override
    public DishViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rest_menu_item_view,parent,false);
        return new DishAdapter.DishViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DishViewHolder holder, int position) {
        holder.bind(dishDtos.get(position));
    }

    @Override
    public int getItemCount() {
        return dishDtos.size();
    }

    public void setItems(Collection<DishDto> dishDtos){
        this.dishDtos.addAll(dishDtos);
        notifyDataSetChanged();
    }

    public void clearItems(){
        dishDtos.clear();
        notifyDataSetChanged();
    }

    class DishViewHolder extends RecyclerView.ViewHolder{
        private Button button;

        public void bind(DishDto dishDto) {
            button.setText(dishDto.getName());
        }

        public DishViewHolder(@NonNull View itemView) {
            super(itemView);
            button = itemView.findViewById(R.id.type_button);
        }
    }
}
