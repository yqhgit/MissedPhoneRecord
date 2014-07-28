package com.missedcall.service;
import java.util.Date;

import android.app.Service;
import android.content.Intent;
import android.os.Environment;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;


public class PhoneService extends Service{

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
        PhoneStateListener listener = new PhoneStateListener(){

            @Override
            public void onCallStateChanged(int state, String incomingNumber) {
                // TODO Auto-generated method stub
                boolean flag = false;
                Date from = null, end = null;
                switch (state) {
                case TelephonyManager.CALL_STATE_IDLE:
                    if(flag){
                        end = new Date();
                        insertinfo(from, end, incomingNumber);
                        }
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK:
                    flag = false;
                    break;
                case TelephonyManager.CALL_STATE_RINGING:
                    from = new Date();
                    flag = true;
                    break;
                default:
                    break;
                }
                super.onCallStateChanged(state, incomingNumber);
            }
        };
    }

    protected void insertinfo(Date from, Date end, String number) {
        // TODO Auto-generated method stub
        String filepath;
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            filepath = Environment.getExternalStorageDirectory() +"/android/data"
        +getPackageName()+"/content.text";
        }
    }
    
}
