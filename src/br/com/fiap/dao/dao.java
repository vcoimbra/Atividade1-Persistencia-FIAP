package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class dao {

	protected Connection cn;
	protected PreparedStatement stmt;
	protected ResultSet rs;
	protected String url = "";

	public void abrirConexao() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		
		this.cn = DriverManager.getConnection(url, "fiap", "fiap123");
	}

	public void fecharConexao(){
		try {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (cn != null) {
				cn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
