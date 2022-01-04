package com.example.customview;

import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.customview.databinding.ActivityMainBinding;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding mActivityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mActivityMainBinding.getRoot());

        mActivityMainBinding.progressBar.setMaxValue((float)30);
        mActivityMainBinding.progressBar.setValue((float)21.4);
    }

    public void onClick(View v){
        SeekBar seekBar = findViewById(R.id.seekBar);
        mActivityMainBinding.progressBar.setValue((float)seekBar.getProgress()/10);
    }
}
