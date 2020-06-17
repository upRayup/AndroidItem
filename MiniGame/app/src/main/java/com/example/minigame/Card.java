package com.example.minigame;

import android.content.Context;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;


public class Card extends FrameLayout {
    private TextView text;
    private int number=0;
    public int getNumber(){
        return number;
    }
    public void setNumber(int number){
        this.number=number;
        if(number<2){
            text.setText("");
        }else{
            if(number==2){
                text.setTextColor(0xFFFFFF00);
            }else if(number==4){
                text.setTextColor(0xFF00FF00);
            }
            else if(number==8){
                text.setTextColor(0xFF00FFFF);
            }
            else if(number==16){
                text.setTextColor(0xFF0000FF);
            }
            else if(number==32){
                text.setTextColor(0xFFFF00FF);
            }
            else if(number==64){
                text.setTextColor(0xFF7F007F);
            }
            else if(number==128){
                text.setTextColor(0xFFFF7F00);
            }
            else if(number==256){
                text.setTextColor(0xFF996633);
            }
            else if(number==512){
                text.setTextColor(0xFFFF0000);
            }
            else if(number==1024){
                text.setTextColor(0xFF350D04);
            }
            else if(number==2048){
                text.setTextColor(0xFFFF3800);
            }
            else{
                text.setTextColor(0xFF95090C);
            }
            text.setText(number+"");
        }
    }
    public Card(@NonNull Context context) {
        super(context);
        text=new TextView(context);
        text.setTextSize(28);
        text.setBackgroundColor(0x9966cccc);
        text.setGravity(Gravity.CENTER);
        LayoutParams params=new LayoutParams(-1,-1);
        params.setMargins(10,10,0,0);
        addView(text,params);
    }
}
