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

public class acceptend extends AppCompatActivity {

    TextView score;
    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Button ok;
    static String isc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceptend);
        score=(TextView)findViewById(R.id.score1);
        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        firebaseDatabase=FirebaseDatabase.getInstance();
        ok=(Button)findViewById(R.id.ok1);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(acceptend.this,Profile.class));
            }
        });
        databaseReference=firebaseDatabase.getReference("users/"+firebaseUser.getUid()+"/"+"requests/11");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                databaseReference.removeEventListener(this);
               int e= dataSnapshot.getValue(Integer.class);
                if(e<acceptreq.sc)
                    isc="You Won";
                else if(e==acceptreq.sc)
                    isc="Draw";
                else
                    isc="You Lost";
                score.setText(isc);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
