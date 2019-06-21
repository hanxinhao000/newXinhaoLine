package erjinzhi.xinhao.xinhaolib.linedata.idata;

import android.graphics.Paint;

import erjinzhi.xinhao.xinhaolib.view.viewlistener.ViewRefreshListener;

public interface IScaleLineData {

    //获取画笔
    public Paint getmPaint();

    //获取宽度
    public int getmWidth();

    //强制设置宽度
    public void setmWidth(int mWidth);

    //获取高度
    public int getmHeight();

    //强制设置高度
    public void setmHeight(int mHeight);

    //计算线的宽高度
    public void calculate();

    //获取计算过后的线（刻度X）坐标
    public int[] getmX();

    //获取计算过后的线（刻度Y）坐标
    public int[] getmY();

    //设置刷新
    public void setViewRefreshListener(ViewRefreshListener mViewRefreshListener);
}
