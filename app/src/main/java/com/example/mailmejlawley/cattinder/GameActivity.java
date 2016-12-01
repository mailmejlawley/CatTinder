package com.example.mailmejlawley.cattinder;

import android.support.v4.app.Fragment;

public class GameActivity extends SingleFragmentActivity
{
    protected Fragment createFragment()
    {
        return new GameActivityFragment();
    }
}