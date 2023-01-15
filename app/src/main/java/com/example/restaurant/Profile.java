package com.example.restaurant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.restaurant.databinding.ActivityProfileBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.net.URI;
import java.util.List;

public class Profile extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    FirebaseFirestore db;



    ActivityProfileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent i=getIntent();
        String name=i.getStringExtra("user_name");
        binding.namePro.setText(name);
        String pass=i.getStringExtra("user_password");
        binding.passwordPro.setText(pass);
        String email=i.getStringExtra("user_email");
        binding.emailPro.setText(email);

//        binding.imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getBaseContext(),UpdateProfile.class));
//            }
//        });



//        String img=i.getStringExtra("user_img");
//        binding.profileImage.setImageURI(img);

    }
//    public void UpdataUsers() {
//        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
//        String currentEmail = firebaseUser.getEmail();
//        AuthCredential credential = EmailAuthProvider.getCredential(currentEmail,binding.oldPassword.getText().toString());
//        firebaseUser.reauthenticate(credential).addOnCompleteListener(task -> {
//            if(task.isSuccessful()){
//                firebaseUser.updatePassword(binding.passwordPro.getText().toString())
//                        .addOnCompleteListener(runnable -> {
//                            if(runnable.isSuccessful()){
//                                Toast.makeText(getBaseContext(), "Change Password Successfully", Toast.LENGTH_LONG).show();
//                                onBackPressed();
//                            }else{
//                                Toast.makeText(getBaseContext(), "Change Password Failed", Toast.LENGTH_LONG).show();
//                            }
//                        });
//            }else{
//                Toast.makeText(getBaseContext(), "Authentication failed, wrong password?", Toast.LENGTH_LONG).show();
//            }
// });
//}


}
