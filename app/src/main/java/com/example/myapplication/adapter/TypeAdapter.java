package com.example.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.DTO.TypeDto;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TypeAdapter extends RecyclerView.Adapter<TypeAdapter.TypeViewHolder> {

    private List<TypeDto> types = new ArrayList<>();

    @NonNull
    @Override
    public TypeAdapter.TypeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rest_menu_item_view,parent,false);
        return new TypeAdapter.TypeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TypeViewHolder holder, int position) {
        holder.bind(types.get(position));
    }

    @Override
    public int getItemCount() {
        return types.size();
    }

    public void setItems(Collection<TypeDto> typeDtos){
        this.types.addAll(typeDtos);
        notifyDataSetChanged();
    }

    public void clearItems(){
        types.clear();
        notifyDataSetChanged();
    }

    class TypeViewHolder extends RecyclerView.ViewHolder{
        private Button button;

        public void bind(TypeDto typeDto) {
            button.setText(typeDto.getName());
        }

        public TypeViewHolder(@NonNull View itemView) {
            super(itemView);
            button = itemView.findViewById(R.id.type_button);
        }
    }
}
