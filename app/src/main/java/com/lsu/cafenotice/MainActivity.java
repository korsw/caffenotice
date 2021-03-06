package com.lsu.cafenotice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.Arrays;
import java.util.List;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {

    RecyclerviewAdapter adapter = new RecyclerviewAdapter();
    private AppBarConfiguration mAppBarConfiguration;


    public LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.DefaultTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Animation rotate = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate);
        //ImageView menu = findViewById(R.id.menu);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        //커스터마이징 하기 위해 필요
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // 툴바 메뉴버튼 생성
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
        // 메뉴 버튼 모양 설정
        //툴바 배경색


        init();

        getData();

        /*menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu.startAnimation(rotate);
                Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }

        });
         */
    }


    private void init() {
        RecyclerView recyclerView = findViewById(R.id.logoRecyclerView);

        RecyclerDecoration spaceDecoration = new RecyclerDecoration(30);
        recyclerView.addItemDecoration(spaceDecoration);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        recyclerView.setAdapter(adapter);
    }

    private void getData() {
        List<String> listName = Arrays.asList(
                "스타벅스",
                "투썸플레이스",
                "커피빈",
                "탐앤탐스",
                "엔제리너스",
                "할리스커피"

        );

        List<Integer> listResId = Arrays.asList(
                R.drawable.logo_starbucks,
                R.drawable.logo_atwosomeplace,
                R.drawable.logo_coffeebean,
                R.drawable.logo_tomntoms,
                R.drawable.logo_angelinus,
                R.drawable.logo_hollys
        );

        for (int i = 0; i < listResId.size(); i++){
            Recyclerviewitem data = new Recyclerviewitem();
            data.setName(listName.get(i));
            data.setResId(listResId.get(i));
            data.setWitdh(getDisplayWidth());

            adapter.addItem(data);
        }


        adapter.notifyDataSetChanged();
    }

    public int getDisplayWidth(){
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics ();
        display.getMetrics(outMetrics);

        float density = getResources().getDisplayMetrics().density;
        //float dpHeight = outMetrics.heightPixels / density;
        float dpWidth = outMetrics.widthPixels / density;

        return (int)dpWidth;
    }

    private void setActionBar() {
        Action_bar ca = new Action_bar(this, getSupportActionBar());
        ca.setResId(R.drawable.ic_arrow_back);
        ca.setActionBar();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home : {
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            }
            default: return super.onOptionsItemSelected(item);
        }
    }
}