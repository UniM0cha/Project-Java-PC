import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {

	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/PCbangDB"; //MySQL 포트 : 3306
	
	static final String USERNAME = "root";
	static final String PASSWORD = "1111";
	
	Statement stmt;
	
	public DB() {
		try {
			Class.forName(JDBC_DRIVER);
			System.out.println("드라이버 지정 성공");
			
			Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			System.out.println("SQL 서버 연결 성공");
			
			stmt = conn.createStatement();	
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 지정 실패");
			e.printStackTrace();
			System.exit(1);
		} catch (SQLException e) {
			System.out.println("SQL 서버 접속 실패");
			e.printStackTrace();
			System.exit(1);
		}
		
		try {

			ResultSet rs = stmt.executeQuery("SELECT * FROM ORDERS");
			while(rs.next()) {
				int id = rs.getInt("pcNum");
				String productName = rs.getString("productName");
				System.out.println(id + "\t" + productName);
			}
		} catch (SQLException e) {
			System.out.println("SQL 입력 실패");
			e.printStackTrace();
		}
	}
	
	public int Update(String sql) {
		try {
			return stmt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("SQL Update 실패");
			e.printStackTrace();
			return 0;
		}
	}
	
	public ResultSet Query(String sql) {
		try {
			return stmt.executeQuery("SELECT * FROM ORDERS");
			
		} catch (SQLException e) {
			System.out.println("SQL Update 실패");
			e.printStackTrace();
			return null;
		}
	}

}
