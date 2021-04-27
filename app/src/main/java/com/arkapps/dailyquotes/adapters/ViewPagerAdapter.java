package com.arkapps.dailyquotes.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.arkapps.dailyquotes.fragments.CategoryFragment;
import com.arkapps.dailyquotes.fragments.FavoriteFragment;
import com.arkapps.dailyquotes.fragments.HomeFragment;
import com.arkapps.dailyquotes.fragments.SettingFragment;

public class ViewPagerAdapter  extends FragmentStateAdapter {


    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
            default:
                return  new HomeFragment();
            case 1:
                return new CategoryFragment();
            case 2:
                return new FavoriteFragment();
            case 3:
                return new SettingFragment();

        }

    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
