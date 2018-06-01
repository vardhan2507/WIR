package com.example.vardhan.wir;

import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import java.util.Locale;

public class searchpro extends AppCompatActivity {
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    Button se,voice;
    EditText su;
    TextView sfe,she;
    String ssu,ssfe,sshe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchpro);
        firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("users");
        se=(Button)findViewById(R.id.se);
        su=(EditText)findViewById(R.id.searchu);
        sfe=(TextView)findViewById(R.id.sfe);
        she=(TextView)findViewById(R.id.she);
        voice=(Button)findViewById(R.id.voice);
        voice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                // Specify free form input
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Tell the username");
                intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.ENGLISH);
                startActivityForResult(intent, 2);
            }
        });
        se.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(searchpro.this,"bu",Toast.LENGTH_SHORT).show();
                if(TextUtils.isEmpty(su.getText().toString().trim()))
                {
                    Toast.makeText(searchpro.this,"Enter a username",Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                {
                    ssu=su.getText().toString().trim();
                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            //Toast.makeText(searchpro.this,"sus",Toast.LENGTH_SHORT).show();
                            databaseReference.removeEventListener(this);
                            for(DataSnapshot sh:dataSnapshot.getChildren())
                            {
                                if(sh.child("uusername").getValue(String.class).equals(ssu))
                                {
                                    sfe.setText(sh.child("fullname").getValue(String.class));
                                    she.setText(String.valueOf(sh.child("highscore").getValue(Integer.class)));
                                   // Toast.makeText(searchpro.this,"success",Toast.LENGTH_SHORT).show();
                                    return;
                                }
                            }
                            Toast.makeText(searchpro.this,"No such User found",Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });


                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {

        if (requestCode == 2) {
            ArrayList<String> results;
            results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            su.setText(results.get(0));
        }

    }
}
