package com.example.vardhan.wir;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class inputque extends AppCompatActivity {
    EditText que,op1,op2,op3,op4,ans;
    Button submit;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inputque);
        que=(EditText) findViewById(R.id.que);
        op1=(EditText) findViewById(R.id.op1);
        op2=(EditText) findViewById(R.id.op2);
        op3=(EditText) findViewById(R.id.op3);
        op4=(EditText) findViewById(R.id.op4);
        ans=(EditText) findViewById(R.id.ans);
        submit=(Button)findViewById(R.id.submit1);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String qe=que.getText().toString();
                final String o1=op1.getText().toString();
                final String o2=op2.getText().toString();
                final String o3=op3.getText().toString();
                final String o4=op4.getText().toString();
                final String a=ans.getText().toString();
                if(TextUtils.isEmpty(qe) || TextUtils.isEmpty(o1) || TextUtils.isEmpty(o2) || TextUtils.isEmpty(o3) || TextUtils.isEmpty(o4)|| TextUtils.isEmpty(a))
                {
                    Toast.makeText(inputque.this,"Please fill all the fields",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    firebaseDatabase=FirebaseDatabase.getInstance();
                    myRef=firebaseDatabase.getReference("quiz");
                    String i=String.valueOf(startgame.j);
                    (startgame.j)++;
                    Que g=new Que(qe,o1,o2,o3,o4,i,Integer.parseInt(a));
                    myRef.child(i).setValue(g).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            startActivity(new Intent(inputque.this,Profile.class));
                        }
                    });
                }
            }
        });
    }
}
