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
		s = "(shflight:" + getInicial() + ":" + getPosicion() + ":" + getTurnoUltimo()  + ":" + getContenedorArmas() +mostrarSaco()+ ")";

		return s;
	}
	
	@Override
	public String mensajeOwneroftheworld() {
		Mapa m = Mapa.getInstancia();
		String s = "";
		// (owneroftheworld:villain:V:1111:38:(Tridente,17))
		s = "(owneroftheworld:shflight:" + getInicial() + ":1111:" + m.getTurno() + ":" + getContenedorArmas() + ")";
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
		s = "(shflight:" + getInicial() + ":1111:" + m.getTurno() + ":" + getContenedorArmas() + ")";
		return s;
	}
	


}
