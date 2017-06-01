package com.yzhang.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by yzhang on 2017/2/9.
 */

public final class JSONUtils {

    /**
     * 把Josn转换为Map形式
     */
    public static Map<String, Object> getMap(String jsonString) {
        JSONObject jsonObject;
        try {

            jsonObject = new JSONObject(jsonString);
            @SuppressWarnings("unchecked")
            Iterator<String> keyIter = jsonObject.keys();

            String key;

            Object value;

            Map<String, Object> valueMap = new HashMap<String, Object>();

            while (keyIter.hasNext()) {

                key = (String) keyIter.next();

                value = jsonObject.get(key);

                if (value.equals(null)) {
                    value = "";
                }
                valueMap.put(key, value);
            }
            return valueMap;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 把Josn 转换为ArrayList形式
     */
    public static List<Map<String, Object>> getList(String jsonString) {
        List<Map<String, Object>> list = null;
        try {

            JSONArray jsonArray = new JSONArray(jsonString);

            JSONObject jsonObject;

            list = new ArrayList<Map<String, Object>>();

            for (int i = 0; i < jsonArray.length(); i++) {

                jsonObject = jsonArray.getJSONObject(i);
                list.add(getMap(jsonObject.toString()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 把Josn 转换为ArrayList 形式
     */
    public static List<Map<String, Object>> getFakeList(String jsonString) {
        List<Map<String, Object>> list = null;
        try {

            JSONArray jsonArray = new JSONArray(jsonString);

            JSONObject jsonObject;

            list = new ArrayList<Map<String, Object>>();

            for (int i = 0; i < jsonArray.length(); i++) {

                jsonObject = jsonArray.getJSONObject(i);
                list.add(getMap(jsonObject.toString()));
            }

        } catch (Exception e) {
            e.printStackTrace();

        }

        return list;
    }

    /**
     * 把Josn 转换为Array List<Object> 形式
     */
    public static String[] JSONArrayToStringArray(String jsonString) {
        String[] str_array = null;
        try {
            JSONArray jsonArray = new JSONArray(jsonString);
            str_array = new String[jsonArray.length()];

            for (int i = 0; i < jsonArray.length(); i++) {
                String str = jsonArray.get(i).toString();
                str_array[i] = str;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return str_array;
    }

    /**
     * 把Josn 转换为 List<Object> 形式
     */
    public static List<Object> JSONArrayToList(String jsonString) {
        List<Object> arraylist = new ArrayList<Object>();
        try {
            JSONArray jsonArray = new JSONArray(jsonString);

            for (int i = 0; i < jsonArray.length(); i++) {
                String item = jsonArray.get(i).toString();
                arraylist.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return arraylist;
    }

    /**
     * 将对象分装为json字符串 (json + 递归)
     *
     * @param obj 参数应为{@link java.util.Map} 或者 {@link java.util.List}
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Object jsonEnclose(Object obj) {
        try {
            if (obj instanceof Map) {   //如果是Map则转换为JsonObject
                Map<String, Object> map = (Map<String, Object>) obj;
                Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
                JSONStringer jsonStringer = new JSONStringer().object();
                while (iterator.hasNext()) {
                    Map.Entry<String, Object> entry = iterator.next();
                    jsonStringer.key(entry.getKey()).value(jsonEnclose(entry.getValue()));
                }
                JSONObject jsonObject = new JSONObject(new JSONTokener(jsonStringer.endObject().toString()));
                return jsonObject;
            } else if (obj instanceof List) {  //如果是List则转换为JsonArray
                List<Object> list = (List<Object>) obj;
                JSONStringer jsonStringer = new JSONStringer().array();
                for (int i = 0; i < list.size(); i++) {
                    jsonStringer.value(jsonEnclose(list.get(i)));
                }
                JSONArray jsonArray = new JSONArray(new JSONTokener(jsonStringer.endArray().toString()));
                return jsonArray;
            } else {
                return obj;
            }
        } catch (Exception e) {
            // TODO: handle exception
//            LogUtil.e("jsonUtil--Enclose", e.getMessage());
            return e.getMessage();
        }
    }


}
