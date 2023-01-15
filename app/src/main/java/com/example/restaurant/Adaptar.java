package com.example.restaurant;

import android.content.Context;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.restaurant.databinding.ItemBinding;

import java.util.List;

public class Adaptar extends RecyclerView.Adapter<Adaptar.MyViewModel> {
    List<Restaurant>restaurantList;
    Context context;





    public Adaptar(List<Restaurant> restaurantList, Context context) {
        this.restaurantList = restaurantList;
        this.context = context;
    }

    public MyViewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemBinding binding=ItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);

        return new MyViewModel(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewModel holder, int position) {
        int pos=position;
        Restaurant restaurant=restaurantList.get(pos);
        holder.name.setText(restaurant.getName());
        holder.city.setText(restaurant.getCity());
        holder.category.setText(restaurant.getCategory());
        holder.price.setText(restaurant.getPrice());
        Glide.with(context).load(restaurant.getPhoto()).into(holder.imgFood);




    }


    @Override
    public int getItemCount() {
        return restaurantList.size();
    }

    public class MyViewModel extends RecyclerView.ViewHolder{
        ImageView imgFood,imgFav;
        TextView name,city,category,price;


        public MyViewModel(@NonNull ItemBinding binding) {
            super(binding.getRoot());
            imgFood=binding.image;
            imgFav=binding.imgFav;
            name=binding.name;
            category=binding.category;
            price=binding.price;
            city=binding.city;
        }
    }
}
