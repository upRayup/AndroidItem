package com.example.download;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity{
    private EditText UrlEdit;//获取一个输入url的编辑框对象
    private Button button;//声明一个"发送POST请求"按钮对象
    private Handler handler;//声明一个Handler对象
    private boolean flag=false;//标记是否成功的变量
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UrlEdit=(EditText)findViewById(R.id.url);
        button=(Button)findViewById(R.id.button);
        //为"下载"按钮添加单击事件监听
        button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                //创建一个新线程用于从网络上获取文件
                new Thread(new Runnable(){


                    @Override
                    public void run() {
                        try {
                            String sourceUrl=UrlEdit.getText().toString();//获取下载地址
                            URL url=new URL(sourceUrl);//创建下载地址对应的URL对象
                            //创建一个连接
                            HttpURLConnection conn=(HttpURLConnection)url.openConnection();
                            InputStream is=conn.getInputStream();//获取输入流对象
                            if(is!=null){
                                String expandName=sourceUrl.substring(sourceUrl.lastIndexOf(".")+1,
                                        sourceUrl.length()).toLowerCase();//获取文件的拓展名
                                String fileName=sourceUrl.substring(sourceUrl.lastIndexOf("/")+1,
                                        sourceUrl.lastIndexOf("."));//获取文件名
                                //在SD卡上创建文件
                                File file=new File("/sdcard/pictures/"+fileName+"."+expandName);
                                FileOutputStream fos=new FileOutputStream(file);//创建一个文件输出流对象
                                byte buf[]=new byte[1024];//创建一个字节数组
                                //读取文件到输入流对象中
                                while(true){
                                    int numread=is.read(buf);
                                    if(numread<=0){
                                        break;
                                    }else{
                                        fos.write(buf, 0, numread);
                                    }
                                }
                            }
                            is.close();//关闭输入流对象
                            conn.disconnect();//关闭连接
                            flag=true;
                        } catch (MalformedURLException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                            flag=false;
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                            flag=false;
                        }
                        Message m=handler.obtainMessage();//获取一个Message
                        handler.sendMessage(m);//发送消息
                    }

                }).start();//开启线程
            }

        });

        //重写Handler的handleMessage()方法，根据flag标记变量flag的值不同显示不同的提示
        handler=new Handler(){


            @Override
            public void handleMessage(Message msg) {
                if(flag){
                    Toast.makeText(MainActivity.this, "文件下载完成！",
                            Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "文件下载失败！",
                            Toast.LENGTH_SHORT).show();
                }
                super.handleMessage(msg);
            }

        };
    }

}
