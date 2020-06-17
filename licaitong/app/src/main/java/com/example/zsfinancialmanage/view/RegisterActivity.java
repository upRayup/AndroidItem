package com.example.zsfinancialmanage.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zsfinancialmanage.R;
import com.example.zsfinancialmanage.db.MyDBHelper;

public class RegisterActivity extends AppCompatActivity {
//3定义对象
    EditText et_name,et_pwd,et_email,et_phone;
    Button btn_register,btn_cancel;
    MyDBHelper mhelper;//创建一个数据库类文件
    SQLiteDatabase db;//创建一个可以操作的数据库对象


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //4 绑定控件
        initView();


        //5 注册按钮功能的实现
        btnRegister();

        //6 取消按钮功能的实现
        btnCancel();
    }

    //4 绑定控件--------代码
    private void initView() {
        et_name=findViewById(R.id.et_name_rg);
        et_pwd=findViewById(R.id.et_pwd_rg);
        et_email=findViewById(R.id.et_email_rg);
        et_phone=findViewById(R.id.et_phone_rg);
        btn_register=findViewById(R.id.bt_ok_rg);
        btn_cancel=findViewById(R.id.bt_cancel_rg);
        mhelper=new MyDBHelper(RegisterActivity.this);
        db=mhelper.getWritableDatabase();
    }

    //5 注册按钮功能的实现--------------------代码
    private void btnRegister() {
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建一个对象，用来封装一行数据
                ContentValues values=new ContentValues();
                values.put("name",et_name.getText().toString());//将输入的用户名放到name列
                values.put("pwd",et_pwd.getText().toString());//将输入的密码放到pwd列
                values.put("email",et_email.getText().toString());//将输入的邮箱放到email列
                values.put("phone",et_phone.getText().toString());//将输入的电话放到phone列

                //将封装好的一行数据保存到数据库的tb_userinfo表中
                db.insert("tb_userinfo",null,values);
                Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
            }
        });

    }

    //6 取消按钮功能的实现-------------------代码
    private void btnCancel() {
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


}
