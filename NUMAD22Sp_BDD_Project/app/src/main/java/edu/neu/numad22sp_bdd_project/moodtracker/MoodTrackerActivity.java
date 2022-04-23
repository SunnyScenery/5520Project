package edu.neu.numad22sp_bdd_project.moodtracker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import edu.neu.numad22sp_bdd_project.R;

public class MoodTrackerActivity extends AppCompatActivity {
    private static int current;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    TextView title;
    TextView subTitle;
    TextView quote;
    ListView list;
    Button plus;
    ArrayList<String> date = new ArrayList<>();
    ArrayList<Integer> index = new ArrayList<>();
    ArrayList<String> description = new ArrayList<>();
    ArrayList<String> mood = new ArrayList<>();

    public static int getCurrentMood() {
        return current;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout frameLayout = findViewById(R.id.frag_container);
        getLayoutInflater().inflate(R.layout.activity_mood_tracker, frameLayout);

        title = findViewById(R.id.title);
        subTitle = findViewById(R.id.subtitle);
        quote = findViewById(R.id.titlequote);
        list = findViewById(R.id.moodlist);
        plus = findViewById(R.id.addmood);

        String user = FirebaseAuth.getInstance().getUid();

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), AddMoodActivity.class);
                startActivity(i);
            }
        });

        db.collection("users").document(user).collection("moodlog").orderBy("id", Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                date.clear();
                for(DocumentSnapshot snapshot: queryDocumentSnapshots) {
                    if(e != null) {
                        Log.w("listen failed.", e);
                        return;
                    }

                    if(snapshot != null && snapshot.exists()) {
                        date.add(snapshot.getString("date"));
                        description.add(snapshot.getString("description"));
                        mood.add(snapshot.getString("moodType"));
                        index.add(Integer.valueOf(snapshot.get("id").toString()));
                        Log.d("", "Current date: " + snapshot.getData());
                    } else {
                        Log.d("", "Current date: null");
                    }
                }
                if(index.size() != 0) {
                    current = index.get(0);
                } else {
                    current = 0;
                }

                Adapter adapter = new Adapter(getApplicationContext(), date, description, mood);
                   adapter.notifyDataSetChanged();
                   list.setAdapter(adapter);
                }

        });

    }

    class Adapter extends ArrayAdapter<String> {
        ArrayList<String> date;
        ArrayList<String> description;
        ArrayList<String> mood;
        Context context;

        Adapter(Context c, ArrayList<String> date, ArrayList<String> description, ArrayList<String> mood) {
            super(c, R.layout.activity_mood_item, R.id.date, date);
            this.context = c;
            this.date = date;
            this.description = description;
            this.mood = mood;
        }

        @NonNull
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.activity_mood_item, parent, false);
            TextView vdate = row.findViewById(R.id.date);
            TextView vdescription = row.findViewById(R.id.description);
            ImageView vmood = row.findViewById(R.id.emoji);

            vdate.setText(date.get(position));
            vdescription.setText(description.get(position));

            if(mood.get(position) != null) {
                if(mood.get(position).equalsIgnoreCase("sad")) {
                    vmood.setImageResource(R.drawable.sad);
                }
                if(mood.get(position).equalsIgnoreCase("afraid")) {
                    vmood.setImageResource(R.drawable.afraid);
                }
                if(mood.get(position).equalsIgnoreCase("cool")) {
                    vmood.setImageResource(R.drawable.cool);
                }
                if(mood.get(position).equalsIgnoreCase("cry")) {
                    vmood.setImageResource(R.drawable.cry);
                }
                if(mood.get(position).equalsIgnoreCase("happy")) {
                    vmood.setImageResource(R.drawable.happy);
                }
                if(mood.get(position).equalsIgnoreCase("kiss")) {
                    vmood.setImageResource(R.drawable.kiss);
                }
                if(mood.get(position).equalsIgnoreCase("silence")) {
                    vmood.setImageResource(R.drawable.silence);
                }
                if(mood.get(position).equalsIgnoreCase("sweat")) {
                    vmood.setImageResource(R.drawable.sweat);
                }
                if(mood.get(position).equalsIgnoreCase("worry")) {
                    vmood.setImageResource(R.drawable.sad);
                }
                if(mood.get(position).equalsIgnoreCase("yummy")) {
                    vmood.setImageResource(R.drawable.afraid);
                }
            }
            return row;
        }
    }
}