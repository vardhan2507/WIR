package com.example.vardhan.wir;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
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

import static com.example.vardhan.wir.R.id.wu;

public class Profile extends AppCompatActivity {

    Button startgame,options,instructions,logout,admin,sear;
    ProgressDialog progressDialog;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    DatabaseReference databaseReference;
    TextView textView;
    static MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mediaPlayer=MediaPlayer.create(this,R.raw.epic);
        mediaPlayer.start();
        setContentView(R.layout.activity_profile);
        progressDialog=new ProgressDialog(this);
        startgame=(Button)findViewById(R.id.startgame);
        options=(Button)findViewById(R.id.options);
        instructions=(Button)findViewById(R.id.instructions);
        logout=(Button)findViewById(R.id.logout);
        admin=(Button)findViewById(R.id.admin);
        sear=(Button)findViewById(R.id.sear);
        textView=(TextView)findViewById(wu);
        databaseReference= FirebaseDatabase.getInstance().getReference("users");
        databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).removeEventListener(this);
                textView.setText(dataSnapshot.child("uusername").getValue(String.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    mediaPlayer.stop();
                    startActivity(new Intent(Profile.this,MainActivity.class));
                }
            }
        };
        sear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profile.this,searchpro.class));
            }
        });
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profile.this,ch.class));
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                progressDialog.setMessage("Signing out...");
                progressDialog.show();
                    mAuth.signOut();
                progressDialog.dismiss();
                startActivity(new Intent(Profile.this, MainActivity.class));
            }
        });
        startgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profile.this,Methods.class));
            }
        });

        options.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profile.this,options.class));
            }
        });

        instructions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profile.this,inst.class));
            }
        });


    }
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}