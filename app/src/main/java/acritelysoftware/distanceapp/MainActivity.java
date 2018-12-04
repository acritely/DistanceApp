package acritelysoftware.distanceapp;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private SensorManager sensorManager;
    private Sensor sensor;
    private Runnable mTimer1;

    private final Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        setContentView(R.layout.activity_main);
    }

    @Override protected void onStart() {
        super.onStart();
        if (null != sensor) {
            sensorManager.registerListener(mSensorEventListener, sensor, SensorManager.SENSOR_DELAY_UI);
        }
    }

    private SensorEventListener mSensorEventListener = new SensorEventListener() {

        @Override public void onSensorChanged(SensorEvent event) {
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < event.values.length; i++) {
                sb.append("values[").append(i).append("] : ").append(event.values[i]).append("\n");//this is the sensor data
            }

            //dataTextView.setText(sb);
            //this is where they sent the sensor values
            EditText accel_x = (EditText) findViewById(R.id.accel_x);
            accel_x.setText(sb.toString());
        }

        @Override public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };

    public void resetClick(View view) {
        EditText distET = (EditText) findViewById(R.id.dist);
        distET.setText("");
    }

    public void measureClick(View view) {

        mTimer1 = new Runnable() {

            @Override
            public void run() {
                double heldTime = 0.0;

                Button measureButton = (Button) findViewById(R.id.measureButton);

                while (measureButton.isPressed()) {
                    heldTime += 1; //ms??
                }

                EditText distET = (EditText) findViewById(R.id.dist);
                distET.setText(String.format("On Time: %s", heldTime));
            }
        };
    }

}
