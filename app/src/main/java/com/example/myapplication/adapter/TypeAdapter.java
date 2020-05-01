package com.example.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.DTO.TypeDto;
import com.example.myapplication.R;
import com.squareup.picasso.Picasso;

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
        Picasso.get().load("http://192.168.1.44:8080/resto/V1/user/getImage/"+
                types.get(position).getImagePath())
                .resize(400,200)
                .centerCrop()
                .error(R.drawable.ic_hourglass_empty_black_24dp)
                .into(holder.imageView);
        holder.textView.setText(types.get(position).getName());
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
        private ImageView imageView;
        private TextView textView;

        public void bind(TypeDto typeDto) {
//            imageView.setText(typeDto.getName());
//            Picasso.get().load(typeDto.getImagePath());
        }

        public TypeViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.type_image_view);
            textView = itemView.findViewById(R.id.type_image_view_text);
        }
    }
}
