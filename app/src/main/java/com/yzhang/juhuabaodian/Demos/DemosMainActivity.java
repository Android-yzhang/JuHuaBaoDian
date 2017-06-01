package com.yzhang.juhuabaodian.Demos;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.yzhang.juhuabaodian.Demos.Beans.MainMenuListItemBean;
import com.yzhang.juhuabaodian.Demos.FragmentPages.CustomWidgetModules;
import com.yzhang.juhuabaodian.Demos.FragmentPages.OSBaseModules;
import com.yzhang.juhuabaodian.Demos.FragmentPages.OtherModules;
import com.yzhang.juhuabaodian.Demos.FragmentPages.WidgetModules;
import com.yzhang.juhuabaodian.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yzhang on 2017/2/10.
 */

public class DemosMainActivity extends AppCompatActivity {

    DrawerLayout drawer_layout;
    FrameLayout main_layout;
    ListView main_page_list_menu;
    ListMenuAdapter adapter;
    List<MainMenuListItemBean> MenuItems;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demos_activity_main);
        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
        main_layout = (FrameLayout) findViewById(R.id.main_layout);
        main_page_list_menu = (ListView) findViewById(R.id.main_page_list_menu);

        initMenuItems();

        adapter = new ListMenuAdapter();
        main_page_list_menu.setAdapter(adapter);
        main_page_list_menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.replace(R.id.main_layout, MenuItems.get(position).getPage(), MenuItems.get(position).getName());
                transaction.commit();
                drawer_layout.closeDrawers();
            }
        });

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.add(R.id.main_layout, new OSBaseModules(), "OSModule");
        transaction.commit();
    }

    private void initMenuItems() {
        MenuItems = new ArrayList<>();

        MainMenuListItemBean OSBean = new MainMenuListItemBean("系统基本组件", new OSBaseModules());
        MenuItems.add(OSBean);

        MainMenuListItemBean WidgetBean = new MainMenuListItemBean("基本控件", new WidgetModules());
        MenuItems.add(WidgetBean);

        MainMenuListItemBean CustomWidgetBean = new MainMenuListItemBean("自定义控件", new CustomWidgetModules());
        MenuItems.add(CustomWidgetBean);

        MainMenuListItemBean OthersBean = new MainMenuListItemBean("其他", new OtherModules());
        MenuItems.add(OthersBean);
    }

    private class ListMenuAdapter extends BaseAdapter {
        LayoutInflater mInflater = LayoutInflater.from(DemosMainActivity.this);

        @Override
        public int getCount() {
            return MenuItems.size();
        }

        @Override
        public Object getItem(int position) {
            return MenuItems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.demos_list_menu_adapter_view, null);
                viewHolder = new ViewHolder();
                viewHolder.text = (TextView) convertView.findViewById(R.id.text);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.text.setText(MenuItems.get(position).getName());

            return convertView;
        }

        private class ViewHolder {
            TextView text;
        }
    }
}
