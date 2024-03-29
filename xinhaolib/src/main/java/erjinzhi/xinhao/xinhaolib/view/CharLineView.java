package erjinzhi.xinhao.xinhaolib.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;

import java.util.List;

import erjinzhi.xinhao.xinhaolib.databean.CharViewData;
import erjinzhi.xinhao.xinhaolib.databean.LineCharBean;
import erjinzhi.xinhao.xinhaolib.databean.listener.NotifyDataSetChangedListener;
import erjinzhi.xinhao.xinhaolib.linedata.idata.IBaseData;
import erjinzhi.xinhao.xinhaolib.linedata.idata.ILineData;
import erjinzhi.xinhao.xinhaolib.linedata.idata.IRollingLine;
import erjinzhi.xinhao.xinhaolib.linedata.idata.IScaleLineData;
import erjinzhi.xinhao.xinhaolib.linedata.idata.IUp2DownLineData;
import erjinzhi.xinhao.xinhaolib.linedata.idata.ImpPointData;
import erjinzhi.xinhao.xinhaolib.linedata.listener.LineDataViewRefreshListener;
import erjinzhi.xinhao.xinhaolib.linedata.listener.PointDataViewRefreshListener;
import erjinzhi.xinhao.xinhaolib.linedata.listener.RollingLineRefreshListener;
import erjinzhi.xinhao.xinhaolib.linedata.listener.Up2DownLineRefreshListener;
import erjinzhi.xinhao.xinhaolib.view.viewlistener.CharViewDataRefreshListener;
import erjinzhi.xinhao.xinhaolib.view.viewlistener.ViewRefreshListener;

/**
 * View 底层,这是一款轻量级的线图 作者XINHAO_HAN
 */

public abstract class CharLineView extends View implements ViewRefreshListener, CharViewDataRefreshListener, PointDataViewRefreshListener, LineDataViewRefreshListener, NotifyDataSetChangedListener, RollingLineRefreshListener, Up2DownLineRefreshListener, IBaseData {

    private Context mContext;

    private int mWidth;

    private int mHeight;

    IScaleLineData mScaleLineData;

    ImpPointData mPointData;

    ILineData mLineData;

    IRollingLine mIRollingLine;

    IUp2DownLineData mIUp2DownLineData;

    private CharViewData mCharViewData;


    private List<LineCharBean> list;

    public CharLineView(Context context) {
        super(context);
        init(context);
    }

