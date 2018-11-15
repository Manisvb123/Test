package com.merit.tlg.dbutility;

import java.util.HashMap;
import java.util.Map;

public class GetJsonStructure {
	
	public String getPageDetailsJsonStructure(HashMap<String, Map<String, String>> out,String[] variables,String[] variables_1,String projectid,String toolid,String templateid,String pageid){
		System.out.println("---------->"+out);
		for(Map.Entry m:out.entrySet()){
			
			System.out.println(m.getKey());
			System.out.println(m.getValue());
			
			
			
			
		}
		
		return null;
		
		
		
	}

}
