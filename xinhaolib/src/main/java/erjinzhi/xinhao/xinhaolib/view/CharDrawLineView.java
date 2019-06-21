package erjinzhi.xinhao.xinhaolib.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;

import java.util.List;

import erjinzhi.xinhao.xinhaolib.databean.LineCharViewData;
import erjinzhi.xinhao.xinhaolib.databean.LineViewData;
import erjinzhi.xinhao.xinhaolib.linedata.IPointData;
import erjinzhi.xinhao.xinhaolib.utils.UIUtils;

public class CharDrawLineView extends CharLineView implements IPointData {

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
        canvas.drawLine(x[0], x[1], x[2], x[3], paint);
        /**
         * Y线
         */
        canvas.drawLine(y[0], y[1], y[2], y[3], paint);

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

            canvas.drawCircle(viewPointCoordinatesList.get(i).getViewDataX(), viewPointCoordinatesList.get(i).getViewDataY(), POINT_SIZE, mPointData.getPaint());
            canvas.drawText("" + viewPointCoordinatesList.get(i).getData(),
                    viewPointCoordinatesList.get(i).getViewDataX() + 15,
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
                    lineViewDatas.get(i).getStartX(),
                    lineViewDatas.get(i).getStartY(),
                    lineViewDatas.get(i).getEndX(),
                    lineViewDatas.get(i).getEndY(),
                    mLineData.getPaint()
            );


        }

    }
}
