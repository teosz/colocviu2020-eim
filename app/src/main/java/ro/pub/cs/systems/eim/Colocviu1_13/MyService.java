package ro.pub.cs.systems.eim.Colocviu1_13;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyService extends Service {
    public MyService() {
    }

    final private IBinder boundedServiceBinder = new BoundedServiceBinder();
    public class BoundedServiceBinder extends Binder {
        public MyService getService() {
            return MyService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return boundedServiceBinder;
    }

    public void pushMessage(String dirs) {
        Log.v("Debug", "pushMessage");
        final String message = String.format("Notificare la %s cu %s",
                new SimpleDateFormat("dd HH").format(new Date()),
                dirs);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                Intent i = new Intent();
                i.setAction("showMessage");
                i.putExtra("message", message);
                sendBroadcast(i);
            }
        }, 5000);

    }
}
