package org.qianhu.app.ui.fragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.qianhu.R;
import org.qianhu.app.ui.activity.HomeItemDetailActivity;
import org.qianhu.bean.Fruit;
import org.qianhu.app.database.MyDatabaseHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by fred on 2017/5/26.
 */

public class DiscoverFragment extends LazyLoadFragment {
    private List<Fruit> fruitList = new ArrayList<>();

    MyDatabaseHelper myDatabaseHelper;
    @Override
    public void lazyLoad() {
        Button btn_discover = (Button) findViewById(R.id.btn_discover);
        btn_discover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HomeItemDetailActivity.class);
                intent.putExtra(HomeItemDetailActivity.DETAIL_NAME, "123");
                intent.putExtra(HomeItemDetailActivity.DETAIL_IMAGE_ID, R.drawable.icon);
                startActivity(intent);
            }
        });
//        initFruit();
//        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view_discover);
////        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
////        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        StaggeredGridLayoutManager layoutManager = new
//                StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
//        recyclerView.setLayoutManager(layoutManager);
//        FruitAdapter adapter = new FruitAdapter(fruitList);
//        recyclerView.setAdapter(adapter);
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
        Log.i("discoveryFragment","GetLayoutId");
        return R.layout.fragment_discover;
    }

    @Override
    public void stopLoad() {
        super.stopLoad();
        Log.i("discoveryFragment","StopLoad");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("discoveryFragment","DestroyView");
    }


}
