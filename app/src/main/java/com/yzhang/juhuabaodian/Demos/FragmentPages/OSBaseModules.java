package com.yzhang.juhuabaodian.Demos.FragmentPages;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.yzhang.juhuabaodian.Demos.Beans.PageMenuListItemBean;
import com.yzhang.juhuabaodian.Demos.DemoBaseActivity;
import com.yzhang.juhuabaodian.Demos.OSBaseModule.ClassLoader.ClassLoaderDemoActivity;
import com.yzhang.juhuabaodian.Demos.OSBaseModule.ContextAndOthers.ContextAndOthersMainActivity;
import com.yzhang.juhuabaodian.Demos.OSBaseModule.PermissionForMarshmallow.PermissionDemoActivity;
import com.yzhang.juhuabaodian.Demos.OSBaseModule.ReflectAndProxy.ProxyDemoActivity;
import com.yzhang.juhuabaodian.Demos.OSBaseModule.ReflectAndProxy.ReflectDemoActivity;
import com.yzhang.juhuabaodian.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yzhang on 2017/2/13.
 */

public class OSBaseModules extends Fragment {

    ListView listView;

    List<PageMenuListItemBean> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.base_fragment, container, false);
        listView = (ListView) view.findViewById(R.id.fragmeng_menu);

        initList();

        MenuAdapter adapter = new MenuAdapter();
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), DemoBaseActivity.class);
                Bundle bundle = new Bundle();
                PageMenuListItemBean bean = list.get(position);
                bundle.putSerializable("bean", bean);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        return view;
    }

    private void initList() {
        list = new ArrayList<>();

        PageMenuListItemBean Context = new PageMenuListItemBean("context、4大基本组件、Intent、bundle和Application", "OSBaseModule/Context及其相关", ContextAndOthersMainActivity.class);
        list.add(Context);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PageMenuListItemBean Permissions = new PageMenuListItemBean("6.0运行时权限系统", "OSBaseModule/permission权限", PermissionDemoActivity.class);
            list.add(Permissions);
        }

        PageMenuListItemBean ClassLoader = new PageMenuListItemBean("类加载器ClassLoader", "OSBaseModule/ClassLoader", ClassLoaderDemoActivity.class);
        list.add(ClassLoader);

        PageMenuListItemBean Reflect = new PageMenuListItemBean("反射Reflect", "OSBaseModule/Reflect", ReflectDemoActivity.class);
        list.add(Reflect);

        PageMenuListItemBean Proxy = new PageMenuListItemBean("动态代理Proxy", "OSBaseModule/Proxy", ProxyDemoActivity.class);
        list.add(Proxy);
        /**
         * Activity
         * Service
         * BroadcastReceiver
         * ContextProvider
         * Intent
         * Bundle
         * Context
         * Application
         * Handler (消息分发机制 ， HandlerThread)
         * Thread (多线程)
         * 线程池
         * AsyncTask (异步任务)
         * Process (Shell 命令)
         * Java Native Interface (JNI)
         * RenderScript
         * Socket
         * http(https)
         * Dialog
         * Notification
         * PopupWindow
         * FloatWindow(悬浮窗)
         * Bluetooth
         * Media
         * OpenGL
         */
        PageMenuListItemBean SQL = new PageMenuListItemBean("SQL", "test2", null);
        list.add(SQL);

    }

    private class MenuAdapter extends BaseAdapter {

        LayoutInflater mInflater = LayoutInflater.from(getActivity());

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
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
            viewHolder.text.setText(list.get(position).getName());

            return convertView;
        }

        private class ViewHolder {
            TextView text;
        }
    }
}
