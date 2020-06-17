package com.example.homework_practicing;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.List;

public class FruitAdapter extends
        RecyclerView.Adapter<FruitAdapter.ViewHolder> {
    private List<Fruit> mFruitList;
    static class ViewHolder extends RecyclerView.ViewHolder {
        View fruitView;
        ImageView fruitImage;
        TextView fruitName;
        TextView fruitIn;
        TextView fruitPrice;
        public ViewHolder(View view) {
            super(view);
            fruitView = view;
            fruitImage = (ImageView) view.findViewById(R.id.fruitImg);
            fruitName = (TextView) view.findViewById(R.id.fruitName);
            fruitIn=(TextView)view.findViewById(R.id.fruitIntroduce);
            fruitPrice=(TextView)view.findViewById(R.id.fruitPrice);
        }
    }
    public FruitAdapter(List<Fruit> fruitList) {
        mFruitList = fruitList;
    }
    //方法 1：用于创建 ViewHolder 实例
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.fruit_item,
                        parent, false);
        final ViewHolder holder = new ViewHolder(view);
        //点击事件-跳转到水果详情界面
        holder.fruitView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Intent intent=new
                        Intent(view.getContext(),DetailActivity.class);

                intent.putExtra("myposition",(Serializable)mFruitList.get(position));
                view.getContext().startActivity(intent);
            }
        });
        return holder;
    }
    //方法 2：用于对 Recyclerview 中子项的数据进行赋值的
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Fruit fruit = mFruitList.get(position);
        holder.fruitImage.setImageResource(fruit.getImageId());
        holder.fruitName.setText(fruit.getName());
        holder.fruitIn.setText(fruit.getIntro());
        holder.fruitPrice.setText(fruit.getPrice());
    }
    //方法 3：返回 Recyclerview 中一共有多少行数据
    @Override
    public int getItemCount() {
        return mFruitList.size();
    } }