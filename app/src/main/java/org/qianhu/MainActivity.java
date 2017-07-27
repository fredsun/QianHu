package org.qianhu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.view.MenuItem;

import org.qianhu.app.ui.fragment.DiscoverFragment;
import org.qianhu.app.ui.fragment.HomeFragment;
import org.qianhu.app.ui.fragment.MeFragment;
import org.qianhu.app.ui.fragment.MessageFragment;
import org.qianhu.app.ui.fragment.NotificationFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView (R.id.home_view_pager)
    ViewPager home_view_pager;

    @BindView(R.id.navigation)
    NavigationView navView;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.tab_home)
    TabLayout tabHome;

    private MenuItem prevMenuItem;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mContext = this;
        initViewPager();
        initNavigation();
    }

    private void initNavigation() {
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_call:
                        drawerLayout.closeDrawers();
                        break;
                    case  R.id.nav_location:
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.nav_setting:
                        drawerLayout.closeDrawers();
                        break;
                }
                return true;
            }
        });
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
                        fragment = new DiscoverFragment();
                        break;
                    case 2:
                        fragment = new NotificationFragment();
                        break;
                    case 3:
                        fragment = new MessageFragment();
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

            @Override
            public CharSequence getPageTitle(int position) {
                Drawable drawable;
                String title;
                switch (position){
                    case 0:
                        drawable = ContextCompat.getDrawable(mContext, R.drawable.ic_featured_play_list_black_24dp);
                        title = "主页";
                        break;

                    case 1:
                        drawable = ContextCompat.getDrawable(mContext, R.drawable.ic_explore_black_24dp);
                        title = "发现";
                        break;

                    case 2:
                        drawable = ContextCompat.getDrawable(mContext, R.drawable.ic_notifications_black_24dp);
                        title = "探索";
                        break;

                    case 3:
                        drawable = ContextCompat.getDrawable(mContext, R.drawable.ic_forum_black_24dp);
                        title = "聊天";
                        break;

                    case 4:
                        drawable = ContextCompat.getDrawable(mContext, R.drawable.ic_menu_black_24dp);
                        title = "我";
                        break;

                    default:
                        drawable = ContextCompat.getDrawable(mContext, R.drawable.ic_featured_play_list_black_24dp);
                        title = "主页";
                        break;
                }
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                ImageSpan imageSpan = new ImageSpan(drawable, ImageSpan.ALIGN_BOTTOM);
                SpannableString spannableString = new SpannableString(" ");
                spannableString.setSpan(imageSpan, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                return spannableString;
            }
        });

        tabHome.setupWithViewPager(home_view_pager);
//        home_view_pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            //滑动过程钟不断调用
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                Log.i("fragment","pageSelected");
//                invalidateOptionsMenu();//APPcompat的方法
//                /**
//                 * 该方法只有在有新的页面被选中时才会回调
//                 *
//                 * 如果 preMenuItem 为 null，说明该方法还没有被回调过
//                 * 则ViewPager从创建到现在都处于 position 为 0 的页面
//                 * 所以当该方法第一次被回调的时候，直接将 position 为 0 的页面的 selected 状态设为 false 即可
//                 *
//                 * 如果 preMenuItem 不为 null，说明该方法内的
//                 * "prevMenuItem = bottomNavView.getMenu().getItem(position);"
//                 * 之前至少被调用过一次
//                 * 所以当该方法再次被回调的时候，直接将上一个 prevMenuItem 的 selected 状态设为 false 即可
//                 * 在做完上一句的事情后，将当前页面设为 prevMenuItem，以备下次调用
//                 *
//                 */
//                if (prevMenuItem == null) {
//                    bottomNavView.getMenu().getItem(0).setChecked(false);
//                } else {
//                    prevMenuItem.setChecked(false);
//                }
//
//                bottomNavView.getMenu().getItem(position).setChecked(true);
//                prevMenuItem = bottomNavView.getMenu().getItem(position);
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });


    }




}
