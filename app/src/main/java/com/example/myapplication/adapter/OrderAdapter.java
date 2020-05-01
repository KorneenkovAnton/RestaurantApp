package com.example.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.DTO.OrderDto;
import com.example.myapplication.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private static final String RESPONSE_FORMAT = "EEE MMM dd HH:mm";
    private static final String MONTH_DAY_FORMAT = "MMM d";
    private List<OrderDto> orderDtos = new ArrayList<>();

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_item_view,parent,false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        holder.bind(orderDtos.get(position));
    }

    @Override
    public int getItemCount() {
        return orderDtos.size();
    }

    public void setItems(Collection<OrderDto> orderDtos){
        this.orderDtos.addAll(orderDtos);
        notifyDataSetChanged();
    }

    public void clearItems(){
        orderDtos.clear();
        notifyDataSetChanged();
    }

   public class OrderViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        private ImageButton orderDetailButton;

        public void bind(OrderDto orderDto){
            textView.setText(getFormatedDate(orderDto.getDate().toString()));
        }

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.order_date_text);
            orderDetailButton = itemView.findViewById(R.id.order_detail_button);
        }

        public String getFormatedDate(String rawDate){
            SimpleDateFormat dateFormat = new SimpleDateFormat(RESPONSE_FORMAT);
            SimpleDateFormat displayFromat = new SimpleDateFormat(MONTH_DAY_FORMAT);
            try {
                Date date = dateFormat.parse(rawDate);
                return displayFromat.format(date);
            } catch (ParseException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }
}
