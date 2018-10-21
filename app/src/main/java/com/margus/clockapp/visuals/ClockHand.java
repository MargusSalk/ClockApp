package com.margus.clockapp.visuals;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.ColorInt;

abstract class ClockHand {

    // Parent view center points
    public static float sViewCenterX, sViewCenterY;
    // Size of the hand
    private final float mMultiplier;
    // Variable for determining position
    private float mShapeRadiusFromCenter;

    private static float mWidth = 30;
    private static float mHeight = 300;

    // Paint for drawing
    private final Paint mPaint;

    public ClockHand(float multiplier) {
        this.mMultiplier = multiplier;

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
     * @param currentAngle   The current angle around the center to draw the hand
     */
    void draw(Canvas canvas, float currentAngle) {
        float width = mWidth * mMultiplier;
        float height = mHeight * mMultiplier;
        float left = ClockHand.sViewCenterX - width / 2;
        float top = ClockHand.sViewCenterY - height;
        // Call the abstract drawThisShape method, this must be defined for each shape.
        drawThisShape(left, top, left + width, top + height, currentAngle, canvas, mPaint);
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
     * Calculates the center y location
     *
     * @param radiusFromCenter    The distance from the center of this hand
     * @param currentAngleRadians The current angle of the hand
     * @return
     */
    private float calcLocationInAnimationY(float radiusFromCenter, double currentAngleRadians) {
        return (float) (sViewCenterY + Math.sin(currentAngleRadians) * radiusFromCenter);
    }

    /**
     * Calculates the center x location
     *
     * @param radiusFromCenter    The distance from the center of this shape
     * @param currentAngleRadians The current angle of the shape
     * @return
     */
    private float calcLocationInAnimationX(float radiusFromCenter, double currentAngleRadians) {
        return (float) (sViewCenterX + Math.cos(currentAngleRadians) * radiusFromCenter);

    }
    /**
     * Sets the shape color
     *
     * @param color Color to set this shape to
     */
    void setColor(@ColorInt int color) {
        mPaint.setColor(color);
    }

}
