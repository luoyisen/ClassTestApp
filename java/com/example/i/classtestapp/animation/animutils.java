package com.example.i.classtestapp.animation;

import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;

import com.example.i.classtestapp.holder.ShapeHolder;

/**
 * Created by I on 2017/6/9.
 */

public class animutils{
    ShapeHolder apple = null;

    private ShapeHolder createApple(float x, float y) {
        OvalShape circle = new OvalShape();
        circle.resize(50f, 50f);
        ShapeDrawable drawable = new ShapeDrawable(circle);
        ShapeHolder shapeHolder = new ShapeHolder(drawable);
        shapeHolder.setX(x - 25f);
        shapeHolder.setY(y - 25f);
        int red = (int)(Math.random() * 255);
        int green = (int)(Math.random() * 255);
        int blue = (int)(Math.random() * 255);
        int color = 0xff000000 | red << 16 | green << 8 | blue;
        Paint paint = drawable.getPaint(); //new Paint(Paint.ANTI_ALIAS_FLAG);
        int darkColor = 0xff000000 | red/4 << 16 | green/4 << 8 | blue/4;
        RadialGradient gradient = new RadialGradient(37.5f, 12.5f,
                50f, color, darkColor, Shader.TileMode.CLAMP);
        paint.setShader(gradient);
        shapeHolder.setPaint(paint);
        return shapeHolder;
    }

}
