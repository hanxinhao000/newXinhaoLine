package erjinzhi.xinhao.xinhaolib.linedata;

import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.List;

import erjinzhi.xinhao.xinhaolib.databean.CharViewData;
import erjinzhi.xinhao.xinhaolib.databean.LineCharBean;
import erjinzhi.xinhao.xinhaolib.databean.LineCharViewData;
import erjinzhi.xinhao.xinhaolib.databean.ScaleLineBoomStringBean;
import erjinzhi.xinhao.xinhaolib.databean.ScaleLineLifeStringBean;
import erjinzhi.xinhao.xinhaolib.databean.listener.DataNotifyDataSetChangedListener;
import erjinzhi.xinhao.xinhaolib.linedata.idata.IBaseData;
import erjinzhi.xinhao.xinhaolib.linedata.idata.IPointData;
import erjinzhi.xinhao.xinhaolib.linedata.idata.ImpPointData;
import erjinzhi.xinhao.xinhaolib.linedata.listener.IScaleLine;
import erjinzhi.xinhao.xinhaolib.linedata.listener.PointDataViewRefreshListener;
import erjinzhi.xinhao.xinhaolib.utils.UIUtils;

/**
 * 专门处理坐标的一个类
 */
public class PointData implements IBaseData, IPointData, DataNotifyDataSetChangedListener, ImpPointData, IScaleLine, ILineData {


    /**
     * 未处理之前的点数据
     */
    private List<LineCharBean> mList;

    /**
     * 通知视图刷新
     */
    private PointDataViewRefreshListener mPointDataViewRefreshListener;

    /**
     * 处理过后的数据
     */
    private List<LineCharViewData> mViewPointCoordinatesList;

    /**
     * View高度
     */
    private int mHeight;

    /**
     * 传入左边刻度线的值
     */
    private ArrayList<ScaleLineLifeStringBean> mScaleLineLifeStringBeans;
    /**
     * 获取刻度线底部的值
     */
    private ArrayList<ScaleLineBoomStringBean> scaleLineBoomStringBeans;

    /**
     * 刻度线个数
     */
    private int mScale = 0;

    /**
     * 自带画笔
     */

    private Paint mPaint;
    /**
     * 刻度线的长度
     */
    private int mScaleWidth;

    /**
     * 用户所传递过来的设置
     */
    private CharViewData mCharViewData;


    //平均值
    private float average = 0;
    private XScale mXScale;


    @Override
    public void setCharViewData(CharViewData mCharViewData) {
        this.mCharViewData = mCharViewData;
    }

    @Override
    public void setList(List<LineCharBean> mList) {

        this.mList = mList;
        mPaint = new Paint();
        mViewPointCoordinatesList = new ArrayList<>();
        //吧 Y轴的信息算出来
        mXScale = new XScale(mList.size());

        mPaint.setTextSize(20);


    }

    public float getAverage() {
        return average;
    }

    //设置View高度
    @Override
    public void setViewHeight(int mHeight) {
        this.mHeight = mHeight;

    }

    @Override
    public void setPointDataViewRefreshListener(PointDataViewRefreshListener mPointDataViewRefreshListener) {

        this.mPointDataViewRefreshListener = mPointDataViewRefreshListener;

    }

    //开始处理点数据
    @Override
    public void calculate() {

        //先初始化画笔
        initPaint();
        //获取当前最大数
        int max = maxiMumNumber();
        //获取当前最小数
        int mid = miniMumNumber();
        //计算当前平均值
        averageCalculate(max, mid);
        //平均点已计算，开始计算每个点所在的高度
        pointHeight(mid);
        //高度已计算完毕，开始计算宽度
        pointWidth();
        //计算左边Text的位置
        calculateLifeText(max, mid);
        //计算底部Text的位置
        calculateBoomText();
        //通知视图说数据全部已算好
        if (mPointDataViewRefreshListener != null) {
            mPointDataViewRefreshListener.refreshPointDataView();
        }

    }

    //设置刻度线个数
    @Override
    public void setScale(int mScale) {

        this.mScale = mScale;

    }

    //设置刻度线长度
    @Override
    public void setScaleWidth(int mScaleWidth) {

        this.mScaleWidth = mScaleWidth;

    }

