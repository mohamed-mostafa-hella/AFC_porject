package com.example.o_o.afc.empOp;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.o_o.afc.Constant;
import com.example.o_o.afc.Max;
import com.example.o_o.afc.R;
import com.example.o_o.afc.Utalites;
import com.example.o_o.afc.modil.empModil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class newemployee extends AppCompatActivity {
    Spinner spinner;

    private FirebaseAuth mAuth;
    private FirebaseUser user;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();


    Utalites utalites;

    public Max number;

    EditText fullname, nationalid, salary, address, password, cofirmpassword;
    ProgressBar looder;
    RadioGroup type;
    Button creat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newemployee);


        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        fullname = findViewById(R.id.fullname);
        nationalid = findViewById(R.id.nationalID);
        salary = findViewById(R.id.salary);
        address = findViewById(R.id.address);
        password = findViewById(R.id.password);
        cofirmpassword = findViewById(R.id.confirmpassword);
        type = findViewById(R.id.gender);
        creat = findViewById(R.id.signup);
        spinner = findViewById(R.id.jobs);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, Constant.Jobs);
        spinner.setAdapter(adapter);

        utalites = new Utalites(this);


        database.child("max").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    number = dataSnapshot.getValue(Max.class);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        creat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cheackvalid()) {
                    // looder.setVisibility(View.VISIBLE);
                    int gender;
                    if (type.getCheckedRadioButtonId() == R.id.male) {
                        gender = 1;
                    } else {
                        gender = 0;
                    }

                    if (number == null) {
                        Toast.makeText(newemployee.this, "cheack the internet connection", Toast.LENGTH_SHORT).show();
                        // looder.setVisibility(View.GONE);
                    } else {
                        int tem = number.getMaxEm();
                        final empModil op = new empModil(fullname.getText().toString().trim(),
                                nationalid.getText().toString().trim(),
                                address.getText().toString().trim(),
                                salary.getText().toString(),
                                spinner.getSelectedItem().toString(),
                                "" + tem,
                              //  "emp",
                                gender,
                                Calendar.getInstance().getTime()
                        );
                        StringBuilder input1 = new StringBuilder();
                        String s = "";
                        for (int i = 0; i < 7; i++) {
                            s += tem % 10;
                            tem /= 10;
                        }
                        input1.append(s);
                        input1.reverse();
                        String email = "em" + input1, pass = password.getText().toString();

                        if (user != null) {
                            mAuth.createUserWithEmailAndPassword(email + "@ex.com", pass)
                                    .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                        @Override
                                        public void onSuccess(AuthResult authResult) {

                                            // Sign in success, update UI with the signed-in user's information
                                            Log.d("tag", "createUserWithEmail:success");
                                            FirebaseUser user = mAuth.getCurrentUser();
                                            database.child("User").child("emp").child(user.getUid()).setValue(op);
                                            number.maxEm++;
                                            database.child("max").setValue(number);
                                            //looder.setVisibility(View.GONE);
                                            Toast.makeText(newemployee.this, "done", Toast.LENGTH_SHORT).show();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(newemployee.this, "error 1", Toast.LENGTH_SHORT).show();
                                    // looder.setVisibility(View.GONE);
                                }
                            });
                        } else {
                            Log.w("tag", "createUserWithEmail:failure : the user is loged in");
                            // looder.setVisibility(View.GONE);
                            Toast.makeText(newemployee.this, "error", Toast.LENGTH_SHORT).show();
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
        salary.setText("");
        password.setText("");
        cofirmpassword.setText("");
        type.clearCheck();
    }

    private boolean cheackvalid() {
        String name = fullname.getText().toString();
        String natid = nationalid.getText().toString();
        String salaryst = salary.getText().toString();
        if (name.trim().isEmpty() ||
                natid.trim().isEmpty() ||
                salaryst.trim().isEmpty() ||
                address.getText().toString().trim().isEmpty() ||
                password.getText().toString().trim().isEmpty() ||
                cofirmpassword.getText().toString().trim().isEmpty() || (type.getCheckedRadioButtonId() != R.id.male && type.getCheckedRadioButtonId() != R.id.female)) {

            Toast.makeText(this, "all fealdes are required", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }


}
