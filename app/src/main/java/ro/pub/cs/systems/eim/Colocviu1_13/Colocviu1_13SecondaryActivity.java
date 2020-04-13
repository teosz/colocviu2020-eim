package ro.pub.cs.systems.eim.Colocviu1_13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Colocviu1_13SecondaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colocviu1_13_secondary);
    }

    private void closeWith(String message) {
        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context, String.format("Inchis cu %s",message), Toast.LENGTH_SHORT);
        toast.show();
        finish();
    }
    public void onRegister(View view) {
        closeWith("Register");
    }

    public void onCancel(View view) {
        closeWith("Cancel");
    }

}
