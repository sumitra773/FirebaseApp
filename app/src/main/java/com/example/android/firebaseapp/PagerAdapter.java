package com.example.android.firebaseapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.android.firebaseapp.fragment.CertificateFragment;
import com.example.android.firebaseapp.fragment.ChatFragment;
import com.example.android.firebaseapp.fragment.ProfileFragment;
import com.example.android.firebaseapp.fragment.ScoreFragment;

public class PagerAdapter extends FragmentPagerAdapter {

    final int PageCount = 4;
    private String tabTitles[] = new String[]{"Certificate","Profile","Chat","Score"};



    public PagerAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                return new CertificateFragment();

            case 1:
                return new ProfileFragment();
            case 2:
                return new ChatFragment();
            case 3:
                return new ScoreFragment();
            default:
                return null;

        }

    }

    @Override
    public int getCount() {
        return PageCount;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
