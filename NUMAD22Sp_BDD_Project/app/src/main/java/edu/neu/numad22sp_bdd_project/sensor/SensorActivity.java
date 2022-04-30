package edu.neu.numad22sp_bdd_project.sensor;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import edu.neu.numad22sp_bdd_project.R;
import edu.neu.numad22sp_bdd_project.home.HomeActivity;
import edu.neu.numad22sp_bdd_project.home.MainActivity;

public class SensorActivity extends MainActivity {

    private TextView pressureText;
    private TextView reactionText;
    private Button button;

    private int REQUEST_CODE_LOCATION = 3;
    private String locationText;

    private SensorManager sensorManager;
    private Sensor pressureSensor;
    private Sensor locationSensor;
    private SensorEventListener sensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float[] values = event.values;
            pressureText.setText(locationText + String.format("The pressure is %.3f mbar", values[0]));
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

        // gps service
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location location = getLocation(lm);
        locationText = "The longitude is " + String.valueOf(location.getLongitude()) +
                "\r\n" + "The latitude is " + String.valueOf(location.getLatitude()) + "\r\n";



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

    protected Location getLocation(LocationManager lm) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
//            Log.println(Log.INFO, "GPS Service", "Start to require location permission");
//            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//            startActivityForResult(intent, 0);
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE_LOCATION);
        }

        List<String> providers = lm.getProviders(true);
        Location location = null;
        for (String provider : providers) {
            Location l = lm.getLastKnownLocation(provider);
            if (l == null) {
                continue;
            }
            if (location == null || l.getAccuracy() < location.getAccuracy()) {
                location = l;
            }
        }
        return location;
    }
}
