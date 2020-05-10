package com.example.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.DTO.DishDto;
import com.example.myapplication.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DishAdapter extends RecyclerView.Adapter<DishAdapter.DishViewHolder>{

    private List<DishDto> dishDtos = new ArrayList<>();

    @NonNull
    @Override
    public DishViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dish_menu_item_view,parent,false);
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
        private ImageView imageView;
        private TextView textView;


        public void bind(DishDto dishDto) {

            Picasso.get().load("http://192.168.1.44:8080/resto/V1/user/getImage/"+
                    dishDto.getImagePath())
                    .resize(400,200)
                    .centerCrop()
                    .error(R.drawable.ic_hourglass_empty_black_24dp)
                    .into(imageView);
            textView.setText("Name: "+dishDto.getName()+"\nCost: "+dishDto.getCost());
        }

        public DishViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.dish_menu_item_image_view);
            textView = itemView.findViewById(R.id.dish_menu_item_text_view);
        }
    }
}
