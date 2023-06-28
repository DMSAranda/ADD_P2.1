package dam2.add.p21.pdf;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import dam2.add.p21.dao.PreguntaDAO;
import dam2.add.p21.lib.Library;
import dam2.add.p21.model.Informe;
import dam2.add.p21.model.Pregunta;
import dam2.add.p21.model.Respuesta;
import dam2.add.p21.model.Usuario;
import dam2.add.p21.xml.LeerXML;

public class EscribirPDF2 {

	/**
	 * Escribir pdf
	 * @param args
	 */
	public static void writePDF(Informe inform) {
		// TODO Auto-generated method stub
		PdfWriter writer = null;
		Document documento = new Document(PageSize.A4, 20, 20, 70, 50);
		
		
	    try {     
	    	
	    	
	    	//Obtenemos la instancia del archivo a utilizar
	    	writer = PdfWriter.getInstance(documento, new FileOutputStream("./ficheros/salida2.pdf"));
	    	
		    //Para insertar cabeceras/pies en todas las p�ginas
	    	writer.setPageEvent(new FormatoPDF());
	        
		    //Abrimos el documento para edici�n
		    documento.open();
		    
		    //PARRAFOS
			Paragraph paragraph = new Paragraph();
			
			Usuario user;
			
			ArrayList<Respuesta> respuestas;
			ArrayList<Pregunta> preguntas;
			
			user = inform.getUsuarioInf();
			respuestas = inform.getRespuestaInf();
			preguntas = inform.getPreguntaInf();
			
			//String contenido = "esto es un p�rrafo";
			//paragraph.setSpacingBefore(100);
			
			paragraph.add("\n\n");
			
			//String font = "Sans";
			//float tamanno = 11;
			//int style = Font.BOLD;
			//BaseColor color = BaseColor.BLACK;
			//float spacBefore = 0;
			//float spacAfter = 5;
		    
			//paragraph.setAlignment(Element.ALIGN_CENTER);
		    //paragraph.setFont(new Font(FontFactory.getFont(font, tamanno, style, color)));
		    //paragraph.add("esto es una p�rrafo");
		    //paragraph.setSpacingBefore(spacBefore);
		    //paragraph.setSpacingAfter(spacAfter);
		    
	    	documento.add(paragraph);
	    	
	    	
	    	//TABLAS
		    
			//Instanciamos una tabla de X columnas
	    	
		    PdfPTable tabla = new PdfPTable(1);
		    
		    //Ancho de cada columna
	        //int[] values = new int[]{40,40,40,40};
	        //tabla.setWidths(values);
		    //tabla.setWidthPercentage(new Float(100));
		    
		    //Phrase phrase = new Phrase("contenido de la celda", 
		    //new Font(FontFactory.getFont("Sans", 9, Font.BOLD, BaseColor.BLACK)));
		    //Phrase phrase = new Phrase("contenido de la celda");
		    
		    Phrase texto = new Phrase("RESULTADO DE LA PARTIDA");
			PdfPCell cabecera = new PdfPCell(texto);
			
			//PdfPCell cabecera2 = new PdfPCell(texto);
			//Propiedades concretas de formato
			
			cabecera.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cabecera.setBorderWidth(1);
			
		    //celda.setBorderColor(BaseColor.WHITE);
			//cabecera.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		    //celda.setVerticalAlignment(PdfPCell.ALIGN_TOP);
		    //celda.setPaddingBottom(3);
		    
		    tabla.addCell(cabecera);
		    tabla.addCell("NOMBRE");
		    tabla.addCell(user.getNombre());
		    tabla.addCell("APELLIDOS");
		    tabla.addCell(user.getApellidos());
		    tabla.addCell("ACIERTOS");
		    tabla.addCell(user.getAciertos());
		    
		    tabla.addCell("LISTADO DE PREGUNTAS");
		    int x=1;
		    for(Pregunta p: preguntas) {
		    	
		    	tabla.addCell(x + " - " + p.getQuestion());
		    	tabla.addCell( "1 - " + p.getAnswer1());
		    	tabla.addCell( "2 - " + p.getAnswer2());
		    	tabla.addCell( "3 - " + p.getAnswer3());
		    	tabla.addCell( "Correcta: " + p.getCorrect2());
		    	x++;
		    }
		    
		    tabla.addCell("LISTADO DE RESPUESTAS");
		    int y=1;
		    for(Respuesta  r: respuestas) {
		    	
		    	tabla.addCell("Pregunta número: " + y + " - Respuesta elegida, número: " + r.getAnswer());
		    	y++;
		    }
		    
		    
		    //documento.add(tabla);
		    
		    /*
		    PdfPTable tabla = new PdfPTable(4);
		    //tabla.addCell(cabecera);
		    //tabla.addCell(cabecera);
		    //tabla.addCell(cabecera);
		    
		    //tabla.addCell(celda);
		    
		    tabla.addCell(new PdfPCell(new Phrase("1")));
		    tabla.addCell(new PdfPCell(new Phrase("2")));
		    tabla.addCell(new PdfPCell(new Phrase("3")));
		    tabla.addCell(new PdfPCell(new Phrase("4")));
		    
		    //tabla.addCell(new PdfPCell(new Phrase("contenido de la celda")));
		    //tabla.addCell(celda);
		    //tabla.addCell(celda);
		    //tabla.completeRow();
		    //tabla.addCell(celda);
*/
		    documento.add(tabla);
	    	
		    documento.close(); //Cerramos el documento
		    writer.close(); //Cerramos writer
		    
		    //Abrir autom�ticamente el fichero creado
		    //http://docs.oracle.com/javase/6/docs/api/java/awt/Desktop.html
		    
		    
		    try {
		        File path = new File("./ficheros/salida2.pdf");
		        Desktop.getDesktop().open(path);
		        Library.logger.warning("ojo");
		    } catch (IOException ex) {
		        ex.printStackTrace();
		        Library.logger.warning("error");
		    }
			
	    } catch (Exception ex) {
	    	ex.getMessage();
	    	Library.logger.warning("error");
	    }
	}
	
}
