package dam2.add.p21.servicio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

import dam2.add.p21.dao.UsuarioDAO;
import dam2.add.p21.lib.Comparador;
import dam2.add.p21.lib.Ficheros;
import dam2.add.p21.lib.Library;
import dam2.add.p21.model.Usuario;

public class Records {
	
	public void imprimirRecords(ResourceBundle idioma) throws IOException {

		ArrayList<Usuario> aux = new ArrayList<Usuario>();
		
		Ficheros f = new Ficheros();

		aux = f.leerUsuarios(idioma);

		Collections.sort(aux, new Comparador());

		String cadena = idioma.getString("aciertos2");

		try {

			for (int i = 0; i < aux.size(); i++) {

				Usuario user = aux.get(i);

				System.out.print(
						user.getNombre() + " " + user.getApellidos() + " " + cadena + " " + user.getAciertos() + "\n");

			}

		} catch (IndexOutOfBoundsException e) {

			cadena = idioma.getString("limites");
			System.out.println(cadena);
			Library.logger.warning("error");
		}

	}

	public void guardarRecords(Usuario user, ResourceBundle idioma) throws IOException {
		
		Ficheros f = new Ficheros();

		ArrayList<Usuario> aux = f.leerUsuarios(idioma);
		
		boolean existe = UsuarioDAO.comprobarUsuarios(user, aux);

		String acierto = idioma.getString("aciertos2");
		String cadena = idioma.getString("limites");
		String newrecord = idioma.getString("frase22");
		

		try {

			if (existe == true) {

				for (int i = 0; i < aux.size(); i++) {

					Usuario user2 = aux.get(i);

					if (user.getNombre().equalsIgnoreCase(user2.getNombre())
							&& user.getApellidos().equalsIgnoreCase(user2.getApellidos())) {

						if (Integer.parseInt(user.getAciertos()) > Integer.parseInt(user2.getAciertos())) {

							user2.setAciertos(user.getAciertos());
							System.out.println();
							System.out.println(acierto);
						}

						else if (user2.getAciertos().equals("3")) {
							System.out.println();
							System.out.println(acierto);
						}

						else {
							System.out.println();
							System.out.println(acierto);
						}

					}

				}
			} else {
				aux.add(user);
				System.out.println();
				System.out.println(newrecord); 
			}

			System.out.println();

		} catch (IndexOutOfBoundsException e) {
			System.out.println(cadena);
			Library.logger.warning("error");
		}

		f.escribirUsuarios(aux, idioma);

	}


}
