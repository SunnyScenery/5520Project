package edu.neu.numad22sp_bdd_project.breath;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import edu.neu.numad22sp_bdd_project.R;
import edu.neu.numad22sp_bdd_project.home.MainActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BreathExerciseActivity extends MainActivity {

    private int[] timers = new int[4];
    private String[] instructionList = new String[]{"Inhale...", "Hold...", "Exhale..."};
    private int instructionCounter = 0;
    TextView instructionText;
    TextView timerText;
    TextView breathText;
    private ImageView circle_inner;
    private CountDownTimer timer1, timer2, timer3;
    int counter;
    int breathsCounter = 0;
    private ConstraintLayout exerciseLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = findViewById(R.id.frag_container);
        getLayoutInflater().inflate(R.layout.activity_breath_exercise, contentFrameLayout);

        exerciseLayout = findViewById(R.id.exercise_layout);
        //exerciseLayout.getBackground().setAlpha(50);

        circle_inner = findViewById(R.id.circle_inner);

        timers[0] = getIntent().getIntExtra("inhale", 5);
        timers[1] = getIntent().getIntExtra("hold", 5);
        timers[2] = getIntent().getIntExtra("exhale", 5);
        timers[3] = getIntent().getIntExtra("cycles", 2);

        instructionText = findViewById(R.id.instruction);
        timerText = findViewById(R.id.timer);
        breathText = findViewById(R.id.breathNumber);

        //create timer1
        counter=timers[instructionCounter];
        instructionText.setText(instructionList[instructionCounter]);
        breathText.setText("Breath "+(breathsCounter+1)+" out of "+timers[3]);

        AnimationSet as = new AnimationSet(true);


        timer1 = new CountDownTimer(timers[instructionCounter]*1000, 1000) {



            //onTick for timer1
            public void onTick(long millisUntilFinished) {
                System.out.println(counter+" : "+timers[0]);

                if(counter==timers[0]){
                    AlphaAnimation alphaAnimation = new AlphaAnimation(0.5f, 1.0f);
                    alphaAnimation.setDuration(timers[0]*1000);
                    circle_inner.startAnimation(alphaAnimation);

                    ScaleAnimation scaleAnimation = new ScaleAnimation(1f, 3f, 1f, 3f,
                            ScaleAnimation.RELATIVE_TO_SELF, 0.5f,
                            ScaleAnimation.RELATIVE_TO_SELF, 0.5f);
                    scaleAnimation.setDuration(timers[0]*1000);
                    circle_inner.startAnimation(scaleAnimation);
                }

                timerText.setText(String.valueOf(counter));

                int val = (counter*10)/timers[instructionCounter];
                counter--;
                //Log.i("counter", "value is "+counter);
            }

            //onFinish for timer1
            public void onFinish() {

                instructionCounter++;
                counter=timers[instructionCounter];
                instructionText.setText(instructionList[instructionCounter]);

                //create timer2
                timer2 = new CountDownTimer(timers[instructionCounter]*1000, 1000) {

                    //onTick for timer2
                    public void onTick(long millisUntilFinished) {
                        if(counter==timers[1]){
                            AlphaAnimation alphaAnimation = new AlphaAnimation(0.5f, 1.0f);
                            alphaAnimation.setDuration(timers[1]*1000);
                            circle_inner.startAnimation(alphaAnimation);

                            ScaleAnimation scaleAnimation = new ScaleAnimation(3f, 3f, 3f, 3f,
                                    ScaleAnimation.RELATIVE_TO_SELF, 0.5f,
                                    ScaleAnimation.RELATIVE_TO_SELF, 0.5f);
                            scaleAnimation.setDuration(timers[1]*1000);
                            circle_inner.startAnimation(scaleAnimation);
                        }
                        timerText.setText(String.valueOf(counter));
                        counter--;
                    }

                    //onFinish for timer2
                    public void onFinish() {

                        instructionCounter++;
                        counter=timers[instructionCounter];
                        instructionText.setText(instructionList[instructionCounter]);

                        //create timer3
                        timer3 = new CountDownTimer(timers[instructionCounter]*1000, 1000) {

                            //onTick for timer3
                            public void onTick(long millisUntilFinished) {
                                if(counter==timers[2]){
                                    AlphaAnimation alphaAnimation = new AlphaAnimation(0.5f, 1.0f);
                                    alphaAnimation.setDuration(timers[2]*1000);
                                    circle_inner.startAnimation(alphaAnimation);

                                    ScaleAnimation scaleAnimation = new ScaleAnimation(3f, 1f, 3f, 1f,
                                            ScaleAnimation.RELATIVE_TO_SELF, 0.5f,
                                            ScaleAnimation.RELATIVE_TO_SELF, 0.5f);
                                    scaleAnimation.setDuration(timers[2]*1000);
                                    circle_inner.startAnimation(scaleAnimation);
                                }
                                timerText.setText(String.valueOf(counter));
                                counter--;
                            }

                            //onFinish for timer3
                            public  void onFinish(){
                                instructionCounter=0;
                                counter=timers[instructionCounter];
                                breathsCounter++;

                                if (breathsCounter < timers[3]) {
                                    breathText.setText("Breath "+(breathsCounter+1)+" out of "+timers[3]);
                                    instructionText.setText(instructionList[instructionCounter]);
                                    timer1.cancel();
                                    timer1.start();
                                } else if (breathsCounter == timers[3]) {
                                    timerText.setText("");
                                    instructionText.setText("Good job ~");
                                }
                            }
                        };
                        timer3.start();
                    }
                };
                timer2.start();
            }
        };


        timer1.start();
    }


}