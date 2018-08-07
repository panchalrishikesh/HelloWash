package com.hw.hellowash.activities;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.hw.hellowash.Custom.NonSwipeableViewPager;
import com.hw.hellowash.R;
import com.hw.hellowash.adapters.MyPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import it.sephiroth.android.library.bottomnavigation.BottomNavigation;
import it.sephiroth.android.library.bottomnavigation.BottomNavigation.OnMenuItemSelectionListener;

public class MainActivity extends BaseActivity implements BottomNavigation.OnMenuChangedListener,
        OnMenuItemSelectionListener{

    private NonSwipeableViewPager view_pager;
    private MyPagerAdapter adapterViewPager;

    @BindView(R.id.bottom_navigation)
    BottomNavigation bottom_navigation;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        super.applyTheme(this);
        ButterKnife.bind(this);

        view_pager = (NonSwipeableViewPager) findViewById(R.id.view_pager);
        adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
        view_pager.setAdapter(adapterViewPager);

        bottom_navigation.setOnMenuChangedListener(this);
        bottom_navigation.setOnMenuItemClickListener(this);

        int PERMISSION_ALL = 10;
        String[] PERMISSIONS = {
                android.Manifest.permission.CAMERA,
                android.Manifest.permission.CALL_PHONE,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                android.Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.ACCESS_COARSE_LOCATION,
                android.Manifest.permission.ACCESS_FINE_LOCATION};

        if (!hasPermissions(this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }
    }

    @Override
    public void onMenuChanged(BottomNavigation bottomNavigation) {
        view_pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(
                    final int position, final float positionOffset, final int positionOffsetPixels) { }

            @Override
            public void onPageSelected(final int position) {
                if (bottom_navigation.getSelectedIndex() != position) {
                    bottom_navigation.setSelectedIndex(position, false);
                }

                switch (position){
                    case 0:
                        toolbar_title.setText(getResources().getString(R.string.hellowash));
                        break;

                    case 1:
                        toolbar_title.setText(getResources().getString(R.string.track_order));
                        break;

                    case 2:
                        toolbar_title.setText(getResources().getString(R.string.profile_hint));
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(final int state) { }
        });
    }

    @Override
    public void onMenuItemSelect(int itemId, int position, boolean fromUser) {
        if (fromUser) {
            bottom_navigation.getBadgeProvider().remove(itemId);
            if (null != view_pager) {
                view_pager.setCurrentItem(position);
            }
        }
    }

    @Override
    public void onMenuItemReselect(int itemId, int position, boolean fromUser) {
        if (fromUser) {
            final FragmentManager manager = getSupportFragmentManager();
            /*MainActivityFragment fragment = (MainActivityFragment) manager.findFragmentById(R.id.fragment);
            if (null != fragment) {
                fragment.scrollToTop();
            }*/
        }
    }

    private boolean hasPermissions(Context context, String[] permissions) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {

                if (ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_DENIED) {

                }
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {

                } else {
                    if (ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED) {

                    } else {
                        //set to never ask again
                        Log.e("set to never ask again", permission);
                    }
                }
                return false;
            }
        }
        return true;
    }
}
