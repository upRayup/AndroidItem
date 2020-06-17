package com.example.activitytest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        TextView birthday=(TextView)findViewById(R.id.birthday);
        TextView result=(TextView)findViewById(R.id.result);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        Info info=(Info)bundle.getSerializable("info");
        birthday.setText("您的阳历生日是"+info.getBirthday());
        result.setText(query(info.getBirthday()));
    }
    public String query(String birthday){
        int month=0;
        int day=0;
        try{
            month=Integer.parseInt(birthday.substring(5,7));
            day=Integer.parseInt(birthday.substring(8,10));
        }catch (Exception e){
            e.printStackTrace();
        }
        String name="";
        if(month>0&&month<13&&day>0&&day<32){
            if((month==3&&day>20)||(month==4&&day<21)){
                name="您是白羊座!";
            }else if((month==4 &&day>20)||(month==5 && day<21)){
                name="您是金牛座!";
            }else if((month==5 &&day>20)||(month==6 &&day<22)){
                name="您是双子座!";
            }else if((month==6 &&day>21)||(month==7 &&day<23)){
                name="您是巨蟹座!";
            }else if((month==7 &&day>22)||(month==8 &&day<23)){
                name="您是狮子座!";
            }else if((month==8 &&day>22)||(month==9 &&day<23)){
                name="您是处女座!";
            }else if((month==9 &&day>22)||(month==10 &&day<23)){
                name="您是天秤座!";
            }else if((month==10 &&day>22)||(month==11 &&day<22)){
                name="您是天蝎座!";
            }else if((month==11 &&day>21)||(month==12 &&day<22)){
                name="您是射手座!";
            }else if((month==12 &&day>21)||(month==1 &&day<20)){
                name="您是摩羯座!";
            }else if((month==1 &&day>19)||(month==2 &&day<19)){
                name="您是水瓶座!";
            }else if((month==2 &&day>18)||(month==3 &&day<21)){
                name="您是双鱼座!";
            }
            name=month+"月"+day+"日"+name;
        }
        else{
            name="您输入的生日格式不正确或者不是真实生日";
        }
        return name;
    }
}
