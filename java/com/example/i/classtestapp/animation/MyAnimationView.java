package com.example.i.classtestapp.animation;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

import com.example.i.classtestapp.holder.ShapeHolder;

import java.util.ArrayList;

/**
 * Created by I on 2017/6/10.
 */

public class MyAnimationView extends View implements ValueAnimator.AnimatorUpdateListener {

    public final ArrayList<ShapeHolder> balls = new ArrayList<>();
    ValueAnimator bounceAnim = null;
    ShapeHolder ball = null;

    public MyAnimationView(Context context) {
        super(context);
        ball = createBall(25, 25);
    }

    private void createAnimation() {
        if (bounceAnim == null) {
            bounceAnim = ObjectAnimator.ofFloat(ball, "y", ball.getY(), getHeight() - 50f).
                    setDuration(1500);
            bounceAnim.setInterpolator(new AccelerateInterpolator(2f));
            bounceAnim.addUpdateListener(this);
        }
    }

    public void startAnimation() {
        createAnimation();
        bounceAnim.start();
    }

    public void reverseAnimation() {
        createAnimation();
        bounceAnim.reverse();
    }

    public void seek(long seekTime) {
        createAnimation();
        bounceAnim.setCurrentPlayTime(seekTime);
    }

    private ShapeHolder createBall(float x, float y) {
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

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        canvas.translate(ball.getX(), ball.getY());
        ball.getShape().draw(canvas);
        canvas.restore();
    }

    public void onAnimationUpdate(ValueAnimator animation) {
        invalidate();
    }

}
