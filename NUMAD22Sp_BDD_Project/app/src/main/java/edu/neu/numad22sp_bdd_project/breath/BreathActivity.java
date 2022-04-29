package edu.neu.numad22sp_bdd_project.breath;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import org.angmarch.views.NiceSpinner;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import edu.neu.numad22sp_bdd_project.R;
import edu.neu.numad22sp_bdd_project.home.MainActivity;

public class BreathActivity extends MainActivity {

    private Button btn_start;
    private NiceSpinner inhale;
    private NiceSpinner holding;
    private NiceSpinner exhale;
    private NiceSpinner cycles;
    private LinearLayout breathLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = findViewById(R.id.frag_container);
        getLayoutInflater().inflate(R.layout.activity_breath, contentFrameLayout);

        breathLayout = findViewById(R.id.breath_layout);
        //breathLayout.getBackground().setAlpha(50);

        //inhale
        inhale = findViewById(R.id.inhale);
        List<String> inhaleList = new LinkedList<>(Arrays.asList("2", "3", "4", "5", "6", "7", "8", "9", "10"));
        inhale.attachDataSource(inhaleList);

        //holding
        holding = findViewById(R.id.hold);
        List<String> holdList = new LinkedList<>(Arrays.asList("2", "3", "4", "5", "6", "7", "8", "9", "10"));
        holding.attachDataSource(holdList);

        //exhale
        exhale = findViewById(R.id.exhale);
        List<String> exhaleList = new LinkedList<>(Arrays.asList("2", "3", "4", "5", "6", "7", "8", "9", "10"));
        exhale.attachDataSource(exhaleList);

        //cycles
        cycles = findViewById(R.id.breaths);
        List<String> breathsList = new LinkedList<>(Arrays.asList("2", "3", "4", "5"));
        cycles.attachDataSource(breathsList);

        btn_start = findViewById(R.id.btn_start);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startBreathingExerciseActivity();
            }
        });

    }

    private void startBreathingExerciseActivity(){
        Intent intent = new Intent(this, BreathExerciseActivity.class);
        intent.putExtra("inhale", Integer.parseInt(inhale.getSelectedItem().toString()));
        intent.putExtra("hold", Integer.parseInt(holding.getSelectedItem().toString()));
        intent.putExtra("exhale", Integer.parseInt(exhale.getSelectedItem().toString()));
        intent.putExtra("breaths", Integer.parseInt(cycles.getSelectedItem().toString()));
        startActivity(intent);
        finish();

    }


}