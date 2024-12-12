package com.company.tienda.response;
import java.util.*;

public class RespuestaRest {
	
	private ArrayList<HashMap<String , String>>metadata = new ArrayList<>();

	//Get & Set
	public ArrayList<HashMap<String, String>> getMetadata() {
		return metadata;
	}

	public void setMetadata(String type, String code, String date) {
		
		HashMap<String, String> mapeo = new HashMap<String , String>();
		mapeo.put("type", type);
		mapeo.put("code", code);
		mapeo.put("date", date);
		metadata.add(mapeo);
		
	}
	

}

/*
	Esta clase se crea para recoger la informacion JSON, si se envia correctamente o no
*/