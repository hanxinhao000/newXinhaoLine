package erjinzhi.xinhao.xinhaolib.linedata.idata;

import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.List;

import erjinzhi.xinhao.xinhaolib.databean.LineCharViewData;
import erjinzhi.xinhao.xinhaolib.databean.LineViewData;
import erjinzhi.xinhao.xinhaolib.databean.ScaleLineBoomStringBean;
import erjinzhi.xinhao.xinhaolib.databean.ScaleLineLifeStringBean;
import erjinzhi.xinhao.xinhaolib.linedata.listener.RollingLineRefreshListener;

public interface IRollingLine {

    //传入点数据
    public void setViewPointCoordinatesList(List<LineCharViewData> mViewPointCoordinatesList);

    //传入刻度线数据(左边刻度线)
    public void setScaleLineLifeStringBeans(ArrayList<ScaleLineLifeStringBean> mScaleLineLifeStringBeans);

    //传入底部刻度线
    public void setScaleLineBoomStringBeans(ArrayList<ScaleLineBoomStringBean> scaleLineBoomStringBeans);

    //传入刻度线的值
    public void setXLine(int[] xLine);

    //传入刻度线的值
    public void setYLine(int[] yLine);

    //通知刷新View
    public void setRollingLineRefreshListener(RollingLineRefreshListener mRollingLineRefreshListener);

    //传入触摸事件Rolling
    public void onTouchEventRolling(MotionEvent event);

    //传入线的坐标
    public void setLineViewDatas(List<LineViewData> mLineViewDatas);

    //传入屏幕宽度
    public void setWidth(int mWidth);


}
