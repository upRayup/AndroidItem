package com.example.zsfinancialmanage.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;

import com.example.zsfinancialmanage.R;
import com.example.zsfinancialmanage.db.MyDBHelper;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;


public class DataAnalyseActivity extends AppCompatActivity {
//1 定义对象
    LineChart income_chart,outpay_chart;
    MyDBHelper mhelper;
    SQLiteDatabase db;
    String[] indata={"学习-奖金","补助-奖金","比赛-奖励","业余-兼职","基本-工资","福利-分红","加班-津贴","其他"};
    //收入类型数据统计的初始值
    int  xxjjmoney=0;
    int bzjjmoney=0;
    int bsjlmoney=0;
    int yyjzmoney=0;
    int jbgzmoney=0;
    int  flfhmoney=0;
    int jbjtmoney=0;
    int qtmoney=0;
    String[] outdata={"电影-娱乐","美食-畅饮","欢乐-购物","手机-充值","交通-出行","教育-培训","社交-礼仪","生活-日用","其他"};
    //收入类型数据统计的初始值
    int  dyylmoney=0;
    int mscymoney=0;
    int hlgwmoney=0;
    int sjczmoney=0;
    int jtcxmoney=0;
    int  jypxmoney=0;
    int sjlymoney=0;
    int shrymoney=0;
    int othermoney=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_analyse);
        //2 绑定控件
        initView();

        //3 收入汇总分析
        inComeData();

        //4 支出汇总分析
        outComeData();

    }


    //2 绑定控件---------------代码
    private void initView() {
        income_chart=findViewById(R.id.income_chart_data);
        outpay_chart=findViewById(R.id.outpay_chart_data);
        mhelper=new MyDBHelper(DataAnalyseActivity.this);
        db=mhelper.getWritableDatabase();
    }

    //3 收入汇总分析-------------------代码
    private void inComeData() {
       //第一部分：获取数据
        Cursor cursor =db.rawQuery("select * from in_come",null);
        while(cursor.moveToNext()){
            Double mymoney=cursor.getDouble(cursor.getColumnIndex("inmoney"));
            String mytype=cursor.getString(cursor.getColumnIndex("intype"));
            if(mytype.equals("学习-奖金")){
                xxjjmoney+=mymoney;
            }else if(mytype.equals("补助-奖金")){
                bzjjmoney+=mymoney;
            }else if(mytype.equals("比赛-奖励")){
                bzjjmoney+=mymoney;
            }else if(mytype.equals("业余-兼职")){
                yyjzmoney+=mymoney;
            }else if(mytype.equals("基本-工资")){
                jbgzmoney+=mymoney;
            }else if(mytype.equals("福利-分红")){
                flfhmoney+=mymoney;
            }else if(mytype.equals("加班-津贴")){
                jbjtmoney+=mymoney;
            }else if(mytype.equals("其他")){
                qtmoney+=mymoney;
            }
        }

       //第二部分：LineChart图表初始化设置---Xy轴的设置
            XAxis xAxis=income_chart.getXAxis();//获取此图表的x轴轴线
            YAxis yAxisleft =income_chart.getAxisLeft();//获取此图表的Y轴左侧轴线
            YAxis yAxisright =income_chart.getAxisRight();//获取此图表的Y轴右侧轴线
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//设置X轴线的位置为底部
            yAxisleft.setAxisMinimum(0f);//保证Y轴从0开始，不然会上移一点。
            yAxisright.setAxisMinimum(0f);
            xAxis.setValueFormatter(new IAxisValueFormatter() {//x轴自定义标签的设置
            @Override
            public String getFormattedValue(float v, AxisBase axisBase) {
                return indata[(int) v];
            }
        });

        //第三部分:LineDataSet曲线初始化设置
        List<Entry>   inentries=new ArrayList<>();//Y轴的数据
        inentries.add(new Entry(0,xxjjmoney));
        inentries.add(new Entry(1,bzjjmoney));
        inentries.add(new Entry(2,bsjlmoney));
        inentries.add(new Entry(3,yyjzmoney));
        inentries.add(new Entry(4,jbgzmoney));
        inentries.add(new Entry(5,flfhmoney));
        inentries.add(new Entry(6,jbjtmoney));
        inentries.add(new Entry(7,qtmoney));
        LineDataSet lineDataSet=new LineDataSet(inentries,"金额");//代表一条线,“金额”是曲线名称
        lineDataSet.setValueTextSize(25);//曲线上文字的大小
        lineDataSet.setValueTextColor(Color.WHITE);//曲线上文字的颜色
        lineDataSet.setDrawFilled(true);//设置折线图填充


        //第四部分：曲线展示
        LineData data=new LineData(lineDataSet);//创建LineData对象 属于LineChart折线图的数据集合
        income_chart.setData(data);// 添加到图表中
    }

    //4 支出汇总分析--------------------代码
    private void outComeData() {
        //第一部分：获取数据
        Cursor cursor =db.rawQuery("select * from pay_out",null);
        while(cursor.moveToNext()){
            Double mymoney=cursor.getDouble(cursor.getColumnIndex("outmoney"));
            String mytype=cursor.getString(cursor.getColumnIndex("outtype"));
            if(mytype.equals("电影-娱乐")){
                dyylmoney+=mymoney;
            }else if(mytype.equals("美食-畅饮")){
                mscymoney+=mymoney;
            }else if(mytype.equals("欢乐-购物")){
                hlgwmoney+=mymoney;
            }else if(mytype.equals("手机-充值")){
                sjczmoney+=mymoney;
            }else if(mytype.equals("交通-出行")){
                jtcxmoney+=mymoney;
            }else if(mytype.equals("教育-培训")){
                jypxmoney+=mymoney;
            }else if(mytype.equals("社交-礼仪")){
                sjlymoney+=mymoney;
            }else if(mytype.equals("生活-日用")){
                shrymoney+=mymoney;
            }else if(mytype.equals("其他")){
                othermoney+=mymoney;
            }
        }

        //第二部分：LineChart图表初始化设置---Xy轴的设置
        XAxis xAxis=outpay_chart.getXAxis();//获取此图表的x轴轴线
        YAxis yAxisleft =outpay_chart.getAxisLeft();//获取此图表的Y轴左侧轴线
        YAxis yAxisright =outpay_chart.getAxisRight();//获取此图表的Y轴右侧轴线
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//设置X轴线的位置为底部
        yAxisleft.setAxisMinimum(0f);//保证Y轴从0开始，不然会上移一点。
        yAxisright.setAxisMinimum(0f);
        xAxis.setValueFormatter(new IAxisValueFormatter() {//x轴自定义标签的设置
            @Override
            public String getFormattedValue(float v, AxisBase axisBase) {
                return outdata[(int) v];
            }
        });

        //第三部分:LineDataSet曲线初始化设置
        List<Entry>   outentries=new ArrayList<>();//Y轴的数据
        outentries.add(new Entry(0,dyylmoney));
        outentries.add(new Entry(1,mscymoney));
        outentries.add(new Entry(2,hlgwmoney));
        outentries.add(new Entry(3,sjczmoney));
        outentries.add(new Entry(4,jtcxmoney));
        outentries.add(new Entry(5,jypxmoney));
        outentries.add(new Entry(6,sjlymoney));
        outentries.add(new Entry(7,shrymoney));
        outentries.add(new Entry(8,othermoney));
        LineDataSet lineDataSet=new LineDataSet(outentries,"金额");//代表一条线,“金额”是曲线名称
        lineDataSet.setValueTextSize(25);//曲线上文字的大小
        lineDataSet.setValueTextColor(Color.WHITE);//曲线上文字的颜色
        lineDataSet.setDrawFilled(true);//设置折线图填充


        //第四部分：曲线展示
        LineData data=new LineData(lineDataSet);//创建LineData对象 属于LineChart折线图的数据集合
        outpay_chart.setData(data);// 添加到图表中
    }

}