    /**
     * 计算刻度线(左边)
     *
     * @param max
     * @param mid
     */
    private void calculateLifeText(int max, int mid) {
        //刻度线分为10个
        int temp = (mScaleWidth / NUMBER_OF_SCALE_LINES);

        if (mScaleLineLifeStringBeans == null) {
            mScaleLineLifeStringBeans = new ArrayList<>();
        } else {
            //清空刻度线
            mScaleLineLifeStringBeans.clear();
        }

        //平均刻度线
        int t = absoluteValue(max - mid) / (NUMBER_OF_SCALE_LINES);


        for (int i = 0; i <= NUMBER_OF_SCALE_LINES; i++) {


            ScaleLineLifeStringBean scaleLineLifeStringBean = new ScaleLineLifeStringBean();

            scaleLineLifeStringBean.setmX(SCALE);


            //获取最大点位置

            float maxIndex = getMaxIndex();

            //获取最小点位置
            float minIndex = getMinIndex();

            //再除以N份
            float temp_s = (maxIndex - minIndex) / (NUMBER_OF_SCALE_LINES);

            //获取底部位置
            int boom = mHeight;

            boom -= (BOTTOM_DISTANCE + POINT_INTERVAL);

            scaleLineLifeStringBean.setmY(boom + (temp_s * i));

            scaleLineLifeStringBean.setText((mid + (t * i)) + "");

            mScaleLineLifeStringBeans.add(scaleLineLifeStringBean);
        }


    }

    //计算刻度线(底部)
    @Override
    public void calculateBoomText() {

        //整理数据
        if (scaleLineBoomStringBeans == null) {
            scaleLineBoomStringBeans = new ArrayList<>();
        }


        //将字符串传入，并计算坐标
        for (int i = 0; i < mList.size(); i++) {
            ScaleLineBoomStringBean scaleLineBoomStringBean = new ScaleLineBoomStringBean();

            scaleLineBoomStringBean.setText(mList.get(i).getTextBoom());

            int temp = (mHeight - BOTTOM_DISTANCE + BOOM_TEXT_DISTANCE + 20);

            int temp_x = mXScale.getXScales().get(i) + LEFT_DISTANCE + POINT_INTERVAL;

            scaleLineBoomStringBean.setmX(temp_x);

            scaleLineBoomStringBean.setmY(temp);

            //计算线的坐标X startX

            int startX = temp_x;

            //计算线的坐标Y startY

            int startY = mHeight - BOTTOM_DISTANCE;

            //计算线的坐标X endX

            int endX = temp_x;

            //计算线的坐标Y endY

            int endY = mHeight - BOTTOM_DISTANCE - NUMBER_OF_SCALE_LINES_LINEG;


            scaleLineBoomStringBean.setLineStartX(startX);

            scaleLineBoomStringBean.setLineStartY(startY);

            scaleLineBoomStringBean.setLineEndX(endX);

            scaleLineBoomStringBean.setLineEndY(endY);

            scaleLineBoomStringBeans.add(scaleLineBoomStringBean);


        }


    }


    /**
     * 取得画笔
     */
    @Override
    public Paint getPaint() {
        return mPaint;
    }

    //初始化画笔
    private void initPaint() {

        mPaint.setColor(Color.parseColor("#35ADA7"));
        mPaint.setStrokeWidth(20);
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(30);
    }

    //计算点的宽度
    private void pointWidth() {

        ArrayList<Integer> xScales = mXScale.getXScales();

        for (int i = 0; i < mViewPointCoordinatesList.size(); i++) {

            LineCharViewData lineCharViewData = mViewPointCoordinatesList.get(i);

            //与左边线对齐
            int temp = xScales.get(i) + LEFT_DISTANCE;
            //点之与线的padding
            temp += POINT_INTERVAL;
            lineCharViewData.setViewDataX(temp);


        }

    }


    //计算每个点所占用的高度
    private void pointHeight(int mid) {

        //因为我们用了padding所以要减去相对应的坐标
        int tempHeight = mHeight;

        tempHeight -= (BOTTOM_DISTANCE + TOP_DISTANCE);

        //使用for循环开始乘
        for (int i = 0; i < mList.size(); i++) {

            //计算之前的点
            int data = mList.get(i).getData();
            //开始计算每个点实质在View中所占用的高度,并存入
            LineCharViewData lineCharViewData = new LineCharViewData();

            float midTemp = mid * average;

            float temp = data * average;
            //减去最小值高度，始终使最小值在底部，不然可能会出现"占位"
            temp -= midTemp;
            //如果为0的话可能就没有了，所以如果是0的话默认在底部
            if (average == 0) {
                temp = tempHeight + TOP_DISTANCE;
            } else {
                //减反,如果不理解的哥们，可去除本行(注释)加以验证
                temp = tempHeight - temp;
                //减去顶部的距离
                temp = temp + TOP_DISTANCE;
                //减去padding的距离
                temp = temp - POINT_INTERVAL;
            }


            //设置点信息
            lineCharViewData.setViewDataY(temp);
            lineCharViewData.setData(data);
            mViewPointCoordinatesList.add(lineCharViewData);

        }

    }

