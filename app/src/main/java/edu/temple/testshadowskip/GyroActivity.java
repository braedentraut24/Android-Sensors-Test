package edu.temple.testshadowskip;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import java.text.DecimalFormat;

public class GyroActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor gyroscope;
    private TextView xValue;
    private TextView yValue;
    private TextView zValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyro);

        xValue = findViewById(R.id.txt_gyro_x_axis);
        yValue = findViewById(R.id.txt_gyro_y_axis);
        zValue = findViewById(R.id.txt_gyro_z_axis);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (gyroscope != null) {
            sensorManager.registerListener(this, gyroscope, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        float current_xValue = sensorEvent.values[0];
        float current_yValue = sensorEvent.values[1];
        float current_zValue = sensorEvent.values[2];

        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(5);

        xValue.setText("X: " + df.format(current_xValue).toString() + "rad/s");
        yValue.setText("Y: " + df.format(current_yValue).toString() + "rad/s");
        zValue.setText("Z: " + df.format(current_zValue).toString() + "rad/s");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //Nothing goes here in this project
    }

    //stop the sensor when the activity stops to reduce battery usage
    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}