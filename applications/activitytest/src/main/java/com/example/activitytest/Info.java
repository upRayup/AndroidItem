package com.example.activitytest;

import java.io.Serializable;

public class Info implements Serializable {
    private static final long serrialVersionUID=1L;
    private String birthday="";
    public String getBirthday(){
        return birthday;
    }
    public void setBirthday(String birthday){
        this.birthday=birthday;
    }
}