    //获取最大点位置
    private float getMaxIndex() {

        int temp = mViewPointCoordinatesList.get(0).getData();

        float index = 0;

        for (int i = 0; i < mViewPointCoordinatesList.size(); i++) {

            if (mViewPointCoordinatesList.get(i).getData() > temp) {
                temp = mViewPointCoordinatesList.get(i).getData();
                index = mViewPointCoordinatesList.get(i).getViewDataY();
            }


        }


        return index;

    }

    //获取最小点位置

    private float getMinIndex() {

        int temp = mViewPointCoordinatesList.get(0).getData();

        float index = 0;

        for (int i = 0; i < mViewPointCoordinatesList.size(); i++) {

            if (mViewPointCoordinatesList.get(i).getData() < temp) {
                temp = mViewPointCoordinatesList.get(i).getData();
                index = mViewPointCoordinatesList.get(i).getViewDataY();
            }


        }


        return index;

    }

    /**
     * 计算每个点的平均值
     */

    private void averageCalculate(int max, int mid) {

        UIUtils.loge("大：" + max + " 小:" + mid);

        //用最大值减去最小值 获取中间值(必须为正数)
        int middle = absoluteValue(max - mid);
        //因为我们用了padding所以要减去相对应的坐标
        float tempHeight = mHeight;

        tempHeight -= (BOTTOM_DISTANCE + TOP_DISTANCE + (POINT_INTERVAL * 2));


        //用高度除以中间值 得到每个点的平均值
        //如果为0就直接等于0
        if (middle == 0) {
            average = tempHeight;
        } else {
            average = tempHeight / middle;
        }
        UIUtils.loge(average + "  ---每个的等分");


    }

    /**
     * 如果为负数就一定要让他变为正数
     */

    private int absoluteValue(int temp) {

        if (temp < 0) {
            temp *= -1;
        }

        return temp;
    }

    /**
     * 获取当前最大数
     */


    private int maxiMumNumber() {

        int temp = mList.get(0).getData();

        for (int i = 1; i < mList.size(); i++) {

            if (mList.get(i).getData() > temp) {
                temp = mList.get(i).getData();
            }

        }
        //如果显示高低线，那就看看高低线的值是不是最大的
        if (mCharViewData.isShowUp2DownLine()) {
            float max = mCharViewData.getUp2Down()[1];
            if (temp < max) {
                temp = (int) max;
            }
        }

        return temp;

    }


    /**
     * 获取当前最小数
     */

    private int miniMumNumber() {
        int temp = mList.get(0).getData();
        for (int i = 1; i < mList.size(); i++) {
            if (mList.get(i).getData() < temp) {
                temp = mList.get(i).getData();
            }

        }

        //如果显示高低线，那就看看高低线的值是不是最大的
        if (mCharViewData.isShowUp2DownLine()) {
            float min = mCharViewData.getUp2Down()[0];
            if (temp > min) {
                temp = (int) min;
            }
        }

        return temp;

    }

    /**
     * 获取到计算过后的点
     *
     * @return
     */
    @Override
    public List<LineCharViewData> getViewPointCoordinatesList() {
        return mViewPointCoordinatesList;
    }

    /**
     * 用户通知刷新数据
     */
    @Override
    public void notifyDataSetChanged() {


    }


    /**
     * 设置刻度线左边的值
     */

    @Override
    public void setScaleLineLifeStringBeans(ArrayList<ScaleLineLifeStringBean> scaleLineLifeStringBeans) {
        mScaleLineLifeStringBeans = scaleLineLifeStringBeans;
    }


    /**
     * 设置刻度线底部的值
     *
     * @param scaleLineBoomStringBeans
     */
    @Override
    public void setScaleLineBoomStringBeans(ArrayList<ScaleLineBoomStringBean> scaleLineBoomStringBeans) {
        this.scaleLineBoomStringBeans = scaleLineBoomStringBeans;
    }

    /**
     * 获取左边的一些东西
     *
     * @return
     */
    @Override
    public ArrayList<ScaleLineLifeStringBean> getScaleLineLifeStringBeans() {

        return mScaleLineLifeStringBeans;
    }

    /**
     * 获取底部的一些东西
     *
     * @return
     */
    @Override
    public ArrayList<ScaleLineBoomStringBean> getScaleLineBoomStringBeans() {


        return scaleLineBoomStringBeans;
    }
}
