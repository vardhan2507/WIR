package com.example.vardhan.wir;

import android.app.ProgressDialog;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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
import java.util.Locale;

public class CheckUser extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;
    static EditText username,fullname;
    static Button submit,gv;
    static String uname;
    static EditText fname;
    static String fn;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_user);
        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("users");
        progressDialog=new ProgressDialog(CheckUser.this);
        submit=(Button)findViewById(R.id.submit);
        fname=(EditText)findViewById(R.id.FullName);
        gv=(Button)findViewById(R.id.gv);
        username=(EditText)findViewById(R.id.username1);
        gv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                // Specify free form input
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Tell your fullname");
                intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.ENGLISH);
                startActivityForResult(intent, 2);
            }
        });
        progressDialog.setMessage("Checking your identity...Hold on!!");
        submit.setEnabled(false);
        progressDialog.show();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild(firebaseUser.getUid()))
                {
                    progressDialog.dismiss();
                    databaseReference.removeEventListener(this);
                    startActivity(new Intent(CheckUser.this,Profile.class));
                }
                else
                {
                    databaseReference.removeEventListener(this);
                    progressDialog.dismiss();
                    submit.setEnabled(true);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uname=username.getText().toString();
                fn=fname.getText().toString();
                if(TextUtils.isEmpty(uname))
                {
                    Toast.makeText(CheckUser.this,"Enter a unique username",Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                {
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            int flag=0;
                            for(DataSnapshot us:dataSnapshot.getChildren())
                            {
                                databaseReference.removeEventListener(this);
                                User user=us.getValue(User.class);
                                if(user.getUusername().equals(uname))
                                {
                                    flag=1;
                                    break;
                                }
                            }
                            if(flag==1)
                            {
                                Toast.makeText(CheckUser.this,"Username alredy exists!",Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                User user=new User(firebaseUser.getUid(),uname,fn,0,null,null,null,0,false,0);
                                databaseReference.child(firebaseUser.getUid()).setValue(user);
                                startActivity(new Intent(CheckUser.this,Profile.class));
                            }
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
            fname.setText(results.get(0));
        }

    }
}
