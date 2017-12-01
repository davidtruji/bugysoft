package personajes;

import mapa.Mapa;

/**
 * ProyectoDP
 * 
 * @Fichero: SHFlight.java
 * @Autor: David Trujillo Torres y Alberto Diaz Martin
 * @Fecha: 27 nov. 2017
 */
public class SHFlight extends SuperHeroe {

	/**
	 * Constructor de la clase SHFlight
	 * 
	 * @param nom
	 *            del heroe
	 * @param ini
	 *            del heroe
	 */
	public SHFlight(String nom, char ini, int turno) {
		super(nom, ini, turno);
		Mapa m = Mapa.getInstancia();
		// E E E N E E S
		Dir[] ruta = { Dir.E, Dir.E, Dir.E, Dir.N, Dir.E, Dir.E, Dir.S };
		setRuta(ruta);
		setPosicion(m.getDimX() * m.getDimY() - m.getDimX());
	}

	/**
	 * Metodo toString de SHFlight
	 */
	@Override
	public String toString() {
		String s = "";
		s = "(shflight:" + getInicial() + ":" + getPosicion() + ":" + getTurnoUltimo() + ":" + getContenedorArmas()
				+ mostrarSaco() + ")";

		return s;
	}

	@Override
	public String mensajeOwneroftheworld() {
		Mapa m = Mapa.getInstancia();
		String s = "";
		s = "(owneroftheworld:shflight:" + getInicial() + ":1111:" + m.getTurno() + ":" + getContenedorArmas() + ")";
		return s;
	}

	@Override
	public String mensajeTeseractomember() {
		Mapa m = Mapa.getInstancia();
		String s = "";
		s = "(shflight:" + getInicial() + ":1111:" + m.getTurno() + ":" + getContenedorArmas() + ")";
		return s;
	}

}
