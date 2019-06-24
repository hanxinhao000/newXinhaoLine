package erjinzhi.xinhao.xinhaolib.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;

import java.util.ArrayList;
import java.util.List;

import erjinzhi.xinhao.xinhaolib.databean.LineCharViewData;
import erjinzhi.xinhao.xinhaolib.databean.LineViewData;
import erjinzhi.xinhao.xinhaolib.databean.ScaleLineBoomStringBean;
import erjinzhi.xinhao.xinhaolib.databean.ScaleLineLifeStringBean;
import erjinzhi.xinhao.xinhaolib.linedata.idata.IPointData;
import erjinzhi.xinhao.xinhaolib.linedata.listener.IScaleLine;

public class CharDrawLineView extends CharLineView implements IPointData, IScaleLine {

    private static float SLIDING_X = 0;

    public CharDrawLineView(Context context) {
        super(context);
    }

    public CharDrawLineView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CharDrawLineView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    /**
     * 画刻度线
     *
     * @param canvas
     */

    @Override
    public void calibrationDraw(Canvas canvas) {

        Paint paint = mScaleLineData.getmPaint();
        int[] x = mScaleLineData.getmX();
        int[] y = mScaleLineData.getmY();

        /**
         * X线
         *
         */
        canvas.drawLine(x[0] + SLIDING_X, x[1], x[2] + SLIDING_X, x[3], paint);
        /**
         * Y线
         */
        canvas.drawLine(y[0] + SLIDING_X, y[1], y[2] + SLIDING_X, y[3], paint);

        /**
         * 刻度线的数字(左边)
         *
         */
        ArrayList<ScaleLineLifeStringBean> scaleLineLifeStringBeans = mScaleLineData.getScaleLineLifeStringBeans();
        if (scaleLineLifeStringBeans != null) {

            for (int i = 0; i < scaleLineLifeStringBeans.size(); i++) {

                canvas.drawText(
                        scaleLineLifeStringBeans.get(i).getText(),
                        scaleLineLifeStringBeans.get(i).getmX() + SLIDING_X,
                        scaleLineLifeStringBeans.get(i).getmY(),
                        mScaleLineData.getmPaint()
                );

                canvas.drawLine(
                        x[0] + SLIDING_X,
                        scaleLineLifeStringBeans.get(i).getmY(),
                        x[0] + NUMBER_OF_SCALE_LINES_LINEG + SLIDING_X,
                        scaleLineLifeStringBeans.get(i).getmY(),
                        mScaleLineData.getmPaint()

                );


            }

        }

        /**
         * 刻度线的数字(底边)
         *
         */

        ArrayList<ScaleLineBoomStringBean> scaleLineBoomStringBeans = mScaleLineData.getScaleLineBoomStringBeans();
        if (scaleLineBoomStringBeans != null) {

            for (int i = 0; i < scaleLineBoomStringBeans.size(); i++) {

                canvas.drawText(
                        scaleLineBoomStringBeans.get(i).getText(),
                        scaleLineBoomStringBeans.get(i).getmX() + SLIDING_X,
                        scaleLineBoomStringBeans.get(i).getmY(),
                        mScaleLineData.getmPaint()
                );

                canvas.drawLine(
                        scaleLineBoomStringBeans.get(i).getLineStartX() + SLIDING_X,
                        scaleLineBoomStringBeans.get(i).getLineStartY(),
                        scaleLineBoomStringBeans.get(i).getLineEndX() + SLIDING_X,
                        scaleLineBoomStringBeans.get(i).getLineEndY(),
                        mScaleLineData.getmPaint()

                );


            }

        }
    }

    /**
     * 画点
     *
     * @param canvas
     */
    @Override
    public void pointDraw(Canvas canvas) {

        List<LineCharViewData> viewPointCoordinatesList = mPointData.getViewPointCoordinatesList();

        for (int i = 0; i < viewPointCoordinatesList.size(); i++) {

            canvas.drawCircle(
                    viewPointCoordinatesList.get(i).getViewDataX() + SLIDING_X,
                    viewPointCoordinatesList.get(i).getViewDataY(),
                    POINT_SIZE,
                    mPointData.getPaint());
            canvas.drawText("" + viewPointCoordinatesList.get(i).getData(),
                    viewPointCoordinatesList.get(i).getViewDataX() + 15 + SLIDING_X,
                    viewPointCoordinatesList.get(i).getViewDataY(),
                    mPointData.getPaint()


            );
        }


    }

    /**
     * 画点与点之间的线
     *
     * @param canvas
     */
    @Override
    public void pointIsLineDraw(Canvas canvas) {

        List<LineViewData> lineViewDatas = mLineData.getLineViewDatas();

        for (int i = 0; i < lineViewDatas.size(); i++) {

            canvas.drawLine(
                    lineViewDatas.get(i).getStartX() + SLIDING_X,
                    lineViewDatas.get(i).getStartY(),
                    lineViewDatas.get(i).getEndX() + SLIDING_X,
                    lineViewDatas.get(i).getEndY(),
                    mLineData.getPaint()
            );


        }

    }

    @Override
    public void rollingRefreshLineView(float x) {
        SLIDING_X += x;
        invalidate();
    }

    @Override
    public float crossLine() {
        return mScaleLineData.getScaleLineLifeStringBeans().get(0).getmX() + SLIDING_X;
    }
}
