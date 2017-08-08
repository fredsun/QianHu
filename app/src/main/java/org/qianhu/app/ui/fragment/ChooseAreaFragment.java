package org.qianhu.app.ui.fragment;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.litepal.crud.DataSupport;
import org.qianhu.R;
import org.qianhu.app.api.Utility;
import org.qianhu.app.database.City;
import org.qianhu.app.database.County;
import org.qianhu.app.database.Province;
import org.qianhu.app.database.WeatherDatabaseHelper;
import org.qianhu.app.ui.activity.WeatherActivity;
import org.qianhu.utils.HttpUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by fred on 2017/8/1.
 */

public class ChooseAreaFragment extends Fragment{
    private static final String TAG = "ChooseAreaFragment";

    public static final int LEVEL_PROVINCE = 0;

    public static final int LEVEL_CITY = 1;

    public static final int LEVEL_COUNTY = 2;

    private ProgressDialog progressDialog;

    private TextView tv_weather_area_title;

    private Button btn_back_weather_area;

    private ListView listview_weather_area;

    private ArrayAdapter<String> adapter;

    private List<String> dataList = new ArrayList<>();

    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS,
    };

    private WeatherDatabaseHelper weatherDatabaseHelper;

    /**
     * 省列表
     */
    private List<Province> provinceList;

    /**
     * 市列表
     */
    private List<City> cityList;

    /**
     * 县列表
     */
    private List<County> countyList;

    /**
     * 选中的省份
     */
    private Province selectedProvince;

    /**
     * 选中的城市
     */
    private City selectedCity;

    /**
     * 当前选中的级别
     */
    private int currentLevel;

    /**
     * 直辖市
     * */
    String[] municipalities=new String[]{"北京","天津","上海","重庆"};


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather_choose_area, container, false);
        btn_back_weather_area = (Button) view.findViewById(R.id.btn_weather_area_close);
        tv_weather_area_title = (TextView) view.findViewById(R.id.tv_weather_area_title);
        listview_weather_area = (ListView) view.findViewById(R.id.lv_weather_area);

        adapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_list_item_1,
                dataList);
        listview_weather_area.setAdapter(adapter);
        return view;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listview_weather_area.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (currentLevel == LEVEL_PROVINCE){
                    selectedProvince = provinceList.get(position);
                    queryCityFromDB(selectedProvince.getProvinceName());
                }else if (currentLevel == LEVEL_CITY){
                    selectedCity = cityList.get(position);
                    if (Arrays.asList(municipalities).contains(selectedProvince.getProvinceName())){

                        Intent intent = new Intent(getActivity(), WeatherActivity.class);
                        intent.putExtra("weather_id", selectedCity.getCityName());
                        startActivity(intent);
                    } else {
                        queryCountyFromDB(selectedCity.getCityName());
                    }
                }else if (currentLevel == LEVEL_COUNTY){
                    String weatherId = countyList.get(position).getWeatherId();
                    Intent intent = new Intent(getActivity(), WeatherActivity.class);
                    intent.putExtra("weather_id", weatherId);
                    startActivity(intent);
                }
            }
        });

        btn_back_weather_area.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentLevel == LEVEL_COUNTY){
                    queryCityFromDB(selectedProvince.getProvinceName());
                }else if (currentLevel == LEVEL_CITY){
                    queryProvinceFromDB();
                }
            }
        });
        queryProvinceFromDB();
    }

    private void queryProvinceFromDB() {
        provinceList = new ArrayList<>();
        SQLiteDatabase weatherDB = null;
        weatherDatabaseHelper = new WeatherDatabaseHelper(getContext(), "qianhu.db",null,1);
        try{
            weatherDB = weatherDatabaseHelper.getReadableDatabase();
            String sql = "SELECT DISTINCT province_name_ch FROM HeFengWeatherCity";
            Cursor cursor = weatherDB.rawQuery(sql,null);
            if (null  != cursor){
                while (cursor.moveToNext()){
                    Province province = new Province();
                    String provinceName = cursor.getString(cursor.getColumnIndex("province_name_ch"));
                    province.setProvinceName(provinceName);
                    provinceList.add(province);
                    Log.i("provinceName", provinceName);
                }
            }
            cursor.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (null != weatherDB){
                weatherDB.close();
            }
        }

        tv_weather_area_title.setText("中国");
        btn_back_weather_area.setVisibility(View.GONE);

        dataList.clear();
        for (Province province : provinceList){
            dataList.add(province.getProvinceName());
        }
        adapter.notifyDataSetChanged();
        listview_weather_area.setSelection(0);
        currentLevel = LEVEL_PROVINCE;


    }

    private void queryCityFromDB(String provinceName){
        cityList = new ArrayList<>();
        SQLiteDatabase weatherDB = null;
        weatherDatabaseHelper = new WeatherDatabaseHelper(getContext(), "qianhu.db",null,1);
        try{
            weatherDB = weatherDatabaseHelper.getReadableDatabase();
            String sql ;
            if (Arrays.asList(municipalities).contains(provinceName)){
                sql = "SELECT area_ch FROM HeFengWeatherCity WHERE province_name_ch = '"+provinceName+"' AND area_ch != province_name_ch";
            }else {
                sql = "SELECT area_ch FROM HeFengWeatherCity WHERE province_name_ch = '"+provinceName+"' AND belong_area_name_ch = area_ch";
            }
            Cursor cursor = weatherDB.rawQuery(sql,null);
            if (null  != cursor){
                while (cursor.moveToNext()){
                    City city = new City();
                    String cityName = cursor.getString(cursor.getColumnIndex("area_ch"));
                    city.setCityName(cityName);
                    cityList.add(city);
                    Log.i("cityName", cityName);
                }
            }
            cursor.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (null != weatherDB){
                weatherDB.close();
            }
        }

        tv_weather_area_title.setText(selectedProvince.getProvinceName());
        btn_back_weather_area.setVisibility(View.VISIBLE);
        if (cityList.size() > 0){
            dataList.clear();
            for (City city : cityList){
                dataList.add(city.getCityName());
            }
            adapter.notifyDataSetChanged();
            listview_weather_area.setSelection(0);
            currentLevel = LEVEL_CITY;
        }else {
//            int provinceId = selectedProvince.getProvinceCode();
//            String address = "http://guolin.teach/api/china/" + provinceId;
//            queryFromServer(address, "city");
        }
    }

    private void queryCountyFromDB(String cityName){
        countyList = new ArrayList<>();
        SQLiteDatabase weatherDB = null;
        weatherDatabaseHelper = new WeatherDatabaseHelper(getContext(), "qianhu.db",null,1);
        try{
            weatherDB = weatherDatabaseHelper.getReadableDatabase();
            String sql = "SELECT area_ch,area_id FROM HeFengWeatherCity WHERE belong_area_name_ch = '"+cityName+"' AND belong_area_name_ch != area_ch";
            Cursor cursor = weatherDB.rawQuery(sql,null);
            if (null  != cursor){
                while (cursor.moveToNext()){
                    County county = new County();
                    String countyName = cursor.getString(cursor.getColumnIndex("area_ch"));
                    String countyId = cursor.getString(cursor.getColumnIndex("area_id"));
                    county.setCountyName(countyName);
                    county.setWeatherId(countyId);
                    countyList.add(county);
                    Log.i("countyName", countyName);
                }
            }
            cursor.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (null != weatherDB){
                weatherDB.close();
            }
        }

        tv_weather_area_title.setText(selectedCity.getCityName());
        btn_back_weather_area.setVisibility(View.VISIBLE);
        if (countyList.size() > 0){
            dataList.clear();
            for (County county : countyList){
                dataList.add(county.getCountyName());
            }
            adapter.notifyDataSetChanged();
            listview_weather_area.setSelection(0);
            currentLevel = LEVEL_COUNTY;
        }else {
//            int provinceId = selectedProvince.getProvinceCode();
//            int cityCode = selectedCity.getCityCode();
//            String address = "http://guolin.teach/api/china/" + provinceId + "/" + cityCode;
//            queryFromServer(address, "county");
        }
    }



    private void closeProgressDialog() {
        if (progressDialog != null){
            progressDialog.dismiss();
        }
    }

    private void showProgressDialog() {
        if (progressDialog != null){
            ProgressDialog progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("正在加载");
            progressDialog.setCanceledOnTouchOutside(false);
        }
    }

}
