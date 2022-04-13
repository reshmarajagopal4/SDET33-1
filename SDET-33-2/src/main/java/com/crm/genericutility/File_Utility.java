package com.crm.genericutility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * 
 * @author Sumanth
 *
 */
public class File_Utility {
public static Properties p;
	/**
	 * 
	 * @param loc
	 * @throws Throwable 
	 */
	public static void InitializePropertydata(String path) throws Throwable {

		FileInputStream fis= new FileInputStream(path);
		p=new Properties();
		p.load(fis);
		

	} 
	public  static String getProperty(String key) {
		String value=p.getProperty(key);
		return value;
	}
	

	
	

	


}
