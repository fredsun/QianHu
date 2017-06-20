package org.qianhu.app.ui.fragment;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

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
    private List<Fruit> fruitList = new ArrayList<>();

    @Override
    public void lazyLoad() {
        initFruit();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclver_view);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
//        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        StaggeredGridLayoutManager layoutManager = new
                StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        FruitAdapter adapter = new FruitAdapter(fruitList);
        recyclerView.setAdapter(adapter);
    }

    private void initFruit() {
        for (int i = 0; i <3; i++){
            Fruit apple = new Fruit(getRandomLengthName("apple"),R.drawable.ic_explore_black_24dp);
            fruitList.add(apple);
            Fruit banana = new Fruit(getRandomLengthName("banana"),R.drawable.ic_explore_black_24dp);
            fruitList.add(banana);
            Fruit peach = new Fruit(getRandomLengthName("peach"),R.drawable.ic_explore_black_24dp);
            fruitList.add(peach);
        }
    }

    private String getRandomLengthName(String name){
        Random random = new Random();
        int length = random.nextInt(20)+1;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++){
            builder.append(name);
        }
        return builder.toString();
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
