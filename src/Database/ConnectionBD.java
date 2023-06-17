package Database;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionBD {
	public static Connection abrir() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://aws.connect.psdb.cloud/lpoo?sslMode=VERIFY_IDENTITY",
		  "h96bsinxh7buxd2hjhc0",
		  "pscale_pw_Y4t2rQeMeNLlk47Li4NwvTtVtBRfOclyCWwMFTZYX3u");
		return connection;
	}
}
