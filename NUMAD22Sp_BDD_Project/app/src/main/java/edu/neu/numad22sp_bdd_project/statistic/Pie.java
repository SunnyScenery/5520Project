package edu.neu.numad22sp_bdd_project.statistic;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


import androidx.annotation.Nullable;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class Pie extends View {

    private List<PieEntry> pieEntries;

    private Paint paint;

    private float centerX;
    private float centerY;
    private float radius;
    private float sRadius;

    public Pie(Context context) {
        super(context);
        init();
    }

    public Pie(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Pie(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        pieEntries = new ArrayList<>();
        paint = new Paint();
        paint.setTextSize(DensityUtils.sp2px(getContext(), 15));
        paint.setAntiAlias(true);
    }


    public void setRadius(float radius) {
        this.sRadius = radius;
    }


    public void setPieEntries(List<PieEntry> pieEntries) {
        this.pieEntries = pieEntries;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float total = 0;
        for (int i = 0; i < pieEntries.size(); i++) {
            total += pieEntries.get(i).getNumber();
        }

        centerX = getPivotX();
        centerY = getPivotY();
        if (sRadius == 0) {
            sRadius = (getWidth() > getHeight() ? getHeight() / 2 : getWidth() / 2);
        }

        radius = sRadius - DensityUtils.dp2px(getContext(), 5);


        float startC = 0;

        for (int i = 0; i < pieEntries.size(); i++) {

            float sweep;
            if (total <= 0) {
                sweep = 360 / pieEntries.size();
            } else {
                sweep = 360 * (pieEntries.get(i).getNumber() / total);
            }

            paint.setColor(pieEntries.get(i).colorRes);

            float radiusT;
                radiusT = radius;

            RectF rectF = new RectF(centerX - radiusT, centerY - radiusT, centerX + radiusT, centerY + radiusT);
            canvas.drawArc(rectF, startC, sweep, true, paint);


            if ((pieEntries.get(i).getNumber() > 0 && total > 0) || (total <= 0 && pieEntries.get(i).getNumber() <= 0)) {

                float arcCenterC = startC + sweep / 2;
                float arcCenterX = 0;
                float arcCenterY = 0;

                float arcCenterX2 = 0;
                float arcCenterY2 = 0;

                DecimalFormat numberFormat = new DecimalFormat("00.00");
                paint.setColor(Color.BLACK);


                if (arcCenterC >= 0 && arcCenterC < 90) {
                    arcCenterX = (float) (centerX + radiusT * Math.cos(arcCenterC * Math.PI / 180));
                    arcCenterY = (float) (centerY + radiusT * Math.sin(arcCenterC * Math.PI / 180));
                    arcCenterX2 = (float) (arcCenterX + DensityUtils.dp2px(getContext(), 10) * Math.cos(arcCenterC * Math.PI / 180));
                    arcCenterY2 = (float) (arcCenterY + DensityUtils.dp2px(getContext(), 10) * Math.sin(arcCenterC * Math.PI / 180));
                    canvas.drawLine(arcCenterX, arcCenterY, arcCenterX2, arcCenterY2, paint);
                    if (total <= 0) {
                        canvas.drawText(pieEntries.get(i).name + ": " + numberFormat.format(0) + "%", arcCenterX2, arcCenterY2 + paint.getTextSize() / 2, paint);
                    } else {
                        canvas.drawText(pieEntries.get(i).name + ": " + numberFormat.format(pieEntries.get(i).getNumber() / total * 100) + "%", arcCenterX2, arcCenterY2 + paint.getTextSize() / 2, paint);
                    }
                } else if (arcCenterC >= 90 && arcCenterC < 180) {
                    arcCenterC = 180 - arcCenterC;
                    arcCenterX = (float) (centerX - radiusT * Math.cos(arcCenterC * Math.PI / 180));
                    arcCenterY = (float) (centerY + radiusT * Math.sin(arcCenterC * Math.PI / 180));
                    arcCenterX2 = (float) (arcCenterX - DensityUtils.dp2px(getContext(), 10) * Math.cos(arcCenterC * Math.PI / 180));
                    arcCenterY2 = (float) (arcCenterY + DensityUtils.dp2px(getContext(), 10) * Math.sin(arcCenterC * Math.PI / 180));
                    canvas.drawLine(arcCenterX, arcCenterY, arcCenterX2, arcCenterY2, paint);
                    if (total <= 0) {
                        canvas.drawText(pieEntries.get(i).name + ": " + numberFormat.format(0) + "%", (float) (arcCenterX2 - paint.getTextSize() * 3.5), arcCenterY2 + paint.getTextSize() / 2, paint);
                    } else {
                        canvas.drawText(pieEntries.get(i).name + ": " + numberFormat.format(pieEntries.get(i).getNumber() / total * 100) + "%", (float) (arcCenterX2 - paint.getTextSize() * 3.5), arcCenterY2 + paint.getTextSize() / 2, paint);
                    }
                } else if (arcCenterC >= 180 && arcCenterC < 270) {
                    arcCenterC = 270 - arcCenterC;
                    arcCenterX = (float) (centerX - radiusT * Math.sin(arcCenterC * Math.PI / 180));
                    arcCenterY = (float) (centerY - radiusT * Math.cos(arcCenterC * Math.PI / 180));
                    arcCenterX2 = (float) (arcCenterX - DensityUtils.dp2px(getContext(), 10) * Math.sin(arcCenterC * Math.PI / 180));
                    arcCenterY2 = (float) (arcCenterY - DensityUtils.dp2px(getContext(), 10) * Math.cos(arcCenterC * Math.PI / 180));
                    canvas.drawLine(arcCenterX, arcCenterY, arcCenterX2, arcCenterY2, paint);
                    if (total <= 0) {
                        canvas.drawText(pieEntries.get(i).name + ": " + numberFormat.format(0) + "%", (float) (arcCenterX2 - paint.getTextSize() * 3.5), arcCenterY2, paint);
                    } else {
                        canvas.drawText(pieEntries.get(i).name + ": " + numberFormat.format(pieEntries.get(i).getNumber() / total * 100) + "%", (float) (arcCenterX2 - paint.getTextSize() * 3.5), arcCenterY2, paint);
                    }
                } else if (arcCenterC >= 270 && arcCenterC < 360) {
                    arcCenterC = 360 - arcCenterC;
                    arcCenterX = (float) (centerX + radiusT * Math.cos(arcCenterC * Math.PI / 180));
                    arcCenterY = (float) (centerY - radiusT * Math.sin(arcCenterC * Math.PI / 180));
                    arcCenterX2 = (float) (arcCenterX + DensityUtils.dp2px(getContext(), 10) * Math.cos(arcCenterC * Math.PI / 180));
                    arcCenterY2 = (float) (arcCenterY - DensityUtils.dp2px(getContext(), 10) * Math.sin(arcCenterC * Math.PI / 180));
                    canvas.drawLine(arcCenterX, arcCenterY, arcCenterX2, arcCenterY2, paint);
                    if (total <= 0) {
                        canvas.drawText(pieEntries.get(i).name + ": " + numberFormat.format(0) + "%", arcCenterX2, arcCenterY2, paint);
                    } else {
                        canvas.drawText(pieEntries.get(i).name + ": " + numberFormat.format(pieEntries.get(i).getNumber() / total * 100) + "%", arcCenterX2, arcCenterY2, paint);
                    }
                }
            }

            pieEntries.get(i).setStartC(startC);
            pieEntries.get(i).setEndC(startC + sweep);

            startC += sweep;
        }
    }



    private float getSweep(float touchX, float touchY) {
        float xZ = touchX - centerX;
        float yZ = touchY - centerY;
        float a = Math.abs(xZ);
        float b = Math.abs(yZ);
        double c = Math.toDegrees(Math.atan(b / a));
        if (xZ >= 0 && yZ >= 0) {
            return (float) c;
        } else if (xZ <= 0 && yZ >= 0) {
            return 180 - (float) c;
        } else if (xZ <= 0 && yZ <= 0) {
            return (float) c + 180;
        } else {
            return 360 - (float) c;
        }
    }


    public static class PieEntry {
        private String name;
        private float number;
        private int colorRes;
        private float startC;
        private float endC;

        public PieEntry(String name, float number, int colorRes) {
            this.name = name;
            this.number = number;
            this.colorRes = colorRes;
        }


        public void setStartC(float startC) {
            this.startC = startC;
        }


        public void setEndC(float endC) {
            this.endC = endC;
        }


        public float getNumber() {
            return number;
        }

    }


}