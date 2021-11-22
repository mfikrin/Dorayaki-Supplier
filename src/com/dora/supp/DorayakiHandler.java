package com.dora.supp;

import java.sql.*;

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
    public static void main(String[] args) {
        DorayakiHandler dd = new DorayakiHandler();
    }
}
