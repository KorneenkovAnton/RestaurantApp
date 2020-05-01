package com.example.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.DTO.OrderDetailsDto;
import com.example.myapplication.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private List<OrderDetailsDto> dishDtos = new ArrayList<>();
    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cart_item_view,parent,false);
        return new CartAdapter.CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        holder.bind(dishDtos.get(position));
    }

    public void setItems(Collection<OrderDetailsDto> dishDtos){
        this.dishDtos.addAll(dishDtos);
        notifyDataSetChanged();
    }

    public void deleteItem(int position){
        dishDtos.remove(position);
        notifyDataSetChanged();
    }

    public void clearItems(){
        dishDtos.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return dishDtos.size();
    }

    class CartViewHolder extends RecyclerView.ViewHolder{
        private TextView cartDishName;
        private TextView cartDishNum;
        private TextView cartDishPrice;
        private ImageView imageView;

        public void bind(OrderDetailsDto orderDetailsDto){
            cartDishName.setText(orderDetailsDto.getDish().getName());
            cartDishNum.setText("x"+ orderDetailsDto.getNum());
            cartDishPrice.setText(String.valueOf(orderDetailsDto.getDish().getCost()*orderDetailsDto.getNum()));
            Picasso.get().load("http://192.168.1.44:8080/resto/V1/user/getImage/"+orderDetailsDto.getDish().getImagePath())
                    .resize(100,50)
                    .centerCrop()
                    .error(R.drawable.ic_hourglass_empty_black_24dp)
                    .into(imageView);
        }

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            cartDishName = itemView.findViewById(R.id.cart_dish_name);
            cartDishNum = itemView.findViewById(R.id.cart_dish_num);
            cartDishPrice = itemView.findViewById(R.id.cart_dish_price);
            imageView = itemView.findViewById(R.id.cart_image);
        }
    }
}
