package dam2.add.p21.servicio;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

import dam2.add.p21.lib.Library;
import dam2.add.p21.prop.ConfigLanguage;

public class MenuIdioma {

	String cadena;
	ResourceBundle idioma;

	public ResourceBundle menuIdioma() throws IOException {

		// Obtener idioma por defecto

		String lang = ConfigLanguage.getParametro("default");
		Locale locale = new Locale(lang);
		ResourceBundle idioma = ResourceBundle.getBundle("idioma", locale);
		
		
		// Devolver bundle

		// Mostrar Idiomas disponibles

		boolean respuesta = false;
		Scanner teclado = new Scanner(System.in);
		String opcion;
		int opcion2;

		do {
			System.out.println();
			cadena = idioma.getString("frase28");
			System.out.println(cadena);

			cadena = idioma.getString("frase29");
			System.out.println(cadena);

			cadena = idioma.getString("frase30");
			System.out.println(cadena);

			cadena = idioma.getString("frase8");
			System.out.println(cadena);

			// Elegir idioma
			opcion = teclado.nextLine();
			System.out.println();

			try {
				opcion2 = Integer.parseInt(opcion);

				if (opcion2 < 1 || opcion2 > 3) {
					System.out.println();
					cadena = idioma.getString("unodos");
					System.out.println(cadena);
					Library.logger.warning("error");
					System.out.println();
				}

			} catch (NumberFormatException excepcion) {

				System.out.println();
				cadena = idioma.getString("numerico");
				System.out.println(cadena);
				System.out.println();
				opcion2 = 0;
				Library.logger.warning("error");
			}

			// Settear idioma y que persista
			if (opcion2 == 1) {
				
				lang = "es";
				locale = new Locale(lang);
				idioma = ResourceBundle.getBundle("idioma", locale);
				ConfigLanguage.setParametro("default", lang);

			}

			else if (opcion2 == 2) {

				lang = "en";
				locale = new Locale(lang);
				idioma = ResourceBundle.getBundle("idioma", locale);
				ConfigLanguage.setParametro("default", lang);
				
			}

		} while (opcion2 < 1 && opcion2 > 2);

		return idioma;
	}

}
