package com.example.learnmagicenchant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.HashMap;

public class readSpell extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TextView tvMeaning;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_spell);
        getSupportActionBar().setTitle("The Power of your Spell~");

        //get the passed intent to read the data
        Intent i = getIntent();

        //read data
        HashMap <String, String> dict = (HashMap<String, String>)i.getSerializableExtra("dictionary");
        String spell = i.getStringExtra("spell");

        //get layout TextView component
        tvMeaning = findViewById(R.id.tvMeaning);

        //check if the inout word is in dictionary. If so, print the value in the TextView
        if (dict.containsKey(spell)){
            tvMeaning.setText("Your Spell can give you "+ dict.get(spell) + " relating powers!");
        }

        //check if input word is empty string, if so, print a message in the TextView
        else if (spell.length()==0){
            tvMeaning.setText("Your Spell is Empty!");
        }
        //otherwise, input word is not found, print a message in the TextView
        else{
            tvMeaning.setText("Couldn't Decipher your Spell!");

        }
    }
}