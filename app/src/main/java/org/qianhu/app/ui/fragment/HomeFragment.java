package org.qianhu.app.ui.fragment;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import org.qianhu.R;
import org.qianhu.bean.Fruit;
import org.qianhu.adapter.FruitAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by fred on 2017/5/26.
 */

public class HomeFragment extends LazyLoadFragment {

    private DrawerLayout mDrawerLayout;

    private Fruit[] fruits = {new Fruit("Apple", R.drawable.ic_explore_black_24dp),
            new Fruit("Banana", R.drawable.ic_forum_black_24dp),
            new Fruit("Peach", R.drawable.ic_featured_play_list_black_24dp),
            new Fruit("PineApple", R.drawable.ic_arrow_back_black_24dp)};

    private List<Fruit> fruitList = new ArrayList<>();
    private FruitAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;


    @Override
    public void lazyLoad() {
        FloatingActionButton fab_home = (FloatingActionButton) findViewById(R.id.fab_home);
        fab_home.setOnClickListener(v -> {
                Snackbar.make(v, "Data Deleted", Snackbar.LENGTH_SHORT)
                        .setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(getContext(),
                                        "data restored",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }).show();

        });

        initFruit();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview_home);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new FruitAdapter(fruitList);
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefresh_home);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshFruits();
            }
        });
    }

    private void refreshFruits() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(2000);
                }catch (Exception e){
                    e.printStackTrace();
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initFruit();
                        adapter.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        }).start();
    }

    private void initFruit() {
        fruitList.clear();
      for (int i = 0; i<50; i++){
          Random random = new Random();
          int index = random.nextInt(fruits.length);
          fruitList.add(fruits[index]);
      }
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void stopLoad() {
        super.stopLoad();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }



}
