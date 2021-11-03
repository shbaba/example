package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Employee;

public class EmployeeDAO {
	// データベース接続に使用する情報
	private final String JDBC_URL = "jdbc:mysql://localhost:3306/webdb";
	private final String DB_USER = "root";
	private final String DB_PASS = "sb161057";
	
	public List<Employee> findAll() {
		List<Employee> empList = new ArrayList<>();
		
		// データベース接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			
			// SELECTぶんを準備
			String sql = "SELECT id, name, age FROM Employee";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			// SELECT文を実行し、結果表を取得
			ResultSet rs = pStmt.executeQuery();
			
			// 結果表に格納されたレコードの内容をEmployeeインスタンスに設定し、ArrayListインスタンスに追加
			while (rs.next()) {
				String id = rs.getString("id");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				Employee employee = new Employee(id, name, age);
				empList.add(employee);
			}
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			return null;
		}
		return empList;
	}
}
