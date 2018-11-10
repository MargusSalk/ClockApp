package com.margus.clockapp.visuals;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.ColorInt;

abstract class ClockHand {

    private static final int DEGREES_IN_SECOND = 360 / 60;
    // Parent view center points
    public static float sViewCenterX, sViewCenterY;
    // Size of the hand
    private final float mMultiplier;

    private static float mWidth = 30;
    private static float mHeight = 300;
    private float mDegreesPerSecond;
    private float mCurrentAngle;

    // Paint for drawing
    private final Paint mPaint;

    public ClockHand(float multiplier, int refreshSpeed) {
        this.mMultiplier = multiplier;
        this.mCurrentAngle = 0.0f;
        this.mDegreesPerSecond = (float) DEGREES_IN_SECOND / (float) refreshSpeed;

        // Setup paint and attributes
        this.mPaint = new Paint();

        mPaint.setStyle(Paint.Style.FILL);
    }

    static void setViewCenterY(float viewCenterY) {
        ClockHand.sViewCenterY = viewCenterY;
    }

    static void setViewCenterX(float viewCenterX) {
        ClockHand.sViewCenterX = viewCenterX;
    }

    /**
     * This draw method abstracts out what is common between drawing all shapes
     *
     * @param canvas         The canvas to draw on
     */
    void draw(Canvas canvas, int ticksPerSecond) {
        float width = mWidth * mMultiplier;
        float height = mHeight * mMultiplier;
        float left = ClockHand.sViewCenterX - width / 2;
        float top = ClockHand.sViewCenterY - height;
        calculateAngle(ticksPerSecond);
        // Call the abstract drawThisShape method, this must be defined for each shape.
        drawThisShape(left, top, left + width, top + height, getCurrentAngle(), canvas, mPaint);
    }

    private void calculateAngle(int secondsInMinute) {
        float newAngle = getCurrentAngle() + (mDegreesPerSecond / (float) secondsInMinute);
        if (newAngle > 360f) newAngle -= 360f;
        setCurrentAngle(newAngle);
    }

    /**
     * Determines how to draw the particular shape
     *
     * @param left         Left coordinate
     * @param top          Top coordinate
     * @param right        Right coordinate
     * @param bottom       Bottom coordinate
     * @param currentAngle Angle of the hand
     * @param canvas       The canvas to draw on
     * @param paint        The paint to draw with
     */
    protected abstract void drawThisShape(float left, float top, float right, float bottom, float currentAngle, Canvas canvas, Paint paint);

    /**
     * Sets the shape color
     *
     * @param color Color to set this shape to
     */
    void setColor(@ColorInt int color) {
        mPaint.setColor(color);
    }

    public void setCurrentAngle(float currentAngle) { this.mCurrentAngle = currentAngle; }
    public float getCurrentAngle() { return this.mCurrentAngle; }
}
