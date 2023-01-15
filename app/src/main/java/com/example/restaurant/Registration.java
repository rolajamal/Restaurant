package com.example.restaurant;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.example.restaurant.databinding.ActivityRegistrationBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Registration extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    ActivityRegistrationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityRegistrationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        ActivityResultLauncher<String> ar1 = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                binding.profileImage.setImageURI(result);
            }
        });
        binding.profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ar1.launch("imag/*");
            }

        });


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

                String name= binding.etName.getText().toString();
                String img=binding.profileImage.getDrawable().toString();
                String email=binding.etEmail.getText().toString();
                String password=binding.etPassword.getText().toString();


                Intent intent=new Intent(getBaseContext(),Profile.class);
                intent.putExtra("user_name",name);
                intent.putExtra("user_img",img);
                intent.putExtra("user_email",email);
                intent.putExtra("user_password",password);
                startActivity(intent);

            }
        });



    }


    private void register() {
        String email = binding.etEmail.getText().toString();
        String password = binding.etPassword.getText().toString();
        firebaseAuth.createUserWithEmailAndPassword(email, password).
                addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //افحصلي ازا التاسك صحيح اعمل انتقال
                        if (task.isSuccessful()) {
                            save(task.getResult().getUser().getUid());
                            Log.d("Registration", task.getResult().getUser().toString());

                            startActivity(new Intent(getApplicationContext(), restaurant_Activity.class));
                            //غير هيك طلعلي رسالة الخطأ
                        } else {
                            Log.d("LoginActivity", task.getException().getMessage());
                            Toast.makeText(Registration.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });


    }


    public void save(String user_id) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String name= binding.etName.getText().toString();
        String img=binding.profileImage.getDrawable().toString();
        String email=binding.etEmail.getText().toString();
        String password=binding.etPassword.getText().toString();


        Map<String, Object> user = new HashMap<>();
        user.put("user_id", user_id);
        user.put("user_name",name);
        user.put("user_img",img);
        user.put("user_email",email);
        user.put("user_password",password);




        db.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                        Toast.makeText(getBaseContext(), "DocumentSnapshot added with ID: " + documentReference.getId()
                                , Toast.LENGTH_LONG).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getBaseContext(),  "Error adding document", Toast.LENGTH_LONG).show();
                    }
 });
}





}