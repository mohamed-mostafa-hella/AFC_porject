package com.example.o_o.afc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoodActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;
    private FirebaseUser user;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lood);

        mAuth=FirebaseAuth.getInstance();
        user=mAuth.getCurrentUser();
    }
}
