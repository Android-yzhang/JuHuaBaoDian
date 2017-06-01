package com.yzhang.APTtestViewFinderCompiler.model;

import com.yzhang.APTtestViewFinder.FindViewByID;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Name;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;

/**
 * Created by yzhang on 2017/4/24.
 */

public class FindViewByIDField {
    private VariableElement mFieldElement;
    private int mResId;

    public FindViewByIDField(Element element) throws IllegalArgumentException {
        if (element.getKind() != ElementKind.FIELD) {
            throw new IllegalArgumentException(
                    String.format("Only fields can be annotated with @%s", FindViewByID.class.getSimpleName()));
        }

        mFieldElement = (VariableElement) element;
        FindViewByID bindView = mFieldElement.getAnnotation(FindViewByID.class);
        mResId = bindView.value();

        if (mResId < 0) {
            throw new IllegalArgumentException(
                    String.format("value() in %s for field %s is not valid !", FindViewByID.class.getSimpleName(),
                            mFieldElement.getSimpleName()));
        }
    }

    public Name getFieldName() {
        return mFieldElement.getSimpleName();
    }

    public int getResId() {
        return mResId;
    }

    public TypeMirror getFieldType() {
        return mFieldElement.asType();
    }
}
