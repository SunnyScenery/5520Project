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

import edu.neu.numad22sp_bdd_project.R;
import edu.neu.numad22sp_bdd_project.home.MainActivity;

public class BreathActivity extends MainActivity {

    private Button btn_start;
    private Spinner inhale;
    private Spinner holding;
    private Spinner exhale;
    private Spinner cycles;
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
        String[] inhaleList = new String[]{"2", "3", "4", "5", "6", "7", "8", "9", "10"};
        ArrayAdapter<String> inhaleAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, inhaleList);
        inhale.setAdapter(inhaleAdapter);

        //holding
        holding = findViewById(R.id.hold);
        String[] holdList = new String[]{"2", "3", "4", "5", "6", "7", "8", "9", "10"};
        ArrayAdapter<String> holdAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, holdList);
        holding.setAdapter(holdAdapter);

        //exhale
        exhale = findViewById(R.id.exhale);
        String[] exhaleList = new String[]{"2", "3", "4", "5", "6", "7", "8", "9", "10"};
        ArrayAdapter<String> exhaleAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, exhaleList);
        exhale.setAdapter(exhaleAdapter);

        //cycles
        cycles = findViewById(R.id.breaths);
        String[] breathsList = new String[]{"2", "3", "4", "5"};
        ArrayAdapter<String> cyclesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, breathsList);
        cycles.setAdapter(cyclesAdapter);

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