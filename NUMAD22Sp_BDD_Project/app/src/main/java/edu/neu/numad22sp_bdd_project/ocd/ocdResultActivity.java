package edu.neu.numad22sp_bdd_project.ocd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import edu.neu.numad22sp_bdd_project.R;
import edu.neu.numad22sp_bdd_project.home.HomeActivity;

public class ocdResultActivity extends HomeActivity {

    private TextView mResult;
    private Button mRetry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = findViewById(R.id.frag_container);
        getLayoutInflater().inflate(R.layout.activity_ocd_result, contentFrameLayout);

        mResult = (TextView) findViewById(R.id.results);
        mRetry = (Button) findViewById(R.id.redo);

        Bundle b = getIntent().getExtras();
        int points = b.getInt("points");

        if (points >= 0 && points < 21){
            mResult.setText("Your symptoms are not suggestive of OCD");
        }
        if (points >= 21){
            mResult.setText("Your symptoms are suggestive of OCD");
        }

        mRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ocdActivity.class);
                startActivity(i);
            }
        });
    }
}
