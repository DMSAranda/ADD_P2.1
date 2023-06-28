package dam2.add.p21.prop;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import dam2.add.p21.lib.Library;


/**
 * Funciones auxiliares
 */
public class ConfigLanguage {
	
	//private static HashMap<String, String> parametros = new HashMap<String, String>();
    private static Properties properties = null;
        
    private ConfigLanguage(String fichero) {
        properties = new Properties();
        try {
        	properties.load(new FileInputStream(fichero));
        } catch (IOException ex) {
        	System.out.println("No existe el fichero de propiedades");
        	ex.printStackTrace();
        	Library.logger.warning("error");
        }
    } 
    
    
    public static ConfigLanguage getInstance(String fichero) {
        return new ConfigLanguage(fichero);
    }

	public static String getParametro(String parametro) {
		String ficheroConf = "./properties/idioma.properties";
		
		if(properties==null) {
			ConfigLanguage.getInstance(ficheroConf);
		}
		
		String param = properties.getProperty(parametro);
		
		
		return param;
	}
	
	public static void setParametro(String parametro, String valor ) {
		String ficheroConf = "./properties/idioma.properties";
		
		//p.load(new FileReader("./properties/idioma.properties\""));
		
		if(properties==null) {
			ConfigLanguage.getInstance(ficheroConf);
		}
		
		properties.setProperty(parametro, valor);    //cambiamos idioma por defecto
		
		try {
			properties.store(new FileOutputStream(ficheroConf),"Último cambio realizado:");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block				//guardamos cambios
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	
}
