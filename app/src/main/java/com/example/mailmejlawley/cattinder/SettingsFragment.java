package com.example.mailmejlawley.cattinder;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class SettingsFragment extends Fragment {

    private Button goHome;
    private Button toggleVolume;
    private Button resetPref;

    MediaPlayer wipeSound;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_settings, container, false);

        wipeSound = MediaPlayer.create(this.getContext(), R.raw.wipe);

        goHome = (Button) v.findViewById(R.id.home_btn);
        toggleVolume = (Button) v.findViewById(R.id.sound_btn);
        resetPref = (Button) v.findViewById(R.id.reset_btn);

        if (HomeActivity.flag)
            toggleVolume.setBackgroundResource(R.drawable.soundoff);

        goHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goHome();
            }
        });
        toggleVolume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleVolume();
            }
        });
        resetPref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPref();
            }
        });

        return v;
    }

    private void goHome(){
        HomeActivity.buttonClick.start();
        if (HomeActivity.flag) {
            HomeActivity.toggleVolume.setBackgroundResource(R.drawable.soundoff);
        }
        else if (!HomeActivity.flag) {
            HomeActivity.toggleVolume.setBackgroundResource(R.drawable.soundon);
        }
        getActivity().onBackPressed(); // close this fragment
    }

    private void toggleVolume(){
        if (!HomeActivity.flag) {
            HomeActivity.buttonClick.start();
            HomeActivity.homeSound.pause();
            toggleVolume.setBackgroundResource(R.drawable.soundoff);
            HomeActivity.flag = true;
        }
        else {
            HomeActivity.buttonClick.start();
            HomeActivity.homeSound.start();
            toggleVolume.setBackgroundResource(R.drawable.soundon);
            HomeActivity.flag = false;
        }
    }

    private void resetPref() {
        wipeSound.start();
        /* PUT CODE FOR WHAT HAPPENS WHEN PLAYER PRESSES RESET HERE */
    }
}
