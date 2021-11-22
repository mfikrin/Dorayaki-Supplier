package com.dora.supp;
 
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.RPC)
public class RequestService {
    @WebMethod
    public String insertRequest(String dora,int qty,String ip,String ts,String epo){
        new RequestHandler().insertRequest(dora, qty, ip, ts, epo);
        String response = "Omedetou";
        return response;
    }
}
