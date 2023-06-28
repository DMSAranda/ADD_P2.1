/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dam2.add.p21.lib;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;

import java.io.DataOutputStream;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import dam2.add.p21.model.Usuario;

/**
 *
 * @author David
 */

public class Ficheros {

	static File records = new File("./ficheros/records.txt");
	static File preguntas = new File("./ficheros/preguntas.xml");

	DataInputStream leer;
	DataOutputStream escribir;

	public static void crearFicheros() throws IOException {

		if (records.exists()) {

		} else {
			try {
				records.createNewFile();
				Library.logger.warning("fichero creado");

			} catch (IOException ex) {
				Logger.getLogger(Ficheros.class.getName()).log(Level.SEVERE, null, ex);
				Library.logger.warning("error");
			}
		}

	}

	public static void resetPreguntas() {

		if (preguntas.exists()) {

			preguntas.delete();
			
			try {
				preguntas.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Library.logger.warning("error");
			}
			

		}

	}
	
	public ArrayList<Usuario> leerUsuarios(ResourceBundle idioma) throws IOException {
		// metodo que devuelve un array de usuarios

		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		String entrada = "./ficheros/records.txt";
		String cadena = idioma.getString("frase21");

		try {

			BufferedReader br = new BufferedReader(new FileReader(new File(entrada)));

			String linea = br.readLine();

			// se lee linea a linea
			// si es null es que ha llegado al final del fichero

			while (linea != null) {
				// se lee cada linea, separando en 3 string nombre y pass y metiendolo en
				// objeto usuario
				Usuario user = new Usuario();

				String[] partes = linea.split(":");
				String name = partes[0];
				String surname = partes[1];
				String hits = partes[2];

				user.setNombre(name);
				user.setApellidos(surname);
				user.setAciertos(hits);

				lista.add(user);

				linea = br.readLine(); // luego se agrega al array
			}

			br.close();

		} catch (IOException errorDeFichero) {

			System.out.println(cadena + errorDeFichero.getMessage());
			Library.logger.warning("error");
		} catch (Exception e) {
			System.out.println(cadena + e.getMessage());
			Library.logger.warning("error");
		} finally {

		}

		return lista; // se devuelve

	}

	public void escribirUsuarios(ArrayList<Usuario> aux, ResourceBundle idioma) throws IOException { // metodo que pisa
																										// el fichero
		// reescribiendo con una lista

		String salida = "./ficheros/records.txt";

		BufferedWriter bw = null;
		String cadena = idioma.getString("frase21");

		try {

			bw = new BufferedWriter(new FileWriter(new File(salida)));

			/*
			 * Iterator<Usuario> it = lista2.iterator();
			 * 
			 * while (it.hasNext()) { bw.write(it.next().getNombre() + ":" +
			 * it.next().getPassword() + ":" + it.next().isBloqueado() + "\n"); //
			 * escribimos // nombre:clave:bloqueado }
			 */

			for (int i = 0; i < aux.size(); i++) {

				Usuario user = aux.get(i);

				bw.write(user.getNombre() + ":" + user.getApellidos() + ":" + user.getAciertos() + "\n");

			}

		} catch (IOException errorDeFichero) {
			System.out.println(cadena + errorDeFichero.getMessage());
			Library.logger.warning("error");
		} finally {
			try {

				bw.close();
			} catch (IOException e) {
// TODO Auto-generated catch block
				e.printStackTrace();
				Library.logger.warning("error");
			}
		}

	}

}
