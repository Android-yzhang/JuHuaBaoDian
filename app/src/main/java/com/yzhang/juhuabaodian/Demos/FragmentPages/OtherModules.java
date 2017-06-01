package com.yzhang.juhuabaodian.Demos.FragmentPages;

import android.content.Intent;
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
import com.yzhang.juhuabaodian.Demos.OtherModule.APT.APTDemoActivity;
import com.yzhang.juhuabaodian.Demos.OtherModule.Annotation.AnnotationDemoActivity;
import com.yzhang.juhuabaodian.Demos.OtherModule.Retrofit.RetrofitDemoActivity;
import com.yzhang.juhuabaodian.Demos.OtherModule.RxJava.RxJavaDemoActivity;
import com.yzhang.juhuabaodian.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yzhang on 2017/2/14.
 */

public class OtherModules extends Fragment {
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

        PageMenuListItemBean Annotation = new PageMenuListItemBean("Annotation简介", "OtherModules/Annotation", AnnotationDemoActivity.class);
        list.add(Annotation);

        PageMenuListItemBean APT = new PageMenuListItemBean("APT简介", "OtherModules/APT", APTDemoActivity.class);
        list.add(APT);

        PageMenuListItemBean RxJava = new PageMenuListItemBean("RxJava简介", "OtherModules/RxJava", RxJavaDemoActivity.class);
        list.add(RxJava);

        PageMenuListItemBean Retrofit = new PageMenuListItemBean("Retrofit简介", "OtherModules/Retrofit", RetrofitDemoActivity.class);
        list.add(Retrofit);
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
            MenuAdapter.ViewHolder viewHolder;
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.demos_list_menu_adapter_view, null);
                viewHolder = new MenuAdapter.ViewHolder();
                viewHolder.text = (TextView) convertView.findViewById(R.id.text);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (MenuAdapter.ViewHolder) convertView.getTag();
            }
            viewHolder.text.setText(list.get(position).getName());

            return convertView;
        }

        private class ViewHolder {
            TextView text;
        }
    }
}
