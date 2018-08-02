package janmejai.com.mvpproject.Presenter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

import janmejai.com.mvpproject.Fragment.NewFragment;
import janmejai.com.mvpproject.Fragment.NextScreenFragment;
import janmejai.com.mvpproject.NextScreenActivity;

/**
 * Created by hitesh-trisys on 9/14/17.
 */

public class NextScreenPresenter {
    private final NextScreen nextScreen;
    private NextScreenActivity nextscreenActivity;

    public NextScreenPresenter(NextScreen nextScreen) {
        this.nextScreen = nextScreen;
    }

    public void addListener(NextScreenActivity nextScreenActivity) {
        this.nextscreenActivity = nextScreenActivity;
    }

    public void setViewPagerAdapter(ViewPager viewPager, FragmentManager fragmentManager) {
        viewPager.setAdapter(new MyPagerAdapter(fragmentManager, getFragments()));
        viewPager.setOnPageChangeListener(new CircularViewPagerHandler(viewPager));

    }

    private List<Fragment> getFragments() {

        List<Fragment> fList = new ArrayList<Fragment>();

        fList.add(NextScreenFragment.newInstance("Fragment 1"));

        fList.add(NewFragment.newInstance("Fragment 2"));

        fList.add(NextScreenFragment.newInstance("Fragment 3"));

        return fList;
    }

    public class CircularViewPagerHandler implements ViewPager.OnPageChangeListener {

        private ViewPager mViewPager;
        private int mScrollState;
        private int mCurrentPosition;

        public CircularViewPagerHandler(final ViewPager viewPager) {
            mViewPager = viewPager;
        }
        
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


        }

        @Override
        public void onPageSelected(int position) {
            mCurrentPosition = position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            handleScrollState(state);
            mScrollState = state;
        }

        private void handleScrollState(int state) {
            if (state == ViewPager.SCROLL_STATE_IDLE) {
                setNextItemIfNeeded();
            }
        }

        private void setNextItemIfNeeded() {
            if (!isScrollStateSettling()) {
                handleSetNextItem();
            }
        }

        private void handleSetNextItem() {
            final int lastPosition = mViewPager.getAdapter().getCount() - 1;
            if (mCurrentPosition == 0) {
                mViewPager.setCurrentItem(lastPosition, false);
            } else if (mCurrentPosition == lastPosition) {
                mViewPager.setCurrentItem(0, false);
            }
        }

        private boolean isScrollStateSettling() {
            return mScrollState == ViewPager.SCROLL_STATE_SETTLING;
        }
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> fragments;

        public MyPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public int getCount() {
            return this.fragments.size();
        }

        @Override
        public Fragment getItem(int position) {
            return this.fragments.get(position);
        }
    }
}
