package com.example.mailmejlawley.cattinder;

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
    boolean flag = HomeActivity.flag;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_settings, container, false);

        goHome = (Button) v.findViewById(R.id.home_btn);
        toggleVolume = (Button) v.findViewById(R.id.sound_btn);

        if (flag)
            toggleVolume.setBackgroundResource(R.drawable.soundoff);

        goHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeActivity.flag = flag;
                if (flag)
                    HomeActivity.toggleVolume.setBackgroundResource(R.drawable.soundoff);
                else if (!flag)
                    HomeActivity.toggleVolume.setBackgroundResource(R.drawable.soundon);
                getActivity().onBackPressed(); // close this fragment
            }
        });
        toggleVolume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!flag) {
                    HomeActivity.buttonClick.start();
                    HomeActivity.homeSound.pause();
                    toggleVolume.setBackgroundResource(R.drawable.soundoff);
                    flag = true;
                }
                else {
                    HomeActivity.buttonClick.start();
                    HomeActivity.homeSound.start();
                    toggleVolume.setBackgroundResource(R.drawable.soundon);
                    flag = false;
                }
            }
        });
        return v;
    }

}
