package com.example.zsfinancialmanage.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zsfinancialmanage.R;
import com.example.zsfinancialmanage.bean.IncomeBean;
import com.example.zsfinancialmanage.bean.OutpayBean;
import com.example.zsfinancialmanage.other.InManageActivity;
import com.example.zsfinancialmanage.other.OutManageActivity;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class OutpayAdapter extends RecyclerView.Adapter<OutpayAdapter.ViewHolder> {
Context mcontext;
List<OutpayBean> arr2;

    public OutpayAdapter(Context mcontext, List<OutpayBean> arr2) {
        this.mcontext = mcontext;
        this.arr2 = arr2;
    }

    //用于创建ViewHolder实例
    @Override
    public OutpayAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mcontext).inflate(R.layout.recy_item_out,parent,false);
        ViewHolder mholder=new ViewHolder(view);
        return mholder;
    }

    //对RecyclerView子项进行赋值的
    @Override
    public void onBindViewHolder(OutpayAdapter.ViewHolder mholder, int position) {
         final OutpayBean outpayBean=arr2.get(position);
            mholder.item_payee.setText("付款-给"+outpayBean.getPayer());
            mholder.item_type.setText(outpayBean.getType());
            mholder.item_time.setText(outpayBean.getTime());
            mholder.item_remark.setText(outpayBean.getRemark());
            mholder.item_money.setText("-"+outpayBean.getMoney());
        //完善：单击某一个条目，跳转到收入管理页面
        mholder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到收入管理页面的代码
                Intent intent=new Intent(mcontext, OutManageActivity.class);
                intent.putExtra("sero",outpayBean);
                mcontext.startActivity(intent);
                ((Activity)mcontext).finish();
            }
        });

    }

    //recyclerView一共有多少子项
    @Override
    public int getItemCount() {
        return arr2.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{
        TextView item_payee,item_type,item_time,item_remark,item_money;
        public ViewHolder( View itemView) {
            super(itemView);
            item_payee=itemView.findViewById(R.id.item_payee_out);
            item_type=itemView.findViewById(R.id.item_type_out);
            item_time=itemView.findViewById(R.id.item_time_out);
            item_remark=itemView.findViewById(R.id.item_remark_out);
            item_money=itemView.findViewById(R.id.item_money_out);
        }
    }
}
