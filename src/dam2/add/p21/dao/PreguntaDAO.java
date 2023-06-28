package dam2.add.p21.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

import dam2.add.p21.jdbc.OperacionesBDTotal;
import dam2.add.p21.lib.Library;
import dam2.add.p21.model.Pregunta;
import dam2.add.p21.xml.EscribirXML;
import dam2.add.p21.xml.LeerXML;

public class PreguntaDAO {

	public Pregunta agregarPregunta(ResourceBundle idioma) {

		boolean number = false;

		Pregunta trivi = new Pregunta();
		Scanner teclado = new Scanner(System.in);

		String cadena = idioma.getString("frase16");
		System.out.println(cadena);
		trivi.setQuestion(teclado.nextLine());

		cadena = idioma.getString("frase17");
		System.out.println(cadena);
		trivi.setAnswer1(teclado.nextLine());

		cadena = idioma.getString("frase18");
		System.out.println(cadena);
		trivi.setAnswer2(teclado.nextLine());

		cadena = idioma.getString("frase19");
		System.out.println(cadena);
		trivi.setAnswer3(teclado.nextLine());

		do {
			try {
				System.out.println();
				cadena = idioma.getString("frase20");
				System.out.println(cadena);
				trivi.setCorrect(teclado.nextLine());
				if (trivi.getCorrect().equals("1"))
					trivi.setCorrect2(trivi.getAnswer1());
				if (trivi.getCorrect().equals("2"))
					trivi.setCorrect2(trivi.getAnswer2());
				if (trivi.getCorrect().equals("3"))
					trivi.setCorrect2(trivi.getAnswer3());
				Integer.parseInt(trivi.getCorrect());
				number = true;

			} catch (NumberFormatException excepcion) {

				System.out.println();
				cadena = idioma.getString("numerico");
				System.out.print(cadena);
				number = false;
				Library.logger.warning("error");
			}

		} while (number != true);

		return trivi;
	}

	public void insertarPregunta(String persistencia, ResourceBundle idioma) {

		Pregunta trivi = agregarPregunta(idioma);

		ArrayList<Pregunta> aux = new ArrayList<Pregunta>();

		if (persistencia.equalsIgnoreCase("bd")) {

			// METODO PARA AÑADIR PREGUNTA EN LA BASE DE DATOS

			try {
				OperacionesBDTotal.insertar2(trivi);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		else {
			
			// METODO PARA AÑADIR PREGUNTA EN XML

			EscribirXML escribir = new EscribirXML();

			LeerXML leer = new LeerXML();

			aux = leer.readXML();

			aux.add(trivi);

			escribir.writeXML(aux);
		}
	}
}
