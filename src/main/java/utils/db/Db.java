package utils.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Db {
	private static final String url = "jdbc:postgresql://localhost/scopa?user=postgres&password=26061998&ssl=true";
	
	private static Connection conn = null;
	
	
	public static String getUrl() {
		return url;
	}
}
