package com.example.restaurant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;

import com.example.restaurant.databinding.ActivityRestaurantBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class restaurant_Activity extends AppCompatActivity {
    ActivityRestaurantBinding binding;
    FirebaseFirestore db;
   Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityRestaurantBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        loadResturant();
    }
    private void loadResturant() {
        db=FirebaseFirestore.getInstance();
        db.collection("Restuarant").limit(10).get()
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        e.printStackTrace();
                    }
                }).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            List<Restaurant>restaurantList=task.getResult().toObjects(Restaurant.class);
                            Log.d("List_size","onComplete"+restaurantList.size());

                            adapter=new Adapter(restaurantList,restaurant_Activity.this);
                            binding.recyclerRestaurants.setLayoutManager(new LinearLayoutManager
                                    (restaurant_Activity.this,LinearLayoutManager.VERTICAL,false));
                            binding.recyclerRestaurants.setHasFixedSize(true);
                            binding.recyclerRestaurants.setAdapter(adapter);
                            Log.d("restaurantList",restaurantList.toString());

                        }else {
                            Log.d("getException",task.getException().getMessage());
                        }
                    }
                });

    }

}






