import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {

	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	// MySQL 포트 : 3306
	// DB 이름 : PCbangDB
	static final String DB_URL = "jdbc:mysql://114.71.137.174:61083/optl";

	static final String USERNAME = "optl";
	static final String PASSWORD = "oneparktwolee";

	Connection conn;

	public DB() {
		try {
			Class.forName(JDBC_DRIVER);
			System.out.println("드라이버 지정 성공");
			conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			System.out.println("SQL 서버 연결 성공");

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 지정 실패");
			e.printStackTrace();
			System.exit(1);
		} catch (SQLException e) {
			System.out.println("SQL 서버 접속 실패");
			e.printStackTrace();
			System.exit(1);
		}
	}

	public int Update(String sql) {
		try {
			Statement stmt = conn.createStatement();
			return stmt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("SQL Update 실패");
			e.printStackTrace();
			return 0;
		}
	}

	public ResultSet Query(String sql) {
		try {
			Statement stmt = conn.createStatement();
			stmt = conn.createStatement();
			return stmt.executeQuery(sql);
		} catch (SQLException e) {
			System.out.println("SQL Query 실패");
			e.printStackTrace();
			return null;
		}
	}

}
