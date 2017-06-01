package com.yzhang.juhuabaodian.Demos.OSBaseModule.ContextAndOthers;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by yzhang on 2017/2/21.
 */

public class MyContentProvider extends ContentProvider {

    private String TAG = getClass().getName();
    private static final String AUTHORITY = "com.yzhang.providers.test";

    private static final UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, "test", 1);
    }

    MatrixCursor matrixCursor;
    int i;

    ContentResolver resolver;

    /**
     * @return
     */
    @Override
    public boolean onCreate() {
        Log.i(TAG, "Provider Create");
        Context context = getContext();
        resolver = context.getContentResolver();
        matrixCursor = new MatrixCursor(new String[]{"id", "name"});
        matrixCursor.addRow(new Object[]{1, "test1"});
        matrixCursor.addRow(new Object[]{2, "test2"});
        i = 2;
        return true;
    }

    /**
     * 查找
     *
     * @param uri
     * @param projection
     * @param selection
     * @param selectionArgs
     * @param sortOrder
     * @return
     */
    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    /**
     * 插入
     *
     * @param uri
     * @param values
     * @return
     */
    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        if (uriMatcher.match(uri) != 1) {
            throw new IllegalArgumentException("Error Uri: " + uri);
        }
        matrixCursor.addRow(new Object[]{i++, values.getAsString("name")});

        Uri newUri = ContentUris.withAppendedId(uri, i);
        resolver.notifyChange(newUri, null);

        return newUri;
    }

    /**
     * 删除
     *
     * @param uri
     * @param selection
     * @param selectionArgs
     * @return
     */
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    /**
     * 更新
     *
     * @param uri
     * @param values
     * @param selection
     * @param selectionArgs
     * @return
     */
    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
