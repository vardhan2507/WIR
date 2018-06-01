package com.example.vardhan.wir;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class leaderboard extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseUser firebaseUser;
    TextView t1,t2,t3,t4;
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("users");
        t1=(TextView)findViewById(R.id.l1u);
        t2=(TextView)findViewById(R.id.l1s);
        t3=(TextView)findViewById(R.id.l2u);
        t4=(TextView)findViewById(R.id.l2s);
        b1=(Button)findViewById(R.id.lok);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(leaderboard.this,Profile.class));
            }
        });
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                databaseReference.removeEventListener(this);
                t1.setText((dataSnapshot.child(firebaseUser.getUid()).child("fullname").getValue(String.class)));
                t2.setText((String.valueOf(dataSnapshot.child(firebaseUser.getUid()).child("curscore").getValue(Integer.class))));
                String w= (dataSnapshot.child(firebaseUser.getUid()).child("fr").getValue(String.class));
                t3.setText((dataSnapshot.child(w).child("fullname").getValue(String.class)));
                t4.setText(String.valueOf(dataSnapshot.child(w).child("curscore").getValue(Integer.class)));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
