
package personajes;

/**
 * 
 * @Grupo: Bugysoft
 * @Autor: David Trujillo Torres y Alberto Diaz Martin
 * @Entrega: EC3
 * @Curso: 2º
 */
public class Arma implements Comparable<Arma> {

	private String nombre;
	private Integer poder;

	/**
	 * Constructor del arma
	 * 
	 * @param nombre
	 *            Entra un nombre.
	 * @param poder
	 *            Entra un poder.
	 */
	public Arma(String nombre, Integer poder) {
		this.nombre = nombre;
		this.poder = poder;
	}

	/**
	 * Get del nombre del arma
	 * 
	 * @return String con el nombre del arma
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Set del nombre del arma
	 * 
	 * @param nombre
	 *            String que sera el nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Get del poder del arma
	 * 
	 * @return Un entero que representa el poder del arma
	 */
	public Integer getPoder() {
		return poder;
	}

	/**
	 * Set del poder del arma
	 * 
	 * @param poder
	 *            Entero que sera el poder del arma
	 */
	public void setPoder(Integer poder) {
		this.poder = poder;
	}

	/**
	 * To String de la clase arma
	 */
	@Override
	public String toString() {
		String s = "";
		if (this != null)
			s = "(" + nombre + "," + poder + ")";
		return s;
	}

	/**
	 * Equals de la clase arma
	 */
	@Override
	public boolean equals(Object a) {
		if (!(a instanceof Arma))
			return false;
		Arma aAux = (Arma) a;
		if (nombre.equalsIgnoreCase(aAux.getNombre()))
			return true;
		else
			return false;
	}

	/**
	 * ConpareTo de la clase arma
	 */
	@Override
	public int compareTo(Arma a) {
		int ret = nombre.compareToIgnoreCase(a.nombre);
		return ret;
	}

}