    public CharLineView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CharLineView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private void init(Context mContext) {
        this.mContext = mContext;

        mCharViewData = new CharViewData();
        mPointData = mCharViewData.getmPointData();
        mPointData.setCharViewData(mCharViewData);
        mLineData = mCharViewData.getmLineData();
        mIRollingLine = mCharViewData.getmIRollingLine();
        mIUp2DownLineData = mCharViewData.getIUp2DownLineData();
        mIUp2DownLineData.setCharViewData(mCharViewData);
        mCharViewData.setCharViewDataRefreshListener(this);
        mCharViewData.setNotifyDataSetChangedListener(this);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {

        mWidth = right;
        mHeight = bottom;

    }

    /**
     * 开始画图
     */
    public void startLine() {


        this.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                CharLineView.this.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                calculateData();
            }
        });

    }

    /**
     * 获取View高度
     */
    public int getViewHeight() {
        return mHeight;
    }

    /**
     * 获取View宽度
     */
    public int getViewWidth() {
        return mWidth;
    }

    /**
     * 计算所有的信息
     *
     * @param
     */

    private void calculateData() {
        /**
         * 计算刻度线
         *
         */
        //计算刻度线 IScaleLineData
        mScaleLineData = mCharViewData.getmIScaleLineData();
        int mWidth = mScaleLineData.getmWidth();
        int mHeight = mScaleLineData.getmHeight();

        if (mWidth == 0 || mHeight == 0) {
            mScaleLineData.setmWidth(this.mWidth);
            mScaleLineData.setmHeight(this.mHeight);
        }

        mScaleLineData.setDataSize(mCharViewData.getList().size());
        //刻度线计算完毕通知本View刷新
        mScaleLineData.setViewRefreshListener(this);
        //开始计算
        mScaleLineData.calculate();


        /**
         * 计算点
         *
         */

        //计算点所在的位置,吧View高度传进去
        mPointData.setViewHeight(this.mHeight);
        //计算好之后通知本View刷新
        mPointData.setPointDataViewRefreshListener(this);
        //传入未处理的数据
        mPointData.setList(mCharViewData.getList());
        //设置刻度线的长度
        mPointData.setScaleWidth(mScaleLineData.getScaleWidth());
        //开始计算
        mPointData.calculate();
        //将算好的刻度线传入刻度线类(左边)
        mScaleLineData.setScaleLineLifeStringBeans(mPointData.getScaleLineLifeStringBeans());
        //将算好的刻度线传入刻度线类(底边)
        mScaleLineData.setScaleLineBoomStringBeans(mPointData.getScaleLineBoomStringBeans());


        /**
         *
         * 计算线
         */
        //传入点数据
        mLineData.setViewPointCoordinatesList(mPointData.getViewPointCoordinatesList());
        //计算好通知View刷新
        mLineData.setLineDataViewRefreshListener(this);
        //开始计算
        mLineData.calculate();

        /**
         * 等点和线位置计算完毕才能执行该条命令
         */
        //计算侧边显示标题的值
        mScaleLineData.calculateString();

        /**
         *
         * 处理滚动
         *
         */

        mIRollingLine.setRollingLineRefreshListener(this);
        mIRollingLine.setScaleLineBoomStringBeans(mPointData.getScaleLineBoomStringBeans());
        mIRollingLine.setScaleLineLifeStringBeans(mPointData.getScaleLineLifeStringBeans());
        mIRollingLine.setViewPointCoordinatesList(mPointData.getViewPointCoordinatesList());
        mIRollingLine.setLineViewDatas(mLineData.getLineViewDatas());
        mIRollingLine.setXLine(mScaleLineData.getmX());
        mIRollingLine.setYLine(mScaleLineData.getmY());
        mIRollingLine.setWidth(getViewWidth());


        /**
         *
         * 处理高低线 mIUp2DownLineData
         *
         */
        mIUp2DownLineData.setAverage(mPointData.getAverage());
        mIUp2DownLineData.setShowUp2DownLine(mCharViewData.isShowUp2DownLine());

        mIUp2DownLineData.setUp2Down(mCharViewData.getUp2Down());

        mIUp2DownLineData.setViewPointCoordinatesList(mPointData.getViewPointCoordinatesList());

        mIUp2DownLineData.setUp2DownLineRefreshListener(this);

        mIUp2DownLineData.setScaleLineY(mScaleLineData.getmY());

        mIUp2DownLineData.calculate();


    }


    @Override
    protected void onDraw(Canvas canvas) {
        //画刻度线
        calibrationDraw(canvas);
        //画点之前先判断有些东西是不是空的
        if (mPointData.getViewPointCoordinatesList() != null && mPointData.getViewPointCoordinatesList().size() > 0) {
            //画点
            pointDraw(canvas);
        }
        //画点与线之前先判断是不是空的
        if (mLineData.getLineViewDatas() != null && mLineData.getLineViewDatas().size() > 0) {
            //画点与点之间的线
            pointIsLineDraw(canvas);
        }

        //画高低线
        up2DownDraw(canvas);

    }

    /**
     * 画高低线
     */

    public abstract void up2DownDraw(Canvas canvas);


    /**
     * 画刻度线
     *
     * @param canvas
     */
    public abstract void calibrationDraw(Canvas canvas);

    /**
     * 画点
     */
    public abstract void pointDraw(Canvas canvas);

    /**
     * 画点与点之间的线
     */
    public abstract void pointIsLineDraw(Canvas canvas);

    @Override
    public void refresh() {
        invalidate();
    }

    public CharViewData getCharViewData() {
        return mCharViewData;
    }

    @Override
    public void charViewDataRefresh() {
        invalidate();


        //获取对外开放存取处理模块，是否开发者传进来的值为空
        list = mCharViewData.getList();
        if (list == null) {
            throw new RuntimeException("CharViewData List<LineCharBean> is null");
        }
        //从此处开始调用
        startLine();


    }

    /**
     * 点通知视图更新
     */
    @Override
    public void refreshPointDataView() {
        invalidate();

    }

    /**
     * 线更新
     */
    @Override
    public void lineDataViewRefresh() {
        invalidate();
    }

    /**
     * 要求刷新数据
     */
    @Override
    public void notifyDataSetChanged() {


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //处理触摸事件
        mIRollingLine.onTouchEventRolling(event);
        return true;
    }

    @Override
    public void rollingRefreshLineView(float x) {
        invalidate();
    }

    @Override
    public float crossLine() {
        return 0;
    }

    @Override
    public void up2DownLineRefresh(String max, String min) {
        invalidate();
    }
}
