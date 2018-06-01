package com.example.vardhan.wir;

import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class ch extends AppCompatActivity {
    EditText et;
    Button b,var;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ch);
        et=(EditText)findViewById(R.id.cp);
        b=(Button)findViewById(R.id.cgo);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et.getText().toString().equals("vardhan"))
                {
                    startActivity(new Intent(ch.this,inputque.class));
                }
                else
                {
                    Toast.makeText(ch.this,"Wrong Password",Toast.LENGTH_SHORT).show();
                    return;
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
            et.setText(results.get(0));
        }

    }
}
