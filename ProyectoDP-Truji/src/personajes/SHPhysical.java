package personajes;

import mapa.Mapa;

/**
 * ProyectoDP
 * 
 * @Fichero: SHPhysical.java
 * @Autor: David Trujillo Torres y Alberto Diaz Martin
 * @Fecha: 27 nov. 2017
 */
public class SHPhysical extends SuperHeroe {

	/**
	 * Constructor de la clase SHPhysical
	 * 
	 * @param nom
	 *            del heroe
	 * @param ini
	 *            del heroe
	 */
	public SHPhysical(String nom, char ini, int turno) {
		super(nom, ini, turno);
		// E E S S E S S E E S
		Dir[] ruta = { Dir.E, Dir.E, Dir.S, Dir.S, Dir.E, Dir.S, Dir.S, Dir.E, Dir.E, Dir.S };
		setRuta(ruta);
		setPosicion(0);

	}

	/**
	 * Metodo toString de SHPhysical
	 */
	@Override
	public String toString() {
		String s = "";
		s = "(shphysical:" + getInicial() + ":" + getPosicion() + ":" + getTurnoUltimo() + ":" + getContenedorArmas()
				+ mostrarSaco() + ")";

		return s;
	}

	@Override
	public String mensajeOwneroftheworld() {
		Mapa m = Mapa.getInstancia();
		String s = "";
		s = "(owneroftheworld:shphysical:" + getInicial() + ":1111:" + m.getTurno() + ":" + getContenedorArmas() + ")";
		return s;
	}

	@Override
	public String mensajeTeseractomember() {
		Mapa m = Mapa.getInstancia();
		String s = "";
		s = "(shphysical:" + getInicial() + ":1111:" + m.getTurno() + ":" + getContenedorArmas() + ")";
		return s;
	}

}
