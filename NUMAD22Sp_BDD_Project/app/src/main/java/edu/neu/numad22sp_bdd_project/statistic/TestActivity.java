package edu.neu.numad22sp_bdd_project.statistic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import edu.neu.numad22sp_bdd_project.R;

public class TestActivity extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static ArrayList<Type> mArrayList = new ArrayList<>();
    private String user;
    private ColumnBarChartView mColumnBarChartView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        user = FirebaseAuth.getInstance().getUid();
        int[] count = getListItems();
        System.out.println(count[8] + " " + count[4]);
        Log.d("TAG", "onCreate: LIST IN ONCREATE = " + mArrayList);
        //drawBarChart(count);
    }

    private int[] getListItems() {
        int[] count = new int[10];
        db.collection("users").document(user).collection("moodlog").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {

                    @Override
                    public void onSuccess(QuerySnapshot documentSnapshots) {
                        if (documentSnapshots.isEmpty()) {
                            Log.d("TAG", "onSuccess: LIST EMPTY");
                            return;
                        } else {
                            // Convert the whole Query Snapshot to a list
                            // of objects directly! No need to fetch each
                            // document.
                            for(DocumentSnapshot snapshot : documentSnapshots) {
                                String result = snapshot.getString("moodtype");
                                if (result.equals("happy")) {
                                    count[0]++;
                                }
                                if (result.equals("sleepy")) {
                                    count[1]++;
                                }
                                if (result.equals("cool")) {
                                    count[2]++;
                                }
                                if (result.equals("afraid")) {
                                    count[3]++;
                                }
                                if (result.equals("sad")) {
                                    count[4]++;
                                }
                                if (result.equals("angry")) {
                                    count[5]++;
                                }
                                if (result.equals("normal")) {
                                    count[6]++;
                                }
                                if (result.equals("sweat")) {
                                    count[7]++;
                                }
                                if (result.equals("worry")) {
                                    count[8]++;
                                }
                                if (result.equals("sick")) {
                                    count[9]++;
                                }
                            }
                            mColumnBarChartView = findViewById(R.id.columnBarChartView);
                            mColumnBarChartView.addColumnData(new ColumnBarChartView.ColumnDataFrom("happy", count[0], Color.RED));
                            mColumnBarChartView.addColumnData(new ColumnBarChartView.ColumnDataFrom("sleepy", count[1], getRandColorCode()));
                            mColumnBarChartView.addColumnData(new ColumnBarChartView.ColumnDataFrom("cool", count[2], getRandColorCode()));
                            mColumnBarChartView.addColumnData(new ColumnBarChartView.ColumnDataFrom("afraid", count[3], getRandColorCode()));
                            mColumnBarChartView.addColumnData(new ColumnBarChartView.ColumnDataFrom("sad", count[4], getRandColorCode()));
                            mColumnBarChartView.addColumnData(new ColumnBarChartView.ColumnDataFrom("angry", count[5], getRandColorCode()));
                            mColumnBarChartView.addColumnData(new ColumnBarChartView.ColumnDataFrom("normal", count[6], getRandColorCode()));
                            mColumnBarChartView.addColumnData(new ColumnBarChartView.ColumnDataFrom("sweat", count[7], getRandColorCode()));
                            mColumnBarChartView.addColumnData(new ColumnBarChartView.ColumnDataFrom("worry", count[8], getRandColorCode()));
                            mColumnBarChartView.addColumnData(new ColumnBarChartView.ColumnDataFrom("sick", count[9], getRandColorCode()));
                            // Add all to your list
                            Log.d("TAG", "onSuccess: " + mArrayList);
                        }
                    }
    });
        return count;
    }

    private void drawBarChart(int[] count) {
        mColumnBarChartView = findViewById(R.id.columnBarChartView);
        //int result = count[0];
        //t.setText(result);
        mColumnBarChartView.addColumnData(new ColumnBarChartView.ColumnDataFrom("happy", count[0], getRandColorCode()));
        mColumnBarChartView.addColumnData(new ColumnBarChartView.ColumnDataFrom("sleepy", count[1], getRandColorCode()));
        mColumnBarChartView.addColumnData(new ColumnBarChartView.ColumnDataFrom("cool", count[2], getRandColorCode()));
        mColumnBarChartView.addColumnData(new ColumnBarChartView.ColumnDataFrom("afraid", count[3], getRandColorCode()));
        mColumnBarChartView.addColumnData(new ColumnBarChartView.ColumnDataFrom("sad", count[4], getRandColorCode()));
        mColumnBarChartView.addColumnData(new ColumnBarChartView.ColumnDataFrom("angry", count[5], getRandColorCode()));
        mColumnBarChartView.addColumnData(new ColumnBarChartView.ColumnDataFrom("normal", count[6], getRandColorCode()));
        mColumnBarChartView.addColumnData(new ColumnBarChartView.ColumnDataFrom("sweat", count[7], getRandColorCode()));
        mColumnBarChartView.addColumnData(new ColumnBarChartView.ColumnDataFrom("worry", count[8], getRandColorCode()));
        mColumnBarChartView.addColumnData(new ColumnBarChartView.ColumnDataFrom("sick", count[9], getRandColorCode()));
    }

    private static int getRandColorCode() {
        String r, g, b;
        Random random = new Random();
        r = Integer.toHexString(random.nextInt(256)).toUpperCase();
        g = Integer.toHexString(random.nextInt(256)).toUpperCase();
        b = Integer.toHexString(random.nextInt(256)).toUpperCase();

        r = r.length() == 1 ? "0" + r : r;
        g = g.length() == 1 ? "0" + g : g;
        b = b.length() == 1 ? "0" + b : b;

        int a =  Color.parseColor("#"+r + g + b);
        return  a;

    }

}