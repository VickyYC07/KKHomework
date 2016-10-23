
package com.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author vicky,2016/10/23
 * Save as csv file
 * 商品名稱 : item.name
 * 商品敘述 : item.description
 * 商品價格 : item.price
 * 上架時間 : item.create_time
 * 賣家序號 : item.seller.serial_number
 * 賣家暱稱 : item.seller.nickname
 */

public class CSVCreator {
	public String m_csvfilename  = "最新商品.csv";
	private Connection m_conn;

	/*
	 * Get rawdata from url and write the attributes names
	 * @param url
	 */
	public CSVCreator(String url){
		//Download rawdata from url
		m_conn = new Connection(url);
		//Write the attributes of CSV
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(m_csvfilename));
			bw.write("\uFEFF");
			bw.write("\"商品名稱\", \"商品描述\", \"商品價格\", \"上架時間\", \"賣家序號\", \"賣家暱稱\",\n");
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * Convert the instant to the date format(yyyy/MM/dd HH:mm:ss) with Taipei Time
	 * @param timestamp
	 * @return String
	 */
	private String InstantToFormat(Instant timestamp){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").withZone(ZoneId.of("Asia/Taipei"));
		return formatter.format(timestamp);
	}
	
	/*
	 * Convert JSON object to Seller and Item object and write the values to CSV file
	 * 
	 */
	
	public void writeValues(){
		JSONArray items = m_conn.m_root.getJSONArray("items");
		try {
			BufferedWriter bw =  new BufferedWriter(new FileWriter(m_csvfilename, true));
			for(int i = 0; i < items.length(); i++ ){
				JSONObject itemJS = items.getJSONObject(i);
				JSONObject sellerJS = itemJS.getJSONObject("seller");
				Seller seller = new Seller(sellerJS.getString("id"), sellerJS.getString("serial_number"), sellerJS.getString("nickname"));
				Item item = new Item(itemJS.getString("id"),
					      seller,
					      itemJS.getLong("serial_number"),
					      itemJS.getString("name"),
					      itemJS.getString("description"),
					      new BigDecimal(itemJS.getInt("price")),
					      new Timestamp(itemJS.getLong("create_time")).toInstant());
				bw.write("\""+item.getName()+"\","
					      +"\""+item.getDescription()+"\","
					      +"\""+item.getPrice()+"\","
					      +"\""+InstantToFormat(item.getCreateTime())+"\","
					      +"\""+seller.getSerialNumber()+"\","
					      +"\""+seller.getNickname()+"\","+"\n");
				
			}
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		String url = "https://api.kktown.com.tw/api/items?size=10";
		CSVCreator creator = new CSVCreator(url);
		creator.writeValues();
	}

}
