package erjinzhi.xinhao.xinhaolib.linedata;

import java.util.ArrayList;

/**
 * 应为Y轴是固定数据，不是由后台传递进来的。所以单独开辟一个
 * <p>
 * 专门管理X轴
 */

public class XScale {

    //默认间隔
    private static final int X_SCALE = 50;

    //要保存的坐标

    private int scale = 0;

    //存取坐标合集

    private ArrayList<Integer> mXScales;

    //决定算几个
    private int mSize;

    public XScale(int mSize) {

        mXScales = new ArrayList<>();
        this.mSize = mSize;
        startCalculate();
    }

    private void startCalculate() {


        for (int i = 0; i < mSize; i++) {

            if (i == 0) {
                mXScales.add(scale);
            }
            scale += X_SCALE;
            mXScales.add(scale);

        }

    }


    /**
     * 获取当前已经算好的坐标
     *
     * @return
     */
    public ArrayList<Integer> getXScales() {

        return mXScales;
    }


}
