package erjinzhi.xinhao.xinhaolib.linedata.idata;

import android.graphics.Paint;

import java.util.List;

import erjinzhi.xinhao.xinhaolib.databean.LineCharViewData;
import erjinzhi.xinhao.xinhaolib.linedata.listener.Up2DownLineRefreshListener;

public interface IUp2DownLineData {

    //传入用户输入的高低线

    public void setUp2Down(float[] up2Down);

    //是否显示
    public void setShowUp2DownLine(boolean showUp2DownLine);

    //获取已处理好的高低线(高线)
    public float[] getShowUpLineView();

    //获取已处理好的高低线(底线)
    public float[] getShowDownLineView();

    //获取处理好的点数据
    public void setViewPointCoordinatesList(List<LineCharViewData> mViewPointCoordinatesList);

    //开始计算
    public void calculate();

    //获取平均值
    public void setAverage(float average);

    //获取画笔
    public Paint getPaint();

    //通知视图刷新
    public void setUp2DownLineRefreshListener(Up2DownLineRefreshListener mUp2DownLineRefreshListener);

    //设置线的总宽度
    public void setScaleLineY(int[] mY);

    //获取高低线
    public float[] getUp2Down();

}
