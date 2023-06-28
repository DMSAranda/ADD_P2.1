package dam2.add.p21.model;

import java.util.ArrayList;

import dam2.add.p21.dao.PreguntaDAO;

public class Informe {

	Usuario usuarioInf;

	ArrayList<Respuesta> respuestaInf;

	ArrayList<Pregunta> preguntaInf;

	public Usuario getUsuarioInf() {
		return usuarioInf;
	}

	public void setUsuarioInf(Usuario usuarioInf) {
		this.usuarioInf = usuarioInf;
	}

	public ArrayList<Respuesta> getRespuestaInf() {
		return respuestaInf;
	}

	public void setRespuestaInf(ArrayList<Respuesta> respuestaInf) {
		this.respuestaInf = respuestaInf;
	}

	public Informe(Usuario usuarioInf, ArrayList<Respuesta> respuestaInf, ArrayList<Pregunta> preguntaInf) {
		super();
		this.usuarioInf = usuarioInf;
		this.respuestaInf = respuestaInf;
		this.preguntaInf = preguntaInf;
	}

	public Informe() {
		super();
	}

	public ArrayList<Pregunta> getPreguntaInf() {
		return preguntaInf;
	}

	public void setPreguntaInf(ArrayList<Pregunta> preguntaInf) {
		this.preguntaInf = preguntaInf;
	}

}
