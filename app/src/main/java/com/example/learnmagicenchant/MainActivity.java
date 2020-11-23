    package com.example.learnmagicenchant;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.HashMap;

    public class MainActivity extends AppCompatActivity {
        HashMap<String, String> dict = new HashMap<String, String>();
        Button btnChant;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnChant = findViewById(R.id.btnChant);
        btnChant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readDictionary();

            }
        });

    }

    private void readDictionary() {
//        System.out.println("got here");
        InputStream is = getResources().openRawResource(R.raw.latino);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8"))
        );

        String line = "";
        int counter = 1;
        {
            try {
                while(((line = reader.readLine()) != null)){
                    //Split by
                    counter++;
                    String [] tokens = line.split(",");
//                    System.out.println("maybe anything?");
                    System.out.println(tokens[counter]);

                }

            } catch (IOException e) {
                Log.wtf("myActivity", "Error reading data file" + line, e);
                e.printStackTrace();
            }

        }

    }
}