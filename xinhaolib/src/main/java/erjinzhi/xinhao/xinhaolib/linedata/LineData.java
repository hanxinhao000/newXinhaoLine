package erjinzhi.xinhao.xinhaolib.linedata;


import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.List;

import erjinzhi.xinhao.xinhaolib.databean.LineCharViewData;
import erjinzhi.xinhao.xinhaolib.databean.LineViewData;
import erjinzhi.xinhao.xinhaolib.databean.listener.DataNotifyDataSetChangedListener;
import erjinzhi.xinhao.xinhaolib.linedata.idata.ILineData;
import erjinzhi.xinhao.xinhaolib.linedata.listener.LineDataViewRefreshListener;

/**
 * 专门管理线的类
 */
public class LineData implements ILineData,DataNotifyDataSetChangedListener {

    //未处理的数据(点)
    private List<LineCharViewData> mViewPointCoordinatesList;
    //刷新
    private LineDataViewRefreshListener mLineDataViewRefreshListener;
    //存取处理过后线的数据
    private List<LineViewData> mLineViewDatas;
    //线的画笔
    private Paint mPaint;

    @Override
    public void setViewPointCoordinatesList(List<LineCharViewData> mViewPointCoordinatesList) {
        this.mViewPointCoordinatesList = mViewPointCoordinatesList;
    }


    //设置刷新通知
    @Override
    public void setLineDataViewRefreshListener(LineDataViewRefreshListener mLineDataViewRefreshListener) {
        this.mLineDataViewRefreshListener = mLineDataViewRefreshListener;

    }

    //开始计算
    @Override
    public void calculate() {
        //先初始化画笔
        initPaint();
        //初始化线数据
        mLineViewDatas = new ArrayList<>();
        //开始计算点与线的数据关系
        calculateLine2Point();
        //通知View层刷新消息
        if (mLineDataViewRefreshListener != null) {
            mLineDataViewRefreshListener.lineDataViewRefresh();
        }

    }

    //初始化画笔
    private void initPaint() {
        mPaint = new Paint();
        mPaint.setColor(Color.parseColor("#ff00ff"));
        mPaint.setStrokeWidth(4);

    }

    //获取画笔
    @Override
    public Paint getPaint() {
        return mPaint;
    }

    //处理线的坐标
    private void calculateLine2Point() {

        //加入循环处理线的数据
        for (int i = 0; i < mViewPointCoordinatesList.size(); i++) {
            if (i < mViewPointCoordinatesList.size() - 1) {

                LineViewData lineViewData = new LineViewData();
                //线的起点
                lineViewData.setStartX(mViewPointCoordinatesList.get(i).getViewDataX());
                lineViewData.setStartY((int) mViewPointCoordinatesList.get(i).getViewDataY());
                //线的终点
                lineViewData.setEndX(mViewPointCoordinatesList.get(i + 1).getViewDataX());
                lineViewData.setEndY((int) mViewPointCoordinatesList.get(i + 1).getViewDataY());
                mLineViewDatas.add(lineViewData);
            }

        }

    }

    //获取线的坐标
    @Override
    public List<LineViewData> getLineViewDatas() {

        return mLineViewDatas;
    }

    /**
     * 用户通知刷新数据
     *
     */

    @Override
    public void notifyDataSetChanged() {

    }
}
