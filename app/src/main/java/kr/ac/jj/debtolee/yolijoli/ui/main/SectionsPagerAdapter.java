package kr.ac.jj.debtolee.yolijoli.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import kr.ac.jj.debtolee.yolijoli.frag.CategoryFragment;
import kr.ac.jj.debtolee.yolijoli.R;
import kr.ac.jj.debtolee.yolijoli.frag.CommunityFragment;
import kr.ac.jj.debtolee.yolijoli.frag.MyFragment;
import kr.ac.jj.debtolee.yolijoli.frag.SearchFragment;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_category, R.string.tab_search,R.string.tab_community,R.string.tab_my};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new CategoryFragment();

            case 1:
                return new SearchFragment();

            case 3:
               return new CommunityFragment();

            case 4:
                return new MyFragment();
        }
         return new CategoryFragment();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 4;
    }
}