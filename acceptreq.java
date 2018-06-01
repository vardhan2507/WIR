package com.example.vardhan.wir;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Random;

public class acceptreq extends AppCompatActivity {
    TextView question1;
    Button q11,q21,q31,q41,quit1,next1;
    TextView score1;
    static int a1;
    static int k=0;
    static  int c1=0;
    static int sc=0;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceptreq);
        c1=0;
        sc=0;
        question1=(TextView)findViewById(R.id.question1);
        mediaPlayer=MediaPlayer.create(this,R.raw.faded);
        mediaPlayer.start();
        q11=(Button)findViewById(R.id.q11);
        q21=(Button)findViewById(R.id.q21);
        q31=(Button)findViewById(R.id.q31);
        q41=(Button)findViewById(R.id.q41);
        quit1=(Button)findViewById(R.id.quit1);
        next1=(Button)findViewById(R.id.next1);
        score1=(TextView) findViewById(R.id.score1);
        question1.setMovementMethod(new ScrollingMovementMethod());
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();

            c1++;
            change();
            next1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    c1++;
                    change();
                    q11.setBackgroundResource(R.drawable.ashbut);
                    q21.setBackgroundResource(R.drawable.ashbut);
                    q31.setBackgroundResource(R.drawable.ashbut);
                    q41.setBackgroundResource(R.drawable.ashbut);
                    q11.setEnabled(true);
                    q21.setEnabled(true);
                    q31.setEnabled(true);
                    q41.setEnabled(true);
                }
            });
            quit1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mediaPlayer.stop();
                    startActivity(new Intent(acceptreq.this, endgame.class));
                }
            });
            q11.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (a1 == 1) {
                        v.setBackgroundColor(Color.GREEN);
                        (sc)++;
                        String s1 = String.valueOf((sc));
                        score1.setText(s1);

                    } else {
                        v.setBackgroundColor(Color.RED);
                    }
                    q11.setEnabled(false);
                    q21.setEnabled(false);
                    q31.setEnabled(false);
                    q41.setEnabled(false);

                }
            });
            q21.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (a1 == 2) {
                        v.setBackgroundColor(Color.GREEN);
                        (sc)++;
                        String s1 = String.valueOf((sc));
                        score1.setText(s1);
                    } else {
                        v.setBackgroundColor(Color.RED);
                    }
                    q11.setEnabled(false);
                    q21.setEnabled(false);
                    q31.setEnabled(false);
                    q41.setEnabled(false);

                }
            });
            q31.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (a1 == 3) {
                        v.setBackgroundColor(Color.GREEN);
                        (sc)++;
                        String s1 = String.valueOf((sc));
                        score1.setText(s1);
                    } else {
                        v.setBackgroundColor(Color.RED);
                    }
                    q11.setEnabled(false);
                    q21.setEnabled(false);
                    q31.setEnabled(false);
                    q41.setEnabled(false);

                }
            });
            q41.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (a1 == 4) {
                        v.setBackgroundColor(Color.GREEN);
                        (sc)++;
                        String s1 = String.valueOf((sc));
                        score1.setText(s1);
                    } else {
                        v.setBackgroundColor(Color.RED);
                    }
                    q11.setEnabled(false);
                    q21.setEnabled(false);
                    q31.setEnabled(false);
                    q41.setEnabled(false);

                }
            });
        //}
    }
    void change()
    {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                databaseReference.removeEventListener(this);
                if(k<=10) {
                    int r = dataSnapshot.child("users").child(firebaseUser.getUid()).child("requests").child(String.valueOf(k)).getValue(Integer.class);
                    k++;

                    Que ques = dataSnapshot.child("quiz").child(String.valueOf(r)).getValue(Que.class);
                    question1.setText(ques.getQue());
                    q11.setText(ques.getO1());
                    q21.setText(ques.getO2());
                    q31.setText(ques.getO3());
                    q41.setText(ques.getO4());
                    a1 = ques.getAns();

                    if (c1 >10) {
                        mediaPlayer.stop();
                        startActivity(new Intent(acceptreq.this, acceptend.class));
                    }
                }
                }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
































