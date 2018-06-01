package com.example.vardhan.wir;

import android.content.Intent;
import android.provider.ContactsContract;
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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class CodeMulti extends AppCompatActivity {

    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    EditText et;
    Button ho,jo;
    static String s;
    ArrayList<Que> ar;
    Button share;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_multi);
        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
        et=(EditText)findViewById(R.id.code);
        ho=(Button)findViewById(R.id.codeh);
        jo=(Button)findViewById(R.id.codej);
        share=(Button)findViewById(R.id.share);
        ar=new ArrayList<Que>();
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String sharebody="Code to Join:- \n "+et.getText().toString();
                String sharesub="Code to Join:- ";
                intent.putExtra(Intent.EXTRA_SUBJECT,sharesub);
                intent.putExtra(Intent.EXTRA_TEXT,sharebody);
                startActivity(Intent.createChooser(intent,"Send Code using"));
            }
        });
        ho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s = et.getText().toString().trim();
                if (TextUtils.isEmpty(s)) {
                    Toast.makeText(CodeMulti.this, "Enter a valid code", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            databaseReference.removeEventListener(this);
                            int flag=0;
                                if (dataSnapshot.child("codeplay").hasChild(s))
                                {
                                    flag=1;
                                }

                            if(flag==1)
                            {
                                Toast.makeText(CodeMulti.this,"Code already exists",Toast.LENGTH_SHORT).show();
                                return;
                            }
                            else
                            {
                             long m=dataSnapshot.child("quiz").getChildrenCount();
                                Random r=new Random(System.currentTimeMillis());
                                for(int i=0;i<=10;i++)
                                {
                                    Que w=dataSnapshot.child("quiz").child(String.valueOf(r.nextInt((int)m))).getValue(Que.class);
                                    ar.add(w);
                                }
                                ArrayList<String> ars=new ArrayList<String>(10);
                                ars.add("Username     Score");
                                mque mq=new mque(s,ar,ars);
                                databaseReference.child("codeplay").child(s).setValue(mq);
                                Toast.makeText(CodeMulti.this,"Ask the other users to join with the same code",Toast.LENGTH_SHORT);
                                startActivity(new Intent(CodeMulti.this,multicque.class));
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            }
        });
        jo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s=et.getText().toString().trim();
                if (TextUtils.isEmpty(s)) {
                    Toast.makeText(CodeMulti.this, "Enter an existing code", Toast.LENGTH_SHORT).show();
                    return;
                } else {

                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            databaseReference.removeEventListener(this);
                            int flag=0;
                            if (dataSnapshot.child("codeplay").hasChild(s))
                            {
                                flag=1;
                            }
                            if(flag==0)
                            {
                                Toast.makeText(CodeMulti.this,"Code doesnt exists",Toast.LENGTH_SHORT).show();
                                return;
                            }
                            else
                            {
                                startActivity(new Intent(CodeMulti.this,multicque.class));
                            }
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