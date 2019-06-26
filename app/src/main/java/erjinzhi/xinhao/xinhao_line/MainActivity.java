package erjinzhi.xinhao.xinhao_line;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import erjinzhi.xinhao.xinhaolib.databean.LineCharBean;
import erjinzhi.xinhao.xinhaolib.utils.UIUtils;
import erjinzhi.xinhao.xinhaolib.view.CharDrawLineView;
import erjinzhi.xinhao.xinhaolib.databean.CharViewData;

public class MainActivity extends Activity {


    private List<LineCharBean> mList;
    private CharDrawLineView char_line;
    private Button refresh;
    private Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        refresh = findViewById(R.id.refresh);
        char_line = findViewById(R.id.char_line);
        final CharViewData charViewData = char_line.getCharViewData();
        mList = new ArrayList<>();
        random = new Random();

        for (int i = 0; i <= 60; i++) {

            int i1 = random.nextInt(600);
            LineCharBean l = new LineCharBean();
            //l.setTextLife("哈哈哈");
            l.setTextBoom("002");
            //测试有负数出现
            /*if (i1 < 100) {

                i1 *= -1;
            }*/
            //l.setData(2000 - i);
            l.setData(i1 + 50);

            UIUtils.loge(">>>>" + (2000 - i));
            mList.add(l);
        }
       /* {
            LineCharBean l = new LineCharBean();
            l.setData(1);
            mList.add(l);
        }
        {
            LineCharBean l = new LineCharBean();
            l.setData(2);
            mList.add(l);
        }
        {
            LineCharBean l = new LineCharBean();
            l.setData(3456);
            mList.add(l);
        }
        {
            LineCharBean l = new LineCharBean();
            l.setData(4000);
            mList.add(l);
        }
        {
            LineCharBean l = new LineCharBean();
            l.setData(500);
            mList.add(l);
        }
        {
            LineCharBean l = new LineCharBean();
            l.setData(2000);
            mList.add(l);
        }*/
        UIUtils.loge("<<<<" + mList.toString());

        charViewData.setLineData(mList);

        float[] upDown = {100, 500};
        charViewData.setUp2Down(upDown);
        charViewData.build();

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CharViewData charViewData1 = char_line.getCharViewData();
                List<LineCharBean> mList = new ArrayList<>();
                for (int i = 0; i < 20; i++) {
                    int i1 = random.nextInt(600);

                    LineCharBean l = new LineCharBean();

                    l.setData(i1 + 50);

                    mList.add(l);

                }
                charViewData1.setLineData(mList);
                charViewData1.build();


                Toast.makeText(MainActivity.this, "更新数据", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
