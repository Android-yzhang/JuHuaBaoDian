package com.yzhang.juhuabaodian.Demos.OSBaseModule.PermissionForMarshmallow;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.yzhang.SweetAlertDialog.SweetAlertDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PermissionDemo2 extends AppCompatActivity {

    List<String> Permissions = new ArrayList<>();
    Map<String, String> PermissionMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        PermissionMap.put(Manifest.permission.ACCESS_FINE_LOCATION, "获取地理位置权限");
        PermissionMap.put(Manifest.permission.ACCESS_COARSE_LOCATION, "定位权限");
        PermissionMap.put(Manifest.permission.CAMERA, "拍照权限");

        checkPermissions();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        Permissions.clear();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < permissions.length; i++) {
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                Permissions.add(permissions[i]);
            }
        }
        Iterator<String> it = Permissions.iterator();
        while (it.hasNext()) {
            String permission = it.next();
            if (!ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                stringBuffer.append(PermissionMap.get(permission)).append(",");
            }
        }
        if (stringBuffer.length() != 0) {
            showPermissionDialog(stringBuffer.toString());
            return;
        }
//        startActivity(new Intent(this , BLEDemo.class));
//        finish();
        Toast.makeText(this, "权限通过", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        checkPermissions();
    }

    private void checkPermissions() {
        Permissions.clear();
        for (String key : PermissionMap.keySet()) {
            if (ContextCompat.checkSelfPermission(this, key)
                    != PackageManager.PERMISSION_GRANTED) {
                Permissions.add(key);
            }
        }
//        Iterator<String> it = Permissions.iterator();
//        StringBuffer stringBuffer = new StringBuffer();
//        while(it.hasNext()){
//            String permission = it.next();
//            if (ActivityCompat.shouldShowRequestPermissionRationale(this,permission)) {
//                stringBuffer.append(PermissionMap.get(permission)).append(",");
//            }
//        }
//        if(stringBuffer.length() != 0){
//            showPermissionDialog(stringBuffer.toString());
//            return;
//        }
        if (Permissions.size() != 0) {
            ActivityCompat.requestPermissions(this, Permissions.toArray(new String[Permissions.size()]), 0);
            return;
        }
//        startActivity(new Intent(this , BLEDemo.class));
//        finish();
        Toast.makeText(this, "权限通过", Toast.LENGTH_SHORT).show();
    }

    private void showPermissionDialog(String unShowPermissions) {
        /*SweetAlertDialog dialog = */
        new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("抱歉")
                .setContentText(unShowPermissions + "是软件提供优质服务所需的基础权限，不会涉及个人隐私，请允许访问。")
                .setCancelText("不同意设置")
                .setConfirmText("马上设置")
                .showCancelButton(true)
                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.setTitleText("抱歉")
                                .setContentText("您将无法使用本软件")
                                .setConfirmText("OK")
                                .showCancelButton(false)
                                .setCancelClickListener(null)
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sDialog) {
                                        sDialog.dismissWithAnimation();
//                                        MainActivity.this.finish();
                                        Toast.makeText(PermissionDemo2.this, "权限未通过", Toast.LENGTH_SHORT).show();
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
//        dialog.setCancelable(false);
//        dialog.show();
    }
}
