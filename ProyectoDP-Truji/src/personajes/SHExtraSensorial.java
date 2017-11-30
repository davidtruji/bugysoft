/**
 * 
 */
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
		Dir[] ruta = { Dir.E, Dir.E };
		setRuta(ruta);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String s = "";
		s = "(shextrasensorial:" + getInicial() + ":ID:" + getTurno() + ":" + getContenedorArmas() + ")";

		return s;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

}
