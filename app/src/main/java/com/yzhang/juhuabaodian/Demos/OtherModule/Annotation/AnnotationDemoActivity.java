package com.yzhang.juhuabaodian.Demos.OtherModule.Annotation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by yzhang on 2017/4/24.
 */

public class AnnotationDemoActivity extends AppCompatActivity {

    @testAnnotation(value = "test1")
    private String test1;
    @testAnnotation(value = "test2")
    public String test2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView textView = new TextView(this);
        textView.setText("@Documented\n" +
                "@Target(ElementType.FIELD)\n" +
                "@Retention(RetentionPolicy.RUNTIME)\n" +
                "public @interface testAnnotation {\n" +
                "    String value() default \"\";\n" +
                "}");
        setContentView(textView);

        testAnnotationReflection.inject(this);
    }
}
