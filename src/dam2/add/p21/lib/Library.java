package dam2.add.p21.lib;

import java.io.IOException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Logger;

import dam2.add.p21.model.Usuario;

public class Library {

	String cadena;

	public static Logger logger = Logger.getLogger("MyLog"); // Metodo loger estatico para los logs

	public static String formatoNombre(String cadena) {

		String[] separadaPorEspacios = cadena.split(" ");
		StringBuilder sb = new StringBuilder();

		for (int indice = 0; indice < separadaPorEspacios.length; indice++) {
			String palabra = separadaPorEspacios[indice];

			// De la palabra, primero agregar la primera letra ya convertida a mayúscula
			char primeraLetra = palabra.charAt(0);
			sb.append(Character.toUpperCase(primeraLetra));

			// Luego agregarle "lo sobrante" de la palabra
			sb.append(palabra.substring(1));

			// Y si no es el último elemento del arreglo, le añadimos un espacio
			if (indice < separadaPorEspacios.length - 1) {
				sb.append(" ");
			}
		}
		// Finalmente regresamos la cadena
		return sb.toString();
	}

	public static boolean isNumeric(String cadena) {

		boolean resultado;
		// metodo que comprueba si una cadena es numerica
		try {
			Integer.parseInt(cadena);
			resultado = true;
		} catch (NumberFormatException excepcion) {
			resultado = false;
			logger.warning("No es valido");
		}

		return resultado;
	}

	public static boolean esDecimal(String cadena) {
		// metodo que comprueba si una cadena es decimal
		boolean resultado;

		try {
			Double.parseDouble(cadena);
			resultado = true;

		} catch (NumberFormatException nfe) {
			resultado = false;
			logger.warning("No es valido");
		}

		return resultado;
	}

}
