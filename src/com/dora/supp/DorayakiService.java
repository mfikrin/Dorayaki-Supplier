package com.dora.supp;

import java.util.ArrayList;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.RPC)
public class DorayakiService {
	@WebMethod
	public String[] getDorayaki(String ip){
		ArrayList<String> al = new DorayakiHandler().getDorayakiName(ip);
        String[] dl = new String[al.size()];
        dl = al.toArray(dl);
        return dl;	
	}
}
