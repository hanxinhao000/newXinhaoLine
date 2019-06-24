package erjinzhi.xinhao.xinhaolib.linedata;


import android.graphics.Color;
import android.graphics.Paint;

import java.util.List;

import erjinzhi.xinhao.xinhaolib.databean.LineCharViewData;
import erjinzhi.xinhao.xinhaolib.linedata.idata.IBaseData;
import erjinzhi.xinhao.xinhaolib.linedata.idata.IPointData;
import erjinzhi.xinhao.xinhaolib.linedata.idata.IUp2DownLineData;
import erjinzhi.xinhao.xinhaolib.linedata.listener.Up2DownLineRefreshListener;

/**
 * 高低线类
 */
public class Up2DownLineData implements IUp2DownLineData, IBaseData, IPointData {


    //传入高低线
    private float[] up2Down;
    //是否显示高低线
    private boolean showUp2DownLine;
    //获取已近处理好的点数据
    private List<LineCharViewData> mViewPointCoordinatesList;
    //获取平均值
    private float average;
    //画笔
    private Paint mPaint;
    //通知视图刷新
    private Up2DownLineRefreshListener mUp2DownLineRefreshListener;

    private int mScaleLineY;

    //高线位置
    private float[] lineMax = new float[4];

    //底线位置
    private float[] lineMin = new float[4];


    @Override
    public void setUp2Down(float[] up2Down) {

        this.up2Down = up2Down;
    }

    @Override
    public void setShowUp2DownLine(boolean showUp2DownLine) {
        this.showUp2DownLine = showUp2DownLine;
    }

    @Override
    public float[] getShowUpLineView() {
        return lineMax;
    }

    @Override
    public float[] getShowDownLineView() {
        return lineMin;
    }

    @Override
    public void setViewPointCoordinatesList(List<LineCharViewData> mViewPointCoordinatesList) {

        this.mViewPointCoordinatesList = mViewPointCoordinatesList;
    }

    //开始计算
    @Override
    public void calculate() {

        //初始化画笔
        initPaint();

        //高线数据
        float max = up2Down[0];

        //底线数据
        float min = up2Down[1];

        //转换高线位置
        max *= average;

        //转换底线位置
        min *= average;

        //处理高线位置

        //startX
        lineMax[0] = LEFT_DISTANCE;

        //startY
        lineMax[1] = BOTTOM_DISTANCE + POINT_INTERVAL + max;

        //endX
        lineMax[2] = LEFT_DISTANCE + mScaleLineY;

        //endY
        lineMax[3] = BOTTOM_DISTANCE + POINT_INTERVAL + max;


        //处理底线

        //startX
        lineMin[0] = LEFT_DISTANCE;

        //startY
        lineMin[1] = BOTTOM_DISTANCE + POINT_INTERVAL + min;

        //endX
        lineMin[2] = LEFT_DISTANCE + mScaleLineY;

        //endY
        lineMin[3] = BOTTOM_DISTANCE + POINT_INTERVAL + min;




        if (mUp2DownLineRefreshListener != null) {
            mUp2DownLineRefreshListener.up2DownLineRefresh();
        }
    }

    //初始化画笔
    private void initPaint() {

        mPaint = new Paint();

        mPaint.setColor(Color.parseColor("#ad0015"));

    }

    @Override
    public void setAverage(float average) {

        this.average = average;
    }

    @Override
    public Paint getPaint() {
        return mPaint;
    }

    @Override
    public void setUp2DownLineRefreshListener(Up2DownLineRefreshListener mUp2DownLineRefreshListener) {
        this.mUp2DownLineRefreshListener = mUp2DownLineRefreshListener;
    }

    @Override
    public void setScaleLineY(int[] mScaleLineY) {
        this.mScaleLineY = mScaleLineY[2];//mY[2]
    }


}
