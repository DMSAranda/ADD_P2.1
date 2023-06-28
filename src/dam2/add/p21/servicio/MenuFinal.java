package dam2.add.p21.servicio;

import java.io.IOException;
import java.util.ResourceBundle;
import java.util.Scanner;

import dam2.add.p21.lib.Library;
import dam2.add.p21.model.Informe;
import dam2.add.p21.pdf.EscribirPDF2;

public class MenuFinal {
	
	public void menuFinal(Informe inform, ResourceBundle idioma) throws IOException {

		boolean respuesta = false;
		Scanner teclado = new Scanner(System.in);
		String opcion;
		int opcion2;
		String frase25 = idioma.getString("frase25");
		String frase26 = idioma.getString("frase26");
		String frase27 = idioma.getString("frase27");
		String cadena = idioma.getString("unodos");
		String cadena2 = idioma.getString("numerico");
		String salto = idioma.getString("salto");
		String frase8 = idioma.getString("frase8");

		do {
			System.out.println();
			System.out.print(frase25);
			System.out.print(salto);
			System.out.print(frase26);
			System.out.print(salto);
			System.out.print(frase27);
			System.out.print(salto);
			System.out.print(frase8);
			opcion = teclado.nextLine();

			System.out.println();
			
			try {
				opcion2 = Integer.parseInt(opcion);

				if (opcion2 < 1 || opcion2 > 2) {
					System.out.println(cadena);
				}

			} catch (NumberFormatException excepcion) {

				System.out.println();
				System.out.println(cadena2);
				System.out.println();
				opcion2 = 0;
				Library.logger.warning("error");
			}

			if (opcion2 == 1) {

				EscribirPDF2 pdf = new EscribirPDF2();

				pdf.writePDF(inform);

			}

		} while (opcion2 != 2 && opcion2!= 1);

		
	}

}
