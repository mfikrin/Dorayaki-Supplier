package com.dora.supp;

import java.util.ArrayList;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.RPC)
public class RequestService {
    @WebMethod
    public boolean insertRequest(String dora,int qty,String ip,String ts,String epo){
        return new RequestHandler().insertRequest(dora, qty, ip, ts, epo);
    }
    @WebMethod
    public String[][] getStatus(String ip){
        ArrayList<ArrayList<String>> st = new RequestHandler().getStatus(ip);
        String[][] statuses = new String[st.size()][];
        for (int i = 0; i < st.size(); i++) {
            ArrayList<String> row = st.get(i);
            statuses[i] = row.toArray(new String[row.size()]);
        }
        return statuses;
    }
}
