package erjinzhi.xinhao.xinhaolib.linedata.idata;

import android.graphics.Paint;

import java.util.List;

import erjinzhi.xinhao.xinhaolib.databean.LineCharViewData;
import erjinzhi.xinhao.xinhaolib.databean.LineViewData;
import erjinzhi.xinhao.xinhaolib.linedata.listener.LineDataViewRefreshListener;

public interface ILineData {

    //传入的数据
    public void setViewPointCoordinatesList(List<LineCharViewData> mViewPointCoordinatesList);

    //设置监听
    public void setLineDataViewRefreshListener(LineDataViewRefreshListener mLineDataViewRefreshListener);

    //开始计算
    public void calculate();

    //获取计算后的结果
    public List<LineViewData> getLineViewDatas();

    //获取画笔
    public Paint getPaint();
}
