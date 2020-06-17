package com.example.dblab;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dblab.DAO.FlagDao;
import com.example.dblab.model.flag;

public class InsertFlag extends Activity {
    EditText txtFlag;
    Button btnflagSaveButton;
    Button btnflagCancelButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insertinfo);
        txtFlag=findViewById(R.id.txtFlag);
        btnflagSaveButton=findViewById(R.id.btnflagSave);
        btnflagCancelButton=findViewById(R.id.btnflagCancel);
        btnflagSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strFlag=txtFlag.getText().toString();
                if(!strFlag.isEmpty()){
                    FlagDao flagDAO=new FlagDao(InsertFlag.this);
                    flag flag=new flag(flagDAO.getMaxId()+1,strFlag);
                    flagDAO.add(flag);
                    Toast.makeText(InsertFlag.this,"【新增便签】数据添加成功",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(InsertFlag.this,"请输入便签！",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnflagCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
