package com.example.i.classtestapp.views;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.example.i.classtestapp.utils.FontCache;


public class TextViewTypeface extends AppCompatTextView {
    public int textviewlength;

    public TextViewTypeface(Context context) {
        super(context);

        applyCustomFont(context);
    }

    public TextViewTypeface(Context context, AttributeSet attrs) {
        super(context, attrs);

        applyCustomFont(context);
    }

    public TextViewTypeface(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        applyCustomFont(context);
    }

    private void applyCustomFont(Context context) {
        Typeface customFont = FontCache.getTypeface("fonts/arialrounded.ttf", context);
        setTypeface(customFont);
    }


}
