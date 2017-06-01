package com.yzhang.juhuabaodian.Demos.OSBaseModule.PermissionForMarshmallow;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.yzhang.SweetAlertDialog.SweetAlertDialog;
import com.yzhang.utils.PermissionUtils;

import java.util.List;

import android.provider.Settings;

/**
 * Created by yzhang on 2017/3/22.
 */

public class PermissionDemoActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        textView = new TextView(this);
        textView.setTextSize(20f);
        textView.setTextColor(Color.BLACK);
        setContentView(textView);

        textView.setText("动态申请照相机权限");
        PermissionUtils.checkPermissionAndRequest(this, Manifest.permission.CAMERA, 0);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        PermissionUtils.checkPermissionAndRequest(this, Manifest.permission.CAMERA, 0);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (PermissionUtils.onRequestPermissionsResult(this, requestCode, permissions, grantResults)) {
            textView.setText("动态申请照相机权限" + "成功");
        } else {
            textView.setText("动态申请照相机权限" + "失败");
        }

        PermissionUtils.onRequestPermissionsResult(this, requestCode, permissions, grantResults, new PermissionUtils.PermissionCallBack() {
            @Override
            public void Success() {
                Toast.makeText(PermissionDemoActivity.this, "动态申请照相机权限成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void Defeat(List<String> unRequestPermissions) {
                Toast.makeText(PermissionDemoActivity.this, "动态申请照相机权限失败", Toast.LENGTH_SHORT).show();
            }
        });

        if (!PermissionUtils.shouldShowRequest(this, Manifest.permission.CAMERA)) {
            new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("抱歉")
                    .setContentText("摄像头权限是软件提供优质服务所需的基础权限，不会涉及个人隐私，请允许访问。")
                    .setCancelText("不同意设置")
                    .setConfirmText("马上设置")
                    .showCancelButton(true)
                    .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.setTitleText("抱歉")
                                    .setContentText("您将无法使用摄像头功能")
                                    .setConfirmText("OK")
                                    .showCancelButton(false)
                                    .setCancelClickListener(null)
                                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sDialog) {
                                            sDialog.dismissWithAnimation();
                                            PermissionDemoActivity.this.finish();
                                        }
                                    })
                                    .changeAlertType(SweetAlertDialog.ERROR_TYPE);
                        }
                    }).setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sDialog) {
                    sDialog.dismissWithAnimation();
                    Intent i = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    String pkg = "com.android.settings";
                    String cls = "com.android.settings.applications.InstalledAppDetails";
                    i.setComponent(new ComponentName(pkg, cls));
                    i.setData(Uri.parse("package:" + getPackageName()));
                    startActivityForResult(i, 0);
                }
            }).show();
        }
    }
}
