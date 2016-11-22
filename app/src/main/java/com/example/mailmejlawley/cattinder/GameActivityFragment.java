package com.example.mailmejlawley.cattinder;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.media.MediaPlayer;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class GameActivityFragment extends Fragment {

    private RelativeLayout layout;
    MediaPlayer hissSound;
    MediaPlayer petSound;
    private Button toggleVolume;
    private Button goHome;
    private Button pickHiss;
    private Button pickPet;

    private ImageView cat;
    private ImageView hat;
    private ImageView neck;
    private ImageView mouth;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_game, container, false);

        layout = (RelativeLayout) v.findViewById(R.id.activity_game);
        hissSound = MediaPlayer.create(this.getContext(), R.raw.angry);
        petSound = MediaPlayer.create(this.getContext(), R.raw.purr);

        goHome = (Button) v.findViewById(R.id.home_btn);
        toggleVolume = (Button) v.findViewById(R.id.sound_btn);
        pickHiss = (Button) v.findViewById(R.id.hiss_btn);
        pickPet = (Button) v.findViewById(R.id.pet_btn);

        hat = (ImageView) v.findViewById(R.id.hat);
        cat = (ImageView) v.findViewById(R.id.cat);
        neck = (ImageView) v.findViewById(R.id.neck);
        mouth = (ImageView) v.findViewById(R.id.mouth);

        if (HomeActivity.flag)
            toggleVolume.setBackgroundResource(R.drawable.soundoff);

        toggleVolume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleVolume();
            }
        });
        goHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goHome();
            }
        });
        pickHiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                pickHiss();
            }
        });
        pickPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                pickPet();
            }
        });

        return v;
    }

    private void goHome(){
        HomeActivity.buttonClick.start();
        if (HomeActivity.flag)
            HomeActivity.toggleVolume.setBackgroundResource(R.drawable.soundoff);
        else if (!HomeActivity.flag)
            HomeActivity.toggleVolume.setBackgroundResource(R.drawable.soundon);
        getActivity().onBackPressed(); // close this fragment
    }

    private void toggleVolume() {
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

    private void randomize() {
        final float scale = getResources().getDisplayMetrics().density;

        // What to change current items to:
        cat.setBackgroundResource(R.drawable.yelloweyes_whiteshorthair_cat);
        hat.setBackgroundResource(0); // 0 = remove current image from hat
        neck.setBackgroundResource(R.drawable.blackbow); // neck positioning doesn't need to change
        mouth.setBackgroundResource(0); // remove current image from mouth
        int width, height, left, top, right, bottom;

        // Head item:
        width = (int) (196 * scale);
        height = (int) (22 * scale);
        RelativeLayout.LayoutParams params_hat = new RelativeLayout.LayoutParams(width, height);
        ImageView hat_change = new ImageView(this.getContext());
        hat_change.setBackgroundResource(R.drawable.headband);
        left = (int) (195 * scale);
        top = (int) (550 * scale);
        right = 0;
        bottom = 0;
        params_hat.setMargins(left, top, right, bottom);
        layout.addView(hat_change, params_hat);

        // Mouth item:
        width = (int) (42 * scale);
        height = (int) (21 * scale);
        RelativeLayout.LayoutParams params_mouth = new RelativeLayout.LayoutParams(width, height);
        ImageView mouth_change = new ImageView(this.getContext());
        mouth_change.setBackgroundResource(R.drawable.mustache);
        left = (int) (269* scale);
        top = (int) (631 * scale);
        right = 0;
        bottom = 0;
        params_mouth.setMargins(left, top, right, bottom);
        layout.addView(mouth_change, params_mouth);

    }

    private void pickHiss() {
        hissSound.start();
        randomize();


    }

    private void pickPet() {
        petSound.start();
        randomize();
    }
}