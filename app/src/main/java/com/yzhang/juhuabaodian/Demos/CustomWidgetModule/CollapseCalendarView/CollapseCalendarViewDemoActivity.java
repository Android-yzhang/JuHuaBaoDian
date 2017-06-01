package com.yzhang.juhuabaodian.Demos.CustomWidgetModule.CollapseCalendarView;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.yzhang.CollapseCalendarView.CollapseCalendarView;
import com.yzhang.CollapseCalendarView.manager.CalendarManager;
import com.yzhang.juhuabaodian.R;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

/**
 * Created by yzhang on 2017/4/5.
 */

public class CollapseCalendarViewDemoActivity extends AppCompatActivity {

    private CollapseCalendarView mCalendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.collapse_calendar_view_demo_activity);

        CalendarManager manager = new CalendarManager(LocalDate.now(), CalendarManager.State.WEEK, LocalDate.fromDateFields(new Date(0)), LocalDate.now());

        mCalendarView = (CollapseCalendarView) findViewById(R.id.calendar);
        mCalendarView.init(manager);

        mCalendarView.setListener(new CollapseCalendarView.OnDateSelect() {
            @Override
            public void onDateSelected(LocalDate date) {
                Toast.makeText(CollapseCalendarViewDemoActivity.this, "" + date.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
