package Database;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionBD {
	public static Connection abrir() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://aws.connect.psdb.cloud/lpoo?sslMode=VERIFY_IDENTITY",
		  "l82ng7dorvijthxplqtt",
		  "pscale_pw_Q0lB0Jl7p9VkyciIMp61pws8tCGpvPvNqbDlASHTQTb");
		return connection;
	}
}
