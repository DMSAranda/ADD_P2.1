package dam2.add.p21.servicio;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

import dam2.add.p21.dao.UsuarioDAO;
import dam2.add.p21.jdbc.OperacionesBDTotal;
import dam2.add.p21.lib.Library;
import dam2.add.p21.model.Informe;
import dam2.add.p21.model.Pregunta;
import dam2.add.p21.model.Respuesta;
import dam2.add.p21.model.Usuario;
import dam2.add.p21.xml.LeerXML;

public class Juego {

	public Informe juego(String persistencia, ResourceBundle idioma) { // devuelve los aciertos

		Usuario user = new Usuario();

		Library lib = new Library();

		ArrayList<Pregunta> listadoPreguntas = null;

		if (persistencia.equalsIgnoreCase("bd")) {

			// METODO PARA LEER PREGUNTAS DE LA BASE DE DATOS

			try {

				listadoPreguntas = OperacionesBDTotal.consultar2(); 
				// descargo el arraylist de mysql y lo meto al arraylist

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Library.logger.warning(e.getMessage());
			}

		}

		else {
			LeerXML Preguntas = new LeerXML();
			listadoPreguntas = Preguntas.readXML();
		}

		ArrayList<Respuesta> listadoRespuestas = new ArrayList<Respuesta>();

		Scanner teclado = new Scanner(System.in);
		String eleccion;
		int eleccion2;
		int acertadas = 0;
		int fallos = 0;
		String pregunta;
		String respuesta1;
		String respuesta2;
		String respuesta3;
		String salto;
		String uno;
		String dos;
		String tres;

		try {

			for (int i = 0; i < listadoPreguntas.size(); i++) {

				Pregunta question = listadoPreguntas.get(i);

				Respuesta answer = new Respuesta();

				respuesta1 = question.getAnswer1();

				respuesta2 = question.getAnswer2();

				respuesta3 = question.getAnswer3();

				pregunta = question.getQuestion();

				salto = idioma.getString("salto");

				uno = idioma.getString("uno");

				dos = idioma.getString("dos");

				tres = idioma.getString("tres");

				System.out.print(pregunta + salto);
				System.out.println();
				System.out.print(uno + respuesta1 + salto);
				System.out.print(dos + respuesta2 + salto);
				System.out.print(tres + respuesta3 + salto);
				System.out.println();

				do {

					String cadena = idioma.getString("frase11");
					System.out.print(cadena);
					eleccion = teclado.nextLine();

					try {
						eleccion2 = Integer.parseInt(eleccion);

						if (eleccion2 < 1 || eleccion2 > 3) {

							cadena = idioma.getString("frase12");
							System.out.print(cadena);
						}

					} catch (NumberFormatException excepcion) {

						System.out.println();
						cadena = idioma.getString("numerico");
						System.out.print(cadena);
						System.out.println();
						eleccion2 = 0;
						Library.logger.warning("error");
					}

				} while (eleccion2 < 1 || eleccion2 > 3);

				System.out.println();

				answer.setAnswer(eleccion);

				listadoRespuestas.add(answer);

				String acierto = idioma.getString("acierto");

				String aciertos = idioma.getString("aciertos");

				String correcto = idioma.getString("frase13");

				String fallado = idioma.getString("frase14");

				String llevas = idioma.getString("frase15");

				String correcta = question.getCorrect();

				if (eleccion.equals(correcta)) {
					acertadas++;

					if (acertadas == 1) {

						System.out.print(correcto + " " + acertadas + " " + acierto + salto);
					} else {
						System.out.print(correcto + " " + acertadas + " " + aciertos + salto);
					}
					System.out.println();
				} else {
					if (acertadas == 1) {
						System.out.print(fallado + " " + correcta + llevas + " " + acertadas + " " + acierto + salto);
					} else {
						System.out.print(fallado + " " + correcta + llevas + " " + +acertadas + " " + aciertos + salto);
					}

					System.out.println();
				}
			}

			user.setNombre(UsuarioDAO.estableceNombre(idioma));
			user.setApellidos(UsuarioDAO.estableceApellidos(idioma));
			user.setAciertos(Integer.toString(acertadas));

		} catch (IndexOutOfBoundsException e) {
			String cadena = idioma.getString("limites");
			System.out.println(cadena);
		}

		Informe inform = new Informe();

		inform.setUsuarioInf(user);
		inform.setRespuestaInf(listadoRespuestas);
		inform.setPreguntaInf(listadoPreguntas);

		return inform;

	}

}
