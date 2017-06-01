package com.yzhang.juhuabaodian.Demos;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yzhang.juhuabaodian.Demos.Beans.PageMenuListItemBean;
import com.yzhang.juhuabaodian.R;
import com.yzhang.utils.AssetsUtils;

/**
 * Created by yzhang on 2017/2/14.
 */

public class DemoBaseActivity extends AppCompatActivity {

    Button button;
    TextView textView;

    String name, documentName;
    Class activity;
    PageMenuListItemBean bean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demos_base_activity);
        button = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.textView);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        bean = (PageMenuListItemBean) bundle.getSerializable("bean");
        name = bean.getName();
        documentName = bean.getDocumentName();
        activity = bean.getActivity();

        textView.setText(AssetsUtils.getAssetString(documentName, this));

        if (activity == null) {
            button.setVisibility(View.GONE);
        } else {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(DemoBaseActivity.this, activity));
                }
            });
        }
    }
}
