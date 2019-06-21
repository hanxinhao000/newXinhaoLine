package erjinzhi.xinhao.xinhaolib.databean;

import java.util.List;

import erjinzhi.xinhao.xinhaolib.databean.listener.NotifyDataSetChangedListener;
import erjinzhi.xinhao.xinhaolib.linedata.LineData;
import erjinzhi.xinhao.xinhaolib.linedata.PointData;
import erjinzhi.xinhao.xinhaolib.linedata.ScaleLineData;
import erjinzhi.xinhao.xinhaolib.linedata.idata.ILineData;
import erjinzhi.xinhao.xinhaolib.linedata.idata.IScaleLineData;
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

    public ImpPointData getmPointData() {
        if (mPointData == null) {

            mPointData = new PointData();
        }
        return mPointData;
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
}
