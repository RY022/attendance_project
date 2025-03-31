package com.example.demo;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import config.DBconfig;

public class Register {

	public void customer_register(int user_id, String name, String password) throws FileNotFoundException {

		DBconfig db_info = new DBconfig();
		String url = db_info.getDBinfo().get("url");
		String user = db_info.getDBinfo().get("user_id");
		String pass = db_info.getDBinfo().get("password");

		String register_sql = "insert into users"
				+ "(user_id, name, password) values(?,?,?)";

		try(Connection conn = DriverManager.getConnection(url,user_id,password)){
			conn.setAutoCommit(false);

			try(PreparedStatement stmt = conn.prepareStatement(register_sql)){
				stmt.setInt(1, user_id);
				stmt.setString(2, name);
				stmt.setString(3, password);
				stmt.executeUpdate();
				conn.commit();
				System.out.println("コミット処理を行いました");
			} catch (SQLException e) {
				conn.rollback();
				System.out.println("ロールバック処理を行いました");
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
}