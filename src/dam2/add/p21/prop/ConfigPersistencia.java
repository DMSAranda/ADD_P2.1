package dam2.add.p21.prop;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import dam2.add.p21.lib.Library;

/**
 * Funciones auxiliares
 */
public class ConfigPersistencia {
	
	//private static HashMap<String, String> parametros = new HashMap<String, String>();
    private static Properties properties = null;
        
    private ConfigPersistencia(String fichero) {
        properties = new Properties();
        try {
        	properties.load(new FileInputStream(fichero));
        } catch (IOException ex) {
        	System.out.println("No existe el fichero de propiedades");
        	ex.printStackTrace();
        	Library.logger.info("Arrancamos el Juego");
        }
    }//Configuration
 
    /**
     * Implementando Singleton
     *
     * @return
     */
    public static ConfigPersistencia getInstance(String fichero) {
        return new ConfigPersistencia(fichero);
    }

	public static String getParametro(String parametro) {
		String ficheroConf = "./properties/persistencia.properties";
		
		if(properties==null) {
			ConfigPersistencia.getInstance(ficheroConf);
		}
		
		String param = properties.getProperty(parametro);
		
		/*
		//Si existe en la hash de las propiedades
		if(parametros.containsKey(parametro)) {
			param = parametros.get(parametro);
		}
		//Si NO existe en la hash -> se lee del fichero
		else {
			param = properties.getProperty(parametro);
			
			//Si la propiedad NO existe en el fichero
			if(param==null) {
				System.out.println("El parametro <" + parametro + "> NO existe en el fichero de propiedades");
			}
			//Si existe el parametro en el fichero de propiedades -> se guarda en el fichero hash
			else {
				parametros.put(parametro, param);
				System.out.println("Se ha leido el parametro <" + parametro + ">: " + param);
			}
		}
		*/
		return param;
	}
}
