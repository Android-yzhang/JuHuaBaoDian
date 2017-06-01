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
import com.yzhang.juhuabaodian.Demos.CustomWidgetModule.CalendarListView.CalendarActivity;
import com.yzhang.juhuabaodian.Demos.CustomWidgetModule.CircleRangeView.CircleRangeViewDemoActivity;
import com.yzhang.juhuabaodian.Demos.CustomWidgetModule.CollapseCalendarView.CollapseCalendarViewDemoActivity;
import com.yzhang.juhuabaodian.Demos.CustomWidgetModule.MPAndroidChart.testChartActivity;
import com.yzhang.juhuabaodian.Demos.CustomWidgetModule.SlideView.SlideViewDemoActivity;
import com.yzhang.juhuabaodian.Demos.CustomWidgetModule.SweetAlertDialog.SweetAlertDialogDemoActivity;
import com.yzhang.juhuabaodian.Demos.CustomWidgetModule.Toasty.ToastyDemoActivity;
import com.yzhang.juhuabaodian.Demos.CustomWidgetModule.UnderLineLinearLayout.UnderLineLinearLayoutDemoActivity;
import com.yzhang.juhuabaodian.Demos.CustomWidgetModule.ViewDragHelper.ViewDragHelperDemoActivity;
import com.yzhang.juhuabaodian.Demos.DemoBaseActivity;
import com.yzhang.juhuabaodian.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yzhang on 2017/2/14.
 */

public class CustomWidgetModules extends Fragment {
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

        PageMenuListItemBean SweetAlertDialog = new PageMenuListItemBean("自定义对话框SweetAlertDialog", "CustomWidgetModules/SweetAlertDialog", SweetAlertDialogDemoActivity.class);
        list.add(SweetAlertDialog);

        PageMenuListItemBean CalendarListView = new PageMenuListItemBean("自定义日历CalendarListView", "CustomWidgetModules/CalendarListView", CalendarActivity.class);
        list.add(CalendarListView);

        PageMenuListItemBean CollapseCalendarView = new PageMenuListItemBean("自定义日历CollapseCalendarView", "CustomWidgetModules/CollapseCalendarView", CollapseCalendarViewDemoActivity.class);
        list.add(CollapseCalendarView);

        PageMenuListItemBean CircleRangeView = new PageMenuListItemBean("自定义仪表盘CircleRangeView", "CustomWidgetModules/CircleRangeView", CircleRangeViewDemoActivity.class);
        list.add(CircleRangeView);

        PageMenuListItemBean UnderLineLinearLayout = new PageMenuListItemBean("自定义时间线UnderLineLinearLayout", "CustomWidgetModules/UnderLineLinearLayout", UnderLineLinearLayoutDemoActivity.class);
        list.add(UnderLineLinearLayout);

        PageMenuListItemBean Toasty = new PageMenuListItemBean("自定义Toast", "CustomWidgetModules/Toasty", ToastyDemoActivity.class);
        list.add(Toasty);

        PageMenuListItemBean MPAndroidChart = new PageMenuListItemBean("自定义图标工具集MPAndroidChart", "CustomWidgetModules/MPAndroidChart", testChartActivity.class);
        list.add(MPAndroidChart);

        PageMenuListItemBean SlideView = new PageMenuListItemBean("自定义轮播控件", "CustomWidgetModules/SlideView", SlideViewDemoActivity.class);
        list.add(SlideView);

        PageMenuListItemBean VDHLayout = new PageMenuListItemBean("自定义拖拽控件", "CustomWidgetModules/VDHLayout", ViewDragHelperDemoActivity.class);
        list.add(VDHLayout);
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
