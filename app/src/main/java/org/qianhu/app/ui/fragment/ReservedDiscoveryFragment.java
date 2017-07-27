package org.qianhu.app.ui.fragment;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;
import org.qianhu.R;
import org.qianhu.adapter.FruitAdapter;
import org.qianhu.bean.Fruit;
import org.qianhu.database.Book;
import org.qianhu.database.MyDatabaseHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by fred on 2017/7/27.
 */

public class ReservedDiscoveryFragment extends LazyLoadFragment{

    private List<Fruit> fruitList = new ArrayList<>();

    MyDatabaseHelper myDatabaseHelper;
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

        myDatabaseHelper = new MyDatabaseHelper(getContext(), "BookStore.db", null, 2);
        View viewById = findViewById(R.id.btn_create_db);
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDatabaseHelper.getWritableDatabase();
            }
        });

        View viewById1 = findViewById(R.id.btn_add_data);
        viewById1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("name", "aaa");
                values.put("author", "bbb");
                values.put("pages", 111);
                values.put("price", 16);
                db.insert("Book", null, values);

                values.put("name", "ccc");
                values.put("author", "ddd");
                values.put("pages", 222);
                values.put("price", 333);
                db.insert("Book", null, values);
            }
        });

        View viewById2 = findViewById(R.id.btn_up_data);
        viewById2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("price", 17);
                db.update("Book", values, "name = ?", new String[]{"aaa"});
            }
        });

        View viewById3 = findViewById(R.id.btn_del_data);
        viewById3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();
                db.delete("Book", "pages > ?", new String[]{"200"});
            }
        });

        View viewById4 = findViewById(R.id.btn_query_data);
        viewById4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();
                Cursor cursor = db.query("Book", null, null, null, null, null, null);
                if (cursor.moveToFirst()){
                    do {
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        double price = cursor.getDouble(cursor.getColumnIndex("pages"));
                        Log.i("db","book name is "+name);
                        Log.i("db","book author is "+author);
                        Log.i("db","book pages is "+pages);
                        Log.i("db","book price is "+price);

                    }while (cursor.moveToNext());
                }
                cursor.close();
            }
        });

        View viewById5 = findViewById(R.id.btn_lite_new_data);
        viewById5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Connector.getDatabase();
            }
        });

        View viewById6 = findViewById(R.id.btn_lite_add_data);
        viewById6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                book.setName("nameAAA");
                book.setAuthor("authorAAA");
                book.setPages(123);
                book.setPrice(456);
                book.setPress("aaa");
                book.save();
            }
        });

        View viewById7 = findViewById(R.id.btn_lite_up_data);
        viewById7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                book.setName("nameBBB");
                book.setAuthor("authorBBB");
                book.setPages(321);
                book.setPrice(654);
                book.setPress("aaa");
                book.save();
                book.setPrice(987);
                book.save();
            }
        });

        View viewById8 = findViewById(R.id.btn_lite_del_data);
        viewById8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataSupport.deleteAll(Book.class, "price < ?", "600");
            }
        });

        View viewById9 = findViewById(R.id.btn_lite_query_data);
        viewById9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Book> books = DataSupport.findAll(Book.class);
                for (Book book : books){
                    Log.i("book", "book name"+book.getName());
                    Log.i("book", "book Author"+book.getAuthor());
                    Log.i("book", "book Pages"+book.getPages());
                    Log.i("book", "book Price"+book.getPrice());
                    Log.i("book", "book Press"+book.getPress());
                }
            }
        });
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
        return R.layout.fragment_reserved_discovery;
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
