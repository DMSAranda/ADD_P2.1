package dam2.add.p21.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dam2.add.p21.jdbc.Conexion;
import dam2.add.p21.lib.Library;
import dam2.add.p21.model.Pregunta;

import java.sql.PreparedStatement;

/**
 * @author davidrc
 */
public class OperacionesBDTotal { // CRUD

	private static Connection conexion;
	private static Statement st;

	// CREAR BASE DE DATOS
	public static void crearBaseDeDatos() throws SQLException {

		conexion = ConexionTest.getConexion();

		st = conexion.createStatement();

		st.executeUpdate("CREATE DATABASE IF NOT EXISTS juego");
	}

	// CREAR TABLA
	public static void crearTabla() throws SQLException {

		conexion = ConexionTest.getConexion();

		st = conexion.createStatement();

		st.executeQuery("use juego");

		st.executeUpdate(
				"CREATE TABLE IF NOT EXISTS preguntas (" + "id INT AUTO_INCREMENT, " + "question VARCHAR(255), "
						+ "answer1 VARCHAR(255), " + "answer2 VARCHAR(255), " + "answer3 VARCHAR(255), "
						+ "correct VARCHAR(255), " + "correct2 VARCHAR(255), " + "PRIMARY KEY(id)" + ")");

	}

	// BORRAR TABLA
	public static void borrarTabla() throws SQLException {

		conexion = Conexion.getConexion();

		st = conexion.createStatement();

		st.executeQuery("use juego");

		st.executeUpdate("DROP TABLE IF EXISTS preguntas");

	}

	// CONSULTAR TABLA (para saber si esta vacia)

	public static boolean consultarVacia() throws SQLException {

		boolean vacia = false;
		int contador = 0;
		int total = 0;

		conexion = Conexion.getConexion();

		try {

			st = conexion.createStatement();
			
			ResultSet rs;

			if (conexion != null) {

				st.executeQuery("use juego");

				rs = st.executeQuery("SELECT * FROM preguntas");
				
				while (rs.next()) {
					contador++;
				}
				
				if (contador==0) vacia = true;
				
				conexion.commit();

				rs.close();
				st.close();
				Conexion.desconectar();

			} else {
				System.out.println("Conexion no realizada");
				Library.logger.warning("conexion fallida");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			if (conexion != null) {
				try {
					conexion.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					Library.logger.warning(e1.getMessage());
				}
			}
		}
		return vacia;
	}

	// INSERTAR PREGUNTA
	public static void insertar2(Pregunta pre) throws SQLException {

		conexion = Conexion.getConexion();

		try {

			st = conexion.createStatement();

			if (conexion != null) {

				st.executeQuery("use juego");

				String question = pre.getQuestion();
				String answer1 = pre.getAnswer1();
				String answer2 = pre.getAnswer2();
				String answer3 = pre.getAnswer3();
				String correct = pre.getCorrect();
				String correct2 = pre.getCorrect2();

				String query = "INSERT INTO preguntas (question, answer1, answer2, answer3, correct, correct2) VALUES (?, ?, ?, ?, ?, ?)";

				PreparedStatement ps = conexion.prepareStatement(query);

				ps.setString(1, question);
				ps.setString(2, answer1);
				ps.setString(3, answer2);
				ps.setString(4, answer3);
				ps.setString(5, correct);
				ps.setString(6, correct2);

				int resultado = ps.executeUpdate();

				if (resultado == 0)
					System.out.println("No se ha podido insertar");
				conexion.commit();

				ps.close();

				st.close();
				Conexion.desconectar();

			} else {
				System.out.println("Conexion no realizada");
				Library.logger.warning("conexion fallida");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			if (conexion != null) {
				try {
					conexion.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					Library.logger.warning(e1.getMessage());
				}
			}
		}

	}

	// EXTRAER ARRAY DE PREGUNTAS USANDO CONEXION CON BD

	public static ArrayList<Pregunta> consultar2() throws SQLException {

		conexion = Conexion.getConexion();

		ArrayList<Pregunta> aux = new ArrayList<Pregunta>();

		try {
			st = conexion.createStatement();

			if (conexion != null) {

				st.executeQuery("use juego");

				String query = "SELECT * FROM preguntas";
				// System.out.println(query);
				PreparedStatement ps = conexion.prepareStatement(query);

				ResultSet rs = ps.executeQuery();

				// Columnas de la tabla: nombre, apellidos y telefono
				while (rs.next()) {

					Pregunta aux2 = new Pregunta();

					aux2.setQuestion(rs.getString(2));
					aux2.setAnswer1(rs.getString(3));
					aux2.setAnswer2(rs.getString(4));
					aux2.setAnswer3(rs.getString(5));
					aux2.setCorrect(rs.getString(6));
					aux2.setCorrect2(rs.getString(7));

					aux.add(aux2);
				}

				rs.close();
				ps.close();

				st.close();
				Conexion.desconectar();
			} else {
				System.out.println("ConexionTest no realizada");
				Library.logger.warning("conexion test fallida");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			if (conexion != null) {
				try {
					conexion.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}

		return aux;

	}

}
