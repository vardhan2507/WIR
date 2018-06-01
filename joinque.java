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

public class joinque extends AppCompatActivity {
    TextView question;
    Button q1,q2,q3,q4,quit,next;
    MediaPlayer mediaPlayer;
    TextView score;
    static int a3;
    static  int c3=0;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    String fuid;
    @Override
     protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joinque);
        mediaPlayer=MediaPlayer.create(this,R.raw.faded);
        multiplay.sc2=0;
        question=(TextView)findViewById(R.id.question3);
        q1=(Button)findViewById(R.id.q13);
        q2=(Button)findViewById(R.id.q23);
        q3=(Button)findViewById(R.id.q33);
        q4=(Button)findViewById(R.id.q43);
        quit=(Button)findViewById(R.id.quit3);
        next=(Button)findViewById(R.id.next3);
        question.setMovementMethod(new ScrollingMovementMethod());
        mediaPlayer.start();
        score=(TextView) findViewById(R.id.score13);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        c3++;
        fuid=multiplay.joinuse;
        next.setEnabled(false);
        change();
        databaseReference.child("users").child(fuid).child("keep").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue(Boolean.class))
                {
                    next.setEnabled(true);
                }
                else
                {
                    next.setEnabled(false);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c3++;
                change();
                q1.setBackgroundResource(R.drawable.ashbut);
                q2.setBackgroundResource(R.drawable.ashbut);
                q3.setBackgroundResource(R.drawable.ashbut);
                q4.setBackgroundResource(R.drawable.ashbut);
                q1.setEnabled(true);
                q2.setEnabled(true);
                q3.setEnabled(true);
                q4.setEnabled(true);
            }
        });
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                startActivity(new Intent(joinque.this,endgame.class));
            }
        });
        q1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child("users").child(firebaseUser.getUid()).child("keep").setValue(true);
                if(a3==1)
                {
                    v.setBackgroundColor(Color.GREEN);
                    (multiplay.sc2)++;
                    String s=String.valueOf((multiplay.sc2));
                    score.setText(s);

                }
                else
                {
                    v.setBackgroundColor(Color.RED);
                }
                q1.setEnabled(false);
                q2.setEnabled(false);
                q3.setEnabled(false);
                q4.setEnabled(false);

            }
        });
        q2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child("users").child(firebaseUser.getUid()).child("keep").setValue(true);
                if(a3==2)
                {
                    v.setBackgroundColor(Color.GREEN);
                    (multiplay.sc2)++;
                    String s=String.valueOf((multiplay.sc2));
                    score.setText(s);
                }
                else
                {
                    v.setBackgroundColor(Color.RED);
                }
                q1.setEnabled(false);
                q2.setEnabled(false);
                q3.setEnabled(false);
                q4.setEnabled(false);

            }
        });
        q3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child("users").child(firebaseUser.getUid()).child("keep").setValue(true);
                if(a3==3)
                {
                    v.setBackgroundColor(Color.GREEN);
                    (multiplay.sc2)++;
                    String s=String.valueOf((multiplay.sc2));
                    score.setText(s);
                }
                else
                {
                    v.setBackgroundColor(Color.RED);
                }
                q1.setEnabled(false);
                q2.setEnabled(false);
                q3.setEnabled(false);
                q4.setEnabled(false);

            }
        });
        q4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child("users").child(firebaseUser.getUid()).child("keep").setValue(true);
                if(a3==4)
                {
                    v.setBackgroundColor(Color.GREEN);
                    (multiplay.sc2)++;
                    String s=String.valueOf((multiplay.sc2));
                    score.setText(s);
                }
                else
                {
                    v.setBackgroundColor(Color.RED);
                }
                q1.setEnabled(false);
                q2.setEnabled(false);
                q3.setEnabled(false);
                q4.setEnabled(false);

            }
        });
    }
    void change()
    {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                databaseReference.removeEventListener(this);
                int r = dataSnapshot.child("users").child(firebaseUser.getUid()).child("ran").getValue(Integer.class);
                Que ques = dataSnapshot.child("quiz").child(String.valueOf(r)).getValue(Que.class);
                question.setText(ques.getQue());
                q1.setText(ques.getO1());
                q2.setText(ques.getO2());
                q3.setText(ques.getO3());
                q4.setText(ques.getO4());
                a3 = ques.getAns();
                databaseReference.child("users").child(firebaseUser.getUid()).child("keep").setValue(false);
                if (c3 > 10) {
                    databaseReference.child("users").child(firebaseUser.getUid()).child("curscore").setValue(multiplay.sc2);
                    mediaPlayer.stop();
                    startActivity(new Intent(joinque.this, leaderboard.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    @Override
    public void onBackPressed() {
        mediaPlayer.pause();
        startActivity(new Intent(joinque.this,Profile.class));

    }
}