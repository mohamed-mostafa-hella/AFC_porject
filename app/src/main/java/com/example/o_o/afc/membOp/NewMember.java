package com.example.o_o.afc.membOp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.o_o.afc.Max;
import com.example.o_o.afc.R;
import com.example.o_o.afc.Utalites;
import com.example.o_o.afc.empOp.newemployee;
import com.example.o_o.afc.modil.empModil;
import com.example.o_o.afc.modil.memModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class NewMember extends AppCompatActivity {



    private FirebaseAuth mAuth;
    private FirebaseUser user;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();


    Utalites utalites;

    public Max number;

    EditText fullname , nationalid  , address , password , cofirmpassword,jop;
    ProgressBar looder;
    RadioGroup type;
    Button creat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newmember_data);



        fullname=findViewById(R.id.fullname);
        nationalid=findViewById(R.id.nationalID);
        jop=findViewById(R.id.job);
        address=findViewById(R.id.address);
        password=findViewById(R.id.password);
        cofirmpassword=findViewById(R.id.confirmpassword);
        type=findViewById(R.id.gender);
        creat=findViewById(R.id.signup);


        mAuth=FirebaseAuth.getInstance();
        user=mAuth.getCurrentUser();


        utalites=new Utalites(this);


        database.child("max").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    number=dataSnapshot.getValue(Max.class);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        creat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cheackvalid()){
                    // looder.setVisibility(View.VISIBLE);
                    int gender;
                    if(type.getCheckedRadioButtonId()== R.id.male){
                        gender=1;
                    }else{
                        gender=0;
                    }
                    final memModel op=new memModel(fullname.getText().toString().trim(),
                            nationalid.getText().toString().trim(),
                            jop.getText().toString().trim(),
                            address.getText().toString().trim(),
                            gender,
                            Calendar.getInstance().getTime()
                    );

                    if(number==null){
                        Toast.makeText(NewMember.this, "cheack the internet connection", Toast.LENGTH_SHORT).show();
                        // looder.setVisibility(View.GONE);
                    }else {
                        int tem=number.getMaxMem();
                        op.setID(tem);
                        StringBuilder input1 = new StringBuilder();
                        String s="";
                        for(int i=0;i<7;i++){
                            s+=tem%10;
                            tem/=10;
                        }
                        input1.append(s);
                        input1.reverse();
                        String email="mem"+input1 ,pass=password.getText().toString();

                        if(user != null){
                            mAuth.createUserWithEmailAndPassword(email+"@ex.com", pass)
                                    .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                        @Override
                                        public void onSuccess(AuthResult authResult) {

                                            // Sign in success, update UI with the signed-in user's information
                                            Log.d("tag", "createUserWithEmail:success");
                                            FirebaseUser user = mAuth.getCurrentUser();
                                            database.child("User").child(user.getUid()).setValue(op);
                                            number.maxMem++;
                                            database.child("max").setValue(number);
                                            //looder.setVisibility(View.GONE);
                                            Toast.makeText(NewMember.this, "done", Toast.LENGTH_SHORT).show();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(NewMember.this, "error 1", Toast.LENGTH_SHORT).show();
                                    // looder.setVisibility(View.GONE);
                                }
                            });
                        }else {
                            Log.w("tag", "createUserWithEmail:failure : the user is loged in");
                            // looder.setVisibility(View.GONE);
                            Toast.makeText(NewMember.this, "error", Toast.LENGTH_SHORT).show();
                        }


                        clearfealds();
                    }
                }
            }
        });




    }

    private void clearfealds() {
        fullname.setText("");
        nationalid.setText("");
        address.setText("");
        jop.setText("");
        password.setText("");
        cofirmpassword.setText("");
        type.clearCheck();
    }

    private boolean cheackvalid() {
        String name=fullname.getText().toString();
        String natid=nationalid.getText().toString();
        String jopst=jop.getText().toString();
        if(name.trim().isEmpty()  ||
                natid.trim().isEmpty() ||
                jopst.trim().isEmpty() ||
                address.getText().toString().trim().isEmpty() ||
                password.getText().toString().trim().isEmpty() ||
                cofirmpassword.getText().toString().trim().isEmpty() || (type.getCheckedRadioButtonId() != R.id.male  && type.getCheckedRadioButtonId() != R.id.female ) ) {

            Toast.makeText(this, "all fealdes are required", Toast.LENGTH_SHORT).show();
            return false;
        }else{
            return true;
        }
    }
}
