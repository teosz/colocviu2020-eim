package ro.pub.cs.systems.eim.Colocviu1_13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {

    String SOUTH = "SOUTH";
    String NORTH = "NORTH";
    String WEST = "WEST";
    String EAST = "EAST";

    ArrayList<String> directions =  new ArrayList<>();
    Integer noDirections = 0;
    private MyService boundedService;
    private Boolean started = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences mPrefs = getSharedPreferences("dirs", MODE_PRIVATE);
        noDirections = mPrefs.getInt("noDirections", 0);

        IntentFilter filter = new IntentFilter("showMessage");
        this.registerReceiver(new Receiver(), filter);


    }
    @Override
    protected  void onPause() {
        super.onPause();
        SharedPreferences.Editor ed = getSharedPreferences("dirs", MODE_PRIVATE).edit();
        ed.putInt("noDirections", directions.size());
        ed.commit();
    }


    @Override
    protected  void onStop() {
        if(started == true) {
            unbindService(serviceConnection);
        }
        super.onStop();
    }

    public void updateText() {
        if(directions.size() >= 4) {
            if(started == false) {
                Intent intent = new Intent(this, MyService.class);
                bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
            }
        }

        TextView text = findViewById(R.id.textView);
        text.setText(getTextDirs());

    }
    public String getTextDirs() {
        return directions.stream()
                .collect(Collectors.joining(", "));

    }
    public void handleNorth(View view) {
        directions.add(NORTH);
        updateText();
    }

    public void handleSouth(View view) {
        directions.add(SOUTH);
        updateText();
    }
    public void handleWest(View view) {
        directions.add(WEST);
        updateText();
    }
    public void handleEast(View view) {
        directions.add(EAST);
        updateText();
    }

    public void handleSecondActivity(View view) {
        Intent i = new Intent(MainActivity.this, Colocviu1_13SecondaryActivity.class);
        startActivity(i);
        directions = new ArrayList<String>();
        updateText();
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyService.BoundedServiceBinder binder = (MyService.BoundedServiceBinder)service;
            boundedService = binder.getService();
            started = true;
            boundedService.pushMessage(getTextDirs());

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            boundedService = null;
            started = false;
        }
    };


}
