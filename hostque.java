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
import java.util.Random;

public class hostque extends AppCompatActivity {
    TextView question;
    Button q1,q2,q3,q4,quit,next;
    TextView score;
    static int a2;
    static  int c2=0;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    MediaPlayer mediaPlayer;
    String fuid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hostque);
        mediaPlayer=MediaPlayer.create(this,R.raw.faded);
        multiplay.sc2=0;
        mediaPlayer.start();
        question=(TextView)findViewById(R.id.question2);
        q1=(Button)findViewById(R.id.q12);
        q2=(Button)findViewById(R.id.q22);
        q3=(Button)findViewById(R.id.q32);
        q4=(Button)findViewById(R.id.q42);
        quit=(Button)findViewById(R.id.quit2);
        next=(Button)findViewById(R.id.next2);
        question.setMovementMethod(new ScrollingMovementMethod());
        score=(TextView) findViewById(R.id.score12);
        fuid=hostuser.hid;
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        c2++;
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
                c2++;
               // databaseReference.child("users").child(firebaseUser.getUid()).child("keep").setValue(true);
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
                startActivity(new Intent(hostque.this,endgame.class));
            }
        });
        q1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(a2==1)
                {
                   databaseReference.child("users").child(firebaseUser.getUid()).child("keep").setValue(true);
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
                if(a2==2)
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
                if(a2==3)
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
                if(a2==4)
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
                Random rand = new Random(System.currentTimeMillis());
                int r = rand.nextInt((int)dataSnapshot.child("quiz").getChildrenCount());
                Que ques = dataSnapshot.child("quiz").child(String.valueOf(r)).getValue(Que.class);
                question.setText(ques.getQue());
                q1.setText(ques.getO1());
                q2.setText(ques.getO2());
                q3.setText(ques.getO3());
                q4.setText(ques.getO4());
                a2 = ques.getAns();
                databaseReference.child("users").child(fuid).child("ran").setValue(r);
                databaseReference.child("users").child(firebaseUser.getUid()).child("keep").setValue(false);
                if (c2 > 10) {
                    databaseReference.child("users").child(firebaseUser.getUid()).child("curscore").setValue(multiplay.sc2);
                    mediaPlayer.stop();
                    startActivity(new Intent(hostque.this, leaderboard.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    @Override
    public void onBackPressed() {
        mediaPlayer.stop();
        startActivity(new Intent(hostque.this,Profile.class));

    }
}