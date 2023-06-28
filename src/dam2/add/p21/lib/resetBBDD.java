package dam2.add.p21.lib;

import java.sql.SQLException;

import dam2.add.p21.jdbc.OperacionesBDTotal;

public class resetBBDD {
	
	public static void reset() {
		
		try {
			OperacionesBDTotal.borrarTabla();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Library.logger.warning("No es valido");
		}//vacio la tabla
		
		try {
			OperacionesBDTotal.crearBaseDeDatos();;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Library.logger.warning("No es valido");
			
		}//creo la BBDD
		
				
		try {
			OperacionesBDTotal.crearTabla();;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Library.logger.warning("No es valido");
		}//creo la tabla
		
		
	}

}
