package DB;

import entities.*;
import java.sql.*;
import java.util.LinkedList;

import javax.swing.JOptionPane;

public class jdbc {

	public void listarProductos() throws IllegalAccessException, InstantiationException {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {

			e.printStackTrace();

		}

		Connection conection = null;

		LinkedList<Product> productos = new LinkedList<>();

		try {

			conection = DriverManager.getConnection("jdbc:mysql://localhost/javamarket?user=user&password=asd123");

			Statement stmt = conection.createStatement();
			ResultSet rs = stmt.executeQuery("select * from Product");

			while (rs.next()) {

				Product p = new Product();

				p.setIdProduct(rs.getInt("idProduct"));
				p.setDescription(rs.getString("description"));
				p.setName(rs.getString("name"));
				p.setPrice(rs.getDouble("price"));
				p.setStock(rs.getInt("stock"));
				p.setShippingIncluded(rs.getBoolean("shippingIncluded"));

				productos.add(p);

			}

			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}

			conection.close();

			System.out.println("Listado de productos: ");
			System.out.println(productos);
			System.out.println();
			System.out.println();

		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
	}

	public Product buscarProducto(int id) {

		Connection conection = null;

		try {

			conection = DriverManager.getConnection("jdbc:mysql://localhost/javamarket?user=user&password=asd123");

			PreparedStatement pstmt = conection.prepareStatement("select * from product where idProduct = ? ");

			pstmt.setInt(1, id);

			Product pSearched = new Product();

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {

				pSearched.setIdProduct(rs.getInt("idProduct"));
				pSearched.setDescription(rs.getString("description"));
				pSearched.setName(rs.getString("name"));
				pSearched.setPrice(rs.getDouble("price"));
				pSearched.setStock(rs.getInt("stock"));
				pSearched.setShippingIncluded(rs.getBoolean("shippingIncluded"));

			}

			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}

			conection.close();

			if (pSearched.equals(rs)) {
				System.out.println("Producto no existente.");
			} else {
				return pSearched;
			}
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		return null;
	}

	public void cargarProducto(String name, String description, double price, int stock, boolean shippingIncluded) {

		Connection conn = null;

		Product pNew = new Product();

		pNew.setName(name);
		pNew.setDescription(description);
		pNew.setPrice(price);
		pNew.setStock(stock);
		pNew.setShippingIncluded(shippingIncluded);

		try {

			conn = DriverManager.getConnection("jdbc:mysql://localhost/javamarket?user=user&password=asd123");

			PreparedStatement pstmt = conn.prepareStatement(
					"insert into product(name, description, price, stock, shippingIncluded) values(?,?,?,?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS);

			pstmt.setString(1, pNew.getName());
			pstmt.setString(2, pNew.getDescription());
			pstmt.setDouble(3, pNew.getPrice());
			pstmt.setInt(4, pNew.getStock());
			pstmt.setBoolean(5, pNew.isShippingIncluded());

			pstmt.executeUpdate();

			ResultSet keyResultSet = pstmt.getGeneratedKeys();

			if (keyResultSet != null && keyResultSet.next()) {

				int idProduct = keyResultSet.getInt(1);
				System.out.println("ID Producto: " + idProduct);
				pNew.setIdProduct(idProduct);

			}

			if (keyResultSet != null) {
				keyResultSet.close();
			}

			if (pstmt != null) {
				pstmt.close();
			}

			conn.close();

			System.out.print("Nuevo producto agregado: ");
			System.out.println();
			System.out.println(pNew);
			System.out.println();

		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}

	}

	public void eliminarProducto(int id) {

		Connection conn = null;

		try {

			conn = DriverManager.getConnection("jdbc:mysql://localhost/javamarket?user=user&password=asd123");
			PreparedStatement pstmt = conn.prepareStatement("delete from product where idProduct = ?");

			pstmt.setInt(1, id);
			int wh =pstmt.executeUpdate();
			
			if (wh!=0) {
				System.out.println("Eliminado.");
			} else {
				System.out.println("No pudo ser eliminado.");
			}
			pstmt.close();
			conn.close();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al eliminar" + e.getMessage());
		}

	}

	public void modificarProducto(String nombre, String description, int id) {

		Connection conn = null;

		try {

			conn = DriverManager.getConnection("jdbc:mysql://localhost/javamarket?user=user&password=asd123");
			PreparedStatement pstmt = conn
					.prepareStatement("update product " + "set name=?, description=? " + "where idProduct=?");

			pstmt.setString(1, nombre);
			pstmt.setString(2, description);
			pstmt.setInt(3, id);

			int wh = pstmt.executeUpdate();

			if (wh == 1) {
				System.out.println("Cambio realizado.");
				System.out.println();
			} else {
				System.out.println("No se pudieron realizar cambios.");
				System.out.println();
			}

			pstmt.close();
			conn.close();

			// ("update articulos set descripcion='" + tf1.getText() + "'," +"precio=" +
			// tf2.getText() + " where codigo="+tf3.getText());

		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}

	}

	public void modificarProducto(float precio, int id) {

		Connection conn = null;

		try {

			conn = DriverManager.getConnection("jdbc:mysql://localhost/javamarket?user=user&password=asd123");
			PreparedStatement pstmt = conn.prepareStatement(
						"update product " 
						+ "set price=?" 
						+ "where idProduct=?");

			pstmt.setFloat(1, precio);
			pstmt.setInt(2, id);

			int wh = pstmt.executeUpdate();

			if (wh == 1) {
				System.out.println("Cambio realizado.");
				System.out.println();
			} else {
				System.out.println("No se pudieron realizar cambios.");
				System.out.println();
			}

			pstmt.close();
			conn.close();

			// ("update articulos set descripcion='" + tf1.getText() + "'," +"precio=" +
			// tf2.getText() + " where codigo="+tf3.getText());

		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}

	}

	public void modificarProducto(boolean shippingIncluded, int id) {

		Connection conn = null;

		try {

			conn = DriverManager.getConnection("jdbc:mysql://localhost/javamarket?user=user&password=asd123");
			PreparedStatement pstmt = conn
					.prepareStatement("update product " + "set shippingIncluded=?" + "where idProduct=?");

			pstmt.setBoolean(1, shippingIncluded);
			pstmt.setInt(2, id);

			int wh = pstmt.executeUpdate();

			if (wh == 1) {
				System.out.println("Cambio realizado.");
				System.out.println();
			} else {
				System.out.println("No se pudieron realizar cambios.");
				System.out.println();
			}

			pstmt.close();
			conn.close();

			// ("update articulos set descripcion='" + tf1.getText() + "'," +"precio=" +
			// tf2.getText() + " where codigo="+tf3.getText());

		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
	}

//	public void openConnection() {
//		
//		Connection conn = null;
//		
//		try {
//			conn = DriverManager.getConnection("jdbc:mysql://localhost/javamarket?user=user&password=123");
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//	}

}
