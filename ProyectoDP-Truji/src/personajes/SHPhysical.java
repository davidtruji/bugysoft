package personajes;

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
		s = "(shphysical:" + getInicial() + ":" + getPosicion() + ":" + getTurno() + ":" + getContenedorArmas() + ")";

		return s;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

}
