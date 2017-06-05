package br.com.fiap.dao;

import java.sql.SQLException;

import br.com.fiap.entity.Cliente;

public class ClienteDAO extends dao{
	
	public void incluirCliente(Cliente cliente){
		String sql = "INSERT INTO CLIENTES (NOME, EMAIL) VALUES (?,?)";
		
		try{
			abrirConexao();
			stmt = cn.prepareStatement(sql);
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getEmail());			
		}catch(SQLException e){
			e.printStackTrace();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}finally{
			fecharConexao();
		}
	}
	
	public Cliente buscarCliente(int id){
		Cliente cliente = new Cliente();
		PedidoDAO pedidoDAO = new PedidoDAO();
		String sql = "SELECT * FROM CLIENTES WHERE IDCLIENTE = ?";
		
		try{
			abrirConexao();
			stmt = cn.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			
			cliente.setId(rs.getInt("IDCLIENTE"));
			cliente.setNome(rs.getString("NOME"));
			cliente.setEmail(rs.getString("EMAIL"));
			cliente.setPedidos(pedidoDAO.buscarPedidoCliente(cliente.getId()));						
		}catch(SQLException e){
			e.printStackTrace();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}finally{
			fecharConexao();
		}
		return cliente;
	}

}
