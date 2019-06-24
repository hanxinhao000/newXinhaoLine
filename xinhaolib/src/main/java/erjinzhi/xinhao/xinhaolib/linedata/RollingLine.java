package erjinzhi.xinhao.xinhaolib.linedata;

import android.util.Log;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.List;

import erjinzhi.xinhao.xinhaolib.databean.LineCharViewData;
import erjinzhi.xinhao.xinhaolib.databean.LineViewData;
import erjinzhi.xinhao.xinhaolib.databean.ScaleLineBoomStringBean;
import erjinzhi.xinhao.xinhaolib.databean.ScaleLineLifeStringBean;
import erjinzhi.xinhao.xinhaolib.linedata.idata.IBaseData;
import erjinzhi.xinhao.xinhaolib.linedata.idata.IRollingLine;
import erjinzhi.xinhao.xinhaolib.linedata.listener.RollingLineRefreshListener;

/**
 * 处理滚动
 */
public class RollingLine implements IRollingLine,IBaseData {

    //不得超过的X线
    private static float BORDER;


    private List<LineCharViewData> mViewPointCoordinatesList;

    private ArrayList<ScaleLineLifeStringBean> mScaleLineLifeStringBeans;

    private ArrayList<ScaleLineBoomStringBean> scaleLineBoomStringBeans;

    private List<LineViewData> mLineViewDatas;

    private int[] xLine;

    private int mWidth;

    private int[] yLine;

    private RollingLineRefreshListener mRollingLineRefreshListener;

    @Override
    public void setViewPointCoordinatesList(List<LineCharViewData> mViewPointCoordinatesList) {

        this.mViewPointCoordinatesList = mViewPointCoordinatesList;

    }

    @Override
    public void setScaleLineLifeStringBeans(ArrayList<ScaleLineLifeStringBean> mScaleLineLifeStringBeans) {

        BORDER = mScaleLineLifeStringBeans.get(0).getmX();
        this.mScaleLineLifeStringBeans = mScaleLineLifeStringBeans;

    }

    @Override
    public void setScaleLineBoomStringBeans(ArrayList<ScaleLineBoomStringBean> scaleLineBoomStringBeans) {
        this.scaleLineBoomStringBeans = scaleLineBoomStringBeans;

    }

    @Override
    public void setXLine(int[] xLine) {
        this.xLine = xLine;

    }

    @Override
    public void setYLine(int[] yLine) {
        this.yLine = yLine;

    }

    @Override
    public void setRollingLineRefreshListener(RollingLineRefreshListener mRollingLineRefreshListener) {
        this.mRollingLineRefreshListener = mRollingLineRefreshListener;

    }

    private float startX = 0;

    @Override
    public void onTouchEventRolling(MotionEvent event) {


        switch (event.getAction()) {

            //按下
            case MotionEvent.ACTION_DOWN:
                startX = event.getX();
                break;
            //抬起
            case MotionEvent.ACTION_UP:

                break;
            //滑动
            case MotionEvent.ACTION_MOVE:
                float endX = event.getX();

                float mid = endX - startX;

                rollingData(mid);

                startX = endX;

                break;


        }


    }

    @Override
    public void setLineViewDatas(List<LineViewData> mLineViewDatas) {

        this.mLineViewDatas = mLineViewDatas;
    }

    @Override
    public void setWidth(int mWidth) {
        this.mWidth = mWidth;
    }

    //滑动

    private void rollingData(float x) {

        //这块主要是判断，有没有越界


        float v = mRollingLineRefreshListener.crossLine();


        int viewDataX = yLine[2];

        int temp = (-viewDataX + mWidth);
        float x1 = scaleLineBoomStringBeans.get(0).getmX();


        //预判断




        if (v < x1 - LEFT_DISTANCE  || x < 0 ) {

            if(v > temp || x > 0){
                if (mRollingLineRefreshListener != null) {
                    mRollingLineRefreshListener.rollingRefreshLineView(x);
                }
            }


        }



    }


}
