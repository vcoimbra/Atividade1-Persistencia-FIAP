package br.com.fiap.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.entity.Pedido;

public class PedidoDAO extends dao {

	public void incluirPedido(Pedido pedido) {
		String query = "INSERT INTO PEDIDOS (idcliente, data, descricao, valor) VALUES (?, ?, ?, ?)";

		try {
			abrirConexao();

			stmt = cn.prepareStatement(query);

			stmt.setInt(1, pedido.getIdCliente());
			stmt.setDate(2, pedido.getData());
			stmt.setString(3, pedido.getDescricao());
			stmt.setDouble(4, pedido.getValor());

			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}finally {
			fecharConexao();
		}

	}

	public Pedido buscarPedido(int id) {
		Pedido pedido = new Pedido();

		String sql = "SELECT * FROM PEDIDOS WHERE IDPEDIDO = ?";

		try {
			abrirConexao();
			stmt = cn.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			if (rs.next()) {

				pedido.setId(rs.getInt("IDPEDIDO"));
				pedido.setIdCliente(rs.getInt("IDCLIENTE"));
				pedido.setData(rs.getDate("DATA"));
				pedido.setDescricao(rs.getString("DESCRICAO"));
				pedido.setValor(rs.getDouble("VALOR"));
			}			
		} catch(SQLException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}finally{
			fecharConexao();
		}
		return pedido;
	}

	public List<Pedido> buscarPedidoCliente(int id) {
		List<Pedido> pedidos = new ArrayList<>();
		Pedido pedido;
		String sql = "SELECT * FROM PEDIDOS WHERE IDCLIENTE = ?";
		try {
			abrirConexao();
			stmt = cn.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			while (rs.next()) {
				pedido = new Pedido();

				pedido.setId(rs.getInt("IDPEDIDO"));
				pedido.setIdCliente(rs.getInt("IDCLIENTE"));
				pedido.setData(rs.getDate("DATA"));
				pedido.setDescricao(rs.getString("DESCRICAO"));
				pedido.setValor(rs.getDouble("VALOR"));

				pedidos.add(pedido);
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}finally{
			fecharConexao();
		}
		return pedidos;
	}
}
