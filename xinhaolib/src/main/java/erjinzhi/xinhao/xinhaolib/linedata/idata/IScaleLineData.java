package erjinzhi.xinhao.xinhaolib.linedata.idata;

import android.graphics.Paint;

import java.util.ArrayList;
import java.util.List;

import erjinzhi.xinhao.xinhaolib.databean.LineCharViewData;
import erjinzhi.xinhao.xinhaolib.databean.ScaleLineBoomStringBean;
import erjinzhi.xinhao.xinhaolib.databean.ScaleLineLifeStringBean;
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

    //获取刻度线左边的值
    public void setScaleLineLifeStringBeans(ArrayList<ScaleLineLifeStringBean> scaleLineLifeStringBeans);

    //获取刻度线底部的值
    public void setScaleLineBoomStringBeans(ArrayList<ScaleLineBoomStringBean> scaleLineBoomStringBeans);

    //计算Text位置
    public void calculateString();

    //获取左边的东西
    public ArrayList<ScaleLineLifeStringBean> getScaleLineLifeStringBeans();

    //获取底部的东西
    public ArrayList<ScaleLineBoomStringBean> getScaleLineBoomStringBeans();

    //获取刻度线长度
    public int getScaleWidth();

    //获取数据总和
    public void setDataSize(int size);
}
