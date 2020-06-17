package com.zqu.cs.myimageswitcherapp;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements ViewSwitcher.ViewFactory {
    private ImageSwitcher myImageSwitcher;	//定义 ImageSwitcher控件变量
    private int[] image = {
            R.drawable.cp, R.drawable.db0, R.drawable.db2, R.drawable.db4, R.drawable.java0, R.drawable.java2, R.drawable.qt1, R.drawable.qt3, R.drawable.qt4 };     	//图书数组存放图片资源ID
    int index;  			//当前图片的索引（从0开始）
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myImageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher);	//赋值控件变量引用
        myImageSwitcher.setFactory(this);
        myImageSwitcher.setImageDrawable(getResources().getDrawable(image[0])); //显示第1幅图
        index = 0;
    }

    public View makeView() {					//显示图片
        ImageView imageView1 = new ImageView(this);
        imageView1.setBackgroundColor(0x00000000);
        imageView1.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageView1.setLayoutParams(new ImageSwitcher.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return imageView1;
    }


    public  void onClick(View view)
    {
        switch (view.getId()) {
            case R.id.buttonBackward:            //向后箭头按钮按键处理
                index--;
                if (index < 0) {
                    index = image.length - 1;
                    Toast.makeText(this, "温馨提示：您正在查看最后一张图片", Toast.LENGTH_SHORT).show();		//显示系统提示信息对话框
                }
                //将Index顺序对应图片ID与显示图片联系
                myImageSwitcher.setImageResource(image[index]);
                break;
            case R.id.buttonForward:            //向前箭头按钮按键处理
                index++;
                if (index >= image.length) {
                    index = 0;
                    Toast.makeText(this, "温馨提示：您正在查看第一张图片", Toast.LENGTH_SHORT).show();
                }
                myImageSwitcher.setImageResource(image[index]);
                break;
        }

    }
}
