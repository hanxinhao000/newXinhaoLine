package erjinzhi.xinhao.xinhaolib.linedata;

import android.util.Log;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.List;

import erjinzhi.xinhao.xinhaolib.databean.LineCharViewData;
import erjinzhi.xinhao.xinhaolib.databean.LineViewData;
import erjinzhi.xinhao.xinhaolib.databean.ScaleLineBoomStringBean;
import erjinzhi.xinhao.xinhaolib.databean.ScaleLineLifeStringBean;
import erjinzhi.xinhao.xinhaolib.linedata.idata.IRollingLine;
import erjinzhi.xinhao.xinhaolib.linedata.listener.RollingLineRefreshListener;

/**
 * 处理滚动
 */
public class RollingLine implements IRollingLine {

    //不得超过的X线
    private static float BORDER;


    private List<LineCharViewData> mViewPointCoordinatesList;

    private ArrayList<ScaleLineLifeStringBean> mScaleLineLifeStringBeans;

    private ArrayList<ScaleLineBoomStringBean> scaleLineBoomStringBeans;

    private List<LineViewData> mLineViewDatas;

    private int[] xLine;

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

               // rollingData(mid);

                startX = endX;

                //通知刷新View
                if (mRollingLineRefreshListener != null) {
                    mRollingLineRefreshListener.rollingRefreshLineView(mid);
                }
                break;



        }



    }

    @Override
    public void setLineViewDatas(List<LineViewData> mLineViewDatas) {

        this.mLineViewDatas = mLineViewDatas;
    }

    //滑动

    private void rollingData(float x) {


        Log.e("坐标", "rollingData: " + x );

        /**
         *
         * 滑动点
         *
         */

        for (int i = 0; i < mViewPointCoordinatesList.size(); i++) {

            int viewDataX = mViewPointCoordinatesList.get(i).getViewDataX();

            mViewPointCoordinatesList.get(i).setViewDataX((int) (viewDataX + x));

        }

        /**
         *
         * 滑动刻度线
         *
         */
        xLine[0] = (int) (xLine[0] + x);
        xLine[2] = (int) (xLine[2] + x);

        yLine[0] = (int) (yLine[0] + x);
        yLine[2] = (int) (yLine[2] + x);

        /**
         *
         * 滑动刻度线的数字(左边)
         *
         */
        for (int i = 0; i < mScaleLineLifeStringBeans.size(); i++) {

            float mX = mScaleLineLifeStringBeans.get(i).getmX();

            mScaleLineLifeStringBeans.get(i).setmX((int) (mX + x));


        }

        /**
         *
         * 滑动线
         */

        for (int i = 0; i < mLineViewDatas.size(); i++) {

            int startX = mLineViewDatas.get(i).getStartX();

            int endX = mLineViewDatas.get(i).getEndX();

            mLineViewDatas.get(i).setStartX((int) (startX + x));

            mLineViewDatas.get(i).setEndX((int) (endX + x));


        }

        /**
         * 滚动底部
         *
         */

        for (int i = 0; i < scaleLineBoomStringBeans.size(); i++) {

            float mX = scaleLineBoomStringBeans.get(i).getmX();

            float lineStartX = scaleLineBoomStringBeans.get(i).getLineStartX();

            float lineEndX = scaleLineBoomStringBeans.get(i).getLineEndX();


            scaleLineBoomStringBeans.get(i).setLineEndX(lineEndX + x);

            scaleLineBoomStringBeans.get(i).setLineStartX(lineStartX + x);

            scaleLineBoomStringBeans.get(i).setmX(mX + x);


        }

    }


}
