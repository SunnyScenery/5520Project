package edu.neu.numad22sp_bdd_project.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import edu.neu.numad22sp_bdd_project.R;
import edu.neu.numad22sp_bdd_project.moodtracker.MoodTrackerActivity;
import edu.neu.numad22sp_bdd_project.quiz.QuizActivity;
import edu.neu.numad22sp_bdd_project.breath.BreathActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.appcompat.widget.Toolbar;
import edu.neu.numad22sp_bdd_project.sensor.SensorActivity;
import edu.neu.numad22sp_bdd_project.statistic.TestActivity;
import edu.neu.numad22sp_bdd_project.todolist.TodolistActivity;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    protected DrawerLayout draw;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.wallpaperView);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        draw = findViewById(R.id.drawer_layout);
        NavigationView navi = findViewById(R.id.nav_view);
        navi.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, draw, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        draw.addDrawerListener(toggle);
        toggle.syncState();

        final FrameLayout frag = findViewById(R.id.frag_container);


        getLayoutInflater().inflate(R.layout.activity_background, frag);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){

            case R.id.nav_home:
                Intent i_home = new Intent(this, HomeActivity.class);
                startActivity(i_home);
                finish();
                draw.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_quiz:
                Intent i_quiz = new Intent(this, QuizActivity.class);
                startActivity(i_quiz);
                finish();
                draw.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_sensor:
                Intent i_sensor = new Intent(this, SensorActivity.class);
                startActivity(i_sensor);
                finish();
                draw.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_todo:
                Intent i_todo = new Intent(this, TodolistActivity.class);
                startActivity(i_todo);
                finish();
                draw.closeDrawer(GravityCompat.START);
                break;

            case R.id.nav_breathe:
                Intent i_breath = new Intent(this, BreathActivity.class);
                startActivity(i_breath);
                finish();
                draw.closeDrawer(GravityCompat.START);
                break;

            case R.id.nav_mood:
                Intent i_mood = new Intent(this, MoodTrackerActivity.class);
                startActivity(i_mood);
                draw.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_stats:
                Intent i_stats = new Intent(this, TestActivity.class);
                startActivity(i_stats);
                draw.closeDrawer(GravityCompat.START);
                break;
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        //close navigation bar before closing app
        if(draw.isDrawerOpen(GravityCompat.START)){
            draw.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }
}