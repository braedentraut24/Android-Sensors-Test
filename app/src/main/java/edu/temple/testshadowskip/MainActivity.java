package edu.temple.testshadowskip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SensorManager sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);

        Button gyroscopeButton = findViewById(R.id.btn_gyroscope);
        Button orientationButton = findViewById(R.id.btn_orientation);
        Button proximityButton = findViewById(R.id.btn_proximity);

        if(sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE) != null) {
            gyroscopeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent gyroIntent = new Intent(MainActivity.this, GyroActivity.class);
                    startActivity(gyroIntent);
                }
            });
        }

        if(sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null && sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD) != null){
            orientationButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent orientationIntent = new Intent(MainActivity.this, OrientationActivity.class);
                    startActivity(orientationIntent);
                }
            });
        }

        if(sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY) != null) {
            proximityButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent proximityIntent = new Intent(MainActivity.this, ProximityActivity.class);
                    startActivity(proximityIntent);
                }
            });
        }


    }
}