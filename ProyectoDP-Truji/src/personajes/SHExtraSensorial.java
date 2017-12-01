package personajes;

import mapa.Mapa;

/**
 * ProyectoDP
 * 
 * @Fichero: SHExtraSensorial.java
 * @Autor: David Trujillo Torres y Alberto Diaz Martin
 * @Fecha: 27 nov. 2017
 */
public class SHExtraSensorial extends SuperHeroe {

	/**
	 * Constructor de la clase SHExtraSensorial
	 * 
	 * @param nom
	 *            del heroe
	 * @param ini
	 *            del heroe
	 */
	public SHExtraSensorial(String nom, char ini, int turno) {
		super(nom, ini, turno);
		// E E S W W E S W E N E S E S W W W S E W N E E S N E S S W W W E E E E E
		Dir[] ruta = { Dir.E, Dir.E, Dir.S, Dir.W, Dir.W, Dir.E, Dir.S, Dir.W, Dir.E, Dir.N, Dir.E, Dir.S, Dir.E, Dir.S,
				Dir.W, Dir.W, Dir.W, Dir.S, Dir.E, Dir.W, Dir.N, Dir.E, Dir.E, Dir.S, Dir.N, Dir.E, Dir.S, Dir.S, Dir.W,
				Dir.W, Dir.W, Dir.E, Dir.E, Dir.E, Dir.E, Dir.E };
		setRuta(ruta);
		setPosicion(0);

	}

	/**
	 * Metodo toString de un SHExtraSensorial
	 */
	@Override
	public String toString() {
		String s = "";
		s = "(shextrasensorial:" + getInicial() + ":" + getPosicion() + ":" + getTurnoUltimo() + ":"
				+ getContenedorArmas() + mostrarSaco() + ")";

		return s;
	}

	@Override
	public String mensajeOwneroftheworld() {
		Mapa m = Mapa.getInstancia();
		String s = "";
		s = "(owneroftheworld:shextrasensorial:" + getInicial() + ":1111:" + m.getTurno() + ":" + getContenedorArmas()
				+ ")";
		return s;
	}

	@Override
	public String mensajeTeseractomember() {
		Mapa m = Mapa.getInstancia();
		String s = "";
		s = "(shextrasensorial:" + getInicial() + ":1111:" + m.getTurno() + ":" + getContenedorArmas() + ")";
		return s;
	}

}
