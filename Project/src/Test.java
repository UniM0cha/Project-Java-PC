import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;

public class Test extends JFrame{

	// 데이터베이스 연결 테스트
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/pcsystem"; //MySQL 포트 : 3306
	
	static final String USERNAME = "root";
	static final String PASSWORD = "1111";
	
	Statement stmt;
	
	public Test(String title, int width, int height) {
		setTitle(title);
		setSize(width, height);
		setLocationRelativeTo(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		Statement stmt = Database_Initialize();
		
		String sql = "INSERT INTO ORDERS VALUES(1,'치킨')";
		
		try {
			stmt.executeUpdate(sql);
			
			// 출력
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

	public static void main(String[] args) {
		new Test("정우 똥꼬 핑크 똥꼬", 300, 300);
	}
	
	// 데이터베이스를 활성화 시키고 Statement를 리턴하는 함수
	public Statement Database_Initialize() {
		try {
			Class.forName(JDBC_DRIVER);
			System.out.println("드라이버 지정 성공");
			
			Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			System.out.println("SQL 서버 연결 성공");
			
			Statement stmt = conn.createStatement();
			return stmt;		
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 지정 실패");
			e.printStackTrace();
			System.exit(1);
		} catch (SQLException e) {
			System.out.println("SQL 서버 접속 실패");
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}

}
