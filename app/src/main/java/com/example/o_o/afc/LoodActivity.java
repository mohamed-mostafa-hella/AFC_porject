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

    Utalites utalites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lood);

        mAuth=FirebaseAuth.getInstance();
        user=mAuth.getCurrentUser();

        utalites=new Utalites(this);

        SharedPreferences prefs = getSharedPreferences(Constant.SHARDPREFNAME, MODE_PRIVATE);
        final String type = prefs.getString(Constant.TYPE, null);
        if(type != null){
            database.child("User").child(type).child(user.getUid()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists()){
                        if(type.equals("emp")){
                            empModil op = dataSnapshot.getValue(empModil.class);
                            startactivityem(op);
                        }else{
                            memModel op = dataSnapshot.getValue(memModel.class);
                            startactivitymem(op);
                        }
                    }else{
                        Toast.makeText(LoodActivity.this, "cheack th inter net", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(LoodActivity.this, "there is error while looding data cheack the connection ", Toast.LENGTH_SHORT).show();
                }
            });
        }else {
            utalites.logout();
        }
    }

    private void startactivitymem(memModel op) {
        Intent intent = new Intent(this , StartActivity.class);
        intent.putExtra("op", op);
        intent.putExtra("type" , ""+0);
        startActivity(intent);
    }

    //{"مدير النادي", "نائب مديرالنادي ", "مدير شئون قانونية", "باحث بشئون قانونية", "مدير نشاط رياضي", "باحث بالنشاط الرياضي", "مدير آمن", "فرد آمن", "مدير العضويات", "باحث العضويات", "رئيس شئون العاملين", "موظف شئون عاملين", "مسئول نظافة", "مدير ثقافى ورحلات", "باحث ثقافى ورحلات", "مدرب"};
    //     15         14                    13                  12                11                    10                   9                8             7           6                5                  4                   3                    2                  1               0


    private int gettyp(empModil op) {
        String jop=op.getJop();
        if(jop.equals(Constant.Jobs.get(0)) ||  jop.equals(Constant.Jobs.get(1)) ){
            return 1;
        }else if(jop.equals(Constant.Jobs.get(11)) ||  jop.equals(Constant.Jobs.get(10))){
            return 2;
        }else if(jop.equals(Constant.Jobs.get(9)) ||  jop.equals(Constant.Jobs.get(8))){
            return 3;
        }else if(jop.equals(Constant.Jobs.get(7)) ||  jop.equals(Constant.Jobs.get(6))){
            return 4;
        }else{
            return 5;
        }
    }

    private void startactivityem(empModil op) {
        Intent intent = new Intent(this , StartActivity.class);
        int typ=  gettyp(op);
        intent.putExtra("op", op);
        intent.putExtra("type" , ""+typ);
        startActivity(intent);
    }
}
