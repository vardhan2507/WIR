package com.example.vardhan.wir;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;

public class multicscore extends AppCompatActivity {
    ListView lv;
    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> arrayList;
    Button okc;
    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    String cus;
    String ans;
    int flag=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multicscore);
        lv = (ListView) findViewById(R.id.msc);
        arrayList = new ArrayList<String>(10);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        okc=(Button) findViewById(R.id.okc);
        lv.setAdapter(arrayAdapter);
        okc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(multicscore.this,Profile.class));
            }
        });
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                databaseReference.removeEventListener(this);
                arrayList.clear();
                int k=0;
                long v = dataSnapshot.child("codeplay").child(CodeMulti.s).child("name").getChildrenCount();
                cus = dataSnapshot.child("users").child(firebaseUser.getUid()).child("uusername").getValue(String.class);
                ans=cus + "     " + String.valueOf(multicque.sc);
                databaseReference.child("codeplay").child(CodeMulti.s).child("name").child(String.valueOf(v)).setValue(ans);
                for(DataSnapshot d:dataSnapshot.child("codeplay").child(CodeMulti.s).child("name").getChildren())
                {
                    arrayList.add(d.getValue(String.class));
                }
                flag=1;
                if(flag==1) {
                    databaseReference.child("codeplay").child(CodeMulti.s).child("name").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            long v = dataSnapshot.getChildrenCount();
                            arrayList.add(dataSnapshot.child(String.valueOf(v - 1)).getValue(String.class));
                            arrayAdapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        }

}