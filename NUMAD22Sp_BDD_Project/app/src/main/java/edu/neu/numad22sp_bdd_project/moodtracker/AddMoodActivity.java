package edu.neu.numad22sp_bdd_project.moodtracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import edu.neu.numad22sp_bdd_project.R;

public class AddMoodActivity extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    String user = FirebaseAuth.getInstance().getUid();
    TextView title;
    TextView subtitle;
    TextView quote;
    TextView selectdate;
    TextView felt;
    TextView activity;
    TextView because;
    TextView descbox;
    ImageView calendar;

    Button happy;
    Button sleepy;
    Button cool;
    Button afraid;
    Button sad;
    Button angry;
    Button normal;
    Button sweat;
    Button worry;
    Button sick;
    Button read;
    Button travel;
    Button family;
    Button work;
    Button food;
    Button addmood;


    int index = MoodTrackerActivity.getmoodid()+1;
    int boolselect = -1;

    String moodtype;
    String acttype;


    private DatePickerDialog.OnDateSetListener dateSetListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mood);


        // get access to all the variables
        title = findViewById(R.id.title);
        subtitle = findViewById(R.id.subtitle);
        quote = findViewById(R.id.titlequote);

        selectdate = findViewById(R.id.selectdate);
        felt = findViewById(R.id.felt);
        activity = findViewById(R.id.activities);
        because = findViewById(R.id.because);
        calendar = findViewById(R.id.calendar);
        descbox = findViewById(R.id.descbox);

        happy = findViewById(R.id.happy);
        sleepy = findViewById(R.id.sleepy);
        cool = findViewById(R.id.cool);
        afraid = findViewById(R.id.afraid);
        sad = findViewById(R.id.sad);
        angry = findViewById(R.id.angry);
        normal = findViewById(R.id.normal);
        sweat = findViewById(R.id.sweat);
        worry = findViewById(R.id.worry);
        sick = findViewById(R.id.sick);

        food = findViewById(R.id.food);
        family = findViewById(R.id.family);
        travel = findViewById(R.id.travel);
        work = findViewById(R.id.work);
        read = findViewById(R.id.read);

        addmood = findViewById(R.id.addmood);


        selectdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog d = new DatePickerDialog(
                        AddMoodActivity.this,
                        //android.R.style.Widget_Holo_ActionBar_Solid,
                        dateSetListener,
                        year, month, day);
                d.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                d.show();

            }
        });


        dateSetListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                selectdate.setText(dayOfMonth + "/" + month + "/" + year);
                Log.d("AddMoodActivity", "date:" + dayOfMonth + "/" + month + "/" + year);
            }
        };


        happy.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                moodtype = "happy";
                Toast.makeText(AddMoodActivity.this, "I feel happy", Toast.LENGTH_SHORT).show();
                if(boolselect == 1){
                    addmood.setEnabled(true);

                }

            }
        });
        sleepy.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                moodtype = "sleepy";
                Toast.makeText(AddMoodActivity.this, "I feel sleepy", Toast.LENGTH_SHORT).show();
                if(boolselect == 1){
                    addmood.setEnabled(true);



                }
            }
        });

        cool.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                moodtype = "cool";
                Toast.makeText(AddMoodActivity.this, "I feel cool", Toast.LENGTH_SHORT).show();
                if(boolselect == 1){
                    addmood.setEnabled(true);

                }
            }
        });
        sick.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                moodtype = "yummy";
                Toast.makeText(AddMoodActivity.this, "I feel fine", Toast.LENGTH_SHORT).show();
                if(boolselect == 1){
                    addmood.setEnabled(true);

                }
            }
        });

        sad.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                moodtype = "sad";
                Toast.makeText(AddMoodActivity.this, "I feel sad", Toast.LENGTH_SHORT).show();
                if(boolselect == 1){
                    addmood.setEnabled(true);

                }
            }
        });

        afraid.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                moodtype = "scared";
                Toast.makeText(AddMoodActivity.this, "I feel scared", Toast.LENGTH_SHORT).show();
                if(boolselect == 1){
                    addmood.setEnabled(true);

                }
            }
        });

        worry.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                moodtype = "worry";
                Toast.makeText(AddMoodActivity.this, "I worry about something", Toast.LENGTH_SHORT).show();
                if(boolselect == 1){
                    addmood.setEnabled(true);

                }
            }
        });

        normal.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                moodtype = "normal";
                Toast.makeText(AddMoodActivity.this, "I feel as normal", Toast.LENGTH_SHORT).show();
                if(boolselect == 1){
                    addmood.setEnabled(true);

                }
            }
        });
        angry.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                moodtype = "angry";
                Toast.makeText(AddMoodActivity.this, "I feel angry", Toast.LENGTH_SHORT).show();
                if(boolselect == 1){
                    addmood.setEnabled(true);

                }
            }
        });
        sweat.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                moodtype = "sweat";
                Toast.makeText(AddMoodActivity.this, "I feel embarrassed", Toast.LENGTH_SHORT).show();
                if(boolselect == 1){
                    addmood.setEnabled(true);

                }
            }
        });


        selectdate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                boolselect =1;
                if(moodtype !=null){
                    addmood.setEnabled(true);

                }

            }
        });

        read.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                acttype = "read";
                Toast.makeText(AddMoodActivity.this, "I was reading", Toast.LENGTH_SHORT).show();
                if(boolselect == 1){
                    addmood.setEnabled(true);

                }
            }
        });

        work.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                acttype = "work";
                Toast.makeText(AddMoodActivity.this, "I was working", Toast.LENGTH_SHORT).show();
                if(boolselect == 1){
                    addmood.setEnabled(true);

                }
            }
        });

        travel.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                acttype = "travel";
                Toast.makeText(AddMoodActivity.this, "I was traveling", Toast.LENGTH_SHORT).show();
                if(boolselect == 1){
                    addmood.setEnabled(true);

                }
            }
        });

        family.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                acttype = "family";
                Toast.makeText(AddMoodActivity.this, "I was spending time with my family", Toast.LENGTH_SHORT).show();
                if(boolselect == 1){
                    addmood.setEnabled(true);

                }
            }
        });

        food.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                acttype = "food";
                Toast.makeText(AddMoodActivity.this, "I was tasting food", Toast.LENGTH_SHORT).show();
                if(boolselect == 1){
                    addmood.setEnabled(true);

                }
            }
        });

        addmood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = v.getId();
                if (i == R.id.addmood) {
                    createMood(user, selectdate.getText().toString(), descbox.getText().toString(), moodtype, acttype, index);
                    index++;

                    Intent j = new Intent(getApplicationContext(),MoodTrackerActivity.class);
                    startActivity(j);
                }
            }
        });



    }


    public void createMood(String UID, String date, String desc, String mood, String act, final Integer moodnum) {
        Map<String, Object> user = new HashMap<>();
        user.put("id", moodnum);
        user.put("date", date);
        user.put("description", desc);
        user.put("moodtype", mood);
        user.put("acttype", act);


        db.collection("users").document(UID).collection("moodlog").document().set(user).addOnSuccessListener(this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.w("SUCCESS", "DocumentSnapshot added with ID:");
            }
        }).addOnFailureListener(this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w("AddingMood Failed", "Error adding document", e);
            }
        });
    }
}
