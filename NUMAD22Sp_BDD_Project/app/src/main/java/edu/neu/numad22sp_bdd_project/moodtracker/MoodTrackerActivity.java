package edu.neu.numad22sp_bdd_project.moodtracker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.util.Log;
import android.view.LayoutInflater;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;


import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;


import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;

import edu.neu.numad22sp_bdd_project.R;
import edu.neu.numad22sp_bdd_project.home.HomeActivity;
import edu.neu.numad22sp_bdd_project.home.MainActivity;


public class MoodTrackerActivity extends MainActivity {

    private static int curmoodid;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    TextView subtitle;
    TextView quote;
    ListView listView;
    Button addmood;
    ArrayList<String> date = new ArrayList<>();
    ArrayList<String> desc = new ArrayList<>();
    ArrayList<String> mood = new ArrayList<>();
    ArrayList<String> act = new ArrayList<>();
    ArrayList<Integer> moodid = new ArrayList<>();



    public static int getmoodid(){
        return curmoodid;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = findViewById(R.id.frag_container);
        getLayoutInflater().inflate(R.layout.activity_mood_tracker, contentFrameLayout);


        // get access to all the variables
        subtitle = findViewById(R.id.subtitle);
        quote = findViewById(R.id.titlequote);

        listView = findViewById(R.id.moodlist);
        addmood = findViewById(R.id.addmood);

        //make sure to step up for current user
        String user = FirebaseAuth.getInstance().getUid();



        addmood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent i = new Intent(getApplicationContext(),AddMoodActivity.class);
                startActivity(i);
            }
        });

        db.collection("users").document(user).collection("moodlog").orderBy("id", Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {

            @Override
            public void onEvent(@Nullable QuerySnapshot docsnapshot,
                                @Nullable FirebaseFirestoreException e) {
                date.clear();

                for (DocumentSnapshot snapshot : docsnapshot) {

                    if (e != null) {
                        Log.w("Listen failed.", e);
                        return;
                    }

                    if (snapshot != null && snapshot.exists()) {
                        date.add(snapshot.getString("date"));
                        desc.add(snapshot.getString("description"));
                        mood.add(snapshot.getString("moodtype"));
                        act.add(snapshot.getString("acttype"));
                        moodid.add(Integer.valueOf(snapshot.get("id").toString()));
                        Log.d("", "Current data: " + snapshot.getData());

                    } else {
                        Log.d("", "Current data: null");
                    }

                }
                if(moodid.size()!=0) {
                    curmoodid = moodid.get(0);
                }
                else{
                    curmoodid =0;
                }

                Adapter adapter = new Adapter(getApplicationContext(), date, desc, mood, act);
                adapter.notifyDataSetChanged();
                listView.setAdapter(adapter);
            }

        });



    }

    class Adapter extends ArrayAdapter<String> {

        ArrayList<String> date;
        ArrayList<String> desc;
        ArrayList<String> mood;
        ArrayList<String> act;
        Context context;

        Adapter(Context c, ArrayList<String> date, ArrayList<String> desc, ArrayList<String> mood, ArrayList<String> act) {
            super(c, R.layout.activity_mood_item, R.id.date, date);
            this.context = c;
            this.date = date;
            this.desc = desc;
            this.mood = mood;
            this.act = act;
        }


        @NonNull
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layout = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layout.inflate(R.layout.activity_mood_item, parent, false);
            TextView vdate = row.findViewById(R.id.date);
            TextView vdesc = row.findViewById(R.id.description);
            ImageView vmood = row.findViewById(R.id.emoji);
            ImageView vact = row.findViewById(R.id.activity);

            vdate.setText(date.get(position));
            vdesc.setText(desc.get(position));

            if(mood.get(position) != null) {

                if (mood.get(position).equalsIgnoreCase("sleepy")) {
                    vmood.setImageResource(R.drawable.sleepy);
                }
                if (mood.get(position).equalsIgnoreCase("cool")) {
                    vmood.setImageResource(R.drawable.cool);
                }
                if (mood.get(position).equalsIgnoreCase("afraid")) {
                    vmood.setImageResource(R.drawable.afraid);
                }
                if (mood.get(position).equalsIgnoreCase("sad")) {
                    vmood.setImageResource(R.drawable.sad);
                }
                if (mood.get(position).equalsIgnoreCase("happy")) {
                    vmood.setImageResource(R.drawable.happy);
                }
                if (mood.get(position).equalsIgnoreCase("angry")) {
                    vmood.setImageResource(R.drawable.angry);
                }
                if (mood.get(position).equalsIgnoreCase("normal")) {
                    vmood.setImageResource(R.drawable.normal);
                }
                if (mood.get(position).equalsIgnoreCase("sweat")) {
                    vmood.setImageResource(R.drawable.sweat2);
                }
                if (mood.get(position).equalsIgnoreCase("worry")) {
                    vmood.setImageResource(R.drawable.tired);
                }
                if (mood.get(position).equalsIgnoreCase("sick")) {
                    vmood.setImageResource(R.drawable.sick);
                }
            }
            if(act.get(position) != null) {
                if (act.get(position).equalsIgnoreCase("read")) {
                    vact.setImageResource(R.drawable.reading);
                }
                if (act.get(position).equalsIgnoreCase("family")) {
                    vact.setImageResource(R.drawable.family);
                }
                if (act.get(position).equalsIgnoreCase("travel")) {
                    vact.setImageResource(R.drawable.travel);
                }
                if (act.get(position).equalsIgnoreCase("work")) {
                    vact.setImageResource(R.drawable.work);
                }
                if (act.get(position).equalsIgnoreCase("food")) {
                    vact.setImageResource(R.drawable.food);
                }
            }




            return row;
        }
    }
}