package dao;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBHelper {
	public static <FileResder> Connection getConnection() throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = null;
		String dbUrl = "jdbc:oracle:thin:@localhost:1521:orcl";
		String dbUser = "admin";
		// 보안이슈로 로컬에서 설정파일 로드(소스코드에서 비밀번호 노출x)
		FileReader fr = new FileReader("D:\\dev\\auth\\oracle.properties");
		Properties prop = new Properties();
		prop.load(fr);
		String dbPw = prop.getProperty("dbPw");
		conn = DriverManager.getConnection(dbUrl, dbUser, dbPw);
		
		return conn;
	}
	
	// getConnection() 메서드 디버깅용 테스트 코드
		public static void main(String[] args) throws Exception {
			Connection conn = DBHelper.getConnection();
			System.out.println(conn);
	}
}
