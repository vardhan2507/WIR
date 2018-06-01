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

public class hostuser extends AppCompatActivity {
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    Button se;
    EditText su;
    String hssu;
    static String hid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hostuser);
        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("users");
        se=(Button)findViewById(R.id.hse);
        su=(EditText)findViewById(R.id.hsearchu);
        se.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(searchpro.this,"bu",Toast.LENGTH_SHORT).show();
                if(TextUtils.isEmpty(su.getText().toString().trim()))
                {
                    Toast.makeText(hostuser.this,"Enter a username",Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                {
                    hssu=su.getText().toString().trim();
                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            databaseReference.removeEventListener(this);
                            for(DataSnapshot sh:dataSnapshot.getChildren())
                            {
                                if(sh.child("uusername").getValue(String.class).equals(hssu))
                                {
                                    hid=sh.child("uid").getValue(String.class);
                                    databaseReference.child(sh.child("uid").getValue(String.class)).child("fr").setValue(firebaseUser.getUid());
                                    Toast.makeText(hostuser.this,"Ask the other player to join",Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(hostuser.this,hostque.class));
                                    return;
                                }
                            }
                            Toast.makeText(hostuser.this,"No such User found",Toast.LENGTH_SHORT).show();
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
