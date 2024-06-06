package DAO;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MaConnexion {

	public Statement stmt;

	public MaConnexion() throws SQLException, ClassNotFoundException {
		super();
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gradebook", "root", "");
		stmt = conn.createStatement();
	}

	public Statement getStmt() {
		return stmt;
	}

}

//OKSlHVwIC81A
//		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gradebook", "debian-sys-maint", "jeEWLyhuEQIKTYMS");
