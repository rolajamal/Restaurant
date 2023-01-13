package com.example.restaurant;

import android.content.Context;
import android.view.LayoutInflater;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.restaurant.databinding.ItemBinding;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewModel> {

    List<Restaurant>restaurantList;
    Context context;

    public Adapter(List<Restaurant> restaurantList, Context context) {
        this.restaurantList = restaurantList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemBinding binding=ItemBinding.inflate(LayoutInflater.from(context),parent,false);
        return new MyViewModel(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewModel holder, int position) {
        Restaurant restaurant=restaurantList.get(position);
        holder.name.setText(restaurant.getName());
       // holder.price.setText(restaurant.getPrice());
        holder.category.setText(restaurant.getCategory());
        holder.city.setText(restaurant.getCity());
        Glide.with(context)
                .load(restaurant.getPhoto()).optionalCircleCrop()
                .into(holder.img);



    }

    @Override
    public int getItemCount() {
        return restaurantList.size();
    }

    public class MyViewModel extends RecyclerView.ViewHolder {
        ImageView img;
        TextView name,category,city;


        public MyViewModel(@NonNull ItemBinding binding) {
            super(binding.getRoot());

            img=binding.image;
            name=binding.name;
           // price=binding.price;
            category=binding.category;
            city=binding.city;
        }
    }
}
