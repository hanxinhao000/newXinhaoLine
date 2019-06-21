package erjinzhi.xinhao.xinhaolib.linedata;

import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;

import erjinzhi.xinhao.xinhaolib.databean.ScaleLineLifeStringBean;
import erjinzhi.xinhao.xinhaolib.databean.ScaleLineRightStringBean;
import erjinzhi.xinhao.xinhaolib.linedata.idata.IScaleLineData;
import erjinzhi.xinhao.xinhaolib.view.viewlistener.ViewRefreshListener;

/**
 * 专门管理刻度线的
 */

public class ScaleLineData implements IBaseData, IScaleLineData {

    /**
     * 视图的宽度
     */
    private int mWidth;
    /**
     * 视图的高度
     */
    private int mHeight;

    /**
     * X轴的线
     */
    private int[] mX = new int[4];
    /**
     * Y轴的线
     */
    private int[] mY = new int[4];
    /**
     * 刷新View
     */
    private ViewRefreshListener mViewRefreshListener;

    /**
     * 线的宽度
     */
    private int STROKE_WIDTH = 5;


    /**
     * 刻度线的画笔
     */
    private Paint mPaint;
    /**
     * 传入左边刻度线的值
     */
    private ArrayList<ScaleLineLifeStringBean> ScaleLineLifeStringBeans;
    /**
     * 获取刻度线底部的值
     */
    private ArrayList<ScaleLineRightStringBean> ScaleLineRightStringBeans;


    public ScaleLineData(int mWidth, int mHeight) {

        this.mWidth = mWidth;

        this.mHeight = mHeight;

        //calculateLine();
        initPaint();
    }

    /**
     * 初始化画笔
     */

    private void initPaint() {

        mPaint = new Paint();
        mPaint.setColor(Color.GREEN);
        mPaint.setStrokeWidth(STROKE_WIDTH);

    }

    /**
     * 取得画笔
     *
     * @return
     */
    @Override
    public Paint getmPaint() {
        return mPaint;
    }


    /**
     * 强制设置线的宽高度
     *
     * @return
     */
    @Override
    public int getmWidth() {
        return mWidth;
    }

    @Override
    public void setmWidth(int mWidth) {
        // calculate();
        this.mWidth = mWidth;
    }

    @Override
    public int getmHeight() {
        return mHeight;
    }

    @Override
    public void setmHeight(int mHeight) {
        // calculate();
        this.mHeight = mHeight;
    }


    /**
     * 计算线的宽高度
     */
    @Override
    public void calculate() {


        //底部左边(底 线位置确认)
        int midHeight = mHeight - BOTTOM_DISTANCE;
        //底部左边(左 线位置确认)
        /* midHeight = midHeight - LEFT_DISTANCE;*/
        //右边(底线 位置确认)
        int midRight = mWidth - RIGHT_DISTANCE;

        //顶部X点确认
        mX[0] = LEFT_DISTANCE;  //startX
        mX[1] = TOP_DISTANCE; //startY
        //底部X点确认
        mX[2] = LEFT_DISTANCE; //endX
        mX[3] = midHeight;     //endY

        //左边Y轴确定 (这块为什么要减去一个 STROKE_WIDTH? 如果要验证，请把 STROKE_WIDTH 数值填到最大,然后把 -STROKE_WIDTH 删掉)
        mY[0] = LEFT_DISTANCE - (STROKE_WIDTH / 2);
        mY[1] = midHeight;
        //右边Y轴确定
        mY[2] = midRight;
        mY[3] = midHeight;

        /**
         * 通知View该刷新啦
         *
         */
        if (mViewRefreshListener != null) {
            mViewRefreshListener.refresh();
        }

    }

    /**
     * 获取计算过后的线（刻度X）坐标
     *
     * @return
     */
    @Override
    public int[] getmX() {
        return mX;
    }

    /**
     * 获取计算过后的线（刻度Y）坐标
     *
     * @return
     */
    @Override
    public int[] getmY() {
        return mY;
    }


    /**
     * 设置刷新
     */
    @Override
    public void setViewRefreshListener(ViewRefreshListener mViewRefreshListener) {
        this.mViewRefreshListener = mViewRefreshListener;
    }


    /**
     * 获取刻度线的值
     */


}
