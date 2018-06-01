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
import java.util.StringTokenizer;

public class multicque extends AppCompatActivity {

    TextView question;
    Button q1,q2,q3,q4,quit,next;
    TextView score;
    static int sc=0,a;
    static  int c=0;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multicque);
        mediaPlayer=MediaPlayer.create(this,R.raw.faded);
        mediaPlayer.start();
        sc=0;
        question=(TextView)findViewById(R.id.question5);
        q1=(Button)findViewById(R.id.q15);
        q2=(Button)findViewById(R.id.q25);
        q3=(Button)findViewById(R.id.q35);
        q4=(Button)findViewById(R.id.q45);
        quit=(Button)findViewById(R.id.quit5);
        next=(Button)findViewById(R.id.next5);
        question.setMovementMethod(new ScrollingMovementMethod());
        score=(TextView) findViewById(R.id.score15);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        c++;
        change();
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c++;
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
                startActivity(new Intent(multicque.this,multicscore.class));
            }
        });
        q1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(a==1)
                {
                    v.setBackgroundColor(Color.GREEN);
                    sc++;
                    String s=String.valueOf(sc);
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
                if(a==2)
                {
                    v.setBackgroundColor(Color.GREEN);
                    sc++;
                    String s=String.valueOf(sc);
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
                if(a==3)
                {
                    v.setBackgroundColor(Color.GREEN);
                    sc++;
                    String s=String.valueOf(sc);
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
                if(a==4)
                {
                    v.setBackgroundColor(Color.GREEN);
                    sc++;
                    String s=String.valueOf(sc);
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
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                databaseReference.removeEventListener(this);
                Que ques = dataSnapshot.child("codeplay").child(CodeMulti.s).child("ques").child(String.valueOf(c-1)).getValue(Que.class);
                question.setText(ques.getQue());
                q1.setText(ques.getO1());
                q2.setText(ques.getO2());
                q3.setText(ques.getO3());
                q4.setText(ques.getO4());
                a = ques.getAns();
                if (c >=10) {
                    mediaPlayer.stop();
                    startActivity(new Intent(multicque.this, multicscore.class));
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
        startActivity(new Intent(multicque.this,Profile.class));

    }
}
