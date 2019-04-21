package com.example.hashi.servicesjava;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivityTag";
    private MyBoundService myBoundService;
    private Boolean isBound = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        Log.d(TAG,"onCreate");
    }


    public void startService(View view) {

        Intent intent = new Intent( this, MyIntentService.class );
        Intent boundIntent = new Intent( this,MyBoundService.class );
        Intent normalIntent = new Intent( this,MyNormalService.class );

        switch (view.getId()){
            case R.id.btnStartIntentService:
                intent.putExtra( "data","This is an intent service repo" );
                intent.setAction( "getRepo" );
                startService( intent );
                break;

            case R.id.btnBindService:
                bindService( boundIntent, serviceConnection, Context.BIND_AUTO_CREATE );
                break;
            case R.id.btnUnBindService:
                if (isBound){
                    unbindService( serviceConnection );
                    isBound=false;
                }
                break;
            case R.id.btnStartNormalService:
                normalIntent.putExtra("data", "this is a normal service");
                //normalIntent.setAction("normalAction");
                startService(normalIntent);
                break;

        }
    }

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MyBoundService.MyBinder myBinder = (MyBoundService.MyBinder) iBinder;
            myBoundService = myBinder.getService();
            Log.d( TAG,"onServiceConnected:" + myBoundService.getRandomData());

            Toast.makeText(MainActivity.this, String.valueOf(myBoundService.getRandom2()), Toast.LENGTH_LONG).show();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d( TAG,"onServiceDisconected: " );
            isBound = false;
        }
    };
}
