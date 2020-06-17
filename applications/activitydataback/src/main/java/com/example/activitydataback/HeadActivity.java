package com.example.activitydataback;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HeadActivity extends AppCompatActivity {
    public String[] city=new String[]{"北京","上海","广州","长春","沈阳","哈尔滨",
            "天津","西安","杭州","深圳","南京","洛阳"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_head);
        GridView gridView=findViewById(R.id.gridView1);
        BaseAdapter adapter=new BaseAdapter() {
            @Override
            public int getCount() {
                return city.length;
            }

            @Override
            public Object getItem(int position) {
                return position;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView tv;
                if(convertView==null){
                    tv=new TextView(HeadActivity.this);
                    tv.setPadding(5,5,5,5);
                }else{
                    tv=(TextView)convertView;
                }
                tv.setText(city[position]);
                return tv;
            }
        };
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=getIntent();
                Bundle bundle=new Bundle();
                bundle.putString("city",city[position]);
                intent.putExtras(bundle);
                setResult(0x11,intent);
                finish();
            }
        });
    }
}
