package ro.pub.cs.systems.eim.Colocviu1_13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences mPrefs = getSharedPreferences("dirs", MODE_PRIVATE);
        noDirections = mPrefs.getInt("noDirections", 0);


    }
    @Override
    protected  void onPause() {
        super.onPause();
        SharedPreferences.Editor ed = getSharedPreferences("dirs", MODE_PRIVATE).edit();
        ed.putInt("noDirections", directions.size());
        ed.commit();
    }



    public void updateText() {
        TextView text = findViewById(R.id.textView);
        text.setText(directions.stream()
                .collect(Collectors.joining(", ")));

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

}
