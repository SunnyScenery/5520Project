package edu.neu.numad22sp_bdd_project.statistic;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import edu.neu.numad22sp_bdd_project.R;

public class ColumnBarChartView extends View {
    private Context mContext;
    private Paint mPaint=new Paint();
    private ArrayList<ColumnDataFrom> mArray=new ArrayList<>();
    private float mWidth=0;
    private float mHeight=0;
    private RectF mRectF = new RectF();
    private Rect mTextRect=new Rect();
    private int mMargin=30;
    private boolean mDisplayData =true;
    private int mTextColor= Color.BLACK;

    public ColumnBarChartView(Context context) {
        super(context);
        initial(context);
    }
    public ColumnBarChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray=context.obtainStyledAttributes(attrs, R.styleable.ColumnBarChartView);
        mMargin=typedArray.getInteger(R.styleable.ColumnBarChartView_ColumnBarChart_margin,mMargin);
        mDisplayData =typedArray.getBoolean(R.styleable.ColumnBarChartView_ColumnBarChart_displayData, mDisplayData);
        mTextColor =typedArray.getInteger(R.styleable.ColumnBarChartView_ColumnBarChart_textColor, mTextColor);
        initial(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    private void initial(Context context){
        mContext=context;
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(mTextColor);
        setWillNotDraw(false);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mWidth = 75;
        mHeight = ( getHeight() / getNumber() ) + 100;


        for(int i=0;i<mArray.size();i++){
            mRectF.left= i*(mWidth+mMargin);
            mRectF.right= mRectF.left+mWidth;
            mRectF.bottom=getHeight();
            if(mArray.get(i).mNumber == 0) {
                mRectF.top = 0;
            } else {
                mRectF.top= mRectF.bottom-mHeight*mArray.get(i).mNumber ;
            }

            if(mDisplayData){
                onDrawName(canvas,i);
                onDrawNumber(canvas,i);
            }
            onDrawColumn(canvas,i);
        }
    }

    private void onDrawName(Canvas canvas,int i){
        mPaint.setTextSize(dip2px(mContext, (float) Math.sqrt(mWidth)));
        mPaint.getTextBounds(mArray.get(i).mMame, 0, mArray.get(i).mMame.length(), mTextRect);
        mPaint.setColor(mTextColor);
        canvas.drawText(
                mArray.get(i).mMame,
                (mRectF.left+mRectF.right)/2 - (mTextRect.left+mTextRect.right)/2,
                mRectF.bottom-(mTextRect.bottom-mTextRect.top)/2,
                mPaint);

    }

    private void onDrawNumber(Canvas canvas,int i){
        mPaint.setTextSize(dip2px(mContext, (float) Math.sqrt(mWidth)));
        if(mArray.get(i).mNumber == 0) {
            mPaint.getTextBounds("0.0", 0, "0.0".length(), mTextRect);
        } else {
            mPaint.getTextBounds(String.valueOf(mArray.get(i).mNumber), 0, String.valueOf(mArray.get(i).mNumber).length(), mTextRect);
        }

        mPaint.setColor(mTextColor);
        if(mArray.get(i).mNumber == 0) {

            canvas.drawText(
                    "0.0",
                    (mRectF.left+mRectF.right)/2 - (mTextRect.left+mTextRect.right)/2,
                    mRectF.bottom - 40,
                    mPaint);
        } else {
            canvas.drawText(
                    String.valueOf(mArray.get(i).mNumber),
                    (mRectF.left+mRectF.right)/2 - (mTextRect.left+mTextRect.right)/2,
                    mRectF.top,
                    mPaint);
        }


    }

    private void onDrawColumn(Canvas canvas,int i){
        if(mDisplayData) {
            mRectF.bottom=mRectF.bottom-dip2px(mContext,(mTextRect.bottom-mTextRect.top));
            if(mArray.get(i).mNumber == 0) {
                mRectF.top = mRectF.bottom;
            } else {
                mRectF.top= mRectF.bottom-mHeight*mArray.get(i).mNumber +dip2px(mContext,(mTextRect.bottom-mTextRect.top+2)) ;
            }

        }
        Drawable drawable;
        try{
            drawable=getResources().getDrawable(mArray.get(i).mColorOrDrawable);
            drawable.setBounds((int)mRectF.left,(int)mRectF.top,(int)mRectF.right,(int)mRectF.bottom);
            drawable.draw(canvas);
        }catch (Exception e){
            mPaint.setColor(mArray.get(i).mColorOrDrawable);
            canvas.drawRect(mRectF,mPaint);
        }


    }


    public void addColumnData(ColumnDataFrom columnDataFrom){
        if(null != mArray){
            if(!mArray.contains(columnDataFrom)) {
                mArray.add(columnDataFrom);
                refreshUI();
            }
        }
    }


    public void deleteColumnData(int ColumnIndex){
        if(null != mArray){
            if( mArray.size()>ColumnIndex){
                if(null != mArray.get(ColumnIndex) ){
                    mArray.remove(ColumnIndex);
                    refreshUI();
                }
            }
        }
    }

    public static class ColumnDataFrom {
        String mMame;
        float mNumber;
        int mColorOrDrawable;
        public ColumnDataFrom(String name,float number,int colorOrDrawable){
            mMame=name;
            mNumber=number;
            mColorOrDrawable =colorOrDrawable;
        }
    }


    private void refreshUI(){
        this.invalidate();
    }


    public int getSize(){
        if(null!=mArray){
            return mArray.size();
        }else {
            return 0;
        }
    }


    public float getNumber(){
        float totalNumber=0;
        for(ColumnDataFrom columnDataFrom : mArray){
            totalNumber= totalNumber+ columnDataFrom.mNumber;
        }
        return totalNumber;
    }

    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

}
