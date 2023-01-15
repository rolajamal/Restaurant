package com.example.restaurant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.restaurant.databinding.ActivityRestaurantBinding;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class restaurant_Activity extends AppCompatActivity{
    ActivityRestaurantBinding binding;
    FirebaseFirestore db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityRestaurantBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
         db=FirebaseFirestore.getInstance();

        loadResturant();
    }




    private void loadResturant() {
        db=FirebaseFirestore.getInstance();
        db.collection("Restaurant").limit(10).get()
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

                           Adaptar adaptar=new Adaptar(restaurantList,getBaseContext());
                           binding.RV.setAdapter(adaptar);

                            binding.RV.setLayoutManager(new LinearLayoutManager
                                    (restaurant_Activity.this,LinearLayoutManager.VERTICAL,false));
                            binding.RV.setHasFixedSize(true);
                            adaptar.notifyDataSetChanged();
                            Log.d("restaurantList",restaurantList.toString());

                        }else {
                            Log.d("getException",task.getException().getMessage());
                        }
                    }
                });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.profile:
                Intent intent=new Intent(this,Profile.class);
                startActivity(intent);
                Toast.makeText(this, "profile clicked", Toast.LENGTH_SHORT).show();

                return true;
            case R.id.signOut:
               finish();
               return true;

        }
        return false;
    }

}






