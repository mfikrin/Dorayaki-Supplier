package com.dora.supp;

public class Request {
    public int reqid;
    public int doraid;
    public int qty;
    public String status;

    public Request() {}
    public Request(int _reqid, int _doraid, int _qty, String _status) {
        this.reqid = _reqid;
        this.doraid = _doraid;
        this.qty = _qty;
        this.status = _status;
    }
    public int getReqid(){
        return this.reqid;
    }
    public int getDoraid(){
        return this.doraid;
    }
    public int getQty(){
        return this.qty;
    }
    public String getStatus(){
        return this.status;
    }
    public void setReqid(int _reqid){
        this.reqid = _reqid;
    }
    public void setDoraid(int _doraid){
        this.doraid = _doraid;
    }
    public void setQty(int _qty){
        this.qty = _qty;
    }
    public void setStatus(String _status){
        this.status = _status;
    }

}

