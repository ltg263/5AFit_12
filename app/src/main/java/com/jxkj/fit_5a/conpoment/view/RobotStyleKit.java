package com.jxkj.fit_5a.conpoment.view;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;


/**
 * Created by AuthorName on 10/24/16.
 * Copyright © 2016 CompanyName. All rights reserved.
 *
 * Generated by PaintCode
 * http://www.paintcodeapp.com
 *
 * @author AuthorName
 */
class RobotStyleKit {
    // Resizing Behavior
    
    public enum ResizingBehavior {
        AspectFit, //!< The content is proportionally resized to fit into the target rectangle.
        AspectFill, //!< The content is proportionally resized to completely fill the target rectangle.
        Stretch, //!< The content is stretched to match the entire target rectangle.
        Center, //!< The content is centered in the target rectangle, but it is NOT resized.
    }
    
    // Canvas Drawings
    
    // Tab
    
    public static void drawRobot(Canvas canvas, int color, float angle) {
        RobotStyleKit.drawRobot(canvas, new RectF(0f, 0f, 570f, 450f), ResizingBehavior.AspectFit, color, angle);
    }
    
    public static void drawRobot(Canvas canvas, RectF targetFrame, ResizingBehavior resizing, int color, float angle) {
        // General Declarations
        Matrix currentTransformation = new Matrix();
        Paint paint;
        
        // Resize to Target Frame
        canvas.save();
        RectF resizedFrame = RobotStyleKit.resizingBehaviorApply(resizing, new RectF(0f, 0f, 570f, 450f), targetFrame);
        canvas.translate(new PointF(resizedFrame.left, resizedFrame.top).x, new PointF(resizedFrame.left, resizedFrame.top).y);
        canvas.scale(resizedFrame.width() / 570f, resizedFrame.height() / 450f);
        
        // Rectangle
        RectF rectangleRect = new RectF(185f, 161f, 388f, 333f);
        Path rectanglePath = new Path();
        float rectangleCornerRadius = Math.min(Math.min(rectangleRect.width(), rectangleRect.height()) / 2f, 23f);
        rectanglePath.addRoundRect(rectangleRect, new float[]{0f, 0f, 0f, 0f, rectangleCornerRadius, rectangleCornerRadius, rectangleCornerRadius, rectangleCornerRadius}, Path.Direction.CW);
        
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(color);
        canvas.drawPath(rectanglePath, paint);
        
        // Rectangle 2
        canvas.save();
        canvas.translate(153.03f, 179.97f);
        currentTransformation.postTranslate(153.03f, 179.97f);
        canvas.rotate(-angle);
        currentTransformation.postRotate(-angle);
        RectF rectangle2Rect = new RectF(-22.03f, -22.97f, 22.97f, 118.03f);
        
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(color);
        canvas.drawRoundRect(rectangle2Rect, 23f, 23f, paint);
        canvas.restore();
        
        // Rectangle 3
        RectF rectangle3Rect = new RectF(397f, 157f, 442f, 298f);
        
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(color);
        canvas.drawRoundRect(rectangle3Rect, 23f, 23f, paint);
        
        // Rectangle 4
        RectF rectangle4Rect = new RectF(226f, 333f, 271f, 406f);
        Path rectangle4Path = new Path();
        float rectangle4CornerRadius = Math.min(Math.min(rectangle4Rect.width(), rectangle4Rect.height()) / 2f, 23f);
        rectangle4Path.addRoundRect(rectangle4Rect, new float[]{0f, 0f, 0f, 0f, rectangle4CornerRadius, rectangle4CornerRadius, rectangle4CornerRadius, rectangle4CornerRadius}, Path.Direction.CW);
        
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(color);
        canvas.drawPath(rectangle4Path, paint);
        
        // Rectangle 5
        RectF rectangle5Rect = new RectF(302f, 333f, 347f, 406f);
        Path rectangle5Path = new Path();
        float rectangle5CornerRadius = Math.min(Math.min(rectangle5Rect.width(), rectangle5Rect.height()) / 2f, 23f);
        rectangle5Path.addRoundRect(rectangle5Rect, new float[]{0f, 0f, 0f, 0f, rectangle5CornerRadius, rectangle5CornerRadius, rectangle5CornerRadius, rectangle5CornerRadius}, Path.Direction.CW);
        
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(color);
        canvas.drawPath(rectangle5Path, paint);
        
        // Bezier 2
        Path bezier2Path = new Path();
        bezier2Path.moveTo(349.38f, 36.19f);
        bezier2Path.cubicTo(350.84f, 36.98f, 351.39f, 38.8f, 350.6f, 40.26f);
        bezier2Path.cubicTo(350.6f, 40.26f, 343.71f, 53.08f, 334.62f, 69.98f);
        bezier2Path.cubicTo(366.98f, 85.79f, 389f, 117.04f, 389f, 153f);
        bezier2Path.lineTo(184f, 153f);
        bezier2Path.cubicTo(184f, 117.61f, 205.32f, 86.79f, 236.83f, 70.76f);
        bezier2Path.cubicTo(237.59f, 70.37f, 238.36f, 69.99f, 239.14f, 69.61f);
        bezier2Path.cubicTo(230.15f, 52.91f, 223.36f, 40.28f, 223.36f, 40.28f);
        bezier2Path.cubicTo(222.57f, 38.82f, 223.12f, 37f, 224.58f, 36.21f);
        bezier2Path.cubicTo(226.04f, 35.43f, 227.86f, 35.97f, 228.64f, 37.43f);
        bezier2Path.cubicTo(228.64f, 37.43f, 235.55f, 50.27f, 244.64f, 67.17f);
        bezier2Path.cubicTo(257.42f, 61.92f, 271.59f, 59f, 286.5f, 59f);
        bezier2Path.cubicTo(301.72f, 59f, 316.16f, 62.04f, 329.15f, 67.5f);
        bezier2Path.cubicTo(338.32f, 50.42f, 345.32f, 37.41f, 345.32f, 37.41f);
        bezier2Path.cubicTo(345.65f, 36.8f, 346.17f, 36.34f, 346.77f, 36.08f);
        bezier2Path.cubicTo(347.58f, 35.73f, 348.54f, 35.74f, 349.38f, 36.19f);
        bezier2Path.close();
        bezier2Path.moveTo(333.5f, 99f);
        bezier2Path.cubicTo(330.46f, 99f, 327.79f, 100.6f, 326.29f, 102.99f);
        bezier2Path.cubicTo(325.47f, 104.3f, 325f, 105.85f, 325f, 107.5f);
        bezier2Path.cubicTo(325f, 112.19f, 328.81f, 116f, 333.5f, 116f);
        bezier2Path.cubicTo(338.19f, 116f, 342f, 112.19f, 342f, 107.5f);
        bezier2Path.cubicTo(342f, 102.81f, 338.19f, 99f, 333.5f, 99f);
        bezier2Path.close();
        bezier2Path.moveTo(240.5f, 99f);
        bezier2Path.cubicTo(238.24f, 99f, 236.19f, 99.88f, 234.66f, 101.32f);
        bezier2Path.cubicTo(233.02f, 102.87f, 232f, 105.06f, 232f, 107.5f);
        bezier2Path.cubicTo(232f, 112.19f, 235.81f, 116f, 240.5f, 116f);
        bezier2Path.cubicTo(245.19f, 116f, 249f, 112.19f, 249f, 107.5f);
        bezier2Path.cubicTo(249f, 102.81f, 245.19f, 99f, 240.5f, 99f);
        bezier2Path.close();
        
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(color);
        canvas.drawPath(bezier2Path, paint);
        
        canvas.restore();
    }
    
    
    // Resizing Behavior
    
    public static RectF resizingBehaviorApply(ResizingBehavior behavior, RectF rect, RectF target) {
        if (rect.equals(target) || target == null) {
            return rect;
        }
        
        if (behavior == ResizingBehavior.Stretch) {
            return target;
        }
        
        PointF ratio = new PointF();
        ratio.x = Math.abs(target.width() / rect.width());
        ratio.y = Math.abs(target.height() / rect.height());
        
        float scale = 0f;
        
        switch (behavior) {
            case AspectFit: {
                scale = Math.min(ratio.x, ratio.y);
                break;
            }
            case AspectFill: {
                scale = Math.max(ratio.x, ratio.y);
                break;
            }
            case Center: {
                scale = 1f;
                break;
            }
        }
        
        PointF newSize = new PointF(Math.abs(rect.width() * scale), Math.abs(rect.height() * scale));
        RectF result = new RectF(target.centerX(), target.centerY(), target.centerX(), target.centerY());
        result.inset(-newSize.x / 2f, -newSize.y / 2f);
        return result;
    }
    
    
}