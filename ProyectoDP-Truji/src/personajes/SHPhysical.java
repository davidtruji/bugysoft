package personajes;

import mapa.Mapa;

/**
 * ProyectoDP-Truji
 * 
 * @Fichero: SHPhysical.java
 * @Autor: David Trujillo Torres
 * @Fecha: 27 nov. 2017
 */
public class SHPhysical extends SuperHeroe {

	/**
	 * @param nom
	 * @param ini
	 */
	public SHPhysical(String nom, char ini, int turno) {
		super(nom, ini, turno);
		// E E S S E S S E E S
		Dir[] ruta = { Dir.E, Dir.E, Dir.S, Dir.S, Dir.E, Dir.S, Dir.S, Dir.E, Dir.E, Dir.S };
		setRuta(ruta);
		setPosicion(0);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
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
		// (owneroftheworld:villain:V:1111:38:(Tridente,17))
		s = "(owneroftheworld:shphysical:" + getInicial() + ":1111:" + m.getTurno() + ":" + getContenedorArmas() + ")";
		return s;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see personajes.Personaje#mensajeTeseractomember()
	 */
	@Override
	public String mensajeTeseractomember() {
		Mapa m = Mapa.getInstancia();
		String s = "";
		// (villain:P:1111:38:(Flecha,17)(GuanteInfinito,21)(RayoEnergia,20)(Sable,28)(Tentaculo,24))
		s = "(shphysical:" + getInicial() + ":1111:" + m.getTurno() + ":" + getContenedorArmas() + ")";
		return s;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

}
