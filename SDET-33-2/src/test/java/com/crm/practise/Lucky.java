package com.crm.practise;

import java.sql.DriverManager;
import com.mysql.cj.jdbc.Driver;

public class Lucky {

	public static void main(String[] args) throws Throwable {
		
		Driver driver = new Driver();
		
		DriverManager.registerDriver(driver);

	}

}
