package personajes;

/**
 * ProyectoDP-Truji
 * 
 * @Fichero: SHExtraSensorial.java
 * @Autor: David Trujillo Torres
 * @Fecha: 27 nov. 2017
 */
public class SHExtraSensorial extends SuperHeroe {

	/**
	 * @param nom
	 * @param ini
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String s = "";
		s = "(shextrasensorial:" + getInicial() + ":" + getPosicion() + ":" + getTurno() + ":" + getContenedorArmas()
				+ ")";

		return s;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

}
