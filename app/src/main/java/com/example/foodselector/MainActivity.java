package com.example.foodselector;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;
import androidx.fragment.app.FragmentTransaction;
import com.example.foodselector.ui.home.HomeFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 获取侧边栏控件
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        // 默认加载HomeFragment
        if (savedInstanceState == null) {
            loadFragment(new HomeFragment());
        }

        // 配置 NavigationView 的点击事件
    }

    // 用于加载Fragment的通用方法
    private void loadFragment(androidx.fragment.app.Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment_content_main, fragment);
        transaction.addToBackStack(null);  // 可以让Fragment添加到回退栈，这样按返回键可以返回到上一个Fragment
        transaction.commit();
    }

    @Override
    public boolean onSupportNavigateUp() {
        // 返回按钮处理
        onBackPressed();
        return true;
    }
}
