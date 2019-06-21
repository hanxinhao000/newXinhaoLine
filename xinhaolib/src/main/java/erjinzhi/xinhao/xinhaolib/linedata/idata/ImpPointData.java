package erjinzhi.xinhao.xinhaolib.linedata.idata;

import android.graphics.Paint;

import java.util.ArrayList;
import java.util.List;

import erjinzhi.xinhao.xinhaolib.databean.LineCharBean;
import erjinzhi.xinhao.xinhaolib.databean.LineCharViewData;
import erjinzhi.xinhao.xinhaolib.databean.ScaleLineBoomStringBean;
import erjinzhi.xinhao.xinhaolib.databean.ScaleLineLifeStringBean;
import erjinzhi.xinhao.xinhaolib.linedata.listener.PointDataViewRefreshListener;

public interface ImpPointData {

    //获取刻度线左边的值
    public void setScaleLineLifeStringBeans(ArrayList<ScaleLineLifeStringBean> scaleLineLifeStringBeans);

    //获取刻度线底部的值
    public void setScaleLineBoomStringBeans(ArrayList<ScaleLineBoomStringBean> scaleLineBoomStringBeans);

    //获取左边的东西
    public ArrayList<ScaleLineLifeStringBean> getScaleLineLifeStringBeans();

    //获取底部的东西
    public ArrayList<ScaleLineBoomStringBean> getScaleLineBoomStringBeans();

    //获取传入的原始
    public void setList(List<LineCharBean> mList);

    //设置高度
    public void setViewHeight(int mHeight);

    //设置刷新视图
    public void setPointDataViewRefreshListener(PointDataViewRefreshListener mPointDataViewRefreshListener);

    //开始处理点数据
    public void calculate();

    //取得画笔
    public Paint getPaint();

    //获取计算过后的点
    public List<LineCharViewData> getViewPointCoordinatesList();

    //设置刻度线个数
    public void setScale(int mScale);
}
