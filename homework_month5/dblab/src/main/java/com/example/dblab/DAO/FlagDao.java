package com.example.dblab.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.dblab.model.flag;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class FlagDao {
    private  DBOpenHelper helper;
    private SQLiteDatabase db;
    public FlagDao(Context context){
        helper=new DBOpenHelper(context);
    }
    public void add(flag flag){
        db=helper.getWritableDatabase();
        db.execSQL("insert into tb_flag(_id,flag) values(?,?)",new Object[]{
                flag.getid(),flag.getFlag()
        });
    }
    public void update(flag tb_flag){
        db=helper.getWritableDatabase();
        db.execSQL("update tb_flag set flag= ? where _id = ?",new Object[]{
                tb_flag.getFlag(),tb_flag.getid()
        });
    }
    public flag find(int id){
        db=helper.getWritableDatabase();
        Cursor cursor=db.rawQuery("select _id,flag from tb_flag where _id = ?",new String[]{
                String.valueOf(id)
        });
        if(cursor.moveToNext()){
            return new flag(cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getString(cursor.getColumnIndex("flag")));
        }
        return null;
    }
    public void detele(Integer... ids){
        if(ids.length>0){
            StringBuffer sb=new StringBuffer();
            for(int i=0;i<ids.length;i++){
                sb.append('?').append(',');
            }
            sb.deleteCharAt(sb.length()-1);
            db=helper.getWritableDatabase();
            db.execSQL("delete from tb_flag where _id in (" + sb + ")",(Object[]) ids);
        }
    }
    public List<flag> getScrollData(int start,int count){
        List<flag> lisTb_flags=new ArrayList<>();
        db=helper.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from tb_flag limit ?,?",new String[]{String.valueOf(start),String.valueOf(count)});
        while(cursor.moveToNext()){
            lisTb_flags.add(new flag(cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getString(cursor.getColumnIndex("flag"))));
        }
        return lisTb_flags;
    }
    public long getCount(){
        db=helper.getWritableDatabase();
        Cursor cursor=db.rawQuery("select count(_id) from tb_flag",null);
        if (cursor.moveToNext()){
            return cursor.getLong(0);
        }
        return 0;
    }
    public int getMaxId(){
        db=helper.getWritableDatabase();
        Cursor cursor=db.rawQuery("select max(_id) from tb_flag",null);
        while(cursor.moveToLast()){
            return cursor.getInt(0);
        }
        return 0;
    }
}
