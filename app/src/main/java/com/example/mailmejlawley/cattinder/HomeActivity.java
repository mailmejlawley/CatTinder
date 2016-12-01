package com.example.mailmejlawley.cattinder;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends Activity
{
    static MediaPlayer homeSound, buttonClick;
    static boolean flag = false;

    private Button playGame;
    static public Button toggleVolume;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        homeSound = MediaPlayer.create(this, R.raw.catmusicvideo);
        buttonClick = MediaPlayer.create(this, R.raw.button_click);
        setContentView(R.layout.activity_home);
        homeSound.setLooping(true);
        homeSound.start();

        playGame = (Button) findViewById(R.id.play_btn);
        toggleVolume = (Button) findViewById(R.id.sound_btn);

        playGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playGame();
            }
        });
        toggleVolume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleVolume();
            }
        });
    }

    private void playGame()
    {
        if(!HomeActivity.flag)
            buttonClick.start();
        startActivity(new Intent(this, GameActivity.class));
    }

    private void toggleVolume()
    {
        buttonClick.start();
        if (flag)
        {
            homeSound.start();
            toggleVolume.setBackgroundResource(R.drawable.soundon);
        }
        else
        {
            homeSound.pause();
            toggleVolume.setBackgroundResource(R.drawable.soundoff);
        }
        flag = !flag;
    }
}