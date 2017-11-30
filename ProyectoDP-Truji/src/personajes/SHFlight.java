package personajes;

import mapa.Mapa;

/**
 * ProyectoDP-Truji
 * 
 * @Fichero: SHFlight.java
 * @Autor: David Trujillo Torres
 * @Fecha: 27 nov. 2017
 */
public class SHFlight extends SuperHeroe {

	/**
	 * @param nom
	 * @param ini
	 */
	public SHFlight(String nom, char ini, int turno) {
		super(nom, ini, turno);
		Mapa m = Mapa.getInstancia();
		// E E E N E E S
		Dir[] ruta = { Dir.E, Dir.E, Dir.E, Dir.N, Dir.E, Dir.E, Dir.S };
		setRuta(ruta);
		setPosicion(m.getDimX() * m.getDimY() - m.getDimX());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String s = "";
		s = "(shflight:" + getInicial() + ":" + getPosicion() + ":" + getTurno() + ":" + getContenedorArmas() + ")";

		return s;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

}
