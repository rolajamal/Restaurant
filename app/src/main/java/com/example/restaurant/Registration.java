package com.example.restaurant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.example.restaurant.databinding.ActivityRegistrationBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Registration extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    ActivityRegistrationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityRegistrationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
        //هين ازا المستخدم موجود مسبقا فوتني ع الصفحة عطول
        if (firebaseUser!=null){
            startActivity(new Intent(getApplicationContext(),restaurant_Activity.class));

        }
        binding.btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();

            }
        });

    }


    private void register(){
        String email=binding.etEmail.getText().toString();
        String password=binding.etPassword.getText().toString();
        firebaseAuth.createUserWithEmailAndPassword(email,password).
                addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //افحصلي ازا التاسك صحيح اعمل انتقال
                        if (task.isSuccessful()){

                            Log.d("Registration",task.getResult().getUser().toString());

                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            //غير هيك طلعلي رسالة الخطأ
                        }else {
                            Log.d("LoginActivity",task.getException().getMessage());
                            Toast.makeText(Registration.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }

}