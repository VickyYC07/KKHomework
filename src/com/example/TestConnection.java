/**
 * 
 */
package com.example;

import static org.junit.Assert.*;

import java.io.File;

import org.json.JSONArray;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author vicky,2016/10/23
 *
 */
public class TestConnection {

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		//Clear file
		File file = new File("test.json");
		if(file.exists()){
			file.delete();
			System.out.println("Clear output file");
		}
	}


	@Test
	public void testConstructor() {
		String url = "https://api.kktown.com.tw/api/items?size=10";
		Connection conn = new Connection(url);
		//Check JSONObject is created
		assertNotNull(conn.m_root);
	}
	@Test
	public void testHasData(){
		String url = "https://api.kktown.com.tw/api/items?size=10";
		Connection conn = new Connection(url);
		//Check JSONObject contains the data (10 items)
		JSONArray items = conn.m_root.getJSONArray("items");
		assertEquals(10, items.length());
	}
	@Test 
	public void testJSONFile(){
		String url = "https://api.kktown.com.tw/api/items?size=10";
		Connection conn = new Connection(url);
		//Check the function of write JSON file
		conn.JSONtoFile("test");
		File testfile = new File("test.json");
		assertTrue(testfile.exists());	
	}

}
