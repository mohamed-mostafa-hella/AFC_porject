package com.example.o_o.afc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.o_o.afc.modil.empModil;
import com.example.o_o.afc.modil.memModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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

        SharedPreferences prefs = getSharedPreferences(Constant.SHARDPREFNAME, MODE_PRIVATE);
        final String type = prefs.getString(Constant.TYPE, null);
        database.child("User").child(type).child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(type.equals("em")){
                    memModel op = dataSnapshot.getValue(memModel.class);
                    startactivityem(op);
                }else{
                    empModil op = dataSnapshot.getValue(empModil.class);
                    startactivitymem(op);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(LoodActivity.this, "there is error while looding data cheack the connection ", Toast.LENGTH_SHORT).show();
            }
        });
    }

    Intent intent = new Intent(this , StartActivity.class);

    private void startactivitymem(empModil op) {
        int typ=gettyp(op);
        intent.putExtra("op", op);
        intent.putExtra("type" , typ);
        startActivity(intent);
    }

    //{"مدير النادي", "نائب مديرالنادي ", "مدير شئون قانونية", "باحث بشئون قانونية", "مدير نشاط رياضي", "باحث بالنشاط الرياضي", "مدير آمن", "فرد آمن", "مدير العضويات", "باحث العضويات", "رئيس شئون العاملين", "موظف شئون عاملين", "مسئول نظافة", "مدير ثقافى ورحلات", "باحث ثقافى ورحلات", "مدرب"};
    //     0         1                    2                  3                4                     5                    6                7             8           9                 10                   11                   12                    13                  14               15

    private int gettyp(empModil op) {
        String jop=op.getJop();
        if(jop.equals(Constant.Jobs[15]) ||  jop.equals(Constant.Jobs[14]) ){
            return 1;
        }else if(jop.equals(Constant.Jobs[4]) ||  jop.equals(Constant.Jobs[5])){
            return 2;
        }else if(jop.equals(Constant.Jobs[6]) ||  jop.equals(Constant.Jobs[7])){
            return 3;
        }else if(jop.equals(Constant.Jobs[8]) ||  jop.equals(Constant.Jobs[9])){
            return 4;
        }else{
            return 5;
        }
    }

    private void startactivityem(memModel op) {
        intent.putExtra("op", op);
        intent.putExtra("type" , 0);
        startActivity(intent);
    }
}
