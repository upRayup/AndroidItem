package com.example.dblab.model;

public class flag {
    private int _id;
    private String flag;

    public flag(){
        super();
    }
    public flag(int id, String flag) {
        super();
        this._id=id;
        this.flag=flag;
    }

    public int getid() {
        return _id;
    }

    public void setid(int _id) {
        this._id = _id;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
