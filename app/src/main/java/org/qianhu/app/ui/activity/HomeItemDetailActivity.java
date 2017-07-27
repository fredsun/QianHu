package org.qianhu.app.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.qianhu.R;


/**
 * Created by fred on 2017/7/27.
 */

public class HomeItemDetailActivity extends AppCompatActivity {
    public static final String DETAIL_NAME ="detail name";
    public static final String DETAIL_IMAGE_ID="detail image id";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home_item_detail);
        Intent intent = getIntent();
        String detailTitleName = intent.getStringExtra(DETAIL_NAME);
        int detailImageID = intent.getIntExtra(DETAIL_IMAGE_ID, 0);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_home_detail_title);
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbarLayout_home);
        ImageView detailTitleImage = (ImageView)findViewById(R.id.iv_home_detail_title);
        TextView detailContentText = (TextView) findViewById(R.id.tv_home_detail_content_text);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbarLayout.setTitle(detailTitleName);
        Glide.with(this).load(detailImageID).into(detailTitleImage);
        String detailContent = generateDetailContent(DETAIL_NAME);
        detailContentText.setText(detailContent);
    }

    private String generateDetailContent(String detailName) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i=0; i < 500; i++){
            stringBuilder.append(detailName);
        }
        return stringBuilder.toString();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case  android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
