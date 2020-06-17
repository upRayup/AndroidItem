package com.example.memotest.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.memotest.R;
import com.example.memotest.bean.MemoBean;
import com.example.memotest.db.MyDbHelper;

import java.util.List;
import java.util.Random;
//1 类文件后面添加泛型
//2 鼠标定位类文件行红色波浪线处，Alt+Enter 键：添加未实现的方法
//3 鼠标定位类文件行 ViewHolder 处，Alt+Enter 键：添加内部类
//4 鼠标定位界面最下方内部类 ViewHolder 处，添加 extends RecyclerView.ViewHolder
//5 鼠标定位界面最下方内部类 ViewHolder 红色波浪线处，Alt+Enter 键：添加构造方法
//6 定义两个对象：上下文环境和数组
//7 定义两个对象下方的空白处：Alt+Insert 键，添加适配器的构造方法
public class MemoAdapter extends RecyclerView.Adapter<MemoAdapter.ViewHolder> {
    private Context mcontext;
    private List<MemoBean> arr1;
    private MyDbHelper mhelper1;
    private SQLiteDatabase db;
    public MemoAdapter(Context mcontext, List<MemoBean> arr1) {
        this.mcontext = mcontext;
        this.arr1 = arr1;
    }
    //负责加载 item 布局
    @NonNull
    @Override
    public MemoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                     int i) {
        View view=
                LayoutInflater.from(mcontext).inflate(R.layout.recy_item,parent,false);
        ViewHolder mholder=new ViewHolder(view);
        return mholder;
    }
    //负责加载 item 的数据
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(@NonNull MemoAdapter.ViewHolder mholder, final
    int i) {
        final MemoBean memoBean=arr1.get(i);
        mholder.item_title.setText(memoBean.getTitle());
        mholder.item_content.setText(memoBean.getContent());
        mholder.item_time.setText(memoBean.getTime());

        Glide.with(mcontext).load(memoBean.getImgpath()).into(mholder.item_img);
        Random random = new Random();
        int color = Color.argb(255, random.nextInt(256), random.nextInt(256),
                random.nextInt(256));
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(GradientDrawable.RECTANGLE);//形状
        gradientDrawable.setCornerRadius(10f);//设置圆角 Radius
        gradientDrawable.setColor(color);//颜色
        mholder.item_layout.setBackground(gradientDrawable);//设置为 background
        //完善：单击其中的一个子项，弹出删除功能
        mholder.item_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //弹出对话框，删除
                AlertDialog.Builder dialog=new AlertDialog.Builder(mcontext);
                dialog.setMessage("确定删除吗？");
                dialog.setPositiveButton("确定", new
                        DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int abc)
                            {
                                //从数据库当中删除掉
                                mhelper1= new MyDbHelper(mcontext);
                                db=mhelper1.getWritableDatabase();
                                db.delete("tb_memory","title=?",new
                                        String[]{arr1.get(i).getTitle()});
                                arr1.remove(i);
                                notifyItemRemoved(i);
                                dialogInterface.dismiss();
                            }
                        });
                dialog.setNegativeButton("取消",null);
                dialog.setCancelable(false);
                dialog.create();
                dialog.show();
            }
        });
    }
    //recyView 一共有多少个子项
    @Override
    public int getItemCount() {
        return arr1.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView item_title,item_content,item_time;
        ImageView item_img;
        LinearLayout item_layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_title=itemView.findViewById(R.id.item_title);
            item_content=itemView.findViewById(R.id.item_content);
            item_img=itemView.findViewById(R.id.item_image);
            item_time=itemView.findViewById(R.id.item_time);
            item_layout=itemView.findViewById(R.id.item_layout);
        }
    } }
