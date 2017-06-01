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
import com.yzhang.juhuabaodian.Demos.WidgetModule.BottomNavigationView.BottomNavigationViewDemoActivity;
import com.yzhang.juhuabaodian.Demos.WidgetModule.CoordinatorLayout.CoordinatorLayoutDemoActivity;
import com.yzhang.juhuabaodian.Demos.WidgetModule.RecyclerView.RecyclerViewDemoMainActivity;
import com.yzhang.juhuabaodian.Demos.WidgetModule.ViewAndViewGroup.ViewAndViewGroupMainActivity;
import com.yzhang.juhuabaodian.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yzhang on 2017/2/14.
 */

public class WidgetModules extends Fragment {
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

        PageMenuListItemBean View = new PageMenuListItemBean("View简介", "WidgetModules/View及其相关", ViewAndViewGroupMainActivity.class);
        list.add(View);

        PageMenuListItemBean RecyclerView = new PageMenuListItemBean("RecyclerView简介", "WidgetModules/RecyclerView", RecyclerViewDemoMainActivity.class);
        list.add(RecyclerView);

        PageMenuListItemBean BottomNavigationView = new PageMenuListItemBean("BottomNavigationView简介", "WidgetModules/BottomNavigationView", BottomNavigationViewDemoActivity.class);
        list.add(BottomNavigationView);

        PageMenuListItemBean CoordinatorLayout = new PageMenuListItemBean("CoordinatorLayout简介", "WidgetModules/CoordinatorLayout", CoordinatorLayoutDemoActivity.class);
        list.add(CoordinatorLayout);
        /**
         * View
         * Layout(布局基础属性) (http://blog.csdn.net/xyz_lmn/article/details/14524567)
         * LinearLayout
         * RelativeLayout
         * GridLayout
         * TableLayout
         * FrameLayout
         * ConstraintLayout (约束布局，增强型RelativeLayout)
         * TextView
         * EditText
         * Button
         * ImageView
         * CheckBox
         * RadioButton(RadioGroup)
         * Spinner
         * SeekBar(滑块)
         * ScrollView
         * ListView
         * GridView
         * WebView
         * NumberPicker
         * TimePicker
         * DataPicker
         * CalendarView
         * TextClock
         * SurfaceView
         * ToggleButton
         * CheckedTextView
         * Switch
         * RatingBar(评分控件)
         * Space (空白填充)//http://blog.csdn.net/yulianlin/article/details/52979742
         * AutoCompleteTextView
         * MultiAutoCompleteTextView
         * ExpandableListView (可展开的ListView)
         * VideoView
         * Chronometer(计时器)
         * ImageSwitcher
         * AdapterViewFlipper
         * ViewFlipper
         * StackView
         * TextSwitcher
         * ViewAnimator
         * ViewStub
         * TextureView (SurfaceView基友)
         * CardView
         */

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
