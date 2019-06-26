package erjinzhi.xinhao.xinhaolib.databean;

import java.util.List;

import erjinzhi.xinhao.xinhaolib.databean.listener.NotifyDataSetChangedListener;
import erjinzhi.xinhao.xinhaolib.linedata.LineData;
import erjinzhi.xinhao.xinhaolib.linedata.PointData;
import erjinzhi.xinhao.xinhaolib.linedata.RollingLine;
import erjinzhi.xinhao.xinhaolib.linedata.ScaleLineData;
import erjinzhi.xinhao.xinhaolib.linedata.Up2DownLineData;
import erjinzhi.xinhao.xinhaolib.linedata.idata.ILineData;
import erjinzhi.xinhao.xinhaolib.linedata.idata.IRollingLine;
import erjinzhi.xinhao.xinhaolib.linedata.idata.IScaleLineData;
import erjinzhi.xinhao.xinhaolib.linedata.idata.IUp2DownLineData;
import erjinzhi.xinhao.xinhaolib.linedata.idata.ImpPointData;
import erjinzhi.xinhao.xinhaolib.view.viewlistener.CharViewDataRefreshListener;

/**
 * 管理所有数据(对外开放)
 */
public class CharViewData {


    private List<LineCharBean> mList;


    private ILineData mLineData;

    private IScaleLineData mIScaleLineData;

    private ImpPointData mPointData;

    private IRollingLine mIRollingLine;

    public ImpPointData getmPointData() {
        if (mPointData == null) {

            mPointData = new PointData();
        }
        return mPointData;
    }

    //高低线
    private IUp2DownLineData mIUp2DownLineData;


    public IUp2DownLineData getIUp2DownLineData() {
        if (mIUp2DownLineData == null) {
            mIUp2DownLineData = new Up2DownLineData();
        }
        return mIUp2DownLineData;
    }

    public void setIUp2DownLineData(IUp2DownLineData mIUp2DownLineData) {
        this.mIUp2DownLineData = mIUp2DownLineData;
    }

    //高低线
    private float[] up2Down = new float[2];

    //是否显示高低线
    private boolean isShowUp2DownLine = false;

    public float[] getUp2Down() {
        return up2Down;
    }

    public boolean isShowUp2DownLine() {
        return isShowUp2DownLine;
    }

    public void setShowUp2DownLine(boolean showUp2DownLine) {
        isShowUp2DownLine = showUp2DownLine;
    }

    /**
     * 高低线
     *
     * @param up2Down
     */
    public void setUp2Down(float[] up2Down) {

        /**
         *
         * [0]为底线  [1]为高线，不能让高线小于底线
         *
         */
        if (up2Down[1] < up2Down[0]) {

            throw new RuntimeException("setUp2Down float[min] > float[max]");

        }


        this.up2Down = up2Down;
    }

    public void setmPointData(ImpPointData mPointData) {
        this.mPointData = mPointData;
    }

    public IScaleLineData getmIScaleLineData() {
        if (mIScaleLineData == null) {
            mIScaleLineData = new ScaleLineData(0, 0);
        }
        return mIScaleLineData;
    }

    public void setScaleLineStyle(IScaleLineData mIScaleLineData) {
        this.mIScaleLineData = mIScaleLineData;
    }

    public ILineData getmLineData() {
        if (mLineData == null) {
            mLineData = new LineData();
        }
        return mLineData;
    }

    public void setLineStyle(ILineData mLineData) {
        this.mLineData = mLineData;
    }

    private CharViewDataRefreshListener mCharViewDataRefreshListener;

    /**
     * 设置信息
     */

    public void setCharViewDataRefreshListener(CharViewDataRefreshListener mCharViewDataRefreshListener) {

        this.mCharViewDataRefreshListener = mCharViewDataRefreshListener;
    }

    private NotifyDataSetChangedListener mNotifyDataSetChangedListener;

    public void setNotifyDataSetChangedListener(NotifyDataSetChangedListener mNotifyDataSetChangedListener) {
        this.mNotifyDataSetChangedListener = mNotifyDataSetChangedListener;
    }

    public CharViewData setLineData(List<LineCharBean> mList) {
        this.mList = mList;

        return this;
    }

    public List<LineCharBean> getList() {
        if (mList == null) {
            throw new RuntimeException("List<LineCharBean> is null");
        }
        return mList;
    }


    public void build() {

        if (mCharViewDataRefreshListener != null) {
            mCharViewDataRefreshListener.charViewDataRefresh();
        }

    }

    /**
     * 刷新数据
     */
    public void notifyDataSetChanged() {


        if (mNotifyDataSetChangedListener != null) {
            mNotifyDataSetChangedListener.notifyDataSetChanged();
        }


    }

    public IRollingLine getmIRollingLine() {
        if (mIRollingLine == null) {
            mIRollingLine = new RollingLine();
        }

        return mIRollingLine;

    }

    public void setIRollingLine(IRollingLine mIRollingLine) {
        this.mIRollingLine = mIRollingLine;
    }
}
