package dam2.add.p21.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

import dam2.add.p21.lib.Library;


public class Conexion {
	
	static String bd = ConfigBD.getParametro("bd.nombre");
	static String login = ConfigBD.getParametro("bd.login");
	static String password = ConfigBD.getParametro("bd.pass");
	static String host = ConfigBD.getParametro("bd.host"); // localhost

	public static Connection con; // atributo para guardar el objeto Connection

	public static Connection getConexion() {
		if (con == null) {
			crearConexion();
		}
		return con;
	}

	// devuelve true si se ha creado correctamente
	private static boolean crearConexion() {
		try {
			// cargo el driver
			// Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://";
			con = DriverManager.getConnection(url + host + "/" + bd, login, password);

			// String url = "jdbc:sqlite:"+"./ficheros/misqlite.db";
			// conexion = DriverManager.getConnection(url);

			con.setAutoCommit(false);

			// Para comprobar el driver JDBC utilizado
			// DatabaseMetaData meta = conexion.getMetaData();
			// System.out.println("The driver name is " + meta.getDriverName());

		} catch (SQLException e) {
			System.out.println(e);
			Library.logger.warning("error");
			return false;
		} catch (Exception e) {
			System.out.println(e);
			Library.logger.warning("error");
			return false;
		}
		return true;
	}

	public static void desconectar() {
		try {
			con.close();
			con = null;

		} catch (SQLException e) {
			System.out.println("Error al cerrar la conexion");
			Library.logger.warning("error");
		}
	}

	 public static void main(String[] args) {
	    	Connection c = ConexionTest.getConexion();
	    	System.out.println(c);
	    	c = ConexionTest.getConexion();
	    	System.out.println(c);
	    }

}


