package com.example.mailmejlawley.cattinder;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.media.MediaPlayer;
import android.widget.ImageView;
import java.util.ArrayList;
import java.util.Arrays;

public class GameActivityFragment extends Fragment
{
    private MediaPlayer hissSound, petSound;
    private Button toggleVolume, goHome, pickHiss, pickPet;
    private ImageView cat;
    private AI ai = new AI(new ArrayList<>(Arrays.asList(new String[]
            {       "0\t0\t0\t5\tb",
                    "0\t0\t0\t5\to",
                    "0\t0\t0\t5\tw",
                    "1\t0\t0\t5\tl",
                    "1\t0\t0\t5\ts",
                    "2\t822\t1235\t5\tface_white",
                    "2\t822\t1235\t5\tface_brown",
                    "3\t837\t1250\t5\teyes_blue",
                    "3\t837\t1250\t5\teyes_green",
                    "3\t837\t1250\t5\teyes_yellow",
                    "4\t458\t466\t5\that_blue",
                    "4\t458\t466\t5\that_brown",
                    "4\t645\t331\t5\that_graduate",
                    "4\t516\t846\t5\that_headband",
                    "4\t830\t128\t5\that_top",
                    "4\t0\t0\t5\t-",
                    "5\t685\t1120\t5\tglass_round_black",
                    "5\t685\t1120\t5\tglass_round_red",
                    "5\t685\t1120\t5\tglass_round_yellow",
                    "5\t675\t1200\t5\tglass_square_black",
                    "5\t675\t1200\t5\tglass_square_red",
                    "5\t675\t1200\t5\tglass_square_yellow",
                    "5\t748\t1192\t5\tglass_sun",
                    "5\t875\t1468\t5\tmouth_1",
                    "5\t1020\t1461\t5\tmouth_2",
                    "5\t942\t1611\t5\tneck_black",
                    "5\t942\t1611\t5\tneck_blue",
                    "5\t942\t1611\t5\tneck_green",
                    "5\t942\t1611\t5\tneck_orange",
                    "5\t942\t1611\t5\tneck_purple",
                    "5\t942\t1611\t5\tneck_red",
                    "5\t942\t1611\t5\tneck_white",
                    "5\t942\t1611\t5\tneck_yellow",
                    "5\t0\t0\t5\t-"
            })));

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.activity_game, container, false);

        hissSound = MediaPlayer.create(this.getContext(), R.raw.angry);
        petSound = MediaPlayer.create(this.getContext(), R.raw.purr);

        goHome = (Button) v.findViewById(R.id.home_btn);
        toggleVolume = (Button) v.findViewById(R.id.sound_btn);
        pickHiss = (Button) v.findViewById(R.id.hiss_btn);
        pickPet = (Button) v.findViewById(R.id.pet_btn);
        cat = (ImageView) v.findViewById(R.id.cat);

        if (HomeActivity.flag)
        {
            HomeActivity.homeSound.pause();
            toggleVolume.setBackgroundResource(R.drawable.soundoff);
        }

        generateCat();

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
        pickPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                react(true);
            }
        });
        pickHiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                react(false);
            }
        });

        return v;
    }

    private void goHome()
    {
        if (HomeActivity.flag)
            HomeActivity.toggleVolume.setBackgroundResource(R.drawable.soundoff);
        else if (!HomeActivity.flag)
        {
            HomeActivity.buttonClick.start();
            HomeActivity.toggleVolume.setBackgroundResource(R.drawable.soundon);
        }
        getActivity().onBackPressed();
    }

    private void toggleVolume()
    {
        HomeActivity.buttonClick.start();
        if (HomeActivity.flag)
        {
            HomeActivity.homeSound.start();
            toggleVolume.setBackgroundResource(R.drawable.soundon);
        }
        else
        {
            HomeActivity.homeSound.pause();
            toggleVolume.setBackgroundResource(R.drawable.soundoff);
        }
        HomeActivity.flag = !HomeActivity.flag;
    }

    private void generateCat()
    {
        ArrayList<Feature> catAI = ai.generateCat();
        Bitmap b;
        Canvas c;
        double s;

        {
            Bitmap catBody = BitmapFactory.decodeResource(getResources(), getResources().getIdentifier(catAI.get(0).getFileName() + catAI.get(1).getFileName(), "drawable", this.getContext().getPackageName()));
            b = Bitmap.createBitmap(catBody.getWidth(), catBody.getHeight(), Bitmap.Config.ARGB_8888);
            c = new Canvas(b);
            c.drawBitmap(catBody, 0, 0, null);
            s = (double)catBody.getWidth() / 3000;
        }

        for(int i = 2; i < catAI.size(); i++)
        {
            if(!catAI.get(i).getFileName().equals("-")) {
                Bitmap f = BitmapFactory.decodeResource(getResources(), getResources().getIdentifier(catAI.get(i).getFileName(), "drawable", this.getContext().getPackageName()));
                c.drawBitmap(f, (int)(catAI.get(i).getX() * s), (int)(catAI.get(i).getY() * s), null);
            }
        }

        cat.setImageBitmap(b);
    }

    private void react(boolean like)
    {
        if(!HomeActivity.flag)
            if(like)
                petSound.start();
            else
                hissSound.start();
        ai.react(like);
        generateCat();
    }
}