package com.example.o_o.afc;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.example.o_o.afc.empOp.newemployee;
import com.example.o_o.afc.membOp.NewMember;
import com.example.o_o.afc.membOp.findmem;
import com.example.o_o.afc.modil.empModil;
import com.example.o_o.afc.modil.memModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.content.Context.MODE_PRIVATE;

public class Utalites {
    FirebaseAuth mAuth=FirebaseAuth.getInstance();
    FirebaseUser user = mAuth.getCurrentUser();
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    Context context;



    public Utalites(Context context) {
        this.context = context;
    }

    public void deleteemp(String Uid){
        if(user != null){
            database.child("User").child("emp").child(Uid).setValue(null);
            if(Uid.equals(user.getUid())){
                logout();
            }
        }else{
            Log.e("error : " , "this user has no permition to do this" );
        }
    }

    public void deletemem(String Uid){
        if(user != null){
            database.child("User").child("mem").child(Uid).setValue(null);
            if(Uid.equals(user.getUid())){
                logout();
            }
        }else{
            Log.e("error : " , "this user has no permition to do this" );
        }
    }

    void logout() {
        mAuth.signOut();
        Intent intent = new Intent(context , Login.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    private void saveshared(String email) {
        SharedPreferences.Editor editor = context.getSharedPreferences(Constant.SHARDPREFNAME, MODE_PRIVATE).edit();
        editor.putString(Constant.TYPE, null );
        editor.apply();
    }

    public void updateEmployee(empModil empModil , String Uid){
        database.child("User").child("emp").child(Uid).setValue(empModil);
    }

    public void updateMemper(memModel memModel , String Uid){
        database.child("User").child(Uid).setValue(memModel);
    }






}
