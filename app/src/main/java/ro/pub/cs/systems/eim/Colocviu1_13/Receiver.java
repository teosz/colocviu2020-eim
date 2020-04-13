package ro.pub.cs.systems.eim.Colocviu1_13;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class Receiver extends BroadcastReceiver {
    Boolean showOnScreen = false;
    @Override
    public void onReceive(Context context, Intent intent) {
        String message = intent.getExtras().getString("message");
        if(showOnScreen == true) {
            Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
            toast.show();
        } else {
            Log.v("DEBUG", message);
        }
    }
}
