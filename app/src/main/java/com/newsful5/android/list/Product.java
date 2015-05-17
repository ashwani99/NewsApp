package com.newsful5.android.list;


public class Product {
    private int _id;
    private String _productname;
    private int _value;

    public Product(int id,String productname,int value) {
        this._id=id;
        this._productname=productname;
        this._value=value;
    }


    public void setID(int id) {
        this._id = id;
    }

    public int getID() {
        return this._id;
    }

    public void setLinkName(String productname) {
        this._productname = productname;
    }

    public String getLinkName() {
        return this._productname;
    }

    public void setKey(int value) {
        this._value = value;
    }

    public int getKey() {
        return this._value;
    }
}

