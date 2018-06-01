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

public class endgame extends AppCompatActivity {
    TextView score;
    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Button ok,challenge1;
    static int h=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_endgame);
        score=(TextView)findViewById(R.id.score1);
        score.setText(String.valueOf(Questions.sc));
        firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
        String id="";
        if(firebaseUser!=null) {
            id = firebaseUser.getUid();
            firebaseDatabase=FirebaseDatabase.getInstance();
            databaseReference=firebaseDatabase.getReference("users/"+id);
            ok=(Button)findViewById(R.id.ok1);
            ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   startActivity(new Intent(endgame.this,Profile.class));
                }
            });
            challenge1=(Button)findViewById(R.id.chal);
            challenge1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(endgame.this,challenge.class));
                }
            });

            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    databaseReference.removeEventListener(this);
                        h=dataSnapshot.child("highscore").getValue(Integer.class);
                        if(h<=Questions.sc)
                        {
                            h=Questions.sc;
                        } databaseReference.child("highscore").setValue(h);
                    databaseReference.child("curscore").setValue(Questions.sc);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }
}
