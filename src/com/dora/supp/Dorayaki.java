package com.dora.supp;

public class Dorayaki {
    public int id;
    public String name;
    public int qty;

    public Dorayaki(){}
    public Dorayaki(int _id, String _name){
        this.id = _id;
        this.name = _name;
        this.qty = 0;
    }
    public Dorayaki(int _id, String _name, int _qty){
        this.id = _id;
        this.name = _name;
        this.qty = _qty;
    }
    public int getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    public int getQty(){
        return this.qty;
    }
    public void setId(int _id){
        this.id = _id;
    }
    public void setName(String _name){
        this.name = _name;
    }
    public void setQty(int _qty){
        this.qty = _qty;
    }

}

