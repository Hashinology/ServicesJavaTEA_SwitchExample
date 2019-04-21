package com.example.hashi.servicesjava;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.util.Random;

public class MyBoundService extends Service {
    private static final String TAG = "MyBoundService";

    IBinder iBinder = new MyBinder ();

    public MyBoundService() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d( TAG,"onCreate" );
    }


    @Override
    public IBinder onBind(Intent intent) {
        Log.d( TAG,"onBind" );
        return iBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d( TAG,"onUnbind" );
        return super.onUnbind( intent );
    }

    @Override
    public void onDestroy() {
        Log.d( TAG,"onDestroy" );
        super.onDestroy();
    }
    public class MyBinder extends Binder{
        public MyBoundService getService() {
            return MyBoundService.this;
        }
    }

    public int getRandomData()
    {
        Random randomGenerator = new Random(  );
        return randomGenerator.nextInt(100);
    }

    public int getRandom2(){
        Random random = new Random();
        int firstNum = random.nextInt(100);
        int secondNum = random.nextInt(100);
        int sum = firstNum + secondNum;
        return sum;

    }
}
