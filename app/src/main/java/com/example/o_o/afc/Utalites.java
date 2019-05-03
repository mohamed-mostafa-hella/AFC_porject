package com.example.o_o.afc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.example.o_o.afc.empOp.findemp;
import com.example.o_o.afc.empOp.newemployee;
import com.example.o_o.afc.membOp.NewMember;
import com.example.o_o.afc.modil.empModil;
import com.example.o_o.afc.modil.memModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Utalites {
    FirebaseAuth mAuth=FirebaseAuth.getInstance();
    FirebaseUser user = mAuth.getCurrentUser();
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    Context context;



    public Utalites(Context context) {
        this.context = context;
    }


    void addMemberAcount(String email , String pass , final memModel memModel, final Max number){
       if(user != null){
           mAuth.createUserWithEmailAndPassword(email+"@ex.com", pass)
                   .addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                           if (task.isSuccessful()) {
                               // Sign in success, update UI with the signed-in user's information
                               Log.d("tag", "createUserWithEmail:success");
                               FirebaseUser user = mAuth.getCurrentUser();
                               database.child("User").child(user.getUid()).setValue(memModel);
                               number.maxMem++;
                               database.child("max").setValue(number);
                               Toast.makeText(context, "done", Toast.LENGTH_SHORT).show();
                           } else {
                               // If sign in fails, display a message to the user.
                               Log.w("tag", "createUserWithEmail:failure", task.getException());
                               Toast.makeText(context, "error 1", Toast.LENGTH_SHORT).show();

                           }
                       }
                   });
       }else {
           Log.w("tag", "createUserWithEmail:failure : the user is loged in");
           Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
       }
    }

    public void addEmployeeAcount(String email, String pass, final empModil empModil, final Max number){
        if(user != null){
            mAuth.createUserWithEmailAndPassword(email+"@ex.com", pass)
                    .addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("tag", "createUserWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                database.child("User").child(user.getUid()).setValue(empModil);
                                number.maxEm++;
                                database.child("max").setValue(number);

                                Toast.makeText(context, "done", Toast.LENGTH_SHORT).show();
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("tag", "createUserWithEmail:failure", task.getException());
                                Toast.makeText(context, "error 1", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }else {
            Log.w("tag", "createUserWithEmail:failure : the user is loged in");
            Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteuser(String Uid){
        if(user != null){
            database.child(Uid).setValue(null);
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

    void updateEmployee(empModil empModil , String Uid){
        database.child("User").child(Uid).setValue(empModil);
    }

    void updateMemper(memModel memModel , String Uid){
        database.child("User").child(Uid).setValue(memModel);
    }

    void login(String email , String pass){
        if(user == null){
            mAuth.signInWithEmailAndPassword(email , pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    openStartActivity();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    void openStartActivity() {
        Intent intent = new Intent(context , findemp.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }


}
