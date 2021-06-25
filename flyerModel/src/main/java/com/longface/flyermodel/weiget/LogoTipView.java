package com.longface.flyermodel.weiget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import androidx.annotation.Nullable;

import com.longface.flyermodel.R;

public class LogoTipView extends View {

    private Paint mPaint;
    private Path mPath;
    ValueAnimator valueAnimator;
    private float[] xy = new float[2];
    private float animF = 0.0f;

    private float[][] currentDot = new float[8][2];
    private float[][] targetDot = new float[8][2];
    private float[][] _1Dot = new float[8][2];
    private float[][] _2Dot = new float[8][2];
    private float[][] _3Dot = new float[8][2];
    private float[][] endDot = new float[8][2];

    public LogoTipView(Context context) {
        this(context, null);
    }

    public LogoTipView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LogoTipView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(getResources().getColor(R.color.TextOnP));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(12);
        mPath = new Path();

        initDot();
        initAnim();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.reset();
        mPath.moveTo(getWidth() * getFloat(currentDot[0][0], targetDot[0][0]),
                getHeight() * getFloat(currentDot[0][1], targetDot[0][1]));
        getDot(getFloat(currentDot[1][0], targetDot[1][0]), getFloat(currentDot[1][1], targetDot[1][1]));
        getDot(getFloat(currentDot[2][0], targetDot[2][0]), getFloat(currentDot[2][1], targetDot[2][1]));
        getDot(getFloat(currentDot[3][0], targetDot[3][0]), getFloat(currentDot[3][1], targetDot[3][1]));
        getDot(getFloat(currentDot[4][0], targetDot[4][0]), getFloat(currentDot[4][1], targetDot[4][1]));
        getDot(getFloat(currentDot[5][0], targetDot[5][0]), getFloat(currentDot[5][1], targetDot[5][1]));
        getDot(getFloat(currentDot[6][0], targetDot[6][0]), getFloat(currentDot[6][1], targetDot[6][1]));
        getDot(getFloat(currentDot[7][0], targetDot[7][0]), getFloat(currentDot[7][1], targetDot[7][1]));
        canvas.drawPath(mPath, mPaint);
    }

    private void getDot(float x, float y) {
        xy[0] = getWidth() * x;
        xy[1] = getHeight() * y;
        mPath.lineTo(xy[0], xy[1]);
    }

    private float getFloat(float start, float end) {
        float v = end - start;
        float v1 = v * animF;
        return start + v1;
    }

    public void skipTo(int step) {
        switch (step) {
            case 1:
                targetDot = _1Dot;
                break;
            case 2:
                targetDot = _2Dot;
                break;
            case 3:
                targetDot = _3Dot;
                break;
            case 4:
                if (targetDot == _1Dot) {
                    targetDot = endDot;
                } else {
                    targetDot = _1Dot;
                    isEnd = true;
                }
                break;
        }
        startAnim();
    }

    private boolean isEnd = false;

    public void startAnim() {
        if (valueAnimator.isRunning()) {
            return;
        }
        valueAnimator.start();
    }

    private void initAnim() {
        valueAnimator = ValueAnimator.ofFloat(0.0f, 1.0f);
        valueAnimator.setDuration(800);
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                animF = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                currentDot = targetDot.clone();
                if (isEnd && targetDot == _1Dot) {
                    skipTo(4);
                }
            }
        });
    }

    private void initDot() {
        // 第一个
        _1Dot[0][0] = 0.6f;
        _1Dot[0][1] = 0.5f;

        _1Dot[1][0] = 0.3f;
        _1Dot[1][1] = 0.5f;

        _1Dot[2][0] = 0.3f;
        _1Dot[2][1] = 0.5f;

        _1Dot[3][0] = 0.75f;
        _1Dot[3][1] = 0.5f;

        _1Dot[4][0] = 0.75f;
        _1Dot[4][1] = 0.5f;

        _1Dot[5][0] = 0.3f;
        _1Dot[5][1] = 0.5f;

        _1Dot[6][0] = 0.3f;
        _1Dot[6][1] = 0.5f;

        _1Dot[7][0] = 0.3f;
        _1Dot[7][1] = 0.62f;

        // 第二个
        _2Dot[0][0] = 0.5f;
        _2Dot[0][1] = 0.5f;

        _2Dot[1][0] = 0.5f;
        _2Dot[1][1] = 0.5f;

        _2Dot[2][0] = 0.3f;
        _2Dot[2][1] = 0.5f;

        _2Dot[3][0] = 0.5f;
        _2Dot[3][1] = 0.5f;

        _2Dot[4][0] = 0.5f;
        _2Dot[4][1] = 0.5f;

        _2Dot[5][0] = 0.7f;
        _2Dot[5][1] = 0.5f;

        _2Dot[6][0] = 0.5f;
        _2Dot[6][1] = 0.5f;

        _2Dot[7][0] = 0.5f;
        _2Dot[7][1] = 0.62f;

        // 第三个
        _3Dot[0][0] = 0.4f;
        _3Dot[0][1] = 0.5f;

        _3Dot[1][0] = 0.7f;
        _3Dot[1][1] = 0.5f;

        _3Dot[2][0] = 0.7f;
        _3Dot[2][1] = 0.5f;

        _3Dot[3][0] = 0.25f;
        _3Dot[3][1] = 0.5f;

        _3Dot[4][0] = 0.26f;
        _3Dot[4][1] = 0.5f;

        _3Dot[5][0] = 0.69f;
        _3Dot[5][1] = 0.5f;

        _3Dot[6][0] = 0.69f;
        _3Dot[6][1] = 0.5f;

        _3Dot[7][0] = 0.69f;
        _3Dot[7][1] = 0.62f;

        // 展开
        endDot[0][0] = 0.59f;
        endDot[0][1] = 0.5f;

        endDot[1][0] = 0.39f;
        endDot[1][1] = 0.64f;

        endDot[2][0] = 0.28f;
        endDot[2][1] = 0.55f;

        endDot[3][0] = 0.699f;
        endDot[3][1] = 0.399f;

        endDot[4][0] = 0.701f;
        endDot[4][1] = 0.401f;

        endDot[5][0] = 0.55f;
        endDot[5][1] = 0.77f;

        endDot[6][0] = 0.43f;
        endDot[6][1] = 0.668f;

        endDot[7][0] = 0.415f;
        endDot[7][1] = 0.76f;

        // 初始化这两组点
        currentDot = _1Dot;
        targetDot = _1Dot;
    }

    //       mPath.moveTo(getWidth() * getFloat(0.6f, 0.59f), getHeight() * getFloat(0.5f, 0.5f));    // 1
//    getDot(getFloat(0.3f, 0.39f), getFloat(0.5f, 0.64f));                                           // 2
//    getDot(getFloat(0.3f, 0.28f), getFloat(0.5f, 0.55f));                                           // 3
//    getDot(getFloat(0.75f, 0.699f), getFloat(0.5f, 0.399f));                                        // 4
//    getDot(getFloat(0.75f, 0.701f), getFloat(0.5f, 0.401f));
//    getDot(getFloat(0.3f, 0.55f), getFloat(0.5f, 0.77f));                                           // 5
//    getDot(getFloat(0.3f, 0.43f), getFloat(0.5f, 0.666f));                                          // 6
//    getDot(getFloat(0.3f, 0.415f), getFloat(0.62f, 0.76f));                                         // 7
}