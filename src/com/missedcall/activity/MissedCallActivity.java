package com.missedcall.activity;

import com.example.missedcallrecord.R;

import android.app.Activity;
import android.os.Bundle;
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
                
            }
        });
    }
    private String getfilecontent() {
        // TODO Auto-generated method stub
        return null;
    }

}
