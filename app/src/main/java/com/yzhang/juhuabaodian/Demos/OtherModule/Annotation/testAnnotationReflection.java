package com.yzhang.juhuabaodian.Demos.OtherModule.Annotation;

import android.util.Log;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * Created by yzhang on 2017/4/24.
 */

public class testAnnotationReflection {

    public static void inject(Object clazz) {
        String className = clazz.getClass().getName();
        try {
            Class c = clazz.getClass();
            Field[] fields = c.getDeclaredFields();
            for (Field f : fields) {
                Annotation[] annotations = f.getAnnotations();
                for (Annotation annotation : annotations) {
                    Log.e(f.getName(), annotation.toString());
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Unable to inject for " + className, e);
        }
    }
}
