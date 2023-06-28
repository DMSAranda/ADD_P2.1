package dam2.add.p21.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

import dam2.add.p21.lib.Library;
import dam2.add.p21.model.Usuario;

public class UsuarioDAO {

	ResourceBundle idioma;

	public static  String estableceNombre(ResourceBundle idioma) {

		String nombre;
		boolean correcto = false;
		Scanner teclado = new Scanner(System.in);

		do {
			String cadena = idioma.getString("nombre");
			String bueno = idioma.getString("bueno");
			System.out.println(cadena);
			nombre = teclado.nextLine();
			
			if (Library.isNumeric(nombre) == true || nombre.isEmpty()) {
				Library.logger.warning("No es valido");
				System.out.println(bueno);
				correcto = false;
			} else {
				// nombre = formatoNombre(nombre); para evitar mayusculas en la primera letra
				correcto = true;
			}
		} while (correcto != true);

		return nombre;
	}

	public static String estableceApellidos(ResourceBundle idioma) {

		String apellido;
		boolean correcto = false;
		Scanner teclado = new Scanner(System.in);
		String bueno = idioma.getString("bueno");

		do {
			String cadena = idioma.getString("apellidos");
			System.out.println(cadena);
			apellido = teclado.nextLine();
			
			if (Library.isNumeric(apellido) == true || apellido.isEmpty()) {
				Library.logger.warning("No es valido");
				System.out.println(bueno);
				correcto = false;
			} else {
				// apellido = formatoNombre(apellido); para evitar mayusculas en la primera
				// letra
				correcto = true;
			}
		} while (correcto != true);

		return apellido;

	}

	public static boolean comprobarUsuarios(Usuario user, ArrayList<Usuario> listado) throws IOException { // para
																											// usuario y
		// clave del login

		boolean existe = false;

		for (Usuario var : listado) {

			if (user.getNombre().equalsIgnoreCase(var.getNombre()) && user.getApellidos().equals(var.getApellidos()))
				existe = true;

		}

		return existe;
	}

}
