package com.margus.clockapp.visuals;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.SystemClock;
import android.support.annotation.ColorInt;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.margus.clockapp.R;
import com.margus.clockapp.Ticker;

public class ClockView extends View {

    private final float SMALL_MULTIPLIER = 1;
    private final float BIG_MULTIPLIER = 2;
    // Refresh speed = how many seconds for moving 1 second worth on clock (6 degrees)
    private final int SMALL_REFRESH_SPEED = 60;
    private final int BIG_REFRESH_SPEED = 1;

    @ColorInt
    private int backgroundColor;

    private final ClockHand mSmallHand;
    private final ClockHand mBigHand;
    private Ticker mTicker;

    public ClockView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mSmallHand = new ClockHand(SMALL_MULTIPLIER, SMALL_REFRESH_SPEED) {
            @Override
            protected void drawThisShape(float left, float top, float right, float bottom,
                                         float currentAngle, Canvas canvas, Paint paint) {
                canvas.save();
                canvas.rotate(currentAngle, ClockHand.sViewCenterX, ClockHand.sViewCenterY);
                canvas.drawRect(left, top, right, bottom, paint);
                canvas.restore();
            }
        };
        mBigHand = new ClockHand(BIG_MULTIPLIER, BIG_REFRESH_SPEED) {
            @Override
            protected void drawThisShape(float left, float top, float right, float bottom,
                                         float currentAngle, Canvas canvas, Paint paint) {
                canvas.save();
                canvas.rotate(currentAngle, ClockHand.sViewCenterX, ClockHand.sViewCenterY);
                canvas.drawRect(left, top, right, bottom, paint);
                canvas.restore();
            }
        };
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        // Setup all the view measurement code after the view is laid out. If this is done any
        // earlier the height and width are not yet determined
//        mStartTime = SystemClock.uptimeMillis();

        float viewCenterX = getWidth() / 2.f;
        float viewCenterY = getHeight() / 2.f;
        ClockHand.setViewCenterX(viewCenterX);
        ClockHand.setViewCenterY(viewCenterY);
    }

    /**
     * Sets the color of the visualization. This should be one of the preference color values
     *
     * @param newColorKey
     */
    public void setColor(String newColorKey) {

        @ColorInt
        int shapeColor;

        if (newColorKey.equals(getContext().getString(R.string.pref_color_blue_value))) {
            shapeColor = ContextCompat.getColor(getContext(), R.color.shapeBlue);
            backgroundColor = ContextCompat.getColor(getContext(), R.color.backgroundBlue);
        } else if (newColorKey.equals(getContext().getString(R.string.pref_color_green_value))) {
            shapeColor = ContextCompat.getColor(getContext(), R.color.shapeGreen);
            backgroundColor = ContextCompat.getColor(getContext(), R.color.backgroundGreen);
        } else {
            shapeColor = ContextCompat.getColor(getContext(), R.color.shapeRed);
            backgroundColor = ContextCompat.getColor(getContext(), R.color.backgroundBlue);
        }

        mSmallHand.setColor(shapeColor);
        mBigHand.setColor(shapeColor);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Get the current angle all of the shapes are at
//        double currentAngleRadians = calcCurrentAngle();

        // Draw the background
        canvas.drawColor(backgroundColor);
        int ticksPerSecond = 1;
        if (getTicker() != null) {
            ticksPerSecond = getTicker().getTicksPerSecond();
        }
        mSmallHand.draw(canvas, ticksPerSecond);
        mBigHand.draw(canvas, ticksPerSecond);

        // Invalidate the view to immediately redraw
        invalidate();
    }

    public void updateView() {
        invalidate();
    }

    public void setTicker(Ticker ticker) {
        this.mTicker = ticker;
    }

    public Ticker getTicker() { return this.mTicker; }
}
