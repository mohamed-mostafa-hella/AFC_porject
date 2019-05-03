 package com.example.o_o.afc;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.o_o.afc.empOp.newemployee;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

 public class Login extends AppCompatActivity {

    TextInputEditText email,pass;
    Button button;
    Utalites utalites;

    FirebaseUser firebaseUser ;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FirebaseApp.initializeApp(this);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        email=findViewById(R.id.username);
        pass=findViewById(R.id.password);
        button=findViewById(R.id.signin);
        utalites=new Utalites(this);

        mAuth=FirebaseAuth.getInstance();
        if(firebaseUser != null){
            Intent intent=new Intent(this , LoodActivity.class);
            startActivity(intent);
            finish();
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkvalid()){
                    String mail=email.getText().toString().trim();
                    String password=pass.getText().toString().trim();
                    login(mail+"@ex.com" , password);
                }
            }
        });
    }

     private boolean checkvalid() {
         if(email.getText().toString().trim().isEmpty() || pass.getText().toString().trim().isEmpty() ){
             return false;
         }else{
             return true;
         }
     }

     void openStartActivity() {
         Intent intent = new Intent(this , LoodActivity.class);
         intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
         startActivity(intent);
     }

     void login(String email , String pass){
         if(firebaseUser == null){
             mAuth.signInWithEmailAndPassword(email , pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                 @Override
                 public void onSuccess(AuthResult authResult) {
                     openStartActivity();
                 }
             }).addOnFailureListener(new OnFailureListener() {
                 @Override
                 public void onFailure(@NonNull Exception e) {
                     Toast.makeText(Login.this, "error", Toast.LENGTH_SHORT).show();
                 }
             });
         }
     }
 }
