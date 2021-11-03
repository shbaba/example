package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.AccountEntity;
import model.LoginEntity;

public class AccountDAO {
	// データベースに接続に使用する情報
	private final String JDBC_URL = "jdbc:mysql://localhost:3306/webdb";
	private final String DB_USER = "root";
	private final String DB_PASS = "sb161057";
	
	public AccountEntity findByLogin(LoginEntity loginEntity) {
		AccountEntity accountEntity = null;
		
		// データベースへ接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			// SELECT文を準備
			String sql = 
					"SELECT user_id, pass, mail, name, age FROM webdb.account WHERE user_id = ? AND pass = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, loginEntity.getUserId());
			pStmt.setString(2, loginEntity.getPass());
			System.out.println(pStmt.toString());
			
			// SELECT文を実行し、結果表を取得
			ResultSet rs = pStmt.executeQuery();
			
			// 一致したユーザが存在した場合、そのユーザを表すAccountインスタンスを作成
			if (rs.next()) {
				// 結果表からデータを取得
				String userId = rs.getString("user_id");
				String pass = rs.getString("pass");
				String mail = rs.getString("mail");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				accountEntity = new AccountEntity(userId, pass, mail, name, age);
			}
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			return null;
		}
		// 見つかったユーザまたはnullを返す
		return accountEntity;
	}
}
