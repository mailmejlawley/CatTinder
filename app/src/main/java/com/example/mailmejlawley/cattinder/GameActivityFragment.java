package com.example.mailmejlawley.cattinder;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.INotificationSideChannel;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.media.MediaPlayer;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.Arrays;


// Changes were made. GIthub. ya jerk.
public class GameActivityFragment extends Fragment {

    private RelativeLayout layout;
    MediaPlayer hissSound;
    MediaPlayer petSound;
    private Button toggleVolume;
    private Button goHome;
    private Button pickHiss;
    private Button pickPet;

    private ImageView body;
    private ImageView eyes;
    private ImageView hat;
    private ImageView neck;
   // private ImageView mouth;
/*
    // Evil and satan
    private ImageView bl;
    private ImageView bs;
    private ImageView ol;
    private ImageView os;
    private ImageView wl;
    private ImageView ws;
*/

    // AI
    private AI Ai = new AI(new ArrayList<String>(Arrays.asList(new String[]
            {       "0\t0\t0\t5\tB",
                    "0\t0\t0\t5\tO",
                    "0\t0\t0\t5\tW",
                    "1\t0\t0\t5\tL",
                    "1\t0\t0\t5\tS",
                    "2\t822\t1235\t5\tface_white",
                    "2\t822\t1235\t5\tface_brown",
                    "3\t837\t1250\t5\teyes_blue",
                    "3\t837\t1250\t5\teyes_green",
                    "3\t837\t1250\t5\teyes_yellow",
                    "3\t837\t1250\t5\tno",
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
    private ArrayList<Feature> cat;

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
        body = (ImageView) v.findViewById(R.id.cat);
        eyes = (ImageView) v.findViewById(R.id.eyes);
        neck = (ImageView) v.findViewById(R.id.neck);
       // mouth = (ImageView) v.findViewById(R.id.mouth);
/*
        // fjdaslk;fjadksl
        bl = (ImageView) v.findViewById(R.id.bl);
        bs = (ImageView) v.findViewById(R.id.bs);
        ol = (ImageView) v.findViewById(R.id.ol);
        os = (ImageView) v.findViewById(R.id.os);
        wl = (ImageView) v.findViewById(R.id.wl);
        ws = (ImageView) v.findViewById(R.id.ws);
*/
        getCatFromAI();

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
/*
    private void randomize() {
        final float scale = getResources().getDisplayMetrics().density;

        // What to change current items to:
        body.setBackgroundResource(R.drawable.yelloweyes_whiteshorthair_cat);
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
*/
    private void getCatFromAI(){ // Retrieves the generated cat from the AI class and returns it to the layout.


        cat = Ai.generateCat(); //
/*
        {
            bl.setVisibility(View.INVISIBLE);
            bs.setVisibility(View.INVISIBLE);
            ol.setVisibility(View.INVISIBLE);
            os.setVisibility(View.INVISIBLE);
            wl.setVisibility(View.INVISIBLE);
            ws.setVisibility(View.INVISIBLE);
        }
        switch (cat.get(0).getFileName() + cat.get(1).getFileName()){
            case "bl":
                bl.setVisibility(View.VISIBLE);
                break;
            case "bs":
                bs.setVisibility(View.VISIBLE);
                break;
            case "ol":
                ol.setVisibility(View.VISIBLE);
                break;
            case "os":
                os.setVisibility(View.VISIBLE);
                break;
            case "wl":
                wl.setVisibility(View.VISIBLE);
                break;
            case "ws":
                ws.setVisibility(View.VISIBLE);
                break;
        }
*/
        switch (cat.get(0).getFileName() + cat.get(1).getFileName()){
            case "BL":
                body.setBackgroundResource(R.drawable.bl);
                break;
            case "BS":
                body.setBackgroundResource(R.drawable.bs);
                break;
            case "OL":
                body.setBackgroundResource(R.drawable.ol);
                break;
            case "OS":
                body.setBackgroundResource(R.drawable.os);
                break;
            case "WL":
                body.setBackgroundResource(R.drawable.wl);
                break;
            case "WS":
                body.setBackgroundResource(R.drawable.ws);
                break;
        }

        switch (cat.get(2).getFileName()) {
            case ("face_white"):
                eyes.setBackgroundResource(R.drawable.face_white);
                break;
            case ("face_brown"):
                eyes.setBackgroundResource(R.drawable.face_brown);
                break;
        }

        switch (cat.get(3).getFileName()) {
            case ("eyes_blue"):
                eyes.setBackgroundResource(R.drawable.eyes_blue);
                break;
            case ("eyes_green"):
                eyes.setBackgroundResource(R.drawable.eyes_green);
                break;
            case ("eyes_yellow"):
                eyes.setBackgroundResource(R.drawable.eyes_yellow);
                break;
        }
        if (cat.get(4).getFileName().equals("-")){
            hat.setVisibility(View.INVISIBLE);
        }

        hat.setVisibility(View.VISIBLE);
        switch (cat.get(4).getFileName()) {
            case ("hat_blue"):
                hat.setBackgroundResource(R.drawable.hat_blue);
                break;
            case ("hat_brown"):
                hat.setBackgroundResource(R.drawable.hat_brown);
                break;
            case ("hat_graduate"):
                hat.setBackgroundResource(R.drawable.hat_graduate);
                break;
            case ("hat_headband"):
                hat.setBackgroundResource(R.drawable.hat_headband);
                break;
            case ("hat_top"):
                hat.setBackgroundResource(R.drawable.hat_top);
                break;
            case ("-"):
                hat.setVisibility(View.INVISIBLE);
                break;
        }
        neck.setVisibility(View.VISIBLE);
        switch (cat.get(5).getFileName()){
            case ("glass_round_black"):
                neck.setBackgroundResource(R.drawable.glass_round_black);
                break;
            case ("glass_round_red"):
                neck.setBackgroundResource(R.drawable.glass_round_red);
                break;
            case ("glass_round_yellow"):
                neck.setBackgroundResource(R.drawable.glass_round_yellow);
                break;
            case ("glass_square_black"):
                neck.setBackgroundResource(R.drawable.glass_square_black);
                break;
            case ("glass_square_red"):
                neck.setBackgroundResource(R.drawable.glass_square_red);
                break;
            case ("glass_square_yellow"):
                neck.setBackgroundResource(R.drawable.glass_square_yellow);
                break;
            case ("glass_sun"):
                neck.setBackgroundResource(R.drawable.glass_sun);
                break;
            case ("mouth_1"):
                neck.setBackgroundResource(R.drawable.mouth_1);
                break;
            case ("mouth_2"):
                neck.setBackgroundResource(R.drawable.mouth_2);
                break;
            case ("neck_black"):
                neck.setBackgroundResource(R.drawable.neck_black);
                break;
            case ("neck_blue"):
                neck.setBackgroundResource(R.drawable.neck_blue);
                break;
            case ("neck_green"):
                neck.setBackgroundResource(R.drawable.neck_green);
                break;
            case ("neck_orange"):
                neck.setBackgroundResource(R.drawable.neck_orange);
                break;
            case ("neck_purple"):
                neck.setBackgroundResource(R.drawable.neck_purple);
                break;
            case ("neck_red"):
                neck.setBackgroundResource(R.drawable.neck_red);
                break;
            case ("neck_white"):
                neck.setBackgroundResource(R.drawable.neck_white);
                break;
            case ("neck_yellow"):
                neck.setBackgroundResource(R.drawable.neck_yellow);
                break;
            case ("-"):
                neck.setVisibility(View.INVISIBLE);
                break;
        }


        //Canvas canvas = new Canvas(eyes.copy(Bitmap.Config.ARGB_8888,))


        ////Bitmap body2 = BitmapFactory.decodeResource(getResources(), R.drawable.bl);
        //Canvas canvas = new Canvas(body2.copy(Bitmap.Config.ARGB_8888, true));



       // body.setBackgroundResource(R.drawable.bl);

    }

    private void pickHiss() { // Tells AI to react negatively and calls getCatFromAI
        hissSound.start();
        Ai.react(false);
        getCatFromAI();
        //randomize();
    }

    private void pickPet() { // Tells AI to react positively and calls getCatFromAI
        petSound.start();
        Ai.react(true);
        getCatFromAI();
        //randomize();
    }
}
