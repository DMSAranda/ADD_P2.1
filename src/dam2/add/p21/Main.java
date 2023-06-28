package dam2.add.p21;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import org.apache.log4j.PropertyConfigurator;
import dam2.add.p21.jdbc.OperacionesBDTotal;
import dam2.add.p21.lib.Ficheros;
import dam2.add.p21.lib.Library;
import dam2.add.p21.lib.Sincronizacion;
import dam2.add.p21.model.Pregunta;
import dam2.add.p21.model.Usuario;
import dam2.add.p21.prop.ConfigLanguage;
import dam2.add.p21.prop.ConfigPersistencia;
import dam2.add.p21.servicio.MenuIdioma;
import dam2.add.p21.servicio.MenuPrincipal;

class Main {

	public static void main(String... args) throws IOException {

		PropertyConfigurator.configure("./properties/log4j.properties");

		Ficheros.crearFicheros(); // justo al grabar

		MenuPrincipal principal = new MenuPrincipal();

		Library.logger.warning("Arranca el juego");

		// persistencia

		String persistencia = ConfigPersistencia.getParametro("carga");
		
		Sincronizacion.primeraCarga(persistencia);
		
		MenuIdioma menuidioma = new MenuIdioma();

		ResourceBundle idioma = menuidioma.menuIdioma();

		// menu principal
		principal.menuPrincipal(persistencia, idioma);
		
		
		//sincronizar bbdd con fichero
		
		Sincronizacion.sincro(persistencia);
		
		System.exit(0);
		
		

	}

}