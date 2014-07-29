package com.missedcall.activity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.example.missedcallrecord.R;
import com.missedcall.service.PhoneService;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MissedCallActivity extends Activity{
    private Button button;
    private TextView content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainlayout);
        String s = getfilecontent();
        content = (TextView) findViewById(R.id.content);
        button = (Button) findViewById(R.id.startservice);
        content.setText(s);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent();
                intent.setClass(MissedCallActivity.this, PhoneService.class);
                startService(intent);
                MissedCallActivity.this.finish();
            }
        });
    }
    private String getfilecontent() {
        // TODO Auto-generated method stub
        String filepath = null;
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            filepath = Environment.getExternalStorageDirectory() +"/android/data/"
        +getPackageName()+"/content.txt";
        }else
            filepath = Environment.getDataDirectory().toString() + "/data/"
            +getPackageName()+"/content.txt";
        Log.e("filepath", filepath);
        File file = new File(filepath);
        if(!file.exists()){
            try {
                Log.e("file", "not exists");
                file.createNewFile();
                return null;
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        String content = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String temp;
            while((temp=reader.readLine())!=null){
                    content = content +temp;
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (content == "")
            return "Nothing";
        else
            return content;
    }

}
