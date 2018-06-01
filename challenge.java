package com.example.vardhan.wir;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class challenge extends AppCompatActivity {
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    EditText us;
    Button sen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge);
        firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("users");
        us=(EditText)findViewById(R.id.us);
        sen=(Button)findViewById(R.id.sen);
        sen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(us.getText().toString().trim()))
                {
                    Toast.makeText(challenge.this,"Enter your friends username",Toast.LENGTH_SHORT).show();
                    return;
                }
                else {

                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            databaseReference.removeEventListener(this);
                            for (DataSnapshot h : dataSnapshot.getChildren()) {
                                if (h.child("uusername").getValue(String.class).equals(us.getText().toString().trim())) {
                                    databaseReference.child(h.child("uid").getValue(String.class)).child("requests").setValue(Questions.ar);
                                    startActivity(new Intent(challenge.this,Profile.class));
                                    return;
                                }
                            }
                            Toast.makeText(challenge.this,"No such user available",Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            }
        });
    }
}
