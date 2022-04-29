package edu.neu.numad22sp_bdd_project.statistic;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

import edu.neu.numad22sp_bdd_project.R;
import edu.neu.numad22sp_bdd_project.home.HomeActivity;
import edu.neu.numad22sp_bdd_project.moodtracker.MoodTrackerActivity;

public class StatisticActivity extends HomeActivity {

    private ColumnBarChartView mColumnBarChartView;
    private int[] count = new int[9];
    private DatabaseReference a;
    private TextView t;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
//        FirebaseAuth mAuth = FirebaseAuth.getInstance();
//        userID = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
//        t = findViewById(R.id.textView);
        setContentView(R.layout.activity_statistic);
//        //String user = FirebaseAuth.getInstance().getUid();
//        a = FirebaseDatabase.getInstance().getReference("user");
//        a.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                System.out.println("Before Get Value");
//
//                Object document = dataSnapshot.getValue();
//
//                System.out.println(document);
//
//            }
//
//            @Override
//
//            public void onCancelled(DatabaseError arg0) {
//// TODO Auto-generated method stub
//
//                System.out.println("This didn't work");
//
//            }
//
//        });
//


        initial();
    }
    private void initial() {



        mColumnBarChartView = findViewById(R.id.columnBarChartView);
        //int result = count[0];
        //t.setText(result);
        mColumnBarChartView.addColumnData(new ColumnBarChartView.ColumnDataFrom("sad", 10, Color.YELLOW));
        mColumnBarChartView.addColumnData(new ColumnBarChartView.ColumnDataFrom("happy", 15, Color.RED));
        mColumnBarChartView.addColumnData(new ColumnBarChartView.ColumnDataFrom("cry", 10, Color.BLUE));
    }

    public void addRandomData(View view) {
        int number = (int)(Math.random()*40+5);
        mColumnBarChartView.addColumnData(new ColumnBarChartView.ColumnDataFrom("随机 " + number, number, getRandColorCode()));
    }

    public void deleteRandomData(View view) {
        Random random = new Random();
        int number =  random.nextInt(mColumnBarChartView.getSize()-1);
        mColumnBarChartView.deleteColumnData(number);
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