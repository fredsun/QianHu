package org.qianhu;

import android.support.annotation.NonNull;
import android.support.design.internal.NavigationMenuItemView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView (R.id.home_view_pager)
    ViewPager home_view_pager;

    @BindView(R.id.navigation)
    BottomNavigationView bottomNavView;

    private MenuItem prevMenuItem;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.navigation_home:
                    home_view_pager.setCurrentItem(0,false);
                    break;
                case R.id.navigation_discovery:
                    home_view_pager.setCurrentItem(1,false);
                    break;
                case R.id.navigation_notification:
                    home_view_pager.setCurrentItem(2,false);
                    break;
                case R.id.navigation_chat:
                    home_view_pager.setCurrentItem(3,false);
                    break;
                case R.id.navigation_me:
                    home_view_pager.setCurrentItem(4,false);
                    break;
            }
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initViewPager();
        initBottomNav();
    }

    private void initViewPager() {
        home_view_pager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                Fragment fragment = null;
                switch (position){
                    case 0:
                        fragment = new HomeFragment();
                        break;
                    case 1:
                        fragment = new DiscoveryFragment();
                        break;
                    case 2:
                        fragment = new NotificationFragment();
                        break;
                    case 3:
                        fragment = new ChatFragment();
                        break;
                    case 4:
                        fragment = new MeFragment();
                        break;
                }
                return fragment;
            }

            @Override
            public int getCount() {
                return 5;
            }
        });

        home_view_pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            //滑动过程钟不断调用
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.i("fragment","pageSelected");
                invalidateOptionsMenu();//APPcompat的方法
                /**
                 * 该方法只有在有新的页面被选中时才会回调
                 *
                 * 如果 preMenuItem 为 null，说明该方法还没有被回调过
                 * 则ViewPager从创建到现在都处于 position 为 0 的页面
                 * 所以当该方法第一次被回调的时候，直接将 position 为 0 的页面的 selected 状态设为 false 即可
                 *
                 * 如果 preMenuItem 不为 null，说明该方法内的
                 * "prevMenuItem = bottomNavView.getMenu().getItem(position);"
                 * 之前至少被调用过一次
                 * 所以当该方法再次被回调的时候，直接将上一个 prevMenuItem 的 selected 状态设为 false 即可
                 * 在做完上一句的事情后，将当前页面设为 prevMenuItem，以备下次调用
                 *
                 */
                if (prevMenuItem == null) {
                    bottomNavView.getMenu().getItem(0).setChecked(false);
                } else {
                    prevMenuItem.setChecked(false);
                }

                bottomNavView.getMenu().getItem(position).setChecked(true);
                prevMenuItem = bottomNavView.getMenu().getItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initBottomNav() {
        bottomNavView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        BottomNavigationViewHelper.disableShiftMode(bottomNavView);
    }


}
