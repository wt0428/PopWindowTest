package com.popwindow;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.popwindow.bean.PopDataBean;
import com.popwindow.customviews.PopWindow;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity implements PopupWindow.OnDismissListener {

    @InjectView(R.id.tvJBZD)
    TextView tvJBZD;
    @InjectView(R.id.tv_patient_diseasecontent)
    TextView tvPatientDiseasecontent;
    private PopWindow popWindowJBZD = null;
    List<PopDataBean> datasJBZD = new ArrayList<>();
    //选中单条
    private int mnSeclectItem = -1;
    //单条id
    private String mnSeclectItemID = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        initListeners();
        initDatas();


    }

    /**
     * 初始化数据
     */
    private void initDatas(){
        initPopDatas();
    }

    /**
     * 监听事件
     */
    private void initListeners(){
        tvPatientDiseasecontent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onPopwindowJBZD();
            }
        });

    }





    /**
     * 初始化Popwindow
     */
    private void initPopDatas() {
        datasJBZD = new ArrayList<>();
        datasJBZD.add(new PopDataBean("0", "脑外伤"));
        datasJBZD.add(new PopDataBean("1", "脑血管意外"));
        datasJBZD.add(new PopDataBean("2", "缺血缺氧性脑损伤"));
        datasJBZD.add(new PopDataBean("3", "中毒"));
        datasJBZD.add(new PopDataBean("4", "脑肿瘤"));
        datasJBZD.add(new PopDataBean("5", "其他"));
        // 实例化PopWindow
        popWindowJBZD = new PopWindow(this);
        // 设置点击其他位置mTestPopwindow2消失
        popWindowJBZD.setOnDismissListener(this);
    }

    private void onPopwindowJBZD() {
        if (popWindowJBZD == null) {
            return;
        }

        //回到接受
        popWindowJBZD.setOnData(new PopWindow.OnGetData() {

            //记录上一次选中的item
            @Override
            public int onSeclectItem() {
                return mnSeclectItem;
            }

            @Override
            public void onDataCallBack(int nSectlect, ArrayList<PopDataBean> mArrayList) {
                mnSeclectItem = nSectlect;
                String name = mArrayList.get(nSectlect).getName();
                tvPatientDiseasecontent.setText(name);

                mnSeclectItemID = mArrayList.get(nSectlect).getIndex();
            }


            //传递数据源过去
            @Override
            public ArrayList<PopDataBean> onArrayList() {
                return (ArrayList<PopDataBean>) datasJBZD;
            }
        });
        popWindowJBZD.showAtLocation(tvPatientDiseasecontent, Gravity.CENTER, 10, 10);
    }

    @Override
    public void onDismiss() {

    }
}
