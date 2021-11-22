package com.dora.supp;

import java.sql.*;
import java.util.*;

public class RequestHandler {
    static final String DB_USER = "postgres";
    static final String DB_PASS = "admin";
    static final String DB_URL = "jdbc:postgresql://localhost:5432/factorydb";
    static final int threshold = 10;

    Connection c;
    public RequestHandler(){
        try{
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            System.out.println("Connected to DB");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public ArrayList<Request> getRequests(){ // get pending requests
        ArrayList<Request> reqList = new ArrayList<Request>();
        try{
            String q = "SELECT * FROM request WHERE status='pending'";
            Statement stmt = c.createStatement();
            ResultSet rSet = stmt.executeQuery(q);
            while(rSet.next()){
                Request req = new Request();
                req.setReqid(rSet.getInt("request_id"));
                req.setDoraid(rSet.getInt("dora_id"));
                req.setQty(rSet.getInt("req_qty"));
                req.setStatus(rSet.getString("status"));
                reqList.add(req);
            }
            System.out.println("Yes");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            if(c!=null){
                try{
                    c.close();
                }
                catch(Exception e){
                    System.out.println("Failed to close");
                }
            }
        }
        return reqList;
    }

    public Request getReqOnId(int _reqid){ // get specific req w/ id = reqid
        Request req = new Request();
        try{
            String q = String.format("SELECT * from request WHERE request_id=%d",_reqid);
            Statement stmt = c.createStatement();
            ResultSet rSet = stmt.executeQuery(q);
            while(rSet.next()){
                req.setReqid(rSet.getInt("request_id"));
                req.setDoraid(rSet.getInt("dora_id"));
                req.setQty(rSet.getInt("req_qty"));
                req.setStatus(rSet.getString("status"));
            }
            System.out.println("Yes");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            if(c!=null){
                try{
                    c.close();
                }
                catch(Exception e){
                    System.out.println("Failed to close");
                }
            }
        }
        return req;
    }

    public void insertRequest(int dora,int qty,String ip,String ts,String epo){ // Insert req to log w/ rate limiter
        try{
            int dora_id = dora;
            String q = String.format("SELECT COUNT(*) AS rowcount FROM request_log WHERE ip='%s' AND epoint='%s' AND timestamp_req > NOW() - interval '23 hours'",ip,epo);
            Statement stmt = c.createStatement();
            ResultSet rSet = stmt.executeQuery(q);
            rSet.next();
            int count = rSet.getInt("rowcount");
            if(count > 0 && count <= threshold){
                String insq1 = String.format("INSERT INTO request(dora_id,req_qty,status) VALUES (%d,%d,'%s') RETURNING request_id",dora_id,qty,"pending");
                ResultSet res = stmt.executeQuery(insq1);
                System.out.println("Insert to request Success");
                res.next();
                int reqid = res.getInt("request_id"); 
                String insq2 = String.format("INSERT INTO request_log(request_id,ip,timestamp_req,epoint) VALUES (%d,'%s','%s','%s')",reqid,ip,ts,epo);
                stmt.executeUpdate(insq2);
                System.out.println("Insert to request_log Success");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            if(c!=null){
                try{
                    c.close();
                }
                catch(Exception e){
                    System.out.println("Failed to close");
                }
            }
        }
    }

    public static void main(String[] args) {
        RequestHandler rh = new RequestHandler();
        rh.insertRequest(0,2,"123.123.123","2021-11-22 06:45:10","request");
    }
}
