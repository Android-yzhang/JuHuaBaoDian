package com.yzhang.utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.LauncherApps;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yzhang on 2017/3/22.
 */

public final class PermissionUtils {

    public interface PermissionCallBack {
        void Success();

        void Defeat(List<String> unRequestPermissions);
    }

    public static boolean checkPermission(AppCompatActivity activity, String permission) {
        return (ContextCompat.checkSelfPermission(activity, permission)
                != PackageManager.PERMISSION_GRANTED);
    }

    public static boolean checkPermission(Activity activity, String permission) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return false;
        } else {
            return (activity.checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED);
        }
    }

    public static boolean checkPermissionAndRequest(AppCompatActivity activity, String permission, int requestCode) {
        if (checkPermission(activity, permission)) {
            ActivityCompat.requestPermissions(activity, new String[]{permission}, requestCode);
            return true;
        }
        return false;
    }

    public static boolean checkPermissionAndRequest(Activity activity, String permission, int requestCode) {
        if (checkPermission(activity, permission)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                activity.requestPermissions(new String[]{permission}, requestCode);
                return true;
            }
        }
        return false;
    }

    public static boolean checkPermissionsAndRequest(AppCompatActivity activity, String[] permissions, int requestCode) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < permissions.length; i++) {
            if (checkPermission(activity, permissions[i])) {
                list.add(permissions[i]);
            }
        }
        if (list.size() > 0) {
            ActivityCompat.requestPermissions(activity,
                    list.toArray(new String[list.size()]),
                    requestCode);
            return true;
        }
        return false;
    }

    public static boolean checkPermissionsAndRequest(Activity activity, String[] permissions, int requestCode) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < permissions.length; i++) {
            if (checkPermission(activity, permissions[i])) {
                list.add(permissions[i]);
            }
        }
        if (list.size() > 0) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                activity.requestPermissions(
                        list.toArray(new String[list.size()]),
                        requestCode);
                return true;
            }
        }
        return false;
    }

    public static boolean onRequestPermissionsResult(Activity activity, int requestCode, String[] permissions, int[] grantResults) {
        for (int i = 0; i < permissions.length; i++) {
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    public static void onRequestPermissionsResult(Activity activity, int requestCode, String[] permissions, int[] grantResults, PermissionCallBack callBack) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < permissions.length; i++) {
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                list.add(permissions[i]);
            }
        }
        if (list.size() != 0) {
            callBack.Defeat(list);
            return;
        }
        callBack.Success();
    }

    public static boolean shouldShowRequest(AppCompatActivity activity, String permission) {
        return ActivityCompat.shouldShowRequestPermissionRationale(activity, permission);
    }

    public static void shouldShowRequest(AppCompatActivity activity, String permission, LauncherApps.Callback callback) {
        if (!ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {

        }
    }

    public static void shouldShowRequest(Activity activity, String permission) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!activity.shouldShowRequestPermissionRationale(permission)) {

            }
        }
    }
}
