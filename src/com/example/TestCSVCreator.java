/**
 * 
 */
package com.example;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author vicky,2016/10/23
 *
 */
public class TestCSVCreator {


	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		File outputFile = new File("最新商品.csv");
		if( outputFile.exists() ){
			outputFile.delete();
			System.out.println("Clear output file");
		}
			
	}

	@Test
	public void test() {
		String url = "https://api.kktown.com.tw/api/items?size=10";
		CSVCreator csvC = new CSVCreator(url);
		//Check csv file name
		assertTrue(csvC.m_csvfilename.equalsIgnoreCase("最新商品.csv"));
		//Check csv file is created
		File outputFile = new File("最新商品.csv");
		assertTrue(outputFile.exists());
		//Check attribute title is written in csv file
		double fileSize = outputFile.length();
		assertTrue(fileSize > 0);
		//Check attribute values is written in csv file
		csvC.writeValues();
		double fileSize2 = outputFile.length();
		assertTrue(fileSize2 > fileSize);
	}
	

}
