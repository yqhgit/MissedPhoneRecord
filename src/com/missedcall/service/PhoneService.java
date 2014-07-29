package com.missedcall.service;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;


public class PhoneService extends Service{
    private boolean flag = false;
    Date from = null, end = null;
    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        // TODO Auto-generated method stub
        super.onStart(intent, startId);
        TelephonyManager manager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        PhoneStateListener listener = new PhoneStateListener(){
            @Override
            public void onCallStateChanged(int state, String incomingNumber) {
                // TODO Auto-generated method stub
                switch (state) {
                case TelephonyManager.CALL_STATE_IDLE:
                    Log.e("filepath", "idle1");
                    if(flag){
                        Log.e("filepath", "idle");
                        end = new Date();
                        insertinfo(from, end, incomingNumber);
                        }
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK:
                    Log.e("filepath", "offhook");
                    flag = false;
                    break;
                case TelephonyManager.CALL_STATE_RINGING:
                    Log.e("filepath", "ringing");
                    from = new Date();
                    flag = true;
                    break;
                default:
                    break;
                }
                super.onCallStateChanged(state, incomingNumber);
            }
        };
        manager.listen(listener, PhoneStateListener.LISTEN_CALL_STATE);
    }

    protected void insertinfo(Date from, Date end, String number) {
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
                file.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        int second = end.getSeconds()-from.getSeconds()+60*(end.getMinutes()-from.getMinutes());
        try {
            FileWriter writer = new FileWriter(file);
            writer.append("\tFrom:"+number+"\tDate_from:"+from+"\tDate_end:"+end+"\n"+"\tDuring:"+second+"s\n");
            Log.e("file", "write");
            writer.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
}
