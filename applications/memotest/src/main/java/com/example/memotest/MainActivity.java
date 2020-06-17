package com.example.memotest;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.memotest.adapter.MemoAdapter;
import com.example.memotest.bean.MemoBean;
import com.example.memotest.db.MyDbHelper;

import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity {
    //定义对象
    Button btn_add;
    RecyclerView recy_view;
    MyDbHelper mhelper;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //1 绑定控件
        initView();
        //2 对单击添加单击事件
        btnonclicknext();
        //3 完善：从数据库获取数据，显示到 RecyclerView 控件里面
        recyDisplay();
    }
    //1 绑定控件-----------代码
    private void initView() {
        btn_add=findViewById(R.id.button_add);
        recy_view=findViewById(R.id.recy_view);
        mhelper=new MyDbHelper(MainActivity.this);
        db=mhelper.getWritableDatabase();
    }
    //2 对单击添加单击事件-----代码
    private void btnonclicknext() {
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //单击后跳转到一下页
                Intent intent=new
                        Intent(MainActivity.this,AddInfoActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    //3 完善：从数据库获取数据，显示到 RecyclerView 控件里面---------------代码
    private void recyDisplay() {
        //3.1 准备数据-----------标题、内容、图片、时间（类）
        List<MemoBean> arr=new ArrayList();
        //从数据库里面取数据了
        Cursor cursor=db.rawQuery("select * from tb_memory",null);
        while(cursor.moveToNext()){
            String mytitle=cursor.getString(cursor.getColumnIndex("title"));
            String mycontent=cursor.getString(cursor.getColumnIndex("content"));
            String myimgpath=cursor.getString(cursor.getColumnIndex("imgpath"));
            String mymtime=cursor.getString(cursor.getColumnIndex("mtime"));
            MemoBean memoBean=new MemoBean(mytitle,mycontent,myimgpath,mymtime);
            arr.add(memoBean);
        }
        cursor.close();
        //3.2 子布局 recy_item
        //3.3 数据------桥（适配器 MemoAdapter）----------------子布局
        MemoAdapter adapter=new MemoAdapter(MainActivity.this,arr);
        //3.4 确定显示的方式
        StaggeredGridLayoutManager st=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recy_view.setLayoutManager(st);
        //3.5 让数据显示出来
        recy_view.setAdapter(adapter);
    } }
