package edu.neu.numad22sp_bdd_project.home;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

import edu.neu.numad22sp_bdd_project.R;

import android.media.Image;
import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.appcompat.widget.Toolbar;
import edu.neu.numad22sp_bdd_project.todolist.TodolistActivity;

import com.google.android.material.navigation.NavigationView;
import java.util.Random;

public class HomeActivity extends MainActivity {

    private ImageView homewall;
    private DailySentence dailySentence;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final FrameLayout contentFrameLayout = findViewById(R.id.frag_container);
        dailySentence = new DailySentence();

        //get random daily wallpaper
        Random num = new Random();
        int id = num.nextInt(60);

        //set it to main activity
        getLayoutInflater().inflate(R.layout.wallpaperlayout, contentFrameLayout);
        homewall = findViewById(R.id.wallpaperView);
        homewall.setImageResource(dailySentence.getImage(id));
//        }
    }

}