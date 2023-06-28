package dam2.add.p21.servicio;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

import dam2.add.p21.dao.PreguntaDAO;
import dam2.add.p21.jdbc.OperacionesBDTotal;
import dam2.add.p21.lib.Ficheros;
import dam2.add.p21.lib.Library;
import dam2.add.p21.model.Informe;
import dam2.add.p21.model.Pregunta;
import dam2.add.p21.pdf.EscribirPDF;
import dam2.add.p21.xls.LeerXLS;
import dam2.add.p21.xml.EscribirXML;
import dam2.add.p21.xml.LeerXML;

public class MenuPrincipal {

	public void menuPrincipal(String persistencia, ResourceBundle idioma) throws IOException { // muestra menu principal

		Scanner teclado = new Scanner(System.in);
		String opcion;
		int opcion2;
		String cadena;

		Juego game = new Juego();
		Records record = new Records();
		MenuFinal fin = new MenuFinal();
		PreguntaDAO pregunta = new PreguntaDAO();

		Library.logger.warning("paso medio");

		do {
			do {
				cadena = idioma.getString("frase1");
				System.out.println(cadena);
				System.out.println();
				cadena = idioma.getString("frase2");
				System.out.println(cadena);
				cadena = idioma.getString("frase3");
				System.out.println(cadena);
				cadena = idioma.getString("frase4");
				System.out.println(cadena);
				cadena = idioma.getString("frase5");
				System.out.println(cadena);
				cadena = idioma.getString("frase6");
				System.out.println(cadena);      // instrucciones del juego Y sistema de puntuación
				cadena = idioma.getString("frase7");
				System.out.println(cadena);
				System.out.println();
				cadena = idioma.getString("frase8");
				System.out.print(cadena);
				opcion = teclado.nextLine();
				System.out.println();

				try {
					opcion2 = Integer.parseInt(opcion);

					if (opcion2 < 0 || opcion2 > 5) {
						cadena = idioma.getString("frase9");
						System.out.print(cadena);
					}

				} catch (NumberFormatException excepcion) {

					System.out.println();
					cadena = idioma.getString("numerico");
					System.out.print(cadena);
					System.out.println();
					opcion2 = -1;
					Library.logger.warning("error");
				}

			} while (opcion2 < 0 || opcion2 > 5);

			switch (opcion2) {

			case 0:

				break;

			case 1:

				Informe inform = game.juego(persistencia, idioma);

				record.guardarRecords(inform.getUsuarioInf(), idioma);

				fin.menuFinal(inform, idioma);

				break;

			case 2:

				pregunta.insertarPregunta(persistencia, idioma);

				System.out.println();

				break;

			case 3:

				if (persistencia.equalsIgnoreCase("fich")) {   // insertar xls en fichero xml

					LeerXLS lector = new LeerXLS();

					LeerXML lector2 = new LeerXML();

					EscribirXML escritor = new EscribirXML();

					ArrayList<Pregunta> caja1 = lector.readXLS();

					ArrayList<Pregunta> caja2 = lector2.readXML();

					ArrayList<Pregunta> union = new ArrayList<Pregunta>();
					union.addAll(caja2);
					union.addAll(caja1);

					Ficheros archivo = new Ficheros();

					archivo.resetPreguntas();

					escritor.writeXML(union);

				}

				else { // insertar xls en bbdd

					LeerXLS lector = new LeerXLS();

					ArrayList<Pregunta> nuevas = lector.readXLS();

					OperacionesBDTotal op = new OperacionesBDTotal();

					for (Pregunta q : nuevas) {

						try {
							op.insertar2(q);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							Library.logger.warning("error");
						}
					}

					break;

				}

			case 4:

				record.imprimirRecords(idioma);

				System.out.println();

				break;

			case 5:

				EscribirPDF pdf = new EscribirPDF();

				pdf.writePDF();

				break;

			}

		} while (opcion2 != 0);

	}

}
