package edu.neu.numad22sp_bdd_project.sensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import edu.neu.numad22sp_bdd_project.R;
import edu.neu.numad22sp_bdd_project.home.HomeActivity;
import edu.neu.numad22sp_bdd_project.home.MainActivity;

public class SensorActivity extends MainActivity {

    private TextView pressureText;
    private TextView reactionText;
    private Button button;

    private SensorManager sensorManager;
    private Sensor pressureSensor;
    private SensorEventListener sensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float[] values = event.values;
            pressureText.setText(String.format("The pressure is %.3f mbar", values[0]));
            if (values[0] < 1009.144) {
                reactionText.setText("The pressure is low, take care and try something to get a good mood.");
            } else {
                reactionText.setText("The pressure is good, enjoy your day!");
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = findViewById(R.id.frag_container);
        getLayoutInflater().inflate(R.layout.activity_sensor, contentFrameLayout);

//        setContentView(R.layout.activity_sensor);
        pressureText = findViewById(R.id.sensor_textview1);
        reactionText = findViewById(R.id.sensor_textView2);
//        button = findViewById(R.id.sensor_button);



        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        pressureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(sensorEventListener, pressureSensor, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(sensorEventListener);
    }
}
