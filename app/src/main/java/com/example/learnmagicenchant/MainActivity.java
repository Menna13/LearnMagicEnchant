    package com.example.learnmagicenchant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.HashMap;

    public class MainActivity extends AppCompatActivity {
        //dictionary for latin > english word definitions
        HashMap<String, String> dict = new HashMap<>();
        EditText edInput;
        Button btnLook;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //change title bar
        getSupportActionBar().setTitle("The Power of your Spell~");
        setContentView(R.layout.activity_main);

        //get layout components
        edInput = findViewById(R.id.edInput);
        btnLook = findViewById(R.id.btnLook);

        //set click lister for the button to initiate decipher process
        btnLook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //run the function responsible for loading the dictionary
                readDictionary();

            }
        });

    }

    //readDictionary() loads the latin > english dictionary and add each latin word and english definition as key-value pairs in a hashmap
    private void readDictionary() {
        //loading dictionary csv file from resources
        InputStream is = getResources().openRawResource(R.raw.latino);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("windows-1255"))
        );
        String line = "";
        {
            try {
                while(((line = reader.readLine()) != null)){
                    String [] def = line.split("\\$");

                    //adding first index as key (latin word), adding second index as value (english definition)
                    //trim space ending of latin word (decided after debugging)
                    //replace unnecessary symbols (decided after debugging)
                    String definition =  def[1].replaceAll("[\\.\\?\\+\\=\"]","");
                    dict.put(def[0].trim(), definition);
                    System.out.println(def[1]);
                }

                //set up intent to send information to the new activity readSpell (translation screen)
                Intent i = new Intent(this, readSpell.class);

                //send data: dictionary hashmap and the input string for latin word (spell)
                i.putExtra("dictionary", dict);
                i.putExtra("spell", edInput.getText().toString());

                //start the activity with the data
                startActivity(i);

            } catch (IOException e) {
                Log.wtf("myActivity", "Error reading data file" + line, e);
                e.printStackTrace();
            }

        }

    }
}