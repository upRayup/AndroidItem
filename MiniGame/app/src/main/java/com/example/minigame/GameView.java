package com.example.minigame;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameView extends GridLayout {
    //定义4*4的卡片布局
    private Card cards[][]=new Card[4][4];
    //记录空卡片
    private List<Point> emptyCards=new ArrayList<Point>();
    //随机数
    Random rd=new Random();
    //分数
    int score=0;
    int oldscore=0;
    int currentscore=0;
    int bscore;

    public int getBscore() {
        return bscore;
    }

    public void setBscore(int bscore) {
        this.bscore = bscore;
    }

    //记录所有卡片位置及当前分数
    private List<Point> backCards=new ArrayList<Point>();
    private List<Point> oldbackCards=new ArrayList<Point>();
    private List<Integer> backCardsnums=new ArrayList<Integer>();
    private List<Integer> oldbackCardsnums=new ArrayList<Integer>();


    public GameView(Context context) {
        super(context);
        initGame();
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initGame();
    }

    public GameView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initGame();
    }
    //游戏初始界面
    private void initGame() {
        setColumnCount(4);  //设置列的数量
        setBackgroundColor(0xffffcccc); //设置背景颜色
        setOnTouchListener(new OnTouchListener() {  //触摸监听
            private float startX,startY;
            private float offsetX,offsetY;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:   //手势事件开启
                        startX=event.getX();    //获取当前x坐标
                        startY=event.getY();    //获取当前y坐标
                        break;
                    case MotionEvent.ACTION_UP:     //手势事件结束
                        Gameover();
                        offsetX=event.getX()-startX;    //获取当前x坐标跟移动前坐标的x差值
                        offsetY=event.getY()-startY;    //获取当前y坐标跟移动前坐标的y差值
                        if(Math.abs(offsetX)>Math.abs(offsetY)){    //x差值大于y差值，说明是左右移动
                            if(offsetX<-3){ //左移
                                moveLeft();
                                System.out.println("----左");
                            }else if(offsetX>3){    //右移
                                moveRight();
                                System.out.println("----右");
                            }
                        }
                        else{
                            if(offsetY<-3){
                                moveUp();
                                System.out.println("----上");
                            }else if(offsetY>3){
                                moveDown();
                                System.out.println("----下");
                            }
                        }
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }
    private void moveRight(){
        boolean flage=false;//移动是否有效
        for(int y=0;y<4;y++){
            for(int x=3;x>=0;x--){
                for(int x1=x-1;x1>=0;x1--){
                    if(cards[x1][y].getNumber()>0){//准备往右的这张卡片，需要判断是否有数值
                        if(cards[x][y].getNumber()<2){//在右边的这张卡片，如果没数值，就用准备往右的这张卡片的数值替代它
                            cards[x][y].setNumber(cards[x1][y].getNumber());
                            cards[x1][y].setNumber(0);
                            x++;
                            flage=true;
                            score+=2;
                        }else if(cards[x][y].getNumber()==cards[x1][y].getNumber()){//在右边的这张卡片，如果数值与准备往右的这张卡片的数值相同，就合并数值
                            cards[x][y].setNumber(cards[x][y].getNumber()*2);
                            score+=cards[x][y].getNumber();
                            cards[x1][y].setNumber(0);
                            flage=true;
                        }
                        break;
                    }
                }
            }
        }
        oldremeberCard();
        if(flage){//移动有效就创建新卡片
            creatRandomCard();
            remeberCard();
        }
    }
    private void moveLeft(){
        boolean flage=false;
        for(int y=0;y<4;y++){
            for(int x=0;x<4;x++){
                for(int x1=x+1;x1<4;x1++){
                    if(cards[x1][y].getNumber()>0){
                        if(cards[x][y].getNumber()<2){
                            cards[x][y].setNumber(cards[x1][y].getNumber());
                            cards[x1][y].setNumber(0);
                            x--;
                            flage=true;
                            score+=2;
                        }else if(cards[x][y].getNumber()==cards[x1][y].getNumber()){
                            cards[x][y].setNumber(cards[x][y].getNumber()*2);
                            score+=cards[x][y].getNumber();
                            cards[x1][y].setNumber(0);
                            flage=true;
                        }
                        break;
                    }
                }
            }
        }
        oldremeberCard();
        if(flage){
            creatRandomCard();
            remeberCard();
        }
    }
    private void moveDown(){
        boolean flage=false;
        for(int x=0;x<4;x++){
            for(int y=3;y>=0;y--){
                for(int y1=y-1;y1>=0;y1--){
                    if(cards[x][y1].getNumber()>0){
                        if(cards[x][y].getNumber()<2){
                            cards[x][y].setNumber(cards[x][y1].getNumber());
                            cards[x][y1].setNumber(0);
                            y++;
                            flage=true;
                            score+=2;
                        }else if(cards[x][y].getNumber()==cards[x][y1].getNumber()){
                            cards[x][y].setNumber(cards[x][y].getNumber()*2);
                            score+=cards[x][y].getNumber();
                            cards[x][y1].setNumber(0);
                            flage=true;
                        }
                        break;
                    }
                }
            }
        }
        oldremeberCard();
        if(flage){
            creatRandomCard();
            remeberCard();
        }
    }
    private void moveUp(){
        boolean flage=false;
        for(int x=0;x<4;x++){
            for(int y=0;y<4;y++){
                for(int y1=y+1;y1<4;y1++){
                    if(cards[x][y1].getNumber()>0){
                        if(cards[x][y].getNumber()<2){
                            cards[x][y].setNumber(cards[x][y1].getNumber());
                            cards[x][y1].setNumber(0);
                            y--;
                            flage=true;
                            score+=2;
                        }else if(cards[x][y].getNumber()==cards[x][y1].getNumber()){
                            cards[x][y].setNumber(cards[x][y].getNumber()*2);
                            score+=cards[x][y].getNumber();
                            cards[x][y1].setNumber(0);
                            flage=true;
                        }
                        break;
                    }
                }
            }
        }
        oldremeberCard();
        if(flage){
            creatRandomCard();
            remeberCard();
        }
    }
    private void Gameover(){//判断游戏是否结束
        boolean OverGame=true;
        for(int y=0;y<4;y++){
            for(int x=0;x<4;x++){
                if(cards[x][y].getNumber()<=0 ||    //有一个卡片数值为0说明有空位
                        (x>0&&cards[x][y].getNumber()==cards[x-1][y].getNumber())|| //有相同卡片说明可以合并，下同
                        (x<3&&cards[x][y].getNumber()==cards[x+1][y].getNumber())||
                        (y>0&&cards[x][y].getNumber()==cards[x][y-1].getNumber())||
                        (y<3&&cards[x][y].getNumber()==cards[x][y+1].getNumber())){
                    OverGame=false;
                }
            }
        }
        if(OverGame){   //提示游戏结束
            //游戏结束时判断最高分
            if(score>bscore){
                bscore=score;
            }
            new AlertDialog.Builder(getContext()).setTitle("游戏结束").setMessage("请选择是否继续挑战")
                    .setPositiveButton("好呀好呀",new AlertDialog.OnClickListener(){ //选择是的话就继续开启新游戏
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            GameStart();
                            score=0;
                        }
                    }).setNegativeButton("不啦不啦",null).show();
        }
    }
    private void Addard(int width,int height){
        Card c;
        for(int y=0;y<4;y++){
            for(int x=0;x<4;x++){
                c=new Card(getContext());
                cards[x][y]=c;
                c.setNumber(0);
                addView(c,width,height);
            }
        }
    }
    @Override
    protected void onSizeChanged(int w,int h,int oldw,int oldh){    //界面尺寸变化适应函数
        super.onSizeChanged(w,h,oldw,oldh);
        int width=(w-10)/4;
        Addard(width,width);
        GameStart();
    }
    private void oldremeberCard(){
        oldbackCards.clear();
        oldbackCardsnums.clear();
        oldbackCards.addAll(backCards);
        oldbackCardsnums.addAll(backCardsnums);
        oldscore=currentscore;
    }
    private void remeberCard(){    //记录当前操作
        backCards.clear();
        backCardsnums.clear();
        for(int y=0;y<4;y++){   //重新记录卡片位置
            for(int x=0;x<4;x++){
                Point point=new Point(x,y);
                backCards.add(point);
                backCardsnums.add(cards[x][y].getNumber());
            }
        }
        currentscore=score; //记录当前分数
    }
    private void creatRandomCard(){
        emptyCards.clear(); //卡片位置为空的数组清空
        for(int y=0;y<4;y++){   //重新记录卡片位置为空的数组
            for(int x=0;x<4;x++){
                if(cards[x][y].getNumber()<2){
                    Point point=new Point(x,y);
                    emptyCards.add(point);
                }
            }
        }
        int selat=rd.nextInt(emptyCards.size());//写一个随机数
        Point p=emptyCards.get(selat);//随机取一个空着的卡片
        emptyCards.remove(selat);//取到的卡片准备赋值，所以这个卡片不为空了，踢出去
        int number=0;
        if(rd.nextInt(10)>4){
            number=4;
        }else{
            number=2;
        }
        cards[p.x][p.y].setNumber(number);
    }
    public void GameStart(){//全部卡片置为0，创建两张新卡片
        if(score>bscore){
            bscore=score;
        }
        for(int y=0;y<4;y++){
            for (int x=0;x<4;x++){
                cards[x][y].setNumber(0);
            }
        }
        oldbackCards.clear();
        oldbackCardsnums.clear();
        creatRandomCard();
        creatRandomCard();
        remeberCard();
    }
    public void Gameback(){ //返回上一步操作
        if(oldbackCardsnums.isEmpty() && oldbackCards.isEmpty()){
            System.out.println("没有值，回退不了");
        }
        else {
            for (int y = 0; y < 4; y++) {
                for (int x = 0; x < 4; x++) {
                    Point p = oldbackCards.get(y * 4 + x);//拿出位置
                    cards[p.x][p.y].setNumber(oldbackCardsnums.get(y * 4 + x));//拿出值放在该位置上
                }
            }
            score=oldscore;
            remeberCard();
        }
    }
}
