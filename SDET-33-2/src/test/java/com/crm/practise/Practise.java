package com.crm.practise;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.mysql.cj.jdbc.Driver;

public class Practise {

	public static void main(String[] args) throws Throwable {
		Driver d = new Driver();
		DriverManager.registerDriver(d);
	
		String url="jdbc:mysql://localhost:3306/sdet33";
		Connection c = DriverManager.getConnection(url, "root", "root");
		Statement s =c.createStatement();
		ResultSet r =s.executeQuery("Select * from sdet33");
		while(r.next()) {
			System.out.println(r.getString(1)+r.getString(2)+r.getString(3)+r.getString(4));
			
		}
		

	}

}
