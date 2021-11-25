package com.dora.supp;

import java.sql.*;
import java.util.*;

public class DorayakiHandler {
    static final String DB_USER = "postgres";
    static final String DB_PASS = "admin";
    static final String DB_URL = "jdbc:postgresql://localhost:5432/factorydb";

    Connection c;
    public DorayakiHandler(){
        try{
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            System.out.println("Connected to DB");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public ArrayList<Dorayaki> getDorayaki(){ // Get All Dorayaki 
        ArrayList<Dorayaki> dorList = new ArrayList<Dorayaki>();
        try{
            String q = "SELECT * FROM dora";
            Statement stmt = c.createStatement();
            ResultSet rSet = stmt.executeQuery(q);
            while(rSet.next()){
                Dorayaki dor = new Dorayaki(rSet.getInt("dora_id"),rSet.getString("dora_name"));
                dorList.add(dor);
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
        return dorList;
    }
    public ArrayList<String> getDorayakiName(String ip){ // Get All Dorayaki name - buat add variant 
        ArrayList<String> dorList = new ArrayList<String>();
        try{
            String q = "SELECT dora_name FROM dora";
            Statement stmt = c.createStatement();
            ResultSet rSet = stmt.executeQuery(q);
            while(rSet.next()){
                String dor = rSet.getString("dora_name");
                dorList.add(dor);
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
        return dorList;
    }

    public int getDoraid(String name){
        int doraid = -1;
        try{
            String q = String.format("SELECT dora_id from dora WHERE dora_name='%s'",name);
            Statement stmt = c.createStatement();
            ResultSet rSet = stmt.executeQuery(q);
            rSet.next();
            doraid = rSet.getInt("dora_id");
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
        return doraid;
    }

    public boolean exceedLimit(String ip){
        boolean retval = true;
        try{
            String q = String.format("SELECT COUNT(*) AS rowcount FROM request_log WHERE ip='%s' AND timestamp_req > NOW() - interval '5 minutes'",ip);
            Statement stmt = c.createStatement();
            ResultSet rSet = stmt.executeQuery(q);
            rSet.next();
            int count = rSet.getInt("rowcount");
            if(count < 10){
                retval = false;
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
        return retval;
    }
    // public static void main(String[] args) {
    //     DorayakiHandler dd = new DorayakiHandler();
    //     ArrayList<Dorayaki> ad = dd.getDorayaki();
    //     for (Dorayaki dorayaki : ad) {
    //         System.out.println(dorayaki.name);
    //     }
    // }

}
