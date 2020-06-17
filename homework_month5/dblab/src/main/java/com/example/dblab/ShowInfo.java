package com.example.dblab;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dblab.DAO.FlagDao;
import com.example.dblab.model.flag;

import java.util.List;

public class ShowInfo extends Activity {
    public static final String FLAG="id";
    ListView lvinfo;
    String []strInfos=null;
    ArrayAdapter<String> arrayAdapter=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showinfo);
        lvinfo=(ListView) findViewById(R.id.lvinfo);
        FlagDao flaginfo=new FlagDao(ShowInfo.this);
        List<flag> listFlags=flaginfo.getScrollData(0,(int)flaginfo.getCount());
        strInfos=new String[listFlags.size()];
        int n=0;
        for(flag tb_flag:listFlags){
            strInfos[n]=tb_flag.getid()+"|"+tb_flag.getFlag();
            if (strInfos[n].length()>15)
                strInfos[n]=strInfos[n].substring(0,15)+"......";
            n++;
        }
        arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,strInfos);
        lvinfo.setAdapter(arrayAdapter);
        lvinfo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String strInfo=String.valueOf(((TextView)view).getText());
                String strid=strInfo.substring(0,strInfo.indexOf('|'));
                Intent intent=null;
                intent=new Intent(ShowInfo.this,ManageFlag.class);
                intent.putExtra(FLAG,strid);
                startActivity(intent);
            }
        });
    }
}
