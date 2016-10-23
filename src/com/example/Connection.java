
package com.example;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.json.JSONObject;
import org.json.JSONArray;

/**
 * @author vicky,2016/10/23
 * Get raw data from URL and save as Json Object(root)
 *
 */
public class Connection {
	public JSONObject m_root; //Convert String which are get from url to JSONObject
	private String m_url;
	
	/**
	 * get String from url and save as JSONObject m_root
	 * @param url 
	 */
	
	public Connection(String url){
		m_url = url;
		StringBuilder jsontext = new StringBuilder();
		try{
			URL urlObj = new URL(m_url);
			BufferedReader br = new BufferedReader(new InputStreamReader(urlObj.openStream(), "utf-8"));
			String inputLine;
			while((inputLine = br.readLine()) != null){
				jsontext.append(inputLine);
			}
			br.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		m_root = new JSONObject(jsontext.toString()); //Convert to JSONObject
	}
	/**
	 * Save as .json file
	 * @param filename : json filename
	 */
	public void JSONtoFile(String filename){
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(filename+".json"));
			bw.write(m_root.toString());
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}		
}
