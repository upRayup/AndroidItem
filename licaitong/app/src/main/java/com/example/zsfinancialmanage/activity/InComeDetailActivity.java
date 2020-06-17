package com.example.zsfinancialmanage.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.zsfinancialmanage.R;
import com.example.zsfinancialmanage.adapter.IncomeAdapter;
import com.example.zsfinancialmanage.bean.IncomeBean;
import com.example.zsfinancialmanage.db.MyDBHelper;

import java.util.ArrayList;
import java.util.List;

public class InComeDetailActivity extends AppCompatActivity {
//1 定义对象
    RecyclerView recy_view;
    MyDBHelper mhelper;
    SQLiteDatabase db;
    List<IncomeBean> arr1=new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_come_detail);
        //2 绑定控件
        initView();

        //3 准备数据
        initData();

        //4 设计每一行的子布局

        //5定义适配器： 数据和子布局关联起来（桥梁的作用）
        IncomeAdapter adapter=new IncomeAdapter(InComeDetailActivity.this,arr1);

        //6将适配器和布局管理器加载到控件当中
        StaggeredGridLayoutManager st=new StaggeredGridLayoutManager(StaggeredGridLayoutManager.VERTICAL,1);
        recy_view.setLayoutManager(st);
        recy_view.setAdapter(adapter);
    }

    //2 绑定控件----------------代码
    private void initView() {
        recy_view=findViewById(R.id.recy_view_indetail);
        mhelper=new MyDBHelper(InComeDetailActivity.this);
        db=mhelper.getWritableDatabase();
    }

    //3 准备数据----------------代码
    private void initData() {
        //从数据库查询所有的新增收入信息,取出数据
        Cursor cursor=db.rawQuery("select * from in_come",null);
      while(cursor.moveToNext()){
          int myid=cursor.getInt(cursor.getColumnIndex("id"));
          double mymoney=cursor.getDouble(cursor.getColumnIndex("inmoney"));
          String mytime=cursor.getString(cursor.getColumnIndex("intime"));
          String mytype=cursor.getString(cursor.getColumnIndex("intype"));
          String mypayer=cursor.getString(cursor.getColumnIndex("inpayer"));
          String myremark=cursor.getString(cursor.getColumnIndex("inremark"));
          IncomeBean incomeBean=new IncomeBean( myid,mymoney,mytime,mytype,mypayer,myremark);
          arr1.add(incomeBean);
      }

    }

}
