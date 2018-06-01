package com.example.vardhan.wir;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Methods extends AppCompatActivity {
    Button b1,b2,b3,b4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_methods);
        b1=(Button)findViewById(R.id.qs);
        b2=(Button)findViewById(R.id.m2);
        b3=(Button)findViewById(R.id.my);
        b4=(Button)findViewById(R.id.acr);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Profile.mediaPlayer.stop();
                startActivity(new Intent(Methods.this,startgame.class));
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Profile.mediaPlayer.stop();
                startActivity(new Intent(Methods.this,multiplay.class));
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Profile.mediaPlayer.stop();
                startActivity(new Intent(Methods.this,CodeMulti.class));
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Profile.mediaPlayer.stop();
                startActivity(new Intent(Methods.this,acceptreq.class));

            }
        });
    }
}
