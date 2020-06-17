package com.example.dblab;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dblab.DAO.FlagDao;
import com.example.dblab.model.flag;

public class ManageFlag extends Activity {
    EditText txtFlag;
    Button btnEdit,btnDel;
    String strid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manageflag);
        txtFlag=findViewById(R.id.txtFlagManage);
        btnEdit=findViewById(R.id.btnFlagManageEdit);
        btnDel=findViewById(R.id.btnFlagManageDelete);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        strid=bundle.getString(ShowInfo.FLAG);
        final FlagDao flagDAO=new FlagDao(ManageFlag.this);
        txtFlag.setText(flagDAO.find(Integer.parseInt(strid)).getFlag());
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag tb_flag=new flag();
                tb_flag.setid(Integer.parseInt(strid));
                tb_flag.setFlag(txtFlag.getText().toString());
                flagDAO.update(tb_flag);
                Toast.makeText(ManageFlag.this,"【便签数据】修改成功！",Toast.LENGTH_SHORT).show();
            }
        });
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flagDAO.detele(Integer.parseInt(strid));
                Toast.makeText(ManageFlag.this,"【便签数据】删除成功！",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
