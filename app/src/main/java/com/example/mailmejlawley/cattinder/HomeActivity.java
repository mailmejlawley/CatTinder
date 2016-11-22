package com.example.mailmejlawley.cattinder;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends Activity {
    static MediaPlayer homeSound;
    static MediaPlayer buttonClick;
    static boolean flag = false;   // used for volume toggle

    private Button playGame;
    private Button viewSettings;
    static Button toggleVolume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeSound = MediaPlayer.create(this, R.raw.catmusicvideo);
        buttonClick = MediaPlayer.create(this, R.raw.button_click);
        setContentView(R.layout.activity_home);
        homeSound.setLooping(true);
        homeSound.start();

        playGame = (Button) findViewById(R.id.play_btn);
        viewSettings = (Button) findViewById(R.id.settings_btn);
        toggleVolume = (Button) findViewById(R.id.sound_btn);

        playGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playGame();
            }
        });
        viewSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewSettings();
            }
        });
        toggleVolume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleVolume();
            }
        });
    }

        private void playGame(){
            buttonClick.start();
            Intent intent = new Intent(this, GameActivity.class);
            startActivity(intent);
        }
    private void viewSettings() {
        buttonClick.start();
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    private void toggleVolume() {
        if (!flag) {
            buttonClick.start();
            homeSound.pause();
            toggleVolume.setBackgroundResource(R.drawable.soundoff);
            flag = true;
        }
        else {
            buttonClick.start();
            homeSound.start();
            toggleVolume.setBackgroundResource(R.drawable.soundon);
            flag = false;
        }
    }

}
