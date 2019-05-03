 package com.example.o_o.afc;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

 public class Login extends AppCompatActivity {

    TextInputEditText email,pass;
    Button button;
    Utalites utalites;

    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FirebaseApp.initializeApp(this);

        email=findViewById(R.id.username);
        pass=findViewById(R.id.password);
        button=findViewById(R.id.signin);
        utalites=new Utalites(this);

        if(firebaseUser != null){
            utalites.openStartActivity();
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkvalid()){
                    String mail=email.getText().toString().trim();
                    String password=pass.getText().toString().trim();
                    utalites.login(mail+"@ex.com" , password);
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
 }
