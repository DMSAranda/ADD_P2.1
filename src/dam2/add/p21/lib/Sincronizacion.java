package dam2.add.p21.lib;

import java.sql.SQLException;
import java.util.ArrayList;

import dam2.add.p21.jdbc.OperacionesBDTotal;
import dam2.add.p21.model.Pregunta;
import dam2.add.p21.xml.EscribirXML;
import dam2.add.p21.xml.LeerXML;

public class Sincronizacion {

	public static void primeraCarga(String persistencia) {

		if (persistencia.equalsIgnoreCase("bd")) {

			try { // si nos deja es que no existe la base de datos y la tabla
				OperacionesBDTotal.crearBaseDeDatos();    
				OperacionesBDTotal.crearTabla();
				if(OperacionesBDTotal.consultarVacia()==true) {  //solo la primera vez carga del fichero demo las preguntas
					sincronizarMySQL();
				}
					
			} catch (SQLException e) { // si no nos deja es que existe la base de datos y la tabla
				// TODO Auto-generated catch block
				Library.logger.info("Test de creacion de BBDD ok, ya existe, continua app");
			}

		}

		else {

		}

	}

	public static void sincronizarMySQL() {
		// este proceso es para pasar siempre lo que hay en xml a la base de datos

		LeerXML Preguntas = new LeerXML();
		ArrayList<Pregunta> listadoPreguntas = Preguntas.readXML(); // leo el xml y lo meto en un arraylist

		for (Pregunta p : listadoPreguntas) { // subo el arraylist a mysql

			try {
				OperacionesBDTotal.insertar2(p);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public static void sincronizarXML() {
		// este proceso es para pasar siempre lo que hay en la base de datos a xml

		EscribirXML Preguntas = new EscribirXML();
		ArrayList<Pregunta> listadoPreguntas = null;

		try {
			listadoPreguntas = OperacionesBDTotal.consultar2(); // leo la BBDD MYSQL y lo meto en un arraylist
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Preguntas.writeXML(listadoPreguntas); // Escribo el fichero de preguntas con el arraylist descargado

	}
	
	public static void resetMySQL() {
		// este proceso es para pasar borrar la base de datos antes de sincronizar

		try {
			OperacionesBDTotal.borrarTabla();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	

	}
	
	public static void resetXML() {
		// este proceso es para pasar borrar el fichero xml antes de sincronizar
		
		Ficheros.resetPreguntas();

	}
	
	public static void sincro(String persistencia) {
		// este proceso es para sincronizar ficheros y bbdd al acabar partida
		
		if (persistencia.equalsIgnoreCase("bd")) { //si la persistencia es base de datos reseteamos el xml y 
												   //lo grabamos para que quede identico a la bbdd
			resetXML();
			sincronizarXML();
		}
		else {						//si la persistencia es fichero xml reseteamos la bbdd y 
			   						//lo grabamos para que quede identico al fichero
			resetMySQL();
			sincronizarMySQL();
		}

	}

}
